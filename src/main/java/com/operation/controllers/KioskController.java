package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.operation.dto.KioskRecordDTO;
import com.operation.services.KioskService;

@Controller
@RequestMapping("/kiosk")
public class KioskController {
	@Autowired
	private	KioskService kservice;
	
	@RequestMapping("/listGame")
	public void listGame() {
		// 게임 목록 출력
	}
	
	@RequestMapping("/viewGame")
	public void viewGame() {
		// 게임 화면 출력
	}
	
	@RequestMapping("/listPractice")
	public String listPractice() {
		return "kiosk/kioskList";
	}
	
	@RequestMapping("/viewPractice")
	public String viewPractice() {
		return "kiosk/kiosk";
	}
	
	@CrossOrigin(origins = "https://pushssun.github.io")
	@PostMapping("/insertData")
	public String insert(@RequestBody KioskRecordDTO dto) {
		System.out.println("insert in");
		System.out.println(dto.toString());
		return "hiMH...";
	}
	
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
