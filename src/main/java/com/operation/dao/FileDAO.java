package com.operation.dao;

import java.util.List;

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
	
	// 삭제할 파일 origin_name 가져오기 (서버 컴퓨터에서 삭제하기 위함.)
	public List<String> selectByIds(Integer[] array) {
		List<String> result = db.selectList("File.selectByIds",array);
		return result;
	}
	
	// 기존 파일 전체 삭제
	public int deleteAllByPostId(int bulletin_board_id) {
		return db.delete("File.deleteAllByPostId",bulletin_board_id);
	}
	
	// 삭제할 파일 origin_name 가져오기 (서버 컴퓨터에서 삭제하기 위함.)
	public List<String> selectAllByPostId(int bulletin_board_id) {
		return db.selectList("File.AllByPostId",bulletin_board_id);
	}
}
