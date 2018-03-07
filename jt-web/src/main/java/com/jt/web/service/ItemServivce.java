package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
@Service
public class ItemServivce {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private HttpClientService client;
	
	public Item queryItemById(Long itemId){
		String url="http://manage.jt.com/items/"+itemId;
		try{
			String json = client.doGet(url);
			Item item =MAPPER.readValue(json, Item.class);
			return item;	
		}catch(Exception e){
			e.printStackTrace();
			return null;			
		}
	
	}

}
