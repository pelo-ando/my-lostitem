package com.example.app.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.LostItem;
import com.example.app.domain.UpdateInfo;
import com.example.app.service.AreaService;
import com.example.app.service.ItemTypeService;
import com.example.app.service.LostItemService;
import com.example.app.service.StrageService;
import com.example.app.service.UpdateInfoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LostItemController {
	
	// １ページ当たりの備品表示件数
	private final int NUM_PER_PAGE = 5;
	
	private final LostItemService service;
	private final ItemTypeService itemTypeService;
	private final AreaService areaService;
	private final StrageService strageService;
	private final UpdateInfoService updateInfoService;
	private final HttpSession session;
	
	private static final String UPLOAD_DIRECTORY = "C:/Users/moand/gallery";
	
	@GetMapping("/user/list")
	public String showLostItemList(
					@RequestParam(name = "page", defaultValue = "1") Integer page,
					Model model) throws Exception{
			model.addAttribute("lostItems", service.getLostItemsByPage(page, NUM_PER_PAGE));
//			model.addAttribute("lostItems", service.getLostItemList());
			model.addAttribute("currentPage", page);
			// 他のページから戻る際に利用
			session.setAttribute("page", page);
			model.addAttribute("totalPages", service.getTotalPages(NUM_PER_PAGE));
		System.out.println("getLostItemByPage成功 " );
		return "/user/lostitem";
	}
	
	@GetMapping("/user/show/{id}")
	public String show(
					@PathVariable Integer id,
					Model model) throws Exception{
				model.addAttribute("lostItem",service.getLostItemById(id));
				model.addAttribute("updateInfo",updateInfoService.getUpdateInfoById(id));
				System.out.println("findById成功");
				// file check
				String fileName;
				fileName = "animal" + Integer.toString(id) + ".jpg";
//				System.out.println("fileName : " + fileName);
//				System.out.println(isPhoto(fileName));
				if (isPhoto(fileName)) {
					fileName = "";
				}
				model.addAttribute("fname", fileName);
				return "/user/show-lostitem";
	}
	
	@GetMapping("/user/add")
	public String add(Model model) throws Exception{
			model.addAttribute("lostItem", new LostItem());
			model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());
			model.addAttribute("areaList", areaService.getAreaList());
			model.addAttribute("strageList", strageService.getStrageList());
			model.addAttribute("heading", "忘れ物の登録");
			model.addAttribute("updateInfo", new UpdateInfo());
			
			return "user/save-lostitem";
			
	}

	// 追加画面で保存ボタンが押された時
	@PostMapping("/user/add")
	public String add(
					@Valid LostItem lostItem,
					@Valid String fname ,
					
					Errors errors,
					Model model,
					RedirectAttributes redirectAttributes) throws Exception {
				
				if (errors.hasErrors()) {
					model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());
					model.addAttribute("areaList", areaService.getAreaList());
					model.addAttribute("strageList", strageService.getStrageList());
					model.addAttribute("heading", "忘れ物の登録");
					System.out.println(errors);
					return "user/save-lostitem";
				}
				
				service.addLostItem(lostItem);
				System.out.println("addLostItem成功");
				redirectAttributes.addFlashAttribute("message", "忘れ物を新規登録しました。");
				//
				// 画像が取り込まれている時は、画像を保存する
				//
				System.out.println("「imgPath」: " + fname);
				// 追加後に戻るページ
				int totalPages = service.getTotalPages(NUM_PER_PAGE);
				return "redirect:/user/list?page=" + totalPages;
	}
	
	@GetMapping("/user/edit/{id}")
	public String edit(
					@PathVariable Integer id,
					Model model) throws Exception{
				model.addAttribute("lostItem",service.getLostItemById(id));
				model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());
				model.addAttribute("areaList", areaService.getAreaList());
				model.addAttribute("strageList", strageService.getStrageList());
				model.addAttribute("updateInfo" , updateInfoService.getUpdateInfoById(id));
				model.addAttribute("heading", "忘れ物の編集");
				// file check
				String fileName = "animal" + id.toString() + ".jpg";
//				System.out.println("fileName : " + fileName);
//				System.out.println(isPhoto(fileName));
				if (isPhoto(fileName)) {
					fileName = "";
				}
				model.addAttribute("fname", fileName);
				return "/user/save-lostitem";
	}
	

	@PostMapping("/user/edit/{id}")
	public String edit_addUpdate(
					@Valid LostItem lostItem,
					@Valid UpdateInfo updateinfo,
					@Valid String fname ,
					Errors errors,
					Model model,
					RedirectAttributes redirectAttributes) throws Exception {
				
					// 
		
				if (errors.hasErrors()) {
					model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());
					model.addAttribute("areaList", areaService.getAreaList());
					model.addAttribute("strageList", strageService.getStrageList());
					model.addAttribute("heading", "忘れ物の登録");
					System.out.println(errors);
					return "user/save-lostitem";
				}
				
				service.addLostItem(lostItem);
//				updateInfoService.add
				System.out.println("addLostItem成功");
				redirectAttributes.addFlashAttribute("message", "忘れ物を新規登録しました。");
				//
				// 画像が取り込まれている時は、画像を保存する
				//
				System.out.println("「imgPath」: " + fname);
				// 追加後に戻るページ
				int totalPages = service.getTotalPages(NUM_PER_PAGE);
				return "redirect:/user/list?page=" + totalPages;
	}
	
	

		// 画像があるかないか まだ機能していない！
		public boolean isPhoto (String imgName) {
			// アップロードされているファイルのリストの取得
			boolean result = false;
			File uploadsDirectory = new File(UPLOAD_DIRECTORY);
			File[] fileList = uploadsDirectory.listFiles();
			
			List <String> fileNames = Arrays.stream(fileList)
					.map(file -> file.getName()).toList();
			
			// foreach
			for (String item : fileNames) {
				
				System.out.println("item : " + item + ",imgName :" + imgName);
				if (item == imgName) {
					result = true;
					break;
				} else {
					System.out.println("false!");
				}
			}
			return result;
		}
}