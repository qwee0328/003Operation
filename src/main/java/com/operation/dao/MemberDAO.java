package com.operation.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession db;
	
	// 아이디 중복 체크
	public boolean idDuplicationCheck(String id) {
		return db.selectOne("Member.idDuplicationCheck",id);
	}
	
	// 로그인
	public boolean chkInfo(Map<String, String> param) {
		return db.selectOne("Member.chkInfo",param);
	}
}
