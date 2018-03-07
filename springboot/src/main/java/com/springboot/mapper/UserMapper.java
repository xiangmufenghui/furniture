package com.springboot.mapper;

import java.util.List;

import com.springboot.pojo.User;

public interface UserMapper {
	
	public List<User> findAll();

}
