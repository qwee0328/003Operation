package com.operation.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.constants.Constants;
import com.operation.dao.ReplyDAO;

import jakarta.servlet.http.HttpSession;

@Service
public class ReplyService {
	@Autowired
	private ReplyDAO dao;
	
	@Autowired
	private HttpSession session;
	
	// 내가 작성한 댓글 불러오기
	public Map<String, Object> selectMyReply(int currentPage){
		String loginId = (String) session.getAttribute("loginID");
		Map<String, Object> result = new HashMap<>();
		
		Map<String, Object> param = new HashMap<>();
		param.put("member_id",loginId);
		param.put("start", currentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		List<Map<String, Object>> list = dao.selectMyReply(param);
		
		int recordTotalCount = dao.selectMyReplyCnt(loginId);
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", currentPage);
		result.put("list", list);
		
		return result;
	}
	
	// 내가 작성한 댓글 중 검색한 댓글 불러오기
	public Map<String, Object> searchMyReply(int currentPage, String select, String keyword){
		String loginId = (String) session.getAttribute("loginID");
		Map<String, Object> result = new HashMap<>();
		
		Map<String, Object> param = new HashMap<>();
		param.put("member_id",loginId);
		param.put("select", select);
		param.put("keyword", "%"+keyword+"%");
		param.put("start", currentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		List<Map<String, Object>> list = dao.searchMyReply(param);
		
		int recordTotalCount = dao.searchMyReplyCnt(param);
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", currentPage);
		result.put("list", list);
		
		return result;
	}
	
	// 마이페이지 > 내 게시글에서 선택한 댓글 일괄 삭제
	public void deleteSelectReply(String[] ids) {
		Map<String, Object> param = new HashMap<>();
		param.put("array", ids);
		param.put("member_id", (String) session.getAttribute("loginID"));
		dao.deleteSelectReply(param);
	}

}
