package com.operation.services;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.operation.dao.KioskDAO;
import com.operation.dao.MemberDAO;
import com.operation.dto.KioskCategoryDTO;
import com.operation.dto.KioskInfoDTO;
import com.operation.dto.KioskRecordDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class KioskService {
	@Autowired
	private KioskDAO dao;
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private HttpSession session;

	// 키오스크 카테고리 목록 불러오기
	public List<KioskCategoryDTO> selectAll(String order, int is_game){
		Map<String,Object> param = new HashMap<>();
		param.put("order", order);
		param.put("is_game", is_game);
		return dao.selectAll(param);
	}
	
	// 키오스크 상세 정보 불러오기
	// 현재 플레이할 단계의 키오스크 상세 정보 불러오기
	public Map<String, Object> selectByCategoryAndStage(String kiosk_category_id, int is_game) {
		
		String loginID = (String) session.getAttribute("loginID");
		// 로그인하지 않은 상태
		if(loginID == null) {
			Map<String,Object> param2 = new HashMap<>();
			param2.put("kiosk_category_id", kiosk_category_id);
			param2.put("play_stage", 1);
			param2.put("is_game", is_game);
			return dao.selectByCategoryAndStage(param2);
		}
		
		// 로그인한 상태
		else {
			Map<String,Object> param1 = new HashMap<>();
			param1.put("kiosk_category_id", kiosk_category_id);
			param1.put("member_id", loginID);
			param1.put("is_game", is_game);
			int playStage = dao.selectIdByPlayStage(param1);
			
			if(playStage!=3) {
				playStage++;
			}
			
			Map<String,Object> param2 = new HashMap<>();
			param2.put("kiosk_category_id", kiosk_category_id);
			param2.put("play_stage", playStage);
			param2.put("is_game", is_game);
			return dao.selectByCategoryAndStage(param2);
		}
	}
	
	// 키오스크 기록 추가
	@Transactional
	public int insert(KioskInfoDTO kiosk) {
		KioskRecordDTO record = new KioskRecordDTO();
		record.setKiosk_id(dao.selectId(kiosk));
		record.setMember_id(kiosk.getMember_id());
		record.setMember_nickname(mdao.selectNickNameById(record.getMember_id()));
		record.setPlay_date(kiosk.getPlay_date().plus(9,ChronoUnit.HOURS));
		record.setPlay_time(kiosk.getPlay_time());
		record.setIs_success(kiosk.isIs_success());
		return dao.insert(record);
	}
	
	// 키오스크 내 최고 기록 불러오기
	public List<Map<String, Object>> selectBestRecord(String kiosk_category_id){
		Map<String,Object> param = new HashMap<>();
		param.put("kiosk_category_id", kiosk_category_id);
		param.put("member_id", (String) session.getAttribute("loginID"));
		return dao.selectBestRecord(param);
	}
	
	// 키오스크 인기 랭킹 가져오기 (메인)
	public List<Map<String, Object>> realTimeRank(){
		return dao.realTimeRank();
	}
}
