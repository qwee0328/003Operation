package com.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.KioskCategoryDTO;
import com.operation.dto.KioskRecordDTO;

@Repository
public class KioskDAO {

	@Autowired
	private SqlSession db;
	
	
	// 키오스크 카테고리 목록 불러오기
	public List<KioskCategoryDTO> selectAll(Map<String,Object> param){
		return db.selectList("Kiosk.selectAll", param);
	}
	
	// 현재 플레이 해야할 키오스크 단계 정보 가져오기
	public int selectIdByPlayStage(Map<String, Object> param) {
		return db.selectOne("Kiosk.selectIdByPlayStage",param);
	}
	
	// 키오스크 상세 정보 불러오기
	public Map<String, Object> selectByCategoryAndStage(Map<String, Object> param) {
		return db.selectOne("Kiosk.selectByCategoryAndStage",param);
	}
	
	// 키오스크 기록 추가
	public int insert(KioskRecordDTO dto) {
		return db.insert("Kiosk.insert", dto);
	}
	
	// 키오스크 내 최고 기록 불러오기 (게임)
	public List<Map<String, Object>> selectBestRecord(Map<String, Object> param){
		return db.selectList("Kiosk.selectBestRecord", param);
	}
}
