package com.operation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession db;
	
	public int insert(BoardDTO dto) {
		db.insert("Board.insert",dto);
		return dto.getId();
	}
}
