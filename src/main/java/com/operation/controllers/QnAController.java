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
		Map<String, Object> post = qservice.selectById(postId);
		System.out.println("Q&A 게시글 정보");
		System.out.println("질문: "+post.get("question"));
		System.out.println("답변: "+post.get("answer"));

		post.put("id", dataId); // 기존처럼 select 쿼리로 값 불러오도록 바꿔 주세용
		if(post.get("permission") == null) {
			model.addAttribute("post", post);
			model.addAttribute("isQna", true);
			return "qna/viewQna";
		}else {
			// 에러 페이지로 이동
			return "accessDenied";
		}
	}

	
	// 질문 게시글 수정 페이지
	@RequestMapping("/goUpdateQuestion/{post_id}")
	public String goUpdateQuestion(Model model, @PathVariable String post_id) {
		int postId = Integer.parseInt(post_id);
		Map<String, Object> post = qservice.selectQuestionById(postId);
		if(post.get("permission") == null) {
			model.addAttribute("post", post);
			model.addAttribute("isQna", true);
			return "board/writePost";
		}else {
			// 에러 페이지로 이동
			return "accessDenied";
		}
		
		
	}

//	// 답변 게시글 수정 페이지
//	@ResponseBody
//	@RequestMapping("/goUpdateAnswer/{post_id}")
//	public QnaAnswerDTO goUpdateAnswer(@PathVariable int post_id) {
//		return qservice.selectAnswerById(post_id);
//	}
	
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