package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("index")
	public String toIndex(){
		return "index";
	}
	//ת���¼ҳ��
	@RequestMapping("user/login")
	public String toLogin(){
		return "login";
	}

	//ת��ע��ҳ��
	@RequestMapping("user/register")
	public String toRegister(){
		return "register";
	}
}
