package com.jt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartService cartService;
	@RequestMapping("show")
	public String myCartList(HttpServletRequest request,Model model) throws Exception{
		//userId的数据的获取可以使用拦截器interceptor
		Long userId = (Long) request.getAttribute("userId");
		List<Cart> cartList=cartService.myCartList(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping("add/{itemId}")
	public String addCart(HttpServletRequest request,@PathVariable Long itemId,Integer num) throws Exception{
		Long userId =(Long) request.getAttribute("userId");
		cartService.addCart(userId,itemId,num);
		return "redirect:/cart/show.html";
	}
	
	@RequestMapping("update/num/{itemId}/{num}")
	public String updateNum(@PathVariable Long itemId,@PathVariable Integer num ,HttpServletRequest request) throws Exception{
		Long userId =(Long) request.getAttribute("userId");
		cartService.upadteNum(userId,itemId,num);
		return "";
	}
	
	@RequestMapping("delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId,HttpServletRequest request)throws Exception{
		Long userId =(Long) request.getAttribute("userId");
		cartService.deleteCart(userId,itemId);
		
		return "redirect:/cart/show.html";
		
	}
	      
	
}
