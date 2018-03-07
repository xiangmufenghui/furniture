package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Item;
import com.jt.web.service.SearchService;

@Controller
public class SearchController {
	//查询提交转向页面
	@Autowired
	SearchService searchService;
	
	@RequestMapping("search")
	public String search(String q,Integer page ,Model model){
		try{
			q = new String(q.getBytes("ISO8859-1"),"utf-8");
			Integer rows = 20;
			List<Item> itemList = searchService.queryItemList(q,page,rows);
			model.addAttribute("itemList", itemList);		
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return "search";
	}

}
