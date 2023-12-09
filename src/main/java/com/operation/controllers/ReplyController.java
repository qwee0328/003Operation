package com.operation.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.operation.dto.ReplyDTO;
import com.operation.services.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService rservice;
	
	@RequestMapping("/writeReply")
	public void writeReply() {
		// 댓글 작성
	}
	
	@ResponseBody
	@RequestMapping("/selectAllReply")
	public void selectAllReply() {
		// 댓글 목록 불러오기
	}
	
	@RequestMapping("/updateReply")
	public void update(ReplyDTO dto) {
		// 댓글 수정
	}
	
	@RequestMapping("/deleteReply")
	public void deleteReply() {
		// 댓글 삭제
	}
	
	
//	// 내가 작성한 댓글 불러오기
//	@ResponseBody
//	@RequestMapping("/selectMyReply")
//	public Map<String, Object> selectMyReply(@RequestParam(value = "cpage", required = false) String cpage){
//		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
//		rservice.selectMyReply(currentPage);
//	}
}
