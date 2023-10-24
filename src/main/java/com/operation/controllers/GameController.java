package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.operation.services.GameService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gservice;
	
	@RequestMapping("/listGame")
	public void listGame() {
		// 게임 목록 출력
	}
	
	@RequestMapping("/viewGame")
	public void viewGame() {
		// 게임 화면 출력
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
