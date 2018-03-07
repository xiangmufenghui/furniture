package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("index")
	public String toIndex(){
		return "index";
	}
	//转向登录页面
	@RequestMapping("user/login")
	public String toLogin(){
		return "login";
	}

	//转向注册页面
	@RequestMapping("user/register")
	public String toRegister(){
		return "register";
	}
}
