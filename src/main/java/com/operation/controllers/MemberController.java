package com.operation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/login")
	public void login() {
		// 로그인
	}

	@RequestMapping("/viewMapage")
	public void viewMapage() {
		// 마이페이지 출력
	}

	@RequestMapping("/updateInfo")
	public void updateInfo() {
		// 마이페이지 수정
	}

	@RequestMapping("/logout")
	public void logout() throws Exception {
		// 로그아웃
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
