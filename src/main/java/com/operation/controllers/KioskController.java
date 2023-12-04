package com.operation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.operation.dto.KioskDTO;
import com.operation.dto.KioskRecordDTO;
import com.operation.services.KioskService;

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
	
	// 키오스크 목록 가져오기
	@ResponseBody
	@RequestMapping("/getKioskList")
	public List<KioskDTO> getKioskList(int is_game) {
		List<KioskDTO> list =  kservice.selectAll(is_game);
		return list;
	}
	
	// 키오스크로 페이지로 이동
	@RequestMapping("/viewKiosk/{id}")
	public String viewPractice(Model model, @PathVariable int id) {
		KioskDTO info = kservice.selectById(id);
		model.addAttribute("info",info);
		return "kiosk/kiosk";
	}
	
	@ResponseBody
	@CrossOrigin(origins = "https://pushssun.github.io")
	@PostMapping("/insertData")
	public void insert(@RequestBody KioskRecordDTO dto) {
		String loginID = (String) session.getAttribute("loginID");
		if(loginID!=null) {
			System.out.println("로그인중");
			dto.setMember_id(loginID);
			dto.setMember_nickname((String) session.getAttribute("loginNickName"));
			kservice.insert(dto);
		}
	}
	
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
