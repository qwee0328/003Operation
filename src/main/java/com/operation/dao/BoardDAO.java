package com.operation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession db;

	// 게시글 작성
	public int insert(BoardDTO dto) {
		db.insert("Board.insert", dto);
		return dto.getId();
	}

	// 게시글 목록 불러오기
	public List<Map<String, Object>> selectAll(Map<String, Object> param) {
		return db.selectList("Board.selectAll", param);
	}

	// 게시글 검색 목록 불러오기
	public List<Map<String, Object>> selectByKeyword(Map<String, Object> param) {
		return db.selectList("Board.selectByKeyword", param);
	}

	// 공지 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id) {
		return db.selectList("Board.selectAll", bulletin_category_id);
	}

	// 게시글 개수 불러오기
	public int selectTotalCnt(String bulletin_category_id) {
		return db.selectOne("Board.selectTotalCnt", bulletin_category_id);
	}

	// 검색 게시글 개수 불러오기
	public int selectSearchCnt(Map<String, Object> param) {
		return db.selectOne("Board.selectSearchCnt", param);
	}

	// 게시글 정보 불러오기 ( 수정용 )
	public Map<String, Object> selectPostById(int id) {
		return db.selectOne("Board.selectPostById", id);
	}

	// 게시글 수정
	public int update(BoardDTO dto) {
		return db.update("Board.update", dto);
	}

	// 게시글 조회수 업데이트
	public void updateViewCountById(int id) {
		db.update("Board.updateViewCountById", id);
	}

	// 게시글 추천 수 불러오기
	public int selectRecommendById(int id) {
		Integer result = db.selectOne("Board.selectRecommendById", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 게시글 북마크 수 불러오기
	public int selectBookmarkById(int id) {
		Integer result = db.selectOne("Board.selectBookmarkById", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 게시글 댓글 수 불러오기
	public int selectReplyById(int id) {
		Integer result = db.selectOne("Board.selectReplyById", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 게시글 추천
	public void insertRecommendById(Map<String, Object> param) {
		db.update("Board.insertRecommendById", param);
	}

	// 게시글 북마크
	public void insertBookmarkById(Map<String, Object> param) {
		db.update("Board.insertBookmarkById", param);
	}
	
	// 게시글 파일 불러오기
	public List<Map<String, Object>> selectFileById(Map<String, Object> param){
		return db.selectList("Board.selectFileById",param);
	}

	// 게시글 삭제
	public void deletePost(int id) {
		db.delete("Board.deletePost", id);
	}
}