package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserRepository userReposiptory;
	
	@Autowired
	UserService userService;
	@Autowired 
	User user;
	@GetMapping("getuser")
	public List<User> getUser(){
		//List<User> users= userReposiptory.findAll();
		List<User> users= userService.findAll();
		return users;
	}
	
	@PostMapping("adduser")
	public String addUser(@Validated User user,BindingResult result){
		userReposiptory.save(user);
		
		return result.getFieldError().getDefaultMessage();
	}
	

}
