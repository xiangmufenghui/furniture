package com.jt.web.controller;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("doRegister")
	@ResponseBody
	public SysResult doRegister(User user){
		try{
			userService.saveUser(user);
			return SysResult.oK(user.getUsername());
		}catch(Exception e ){
			return SysResult.build(201, "Ê§°Ü",user.getUsername());
		}
	}
	
	@RequestMapping("doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletRequest request,HttpServletResponse response){
		try{
			String ticket = userService.queryUser(user);
			if(ticket!=null){
				CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
				return SysResult.oK();	
			}else {
				return SysResult.build(201, "Ê§°Ü");
			}
		}catch(Exception e){
			return SysResult.build(201, "Ê§°Ü");
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		
		CookieUtils.deleteCookie(request, response, "JT_TICKET");
		
		return "index";
	}
	

}
