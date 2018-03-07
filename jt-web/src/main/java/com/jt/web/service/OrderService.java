package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;

@Service
public class OrderService {
@Autowired
private HttpClientService client;
private static final ObjectMapper MAPPER = new ObjectMapper();
	public List<Cart> queryItemList(Long userId) throws Exception {
		String url = "http://cart.jt.com/cart/query/"+userId;
		String jsonData =client.doGet(url,"utf-8");
		SysResult result =MAPPER.readValue(jsonData, SysResult.class);
		List<Cart> cartList = (List<Cart>)result.getData();
		return cartList;
	}
	public String submit(Order order) throws Exception {

		String url = "http://order.jt.com/order/create";
		String orderJson = MAPPER.writeValueAsString(order);
		String jsonData= client.doPostJson(url, orderJson);
		
		SysResult result = MAPPER.readValue(jsonData, SysResult.class);
		String orderId=(String)result.getData();
		return orderId;
	}
	public Order success(String id) throws Exception {
		String url = "http://order.jt.com/order/query/"+id;
		String orderJson =client.doGet(url,"utf-8");
		Order order = MAPPER.readValue(orderJson, Order.class);
		
		return order;
	}
	
	

}
