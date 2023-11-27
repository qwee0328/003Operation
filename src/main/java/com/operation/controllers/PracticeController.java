package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.operation.services.PracticeService;

@Controller
@RequestMapping("/practice")
public class PracticeController {
	@Autowired
	private PracticeService pservice;
	
	@RequestMapping("/listPractice")
	public String listPractice() {
		return "kiosk/kioskList";
	}
	
	@RequestMapping("/viewPractice")
	public String viewPractice() {
		return "kiosk/kiosk";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
