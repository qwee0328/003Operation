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
import org.springframework.web.multipart.MultipartFile;

import com.operation.commons.EncryptionUtils;
import com.operation.dto.MemberDTO;
import com.operation.services.BoardService;
import com.operation.services.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService mservice;

	@Autowired
	private BoardService bservice;
	
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
	public MemberDTO selectUserName(@RequestParam("id") String id) {
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
			session.setAttribute("loginNickName", mservice.selectNickNameById(id)); // 닉네임
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
	public String goMapage(Model model) {
		model.addAttribute("info", mservice.selectMainMypageInfoById((String) session.getAttribute("loginID")));
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
		if (key.equals("pw")) {
			value = EncryptionUtils.getSHA512(value);
		}

		int result = mservice.updateInfo((String) session.getAttribute("loginID"), key, value);
		if (key.equals("nickname") && result == 1) {
			session.setAttribute("loginNickName", value); // 닉네임
		}

		return result;
	}

	// 프로필 이미지 변경
	@ResponseBody
	@RequestMapping("/updateProfileImg")
	public int updateProfileImg(MultipartFile profileImg) throws Exception {
//		String path = "C:/003Operation/profileImgs/";
//		File uploadPath = new File(path);
//		if(!uploadPath.exists()) uploadPath.mkdir();
//		
//		String sysName = UUID.randomUUID()+"_"+profileImg.getOriginalFilename();
//		profileImg.transferTo(new File(uploadPath,sysName));
//		return mservice.updateInfo((String) session.getAttribute("loginID"), "profile_image", sysName);
		return mservice.updateInfo((String) session.getAttribute("loginID"), profileImg);
	}

	// 프로필 이미지 불러오기
	@ResponseBody
	@RequestMapping("/selectProfileImgById")
	public String selectProfileImgById() {
		return mservice.selectProfileImgById((String) session.getAttribute("loginID"));
	}

	@ResponseBody
	@RequestMapping("/selectProfileImgByNickBoard")
	public String selectProfileImgByNickBoard(String memberNickname) {
		return mservice.selectProfileImgByNickBoard(memberNickname);
	}

//	// 마이페이지 메인 화면 정보 불러오기 (프로필 이미지, 레벨, 포인트) -> goMypage에 model로 추가
//	@ResponseBody
//	@RequestMapping("/selectMainMypageInfoById")
//	public Map<String, Object> selectMainMypageInfoById() {
//		return mservice.selectMainMypageInfoById((String) session.getAttribute("loginID"));
//	}

	// 닉네임 중복 확인
	@ResponseBody
	@RequestMapping("/chkNickname")
	public boolean chkNickname(String nickname) {
		return mservice.chkNickname(nickname);
	}
	
	// 내 닉네임 불러오기 -> 게시판에서 본인 확인용
	@ResponseBody
	@RequestMapping("/selectUserNick")
	public String selectUserNick() {
		return (String)session.getAttribute("loginNickName");
	}
	
	// 세션 아이디 가져오기 -> 유니티로 보내주기 위함
	@ResponseBody
	@RequestMapping("/userId")
	public String selectUserId() {
		return (String) session.getAttribute("loginID");
	}


	// 마이페이지 내 게시글 내역으로 이동
	@RequestMapping("/goMyPost")
	public String goMyPost() {
		return "mypage/myPost";
	}
	
	// 내 게시글 불러오기
	@ResponseBody
	@RequestMapping("/selectMyPost")
	public Map<String, Object> selectMyPost(String id, @RequestParam(value = "cpage", required = false) String cpage){
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		return bservice.selectMyPost((String) session.getAttribute("loginID"), currentPage);
	}
	
	
	// 마이페이지 내 북마크 내역으로 이동
	@RequestMapping("/goMyBookmark")
	public String goMyBookmark() {
		return "mypage/myBookmark";
	}
		
	// 내 북마크 불러오기
	@ResponseBody
	@RequestMapping("/selectMyBookmark")
	public Map<String, Object> selectMyBookmark(String id, @RequestParam(value = "cpage", required = false) String cpage){
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		return bservice.selectMyBookmark(currentPage);
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
