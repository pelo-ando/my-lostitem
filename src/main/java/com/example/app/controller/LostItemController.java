package com.example.app.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.LostItem;
import com.example.app.service.AreaService;
import com.example.app.service.ItemTypeService;
import com.example.app.service.LostItemService;
import com.example.app.service.StrageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LostItemController {
	
	private final LostItemService service;
	private final ItemTypeService itemTypeService;
	private final AreaService areaService;
	private final StrageService strageService;
	
	private static final String UPLOAD_DIRECTORY = "C:/Users/moand/gallery";
	
	@GetMapping("/user/list")
	public String all(Model model) {
		model.addAttribute("lostItems", service.getLostItemList());
		System.out.println("select成功 " );
		return "/user/lostitem";
	}
	
	@GetMapping("/user/show/{id}")
	public String show(
					@PathVariable Integer id,
					Model model) {
				model.addAttribute("lostitem",service.getLostItemById(id));
				System.out.println("findById成功");
				// file check
				String fileName;
				fileName = "photo_" + id.toString() + ".png";
//				System.out.println("fileName : " + fileName);
//				System.out.println(isPhoto(fileName));
				if (isPhoto(fileName)) {
					fileName = "";
				}
				model.addAttribute("fname", fileName);
				return "/user/show-lostitem";
	}
	
	@GetMapping("/user/add")
	public String add(Model model) {
			model.addAttribute("lostItem", new LostItem());
			model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());
			model.addAttribute("areaList", areaService.getAreaList());
			model.addAttribute("strageList", strageService.getStrageList());
			model.addAttribute("heading", "忘れ物の登録");
			return "user/save-lostitem";
			
	}

	// 追加画面で保存ボタンが押された時
	@PostMapping("/user/add")
	public String add(
					@Valid LostItem lostItem,
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
				// 画像が取り込まれている時は、画像を保存する
				
				
				return "redirect:/user/list";
				
				
	}
	
	// 画像があるかないか
	public Boolean isPhoto (String imgName) {
		// アップロードされているファイルのリストの取得
		File uploadsDirectory = new File(UPLOAD_DIRECTORY);
		File[] fileList = uploadsDirectory.listFiles();
		
		List <String> fileName = Arrays.stream(fileList)
						.map(file -> file.getName()).toList();
		
		// foreach
		for (String item : fileName) {
			
			System.out.println("item : " + item + ",imgName :" + imgName);
			if (item == imgName) {
				System.out.println("true!");
				return true;
			}
		}
		return false;
	}

}
