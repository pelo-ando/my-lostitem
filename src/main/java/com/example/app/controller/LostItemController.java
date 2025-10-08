package com.example.app.controller;

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
				
				return "redirect:/user/list";
				
				
	}
	
	

}
