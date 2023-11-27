package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.operation.services.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService mservice;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/signup")
	public String signup() {
		// 회원가입
		return "member/signup";
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping("/idDuplicationCheck")
	public boolean idDuplicationCheck(@RequestParam("id") String id) {
		return mservice.idDuplicationCheck(id);
	}
	
	@RequestMapping("/goLogin")
	public String goLogin() {
		return "member/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public boolean login(String id, String pw) {
		// 비번 암호화 
		// EncryptionUtils.getSHA512(pw);
		
		boolean loginResult = mservice.login(id, pw);
		if(loginResult) {
			session.setAttribute("loginID", id);
		}
		return loginResult;
	}
	
	@RequestMapping("/viewMypage")
	public String viewMapage() {
		// 마이페이지 출력
		return "mypage/mypageMain";
	}
	
	@RequestMapping("/updateInfo")
	public void updateInfo() {
		// 마이페이지 수정
	}
	
	@RequestMapping("/logout")
	public String logout() throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
