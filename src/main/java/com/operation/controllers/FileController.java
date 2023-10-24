package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.operation.services.FileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fservice;

	@Autowired
	private HttpSession session;
	
	@RequestMapping("/downloadFile")
	public void downloadFile() {
		// 파일 다운로드
	}
	
	@RequestMapping("/uploadFile")
	public void uploadFile() {
		// 파일 업로드
	}
	
	@RequestMapping("/deleteFile")
	public void deleteFile() {
		// 파일 삭제
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
