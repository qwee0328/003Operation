package com.operation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.constants.Constants;
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
	
	// 답변 파일 불러오기
	public List<Map<String, Object>> selectAnswerFileById(int postId) {
		return db.selectList("Qna.selectAnswerFileById", postId);
	}
	
	// 내 qna 게시글 목록 불러오기
	public List<Map<String, Object>> selectMyQnaAll(Map<String, Object> param) {
		return db.selectList("Qna.selectMyQnaAll", param);
	}

	// 내 qna 게시글 총 개수 불러오기
	public int selectMyQnaCnt(String member_id) {
		return db.selectOne("Qna.selectMyQnaCnt",member_id);
	}
	
	// 마이페이지 > 내 게시글에서 선택한 게시글 일괄 삭제
	public void deleteSelectQna(Map<String, Object> param) {
		db.delete("Qna.deleteSelectQna",param);
	}
}
