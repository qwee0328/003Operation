package com.operation.services;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.operation.commons.EncryptionUtils;
import com.operation.constants.Constants;
import com.operation.dao.BoardDAO;
import com.operation.dao.MemberDAO;
import com.operation.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder PasswordEncoder;

	// 아이디 중복 체크
	public boolean idDuplicationCheck(String id) {
		return dao.idDuplicationCheck(id);
	}

	// 이메일 중복 체크
	public boolean emailDuplicationCheck(String email) {
		return dao.emailDuplicationCheck(email);
	}

	// 전화번호 중복 체크
	public boolean phoneDuplicationCheck(String phone) {
		return dao.phoneDuplicationCheck(phone);
	}

	// 추천인 존재 체크
	public boolean recommenderDuplicationCheck(String id) {
		return dao.recommenderDuplicationCheck(id);
	}

	// 회원가입
	public boolean signupUser(String id, String pw, String name, String phoneFirst, String phone, String birth,
			String gender, String nickName, String email, String recommender) throws Exception {
		MemberDTO user = new MemberDTO();
		user.setId(id);
		//user.setPw(EncryptionUtils.getSHA512(pw));
		//user.setPw(PasswordEncoder.encode(pw));
		user.setPw(pw);
		System.out.println(user.getPw());
		user.setName(name);
		user.setPhone(phoneFirst + phone);

		// String -> Timestamp

		String result = null;
		int century = Integer.parseInt(gender);
		if (century < 3) {
			result = "19" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-" + birth.substring(4, 6);
		} else {
			result = "20" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-" + birth.substring(4, 6);
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(result);
		Timestamp timestampDate = new Timestamp(parsedDate.getTime());

		user.setBirthday(timestampDate);

		if (gender.equals("1") || gender.equals("3")) {
			user.setGender("남");
		} else {
			user.setGender("여");
		}
		return dao.signupUser(user);
	}

	// 로그인
	public boolean chkInfo(String id, String pw) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		return dao.chkInfo(param);
	}

	// 닉네임 불러오기
	public String selectNickNameById(String id) {
		return dao.selectNickNameById(id);
	}

	// 내 정보 보기
	public MemberDTO selectInfoById(String id) {
		return dao.selectInfoById(id);
	}

	// 내 정보 수정
	public int updateInfo(String id, String key, String value) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("key", key);
		param.put("value", value);
		return dao.updateInfo(param);
	}

	@Transactional
	public int updateInfo(String id, MultipartFile profileImg) throws Exception {
		String path = "c:/003Operation/profileImgs/";
		File uploadPath = new File(path);
		if (!uploadPath.exists())
			uploadPath.mkdir();

		String sysName = UUID.randomUUID() + "_" + profileImg.getOriginalFilename();
		profileImg.transferTo(new File(uploadPath, sysName));

		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("key", "profile_image");
		param.put("value", sysName);
		return dao.updateInfo(param);
	}

	// 프로필 이미지 불러오기
	public String selectProfileImgById(String id) {
		return dao.selectProfileImgById(id);
	}

	// 프로필 이미지 불러오기
	public String selectProfileImgByNickBoard(String memberNickname) {
		return dao.selectProfileImgByNickBoard(memberNickname);
	}

	// 마이페이지 메인 화면 정보 불러오기 (프로필 이미지, 레벨, 포인트)
	public Map<String, Object> selectMainMypageInfoById(String id) {
		return dao.selectMainMypageInfoById(id);
	}

	// 닉네임 중복 확인
	public boolean chkNickname(String nickname) {
		return dao.chkNickname(nickname);
	}
	
	// 비밀번호 가져오기
	public String getPw(String id) {
		return dao.getPw(id);
	}
}