package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	public List<User> findAll(){
		return userMapper.findAll();
	}

}
