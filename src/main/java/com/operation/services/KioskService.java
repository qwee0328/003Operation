package com.operation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.KioskDAO;
import com.operation.dto.KioskDTO;

@Service
public class KioskService {
	@Autowired
	private KioskDAO dao;
	
	// 키오스크 목록 불러오기
	public List<KioskDTO> selectAll(int is_game){
		return dao.selectAll(is_game);
	}
	
	// 키오스크 상세 정보 불러오기
	public KioskDTO selectById(int id) {
		return dao.selectById(id);
	}
}
