package com.operation.controllers;

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
	
	
	// 내가 작성한 댓글 불러오기
	@ResponseBody
	@RequestMapping("/selectMyReply")
	public Map<String, Object> selectMyReply(@RequestParam(value = "cpage", required = false) String cpage){
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		return rservice.selectMyReply(currentPage);
	}
	
	// 내가 작성한 댓글 중 검색 내역 불러오기
	@ResponseBody
	@RequestMapping("/searchMyReply")
	public Map<String, Object> selectMyReply(@RequestParam(value = "cpage", required = false) String cpage, String select, String keyword){
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		return rservice.searchMyReply(currentPage, select, keyword);
	}
	

	// 마이페이지 > 내 게시글에서 선택한 댓글 일괄 삭제
	@ResponseBody
	@RequestMapping("/deleteSelectReply")
	public void deleteSelectReply(@RequestParam(value = "deleteIds", required = false) String[] deleteIds) {
		if (deleteIds != null && deleteIds.length>=1) {
			rservice.deleteSelectReply(deleteIds);
		}
	}
}
