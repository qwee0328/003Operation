package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.operation.services.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService bservice;
	
	@Autowired
	private HttpSession session;
	
	// 게시글 작성
	@RequestMapping("/writePost")
	public String writePost() {
		return "board/writePost";
	}
	
	@RequestMapping("/listBoard")
	public String listBoard() {
		return "board/boardList";
	}
	
	@RequestMapping("/viewPost")
	public void viewPost() {
		// 게시글 출력
	}
	
	@RequestMapping("/updatePost")
	public void updatePost() {
		// 게시글 수정
	}
	
	@RequestMapping("/deletePost")
	public void deletePost() {
		// 게시글 삭제
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
