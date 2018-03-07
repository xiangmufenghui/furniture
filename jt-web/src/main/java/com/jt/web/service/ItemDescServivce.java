package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.ItemDesc;
@Service
public class ItemDescServivce {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private HttpClientService client;
	
	public ItemDesc queryItemDescById(Long itemId) {
		String url="http://manage.jt.com/itemDesc/"+itemId;
		try{
			String json = client.doGet(url);
			ItemDesc itemDesc =MAPPER.readValue(json, ItemDesc.class);
			return itemDesc;	
		}catch(Exception e){
			e.printStackTrace();
			return null;	
		}
		
	}

}
