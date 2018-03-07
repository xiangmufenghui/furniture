package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;
import com.jt.web.service.ItemDescServivce;
import com.jt.web.service.ItemServivce;

@Controller
public class ItemController {
	@Autowired
	private ItemServivce itemService;
	@Autowired
	private ItemDescServivce itemDescServivce;
	@RequestMapping("items/{itemId}")
	public String queryItemById(@PathVariable Long itemId,Model model){
		Item item = itemService.queryItemById(itemId);
		ItemDesc itemDesc = itemDescServivce.queryItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}

}
