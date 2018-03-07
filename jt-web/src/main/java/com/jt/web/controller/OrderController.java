package com.jt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("create")
	public String create(HttpServletRequest request,Model model) throws Exception{
		Long userId = (Long) request.getAttribute("userId");
		
		List<Cart> cartList = orderService.queryItemList(userId);
		model.addAttribute("carts",cartList);
		return "order-cart";
	}
	
	@RequestMapping("submit")
	@ResponseBody
	public SysResult submit(HttpServletRequest request ,Order order) throws Exception{
		Long userId =(Long) request.getAttribute("userId");
		order.setUserId(userId);
		String orderId=orderService.submit(order);

		return SysResult.oK(orderId);
	}
	
	@RequestMapping("success")
	public String success(String id,Model model) throws Exception{
		Order order = orderService.success(id);
		model.addAttribute("order",order);
		
		return "success";
	}
	
	

}
