package com.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.QnaQuestionDTO;

@Repository
public class QnADAO {
	
	@Autowired
	private SqlSession db;
	
	// 게시글 작성
	public int insert(QnaQuestionDTO dto) {
		db.insert("Qna.insert", dto);
		return dto.getId();
	}
	
	// qna 게시글 불러오기 
	public List<Map<String,Object>> selectAll(Map<String, Object> param){
		return db.selectList("Qna.selectAll",param);
	}
	
	// qna 게시글 총 개수 불러오기
	public int selectTotalCnt(){
		return db.selectOne("Qna.selectTotalCnt");
	}
}
