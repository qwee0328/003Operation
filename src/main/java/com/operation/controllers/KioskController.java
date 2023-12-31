package com.operation.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.operation.dto.KioskCategoryDTO;
import com.operation.dto.KioskDTO;
import com.operation.dto.KioskInfoDTO;
import com.operation.dto.KioskRecordDTO;
import com.operation.services.KioskService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kiosk")
public class KioskController {
	@Autowired
	private	KioskService kservice;
	
	@Autowired
	private HttpSession session;
	
	// 키오스크 목록으로 이동
	@RequestMapping("/goKioskList/{is_game}")
	public String goListGame(Model model, @PathVariable int is_game) {
		model.addAttribute("is_game",is_game);
		return "kiosk/kioskList";
	}
	
	// 키오스크 카테고리 목록 가져오기
	@ResponseBody
	@RequestMapping("/getKioskList")
	public List<KioskCategoryDTO> getKioskList(@RequestParam(value="order", required=false) String order, int is_game) {
		System.out.println(is_game);
		order = (order == null || order.isEmpty()) ? "name"  : order;
		return kservice.selectAll(order, is_game);
	}
	
	// 키오스크 상세 페이지로 이동
	@RequestMapping("/viewKiosk/{id}")
	public String viewPractice(Model model, @PathVariable String id, int is_game) {
		Map<String, Object> info = kservice.selectByCategoryAndStage(id, is_game);
		model.addAttribute("info",info);
		return "kiosk/kiosk";
	}
	
	// 키오스크 이용 기록 저장
	@ResponseBody
	@CrossOrigin(origins = "https://kiosk003.github.io", allowCredentials = "true")
	@PostMapping("/insertData")
	public void insert(@RequestBody KioskInfoDTO kiosk) { 
		System.out.println(kiosk);
		String id = kiosk.getMember_id();
		id = id.substring(1,id.length()-1);
		if(!(id==null || id.isEmpty())) {
			kiosk.setMember_id(id);
			kservice.insert(kiosk);
		}
	}
	
	// 키오스크 내 최고 기록 불러오기 (게임)
	@ResponseBody
	@RequestMapping("/getBestRecord")
	public List<Map<String, Object>> selectBestRecord(String kiosk_category_id){
		return kservice.selectBestRecord(kiosk_category_id);
	}
	
	
	// 키오스크 인기 랭킹 가져오기 (메인)
	@ResponseBody
	@RequestMapping("/realTimeRank")
	public List<Map<String, Object>> realTimeRank(){
		return kservice.realTimeRank();
	}
	
	// test
	@ResponseBody
	@RequestMapping("/test")
	public void test(@RequestBody KioskInfoDTO kiosk) {
		System.out.println(kiosk);
		String id = kiosk.getMember_id();
		id = id.substring(1,id.length()-1);
		if(!(id==null || id.isEmpty())) {
			kiosk.setMember_id(id);
			kservice.insert(kiosk);
		}
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
