package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

public class OrderInterceptor implements HandlerInterceptor{

	@Autowired
	private HttpClientService client;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = CookieUtils.getCookieValue(request,"JT_TICKET");
		if (ticket!=null) {
			String url ="http://sso.jt.com/user/query/"+ticket;
			String jsonData = client.doGet(url,"utf-8");
			SysResult result = MAPPER.readValue(jsonData, SysResult.class);
			String userJson = (String) result.getData();
			if (userJson!=null) {
				User user = MAPPER.readValue(userJson, User.class);
				Long userId = user.getId();
				request.setAttribute("userId", userId);
				return true;
			}
			else {
				response.sendRedirect("/user/login.html");
				return false;
			}
		}
		response.sendRedirect("/user/login.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
