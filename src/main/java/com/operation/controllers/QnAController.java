package com.operation.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.operation.constants.Constants;
import com.operation.dto.QnaAnswerDTO;
import com.operation.dto.QnaQuestionDTO;
import com.operation.services.QnAService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
public class QnAController {
	@Autowired
	private QnAService qservice;
	
	@Autowired
	private HttpSession session;
	
	// QNA 게시글 작성
	@ResponseBody
	@RequestMapping("/writePost")
	public void writePost(QnaQuestionDTO dto,
			@RequestParam(value = "attachFiles", required = false) MultipartFile[] attachFiles,
			@RequestParam(value = "deleteFileList", required = false) Integer[] deleteFileList) throws Exception {
		dto.setMember_id(((String) session.getAttribute("loginID")));
		dto.setMember_nickname(((String) session.getAttribute("loginNickName")));
		qservice.insert(dto, attachFiles, deleteFileList);
	}
	
	// QNA 답글작성
	@ResponseBody
	@RequestMapping("/writeAnswer")
	public void writeAnswer(QnaAnswerDTO dto,
			@RequestParam(value = "attachFiles", required = false) MultipartFile[] attachFiles,
			@RequestParam(value = "deleteFileList", required = false) Integer[] deleteFileList) throws Exception {
		qservice.insert(dto, attachFiles, deleteFileList);
	}

	// qna 게시글 목록으로 이동
	@RequestMapping("/listBoard")
	public String listBoard() {
		return "/qna/qnaList";
	}
	
	
	// qna 게시글 불러오기 
	@ResponseBody
	@RequestMapping("/selectPostAll")
	public Map<String, Object> listBoard(@RequestParam(value = "cpage", required = false) String cpage) {
		Map<String, Object> result = new HashMap<>();
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		List<Map<String,Object>> list = qservice.selectAll(currentPage);
		int recordTotalCount = qservice.selectTotalCnt();
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", currentPage);
		result.put("userNick", (String) session.getAttribute("loginNickName"));
		result.put("list", list);
		
		return result;
	}
	
	
	@RequestMapping("/viewPost")
	public void viewPost() {
		// 질문글 출력
	}
	
	// 게시글 출력
	@RequestMapping("/viewQnaConf/{dataId}")
	public String viewPostConf(@PathVariable String dataId, Model model) {
		int postId = Integer.parseInt(dataId);
		
		//Map<String, Object> post = qservice.selectPostByIdJustView(postId);
		Map<String, Object> post = new HashMap<>();
		post.put("id", dataId); // 기존처럼 select 쿼리로 값 불러오도록 바꿔 주세용
		model.addAttribute("post", post);
		return "qna/viewQna";
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