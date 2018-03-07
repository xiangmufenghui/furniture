package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	HttpClientService client ;
	private static final ObjectMapper MAPPER= new ObjectMapper();
	public void saveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		//httpClient¼¼ÊõµÄdopost
		String url = "http://sso.jt.com/user/register";
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getPhone());
		String jsonData =client.doPost(url, params,"utf-8");		
	}
	
	public String queryUser(User user) throws Exception{
		String url = "http://sso.jt.com/user/login";
		Map<String, String> params = new HashMap<>();
		params.put("u", user.getUsername());
		params.put("p", user.getPassword());
		String jsonData =client.doPost(url, params,"utf-8");	
		SysResult result =MAPPER.readValue(jsonData, SysResult.class);
		String ticket=(String)result.getData();
		return ticket;
	}

}
