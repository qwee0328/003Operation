package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.operation.dto.BoardDTO;
import com.operation.services.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService bservice;
	
	@Autowired
	private HttpSession session;
	
	// 게시글 작성 페이지로 이동
	@RequestMapping("/goWritePost/{catogory}")
	public String goWritePost(@PathVariable String catogory, Model model) {
		if(catogory.equals("question"))
			model.addAttribute("isQuestion",true);
		return "board/writePost";
	}
	
	// 게시글 작성
	@ResponseBody
	@RequestMapping("/writePost")
	public void writePost(BoardDTO dto, @RequestParam(value = "attachFiles", required = false) MultipartFile[] attachFiles) throws Exception {
		dto.setMember_id(((String)session.getAttribute("loginID")));
		dto.setMember_nickname(((String)session.getAttribute("loginNickName")));

		bservice.insert(dto, attachFiles);
	}
	
	// 게시글 목록으로 이동
	@RequestMapping("/listBoard/{catogory}")
	public String listBoard(@PathVariable String catogory, Model model) {
		if(catogory.equals("question"))
			model.addAttribute("isQuestion",true);
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
