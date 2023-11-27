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


	// 회원가입
	@ResponseBody
	@RequestMapping("/signupUser")
	public boolean signupUser(@RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam("name") String name, @RequestParam("phoneFirst") String phoneFirst,
			@RequestParam("phone") String phone, @RequestParam("birth") String birth,
			@RequestParam("gender") String gender, @RequestParam("nickName") String nickName) throws Exception {
		return mservice.signupUser(id, pw, name, phoneFirst, phone, birth, gender, nickName);
	}

	// 로그인 창으로 이동
	@RequestMapping("/goLogin")
	public String goLogin() {
		return "member/login";
	}
	
	
	// 로그인
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public boolean login(String id, String pw) {
		// 비번 암호화 
		// EncryptionUtils.getSHA512(pw);
		
		boolean loginResult = mservice.chkInfo(id, pw);
		if(loginResult) {
			session.setAttribute("loginID", id);
		}
		return loginResult;
	}
	
	// 마이페이지로 이동
	@RequestMapping("/goMypage")
	public String goMapage() {
		// 마이페이지로 이동
		return "mypage/mypageMain";
	}
	
	// 마이페이지 내 정보
	@RequestMapping("/viewMypage")
	public String viewMapage() {
		// 마이페이지 출력
		return "mypage/mypageMyInfo";
	}
	
	// 마이페이지 정보 수정 전 비밀번호 확인창을 이동
	@RequestMapping("/goChkInfo")
	public String goChkInfo() {
		return "mypage/mypageChkInfo";
	}
	
	// 마이페이지 정보 수정 전 비밀번호 확인
	@ResponseBody
	@RequestMapping("/chkInfo")
	public boolean chkInfo(String pw) {
		return mservice.chkInfo((String) session.getAttribute("loginID"), pw);
	}
	
	// 마이페이지 정보 수정 페이지로 이동
	@RequestMapping("/goUpdateInfo")
	public String goUpdateInfo() {
		return "mypage/mypageUpdateInfo";
	}

	// 마이페이지 정보 수정
	@RequestMapping("/updateInfo")
	public void updateInfo() {
		// 마이페이지 수정
	}

	// 로그아웃
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
