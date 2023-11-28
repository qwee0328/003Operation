package com.operation.services;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.commons.EncryptionUtils;
import com.operation.dao.MemberDAO;
import com.operation.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	// 아이디 중복 체크
	public boolean idDuplicationCheck(String id) {
		return dao.idDuplicationCheck("%"+id+"%");
	}
	
	// 이메일 중복 체크
	public boolean emailDuplicationCheck(String email) {
		String cleanedEmail = email.replaceAll("\\s", "");
		return dao.emailDuplicationCheck(cleanedEmail);
	}
	
	// 추천인 존재 체크
	public boolean recommenderDuplicationCheck(String id) {
		return dao.recommenderDuplicationCheck(id);
	}

	// 회원가입
	public boolean signupUser(String id, String pw, String name, String phoneFirst,String phone, String birth, String gender,String nickName,String email, String recommender) throws Exception {
		MemberDTO user = new MemberDTO();
		user.setId(id);
		user.setPw(EncryptionUtils.getSHA512(pw));
		user.setName(name);
		user.setPhone(phoneFirst+phone);
		
		// String -> Timestamp

		
		String result = null;
		int century = Integer.parseInt(gender);
		if(century<3) {
			result ="19" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-" + birth.substring(4, 6);
		}else {
			result ="20" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-" + birth.substring(4, 6);
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(result);
		Timestamp timestampDate = new Timestamp(parsedDate.getTime());
		
		user.setBirthday(timestampDate);
		
		if(gender.equals("1")||gender.equals("3")) {
			user.setGender("남");
		}else {
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
	
	// 내 정보 보기
	public MemberDTO selectInfoById(String id) {
		return dao.selectInfoById(id);
	}
	
	// 닉네임 중복 확인
	public boolean chkNickname(String nickname) {
		return dao.chkNickname(nickname);
	}
}
