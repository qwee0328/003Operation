package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.operation.services.QnAService;

@Controller
@RequestMapping("/qna")
public class QnAController {
	@Autowired
	private QnAService qservice;
	
	@RequestMapping("/writePost")
	public void writePost() {
		// 질문글 작성
	}
	
	@RequestMapping("/listBoard")
	public String listBoard() {
		// QNA 목록 출력
		return "/board/qnaList";
	}
	
	@RequestMapping("/viewPost")
	public void viewPost() {
		// 질문글 출력
	}
	
	@RequestMapping("/updatePost")
	public void updatePost() {
		// 질문글 수정
	}
	
	@RequestMapping("/deletePost")
	public void deletePost() {
		// 질문글 삭제
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
