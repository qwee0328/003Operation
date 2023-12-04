package com.operation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.KioskCategoryDTO;
import com.operation.dto.KioskDTO;
import com.operation.dto.KioskRecordDTO;

@Repository
public class KioskDAO {

	@Autowired
	private SqlSession db;
	
//	// 키오스크 목록 불러오기
//	public List<KioskDTO> selectAll(int is_game){
//		return db.selectList("Kiosk.selectAll", is_game);
//	}
	
	// 키오스크 카테고리 목록 불러오기
	public List<KioskCategoryDTO> selectAll(){
		return db.selectList("Kiosk.selectAll");
	}
	
	// 키오스크 상세 정보 불러오기
	public KioskDTO selectById(int id) {
		return db.selectOne("Kiosk.selectById",id);
	}
	
	// 키오스크 기록 추가
	public int insert(KioskRecordDTO dto) {
		return db.insert("Kiosk.insert", dto);
	}
}
