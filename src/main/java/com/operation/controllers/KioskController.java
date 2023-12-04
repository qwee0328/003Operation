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

@Controller
@RequestMapping("/kiosk")
public class KioskController {
	@Autowired
	private	KioskService kservice;
	
	@RequestMapping("/listGame")
	public void listGame() {
		// 게임 목록 출력
	}
	
	@RequestMapping("/viewGame")
	public void viewGame() {
		// 게임 화면 출력
	}
	
	// 키오스크 목록으로 이동
	@RequestMapping("/goKioskList/{is_game}")
	public String goListGame(Model model, @PathVariable int is_game) {
		model.addAttribute("is_game",is_game);
		return "kiosk/kioskList";
	}
	
	// 연습 목록 가져오기
	@ResponseBody
	@RequestMapping("/getKioskList")
	public List<KioskDTO> getKioskList(int is_game) {
		List<KioskDTO> list =  kservice.selectAll(is_game);
		return list;
	}
	
	// 키오스크로 페이지로 이동
	@RequestMapping("/viewPractice/{id}")
	public String viewPractice(Model model, @PathVariable int id) {
		KioskDTO info = kservice.selectById(id);
		model.addAttribute("info",info);
		return "kiosk/kiosk";
	}
	
	@CrossOrigin(origins = "https://pushssun.github.io")
	@PostMapping("/insertData")
	public String insert(@RequestBody KioskRecordDTO dto) {
		System.out.println("insert in");
		System.out.println(dto.toString());
		return "hiMH...";
	}
	
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
