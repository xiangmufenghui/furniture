package com.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Item;

@Service
public class CartService {

	private static final String ENCODE = "utf-8";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	HttpClientService client;
	public List<Cart> myCartList(Long userId) throws Exception{
		String url = "http://cart.jt.com/cart/query/"+userId;
		String jsonData=client.doGet(url,ENCODE);
		List<Cart> cartList =null;
		if (jsonData!=null) {
			SysResult result =MAPPER.readValue(jsonData, SysResult.class);
			cartList =(List<Cart>)result.getData();
		}
	
		return cartList;
	}
	public void addCart(Long userId, Long itemId, Integer num) throws Exception {
		String url = "http://manage.jt.com/items/"+itemId;
		String itemJson =client.doGet(url,ENCODE);
		Item item =null;
		Map<String, String> params = new HashMap<>();
		if (itemJson!=null) {
			MAPPER.readValue(itemJson, Item.class);
			url="http://cart.jt.com/cart/save";
			params.put("userId", userId+"");
			params.put("itemId", itemId+"");
			params.put("num", num+"");
			params.put("itemTitle",item.getTitle());
			params.put("itemImage", item.getImages()[0]);
			params.put("price", item.getPrice()+"");
		}
		
		client.doPost(url, params,ENCODE);
		
		
	}
	public void upadteNum(Long userId, Long itemId, Integer num) throws Exception {
		String url = "http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		client.doGet(url,ENCODE);
		
	}
	public void deleteCart(Long userId, Long itemId) throws Exception {
		String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		client.doGet(url,ENCODE);
	}

}
