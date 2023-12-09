package com.operation.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.operation.constants.Constants;
import com.operation.dao.ReplyDAO;

import jakarta.servlet.http.HttpSession;

@Service
public class ReplyService {
	@Autowired
	private ReplyDAO dao;
	
	@Autowired
	private HttpSession session;
//	
//	// 내가 작성한 댓글 불러오기
//	public Map<String, Object> selectMyReply(int cpage){
//		String loginId = (String) session.getAttribute("loginID");
//		Map<String, Object> result = new HashMap<>();
//		List<Map<String, Object>> list = dao.selectMyReply(cpage, loginId);
//		int recordTotalCount = dao.selectMyReplyCnt(loginId);
//		result.put("recordTotalCount", recordTotalCount);
//		result.put("recordCountPerPage", Constants.REPLY_COUNT_PER_PAGE);
//		result.put("naviCountPerPage", Constants.REPLY_NAVI_COUNT_PER_PAGE);
//		result.put("postCurPage", cpage);
//		result.put("list", list);
//		
//		return result;
//	}
//	
	
}
