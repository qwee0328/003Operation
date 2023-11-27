package com.operation.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	// 아이디 중복 체크
	public boolean idDuplicationCheck(String id) {
		return dao.idDuplicationCheck("%"+id+"%");
	}
	
	// 로그인
	public boolean login(String id, String pw) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		return dao.login(param);
	}
}
