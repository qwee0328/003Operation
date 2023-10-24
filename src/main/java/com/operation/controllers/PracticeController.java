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
	public void listPractice() {
		// 연습 목록 출력
	}
	
	@RequestMapping("/viewPractice")
	public void viewPractice() {
		// 연습 화면 출력
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
