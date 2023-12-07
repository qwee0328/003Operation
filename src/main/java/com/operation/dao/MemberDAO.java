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
		return db.selectOne("Member.idDuplicationCheck", id);
	}

	// 이메일 중복 체크
	public boolean emailDuplicationCheck(String email) {
		return db.selectOne("Member.emailDuplicationCheck", email);
	}

	// 전화번호 중복 체크
	public boolean phoneDuplicationCheck(String phone) {
		return db.selectOne("Member.phoneDuplicationCheck", phone);
	}

	// 추천인 존재 체크
	public boolean recommenderDuplicationCheck(String id) {
		return db.selectOne("Member.recommenderDuplicationCheck", id);
	}

	// 회원가입
	public boolean signupUser(MemberDTO dto) {
		int result = db.insert("Member.signupUser", dto);
		System.out.println("result : " + result);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 로그인
	public boolean chkInfo(Map<String, String> param) {
		return db.selectOne("Member.chkInfo", param);
	}

	// 닉네임 불러오기
	public String selectNickNameById(String id) {
		return db.selectOne("Member.selectNickNameById", id);
	}

	// 내 정보 보기
	public MemberDTO selectInfoById(String id) {
		return db.selectOne("Member.selectInfoById", id);
	}

	// 내 정보 수정
	public int updateInfo(Map<String, String> param) {
		return db.update("Member.updateInfo", param);
	}

	// 닉네임 중복 확인
	public boolean chkNickname(String nickname) {
		return db.selectOne("Member.selectByNickname", nickname);
	}

	// 프로필 이미지 불러오기
	public String selectProfileImgByNickBoard(String memberNickname) {
		return db.selectOne("Member.selectProfileImgByNickBoard", memberNickname);
	}

	// 프로필 이미지 불러오기
	public String selectProfileImgById(String id) {
		return db.selectOne("Member.selectProfileImgById", id);
	}

	// 마이페이지 메인 화면 정보 불러오기 (프로필 이미지, 레벨, 포인트)
	public Map<String, Object> selectMainMypageInfoById(String id) {
		return db.selectOne("Member.selectMainMypageInfoById", id);
	}
}
