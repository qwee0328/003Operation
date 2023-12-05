package com.operation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.KioskDAO;
import com.operation.dto.KioskCategoryDTO;
import com.operation.dto.KioskDTO;
import com.operation.dto.KioskRecordDTO;

@Service
public class KioskService {
	@Autowired
	private KioskDAO dao;
	
//	// 키오스크 목록 불러오기
//	public List<KioskDTO> selectAll(int is_game){
//		return dao.selectAll(is_game);
//	}
	
	// 키오스크 카테고리 목록 이름순불러오기
	public List<KioskCategoryDTO> selectAll(String order){
		return dao.selectAll(order);
	}
	
	// 키오스크 카테고리 최다 플레이순 불러오기
	public List<KioskCategoryDTO> selectAllOrderByPlayCnt(String order){
		
		return dao.selectAll(order);
	}
	
	// 키오스크 상세 정보 불러오기
	public KioskDTO selectById(int id) {
		return dao.selectById(id);
	}
	
	// 키오스크 기록 추가
	public int insert(KioskRecordDTO dto) {
		return dao.insert(dto);
	}
}
