package com.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.BoardDTO;
import com.operation.dto.QnaAnswerDTO;
import com.operation.dto.QnaQuestionDTO;

@Repository
public class QnADAO {

	@Autowired
	private SqlSession db;

	// 질문 게시글 작성
	public int insert(QnaQuestionDTO dto) {
		db.insert("Qna.insert", dto);
		return dto.getId();
	}

	// 답변 게시글 작성
	public int insert(QnaAnswerDTO dto) {
		db.insert("Qna.insertAnswer", dto);
		return dto.getId();
	}

	// qna 게시글 불러오기
	public List<Map<String, Object>> selectAll(Map<String, Object> param) {
		return db.selectList("Qna.selectAll", param);
	}

	// qna 게시글 총 개수 불러오기
	public int selectTotalCnt() {
		return db.selectOne("Qna.selectTotalCnt");
	}

	// qna 특정 게시글 질문 정보 불러오기
	public Map<String, Object> selectQustionById(int id) {
		return db.selectOne("Qna.selectQustionById", id);
	}

	// qna 특정 게시글 답변 정보 불러오기
	public Map<String, Object> selectAnswerById(int id) {
		return db.selectOne("Qna.selectAnswerById", id);
	}

	// 질문 게시글 수정
	public int update(QnaQuestionDTO dto) {
		return db.update("Qna.updateQuestion", dto);
	}
	
	// 답변게시글 수정
	public int update(QnaAnswerDTO dto) {
		return db.update("Qna.updateAnswer", dto);
	}

	// 게시글 삭제
	public void deletePost(int id) {
		db.delete("Qna.deletePost", id);
	}

	// 파일 불러오기
	public List<Map<String, Object>> selectFileById(String postId) {
		return db.selectList("Qna.selectFileById", postId);
	}
}
