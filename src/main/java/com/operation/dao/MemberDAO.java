package com.operation.dao;

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
}
