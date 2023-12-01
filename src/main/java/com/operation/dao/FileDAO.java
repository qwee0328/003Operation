package com.operation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.FileDTO;

@Repository
public class FileDAO {

	@Autowired
	private SqlSession db;
	
	public int insert(FileDTO dto) {
		return db.insert("File.insert",dto);
	}
}
