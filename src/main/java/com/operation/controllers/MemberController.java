package com.operation.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.operation.commons.EncryptionUtils;
import com.operation.dto.MemberDTO;
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

	// 이메일 중복 체크
	@ResponseBody
	@RequestMapping("/emailDuplicationCheck")
	public boolean emailDuplicationCheck(@RequestParam("email") String email) {
		return mservice.emailDuplicationCheck(email);
	}
	
	// 전화번호 중복 체크
	@ResponseBody
	@RequestMapping("/phoneDuplicationCheck")
	public boolean phoneDuplicationCheck(@RequestParam("phone") String phone) {
		return mservice.phoneDuplicationCheck(phone);
	}

	// 추천인 존재 체크
	@ResponseBody
	@RequestMapping("/recommenderDuplicationCheck")
	public boolean recommenderDuplicationCheck(@RequestParam("id") String id) {
		return mservice.recommenderDuplicationCheck(id);
	}

	// 회원가입
	@ResponseBody
	@RequestMapping("/signupUser")
	public boolean signupUser(@RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam("name") String name, @RequestParam("phoneFirst") String phoneFirst,
			@RequestParam("phone") String phone, @RequestParam("birth") String birth,
			@RequestParam("gender") String gender, @RequestParam("nickName") String nickName,
			@RequestParam("email") String email, @RequestParam("recommender") String recommender) throws Exception {
		return mservice.signupUser(id, pw, name, phoneFirst, phone, birth, gender, nickName, email, recommender);
	}
	
	// 회원가입한 이름 불러오기
	@ResponseBody
	@RequestMapping("/selectUserName")
	public MemberDTO selectUserName(@RequestParam("id") String id){
		return mservice.selectInfoById(id);
	}

	// 로그인 창으로 이동
	@RequestMapping("/goLogin")
	public String goLogin() {
		return "member/login";
	}

	// 로그인
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean login(String id, String pw) {
		// 비번 암호화
		pw = EncryptionUtils.getSHA512(pw);

		boolean loginResult = mservice.chkInfo(id, pw);
		if (loginResult) {
			session.setAttribute("loginID", id);
		}
		return loginResult;
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout() throws Exception {
		session.invalidate();
		return "redirect:/";
	}

	// 마이페이지 -------------------------------------

	// 마이페이지로 이동
	@RequestMapping("/goMypage")
	public String goMapage() {
		// 마이페이지로 이동
		return "mypage/mypageMain";
	}

	// 마이페이지 내 정보
	@RequestMapping("/viewMypage")
	public String viewMapage(Model model) {
		MemberDTO dto = mservice.selectInfoById((String) session.getAttribute("loginID"));
		model.addAttribute("info", dto);
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
		pw = EncryptionUtils.getSHA512(pw);
		return mservice.chkInfo((String) session.getAttribute("loginID"), pw);
	}

	// 마이페이지 정보 수정 페이지로 이동
	@RequestMapping("/goUpdateInfo")
	public String goUpdateInfo(Model model) {
		MemberDTO dto = mservice.selectInfoById((String) session.getAttribute("loginID"));
		model.addAttribute("info", dto);
		model.addAttribute("isUpdate", true);
		return "mypage/mypageMyInfo";
	}

	// 마이페이지 정보 수정
	@ResponseBody
	@RequestMapping("/updateInfo")
	public int updateInfo(String key, String value) {
		if(key.equals("pw")) {
			value = EncryptionUtils.getSHA512(value);
		}
		return mservice.updateInfo((String) session.getAttribute("loginID"), key, value);
	}
	
	// 닉네임 중복 확인
	@ResponseBody
	@RequestMapping("/chkNickname")
	public boolean chkNickname(String nickname) {
		return mservice.chkNickname(nickname);
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
