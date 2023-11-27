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
	

	// 회원가입
	public boolean signupUser(String id, String pw, String name, String phoneFirst,String phone, String birth, String gender,String nickName) throws Exception {
		MemberDTO user = new MemberDTO();
		user.setId(id);
		user.setPw(EncryptionUtils.getSHA512(pw));
		user.setName(name);
		user.setPhone(phoneFirst+phone);
		
		// String -> Timestamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(birth);
		Timestamp timestampDate = new Timestamp(parsedDate.getTime());
		
		user.setBirthday(timestampDate);
		if(gender.equals("1")||gender.equals("3")) {
			user.setGender("남자");
		}else {
			user.setGender("여자");
		}
		
		if(nickName!=null) {
			user.setNickname(nickName);
		}else {
			user.setNickname(user.getId());
		}
		
		return dao.signupUser(user);
	}

	// 로그인
	public boolean login(String id, String pw) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		return dao.login(param);
	}
}
