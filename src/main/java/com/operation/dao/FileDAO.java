package com.operation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.FileDTO;

@Repository
public class FileDAO {

	@Autowired
	private SqlSession db;
	
	
	// 파일 삽입
	public int insert(FileDTO dto) {
		return db.insert("File.insert",dto);
	}
	
	// 파일 삭제
	public int delete(Integer[] array) {
		return db.delete("File.delete",array);
	}
}
