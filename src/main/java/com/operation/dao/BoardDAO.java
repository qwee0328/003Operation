package com.operation.dao;

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
		db.insert("Board.insert",dto);
		return dto.getId();
	}
	
	// 게시글 목록 불러오기
	public List<Map<String, Object>> selectAll(Map<String, Object> param){
		return db.selectList("Board.selectAll",param);
	}
	
	// 공지 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id){
		return db.selectList("Board.selectAll",bulletin_category_id);
	}
	
	// 게시글 개수 불러오기
	public int selectToalCnt(String bulletin_category_id) {
		return db.selectOne("Board.selectToalCnt",bulletin_category_id);
	}
	
	// 게시글 정보 불러오기 ( 수정용 )
	public Map<String, Object> selectPostById(int id){
		return db.selectOne("Board.selectPostById",id);
	}
	
	// 게시글 수정
	public int update(BoardDTO dto) {
		return db.update("Board.update",dto);
	}
}