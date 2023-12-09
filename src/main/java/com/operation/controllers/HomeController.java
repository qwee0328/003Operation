package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRequestURL());
		System.out.println((String)session.getAttribute("loginID")+"home");
		return "home";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public void denied() {}
	
	@RequestMapping(value = "/test")
	public String test() {
		return "test/test";
	}
}
