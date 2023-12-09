package com.operation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {
	
	@Autowired
	private SqlSession db;
	
	// 내 댓글 불러오기
	public List<Map<String, Object>> selectMyReply(Map<String, Object> param) {
		return db.selectList("Reply.selectMyReply", param);
	}
	
	// 내 댓글 개수 불러오기
	public int selectMyReplyCnt(String member_id) {
		return db.selectOne("Reply.selectMyReplyCnt",member_id);
	}
	

	// 내 댓글 중 검색한 내역 불러오기
	public List<Map<String, Object>> searchMyReply(Map<String, Object> param) {
		return db.selectList("Reply.searchMyReply", param);
	}
	
	// 내 댓글 중 검색한 내역 개수 불러오기
	public int searchMyReplyCnt(Map<String, Object> param) {
		return db.selectOne("Reply.searchMyReplyCnt",param);
	}
	
	// 마이페이지 > 내 게시글에서 선택한 댓글 일괄 삭제
	public void deleteSelectReply(Map<String, Object> param) {
		db.delete("Reply.deleteSelectReply",param);
	}
	
}
