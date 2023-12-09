package com.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.operation.dto.BoardDTO;
import com.operation.dto.ReplyDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession db;

	// 게시글 작성
	public int insert(BoardDTO dto) {
		db.insert("Board.insert", dto);
		return dto.getId();
	}

	// 게시글 목록 불러오기
	public List<Map<String, Object>> selectAll(Map<String, Object> param) {
		return db.selectList("Board.selectAll", param);
	}

	// 게시글 검색 목록 불러오기
	public List<Map<String, Object>> selectByKeyword(Map<String, Object> param) {
		return db.selectList("Board.selectByKeyword", param);
	}

	// 공지 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id) {
		return db.selectList("Board.selectAll", bulletin_category_id);
	}

	// 게시글 개수 불러오기
	public int selectTotalCnt(String bulletin_category_id) {
		return db.selectOne("Board.selectTotalCnt", bulletin_category_id);
	}

	// 검색 게시글 개수 불러오기
	public int selectSearchCnt(Map<String, Object> param) {
		return db.selectOne("Board.selectSearchCnt", param);
	}

	// 게시글 정보 불러오기 ( 수정용 )
	public Map<String, Object> selectPostById(int id) {
		return db.selectOne("Board.selectPostById", id);
	}

	// 게시글 수정
	public int update(BoardDTO dto) {
		return db.update("Board.update", dto);
	}

	// 게시글 조회수 업데이트
	public void updateViewCountById(int id) {
		db.update("Board.updateViewCountById", id);
	}

	// 게시글 추천 수 불러오기
	public int selectRecommendById(int id) {
		Integer result = db.selectOne("Board.selectRecommendById", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 게시글 북마크 수 불러오기
	public int selectBookmarkById(int id) {
		Integer result = db.selectOne("Board.selectBookmarkById", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 게시글 댓글 수 불러오기
	public int selectReplyById(int id) {
		Integer result = db.selectOne("Board.selectReplyById", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 게시글 추천
	public void insertRecommendById(Map<String, Object> param) {
		db.update("Board.insertRecommendById", param);
	}

	// 게시글 북마크
	public void insertBookmarkById(Map<String, Object> param) {
		db.update("Board.insertBookmarkById", param);
	}

	// 게시글 추천 삭제
	public void deleteRecommendById(Map<String, Object> param) {
		db.update("Board.deleteRecommendById", param);
	}

	// 게시글 북마크 삭제
	public void deleteBookmarkById(Map<String, Object> param) {
		db.update("Board.deleteBookmarkById", param);
	}

	// 내가 추천했는지
	public boolean selectRecommendFromMy(Map<String, Object> param) {
		Boolean result = db.selectOne("Board.selectRecommendFromMy", param);
		return (result != null) ? result.booleanValue() : false;
	}

	// 내가 북마크했는지
	public boolean selectBookmarkFromMy(Map<String, Object> param) {
		Boolean result = db.selectOne("Board.selectBookmarkFromMy", param);
		return (result != null) ? result.booleanValue() : false;
	}

	// 게시글 파일 불러오기
	public List<Map<String, Object>> selectFileById(Map<String, Object> param) {
		return db.selectList("Board.selectFileById", param);
	}

	// 이전글 다음글 불러오기
	public Map<String, Object> selectPrevNextPost(Map<String, Object> param) {
		return db.selectOne("Board.selectPrevNextPost", param);
	}

	// 게시글 댓글 작성하기
	public boolean insertPostReply(Map<String, Object> param) {
		int result = db.insert("Board.insertPostReply", param);
		return result > 0 ? true : false;
	}

	// 게시글 댓글 불러오기
	public List<ReplyDTO> selectAllReply(Map<String, Object> param) {
		List<ReplyDTO> result = db.selectList("Board.selectAllReply", param);
		System.out.println(result);
		System.out.println(param);
		return result;
	}

	// 게시글 댓글 총 개수
	public int selectTotalReplyCnt(int id) {
		Integer result = db.selectOne("Board.selectTotalReplyCnt", id);
		return (result != null) ? result.intValue() : 0;
	}

	// 댓글 추천하기
	public int insertReplyRecommend(Map<String, Object> param) {
		return db.insert("Board.insertReplyRecommend", param);
	}

	// 댓글 추천 삭제하기
	public int deleteReplyRecommend(Map<String, Object> param) {
		return db.delete("Board.deleteReplyRecommend", param);
	}

	// 댓글 삭제하기
	public int deleteReply(int replyId) {
		return db.delete("Board.deleteReply", replyId);
	}

	// 댓글 업데이트
	public int updateReply(Map<String, Object> param) {
		return db.update("Board.updateReply", param);
	}

	// 대댓글 입력
	public boolean insertReReply(Map<String, Object> param) {
		int result = db.insert("Board.insertReReply", param);
		return result > 0 ? true : false;
	}

	// 대댓글 불러오기
	public List<ReplyDTO> selectReReplyAll(Map<String, Object> param) {
		return db.selectList("Board.selectReReplyAll", param);
	}

	// 게시글 삭제
	public void deletePost(int id) {
		db.delete("Board.deletePost", id);
	}
	
	// 내 게시글 불러오기
	public List<Map<String, Object>> selectMyPost(Map<String, Object> param){
		return db.selectList("Board.selectMyPost",param);
	}
	
	// 내 게시글 총 개수 불러오기
	public int selectMyPostTotalCnt(String id){
		return db.selectOne("Board.selectMyPostTotalCnt",id);
	}
	

	// 마이페이지 > 내 게시글에서 선택한 게시글 일괄 삭제
	public void deleteSelectPost(Map<String, Object> param) {
		db.delete("Board.deleteSelectPost",param);
	}
}