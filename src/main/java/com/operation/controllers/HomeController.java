package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.operation.services.MemberService;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService mServ;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)

	public String home(HttpServletRequest request) {
		String loginID = (String)session.getAttribute("loginID");
		if(loginID!=null) {
			String loginNickName = mServ.selectNick(loginID);
			session.setAttribute("loginNickName", loginNickName);
		}
		return "home";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied() {
		return "accessDenied";
	}
	
	@RequestMapping(value = "/test")
	public String test() {
		return "test/test";
	}
}
