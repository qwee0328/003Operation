package com.operation.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession db;
	
	// 아이디 중복 체크
	public boolean idDuplicationCheck(String id) {
		return db.selectOne("Member.idDuplicationCheck",id);
	}
	

	// 회원가입
	public boolean signupUser(MemberDTO dto) {
		int result = db.insert("Member.signupUser", dto);
		System.out.println("result : "+result);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}

	// 로그인
	public boolean chkInfo(Map<String, String> param) {
		return db.selectOne("Member.chkInfo",param);
	}
	
	// 내 정보 보기
	public MemberDTO selectInfoById(String id) {
		return db.selectOne("Member.selectInfoById",id);
	}
}
