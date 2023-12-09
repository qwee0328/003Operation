package com.operation.services;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.operation.constants.Constants;
import com.operation.dao.BoardDAO;
import com.operation.dao.FileDAO;
import com.operation.dao.MemberDAO;
import com.operation.dto.BoardDTO;
import com.operation.dto.FileDTO;
import com.operation.dto.ReplyDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;

	@Autowired
	private FileDAO fdao;
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private HttpSession session;

	// 게시글 업로드 (+ 파일 업로드)
	@Transactional
	public void insert(BoardDTO dto, MultipartFile[] files, Integer[] deleteFileList) throws Exception {
		String role = mdao.getRole(dto.getMember_id());
		
		// 관리자면 공지게시글로 작성
		if(role.equals("ROLE_ADMIN")) {dto.setBulletin_category_id("notice"); dto.setIs_fix(true);}
	
		int bulletin_board_id = dao.insert(dto);
		if (files != null && files.length >= 1) {
			String path = "c:/003Operation/uploads";
			File uploadPath = new File(path);
			if (!uploadPath.exists())
				uploadPath.mkdir();

			if (deleteFileList != null && deleteFileList.length >= 1) {
				int idx = 0;
				
				for (int i = 0; i < files.length; i++) {
					if (idx < deleteFileList.length && deleteFileList[idx] == i) {
						idx++;
						continue;
					}
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new FileDTO(0, bulletin_board_id, sysName, oriName));
				}
			} else {
				for (int i = 0; i < files.length; i++) {
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new FileDTO(0, bulletin_board_id, sysName, oriName));
				}
			}

		}
	}
	

	// 게시글 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id, int currentPage) {
		Map<String, Object> param = new HashMap<>();
		param.put("bulletin_category_id", bulletin_category_id);
		param.put("start", currentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		return dao.selectAll(param);
	}

	// 게시글 검색 목록 불러오기
	public List<Map<String, Object>> selectByKeyword(String bulletin_category_id, String select, String keyword,
			int currentPage) {
		Map<String, Object> param = new HashMap<>();
		param.put("bulletin_category_id", bulletin_category_id);
		param.put("select", select);
		param.put("keyword", "%" + keyword + "%");
		param.put("start", currentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		return dao.selectByKeyword(param);
	}

	// 공지 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id) {
		return dao.selectAll(bulletin_category_id);
	}

	// 게시글 개수 불러오기
	public int selectTotalCnt(String bulletin_category_id) {
		return dao.selectTotalCnt(bulletin_category_id);
	}

	// 검색 게시글 개수 불러오기
	public int selectSearchCnt(String bulletin_category_id, String select, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("bulletin_category_id", bulletin_category_id);
		param.put("select", select);
		param.put("keyword", "%" + keyword + "%");
		return dao.selectSearchCnt(param);
	}

	// 이미지 업로드
	@Transactional
	public List<String> saveImage(MultipartFile[] files) throws Exception {
		List<String> list = new ArrayList<>();
		if (files != null && files.length >= 1) {
			String path = "c:/003Operation/uploads";
			File uploadPath = new File(path);
			if (!uploadPath.exists())
				uploadPath.mkdir();
			for (MultipartFile f : files) {
				String oriName = f.getOriginalFilename();
				String sysName = UUID.randomUUID() + "_" + oriName;
				f.transferTo(new File(uploadPath, sysName));
				list.add("/uploads/" + sysName);
			}
		}
		return list;
	}

	// 이미지 삭제
	@Transactional
	public void deleteImage(String src) throws Exception {
		System.out.println(src);
		Path path = FileSystems.getDefault().getPath("c:/003Operation" + src);
		Files.deleteIfExists(path);
	}

	// 이미지 일괄 삭제 ( 수정중 이미지 삽입하고 목록으로 이동한 경우 )
	@Transactional
	public void deleteImage(String[] srcList) throws Exception {
		for (String src : srcList) {
			Path path = FileSystems.getDefault().getPath("c:/003Operation" + src);
			Files.deleteIfExists(path);
		}
	}

	// 게시글 정보 불러오기 ( 수정용 )
	public Map<String, Object> selectPostById(int id) {
		return dao.selectPostById(id);
	}

	// 게시글 정보 수정
	@Transactional
	public void update(BoardDTO dto, MultipartFile[] files, Integer[] deleteFileList, Integer[] deleteExisingFileList,
			String[] deleteImgsSrc) throws Exception {
		dao.update(dto);

		// 기존 파일 삭제
		if (deleteExisingFileList != null && deleteExisingFileList.length >= 1) {
			List<String> deleteFileNameList = new ArrayList<>();
			if (deleteExisingFileList[0] == -1) {
				deleteFileNameList = fdao.selectAllByPostId(dto.getId());
				fdao.deleteAllByPostId(dto.getId());
			} else {
				deleteFileNameList = fdao.selectByIds(deleteExisingFileList);
				fdao.delete(deleteExisingFileList);
			}

			for (String src : deleteFileNameList) {
				Path path = FileSystems.getDefault().getPath("c:/003Operation/uploads/" + src);
				Files.deleteIfExists(path);
			}
		}

		// 기존 이미지 삭제
		if (deleteImgsSrc != null && deleteImgsSrc.length >= 1) {
			for (String src : deleteImgsSrc) {
				Path path = FileSystems.getDefault().getPath("c:/003Operation" + src);
				Files.deleteIfExists(path);
			}
		}

		// 새로운 파일 삽입
		if (files != null && files.length >= 1) {
			String path = "c:/003Operation/uploads";
			File uploadPath = new File(path);
			if (!uploadPath.exists())
				uploadPath.mkdir();

			// 삽입했다가 취소한 파일 제외
			if (deleteFileList != null && deleteFileList.length >= 1) {
				int idx = 0;
				for (int i = 0; i < files.length; i++) {
					if (idx < deleteFileList.length && deleteFileList[idx] == i) {
						idx++;
						continue;
					}
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new FileDTO(0, dto.getId(), sysName, oriName));
				}
			} else {
				for (int i = 0; i < files.length; i++) {
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new FileDTO(0, dto.getId(), sysName, oriName));
				}
			}

		}
	}

	// 게시글 시간 차이 계산하기
	public String timeCal(Object object) {
		long currentTime = System.currentTimeMillis();
		long writeTime = ((Timestamp) object).getTime();
		long gapTime = currentTime - writeTime;

		System.out.println(gapTime);
		if (gapTime < 60000) {
			return "방금 전";
		} else if (gapTime < 60000 * 60) {
			return gapTime / 60000 + " 분 전";
		} else if (gapTime < 60000 * 60 * 24) {
			long hour = gapTime / 60000 / 60;
			return "약 " + hour + "시간 전";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(writeTime);
		}
	}

	// 게시글 정보 불러오기 ( 출력용 )
	@Transactional
	public Map<String, Object> selectPostByIdJustView(int id) {
		dao.updateViewCountById(id);
		Map<String, Object> board = dao.selectPostById(id);
		board.put("timeCal", timeCal(board.get("write_date")));
		return board;
	}

	// 게시글 관련 정보 불러오기
	@Transactional
	public Map<String, Object> selectPostInfoById(int id) {
		Map<String, Object> postInfo = new HashMap<>();
		int recommend = dao.selectRecommendById(id);
		int bookmark = dao.selectBookmarkById(id);
		int reply = dao.selectReplyById(id);
		postInfo.put("recommend", recommend);
		postInfo.put("bookmark", bookmark);
		postInfo.put("reply", reply);
		return postInfo;
	}

	// 내가 추천 혹은 북마크를 했는지 불러오기
	@Transactional
	public Map<String, Object> selectPostInfoFromMy(int postId) {
		Map<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("userId", (String) session.getAttribute("loginID"));
		Map<String, Object> postInfo = new HashMap<>();
		boolean recommend = dao.selectRecommendFromMy(param);
		boolean bookmark = dao.selectBookmarkFromMy(param);

		Map<String, Object> info = new HashMap<>();
		info.put("recommend", recommend);
		info.put("bookmark", bookmark);
		return info;
	}

	// 게시글 추천
	public void insertRecommendById(Map<String, Object> param) {
		dao.insertRecommendById(param);
	}

	// 게시글 북마크
	public void insertBookmarkById(Map<String, Object> param) {
		dao.insertBookmarkById(param);
	}

	// 게시글 추천 삭제
	public void deleteRecommendById(Map<String, Object> param) {
		dao.deleteRecommendById(param);
	}

	// 게시글 북마크 삭제
	public void deleteBookmarkById(Map<String, Object> param) {
		dao.deleteBookmarkById(param);
	}

	// 게시판 파일 불러오기
	public List<Map<String, Object>> selectFileById(Map<String, Object> param) {
		return dao.selectFileById(param);
	}

	// 이전글 다음 글 불러오기
	public Map<String, Object> selectPrevNextPost(Map<String, Object> param) {
		return dao.selectPrevNextPost(param);
	}

	// 게시글 댓글 작성하기
	public boolean insertPostReply(int postId, String reply) {
		Map<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("userId", (String) session.getAttribute("loginID"));
		param.put("loginNickName", (String) session.getAttribute("loginNickName"));
		param.put("reply", reply);
		return dao.insertPostReply(param);
	}

	// 댓글 시간 차이 계산하기
	private String formatTimestamp(Timestamp time) {
		LocalDateTime currentTime = LocalDateTime.now();

		LocalDateTime sendTime = null;
		long minutes = 0;
		long hours = 0;
		if (time != null) {
			sendTime = time.toLocalDateTime();

			// 시간 차이 계산
			Duration duration = Duration.between(sendTime, currentTime);
			minutes = duration.toMinutes();
			hours = duration.toHours();
		}
		if (minutes < 60) {
			return minutes + "분 전";
		} else if (hours < 24) {
			return hours + "시간 전";
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return sendTime.format(formatter);
		}
	}

	// 게시글 댓글 불러오기
	@Transactional
	public Map<String, Object> selectAllReply(int id, int currentPage) {
		Map<String, Object> param = new HashMap<>();
		param.put("bulletin_board_id", id);
		param.put("userId", (String) session.getAttribute("loginID"));
		param.put("start", currentPage * Constants.REPLY_COUNT_PER_PAGE - (Constants.REPLY_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.REPLY_COUNT_PER_PAGE);
		System.out.println(Constants.REPLY_COUNT_PER_PAGE+"댓글 몇개 가져올꺼양?ㄴ");
		List<ReplyDTO> list = dao.selectAllReply(param);

		int recordTotalCount = dao.selectTotalReplyCnt(id);

		Map<String, Object> result = new HashMap<>();
		result.put("replyList", list);
		System.out.println(list);
		result.put("recordTotalCount", recordTotalCount);
		return result;
	}

	// 댓글 추천하기
	public int insertReplyRecommend(int replyId) {
		Map<String, Object> param = new HashMap<>();
		param.put("replyId", replyId);
		param.put("userId", (String) session.getAttribute("loginID"));
		System.out.println(replyId);
		System.out.println((String) session.getAttribute("loginID"));
		return dao.insertReplyRecommend(param);
	}

	// 댓글 추천삭제
	public int deleteReplyRecommend(int replyId) {
		Map<String, Object> param = new HashMap<>();
		param.put("replyId", replyId);
		param.put("userId", (String) session.getAttribute("loginID"));
		return dao.deleteReplyRecommend(param);
	}
	
	// 댓글 삭제하기
	public int deleteReply(int replyId) {
		return dao.deleteReply(replyId);
	}
	
	// 댓글 업데이트
	public int updateReply(int replyId,String replyContents) {
		Map<String, Object> param = new HashMap<>();
		param.put("replyId", replyId);
		param.put("replyContents", replyContents);
		return dao.updateReply(param);
	}
	
	// 대댓글 작성하기
	public boolean insertReReply(int postId, int parentId,String content) {
		Map<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("parentId", parentId);
		param.put("reply", content);
		param.put("userId", (String) session.getAttribute("loginID"));
		param.put("loginNickName", (String) session.getAttribute("loginNickName"));
		return dao.insertReReply(param);
	}
	
	// 대댓글 불러오기
	public List<ReplyDTO> selectReReplyAll(int parentId){
		Map<String, Object> param = new HashMap<>();
		param.put("parentId", parentId);
		param.put("userId", (String) session.getAttribute("loginID"));
		return dao.selectReReplyAll(param);
	}

	// 게시글 삭제
	public void deletePost(int id) {
		dao.deletePost(id);
	}
	
	// 내 게시글 불러오기
	public Map<String, Object> selectMyPost(String id, int cpage){
		Map<String, Object> result = new HashMap<>();
		
		
		Map<String, Object> param = new HashMap<>();
		param.put("member_id", id);
		param.put("start", cpage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);

		int recordTotalCount = dao.selectMyPostTotalCnt(id);
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", cpage);
		result.put("post", dao.selectMyPost(param));
		return result;
	}
	
	// 마이페이지 > 내 게시글에서 선택한 게시글 일괄 삭제
	public void deleteSelectPost(String[] ids) {
		Map<String, Object> param = new HashMap<>();
		param.put("array", ids);
		param.put("member_id", (String) session.getAttribute("loginID"));
		dao.deleteSelectPost(param);
	}
	
	// 마이페이지 > 내 게시글 검색
	public List<Map<String, Object>> searchMyPost(String select, String keyword, String loginID, int cpage){
		Map<String, Object> param = new HashMap<>();
		param.put("select", select);
		param.put("keyword", "%"+keyword+"%");
		param.put("member_id", (String) session.getAttribute("loginID"));
		param.put("start", cpage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		return dao.searchMyPost(param);
	}
	
	// 마이페이지 > 내 게시글 검색 총 개수
	public int selectSearchMyPostCnt(String select, String keyword, String loginID){
		Map<String, Object> param = new HashMap<>();
		param.put("select", select);
		param.put("keyword", "%"+keyword+"%");
		param.put("member_id", (String) session.getAttribute("loginID"));
		return dao.selectSearchMyPostCnt(param);
	}
	
	// 마이페이지 > 북마크 불러오기
	public Map<String, Object> selectMyBookmark(int cpage){
		Map<String, Object> result = new HashMap<>();
		
		String id =  (String) session.getAttribute("loginID");
		Map<String, Object> param = new HashMap<>();
		param.put("member_id", id);
		param.put("start", cpage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);

		int recordTotalCount = dao.selectMyBookmarkTotalCnt(id);
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", cpage);
		result.put("post", dao.selectMyBookmark(param));
		return result;
	}
	
	// 마이페이지 > 내 북마크 게시글 검색
	public List<Map<String, Object>> searchMyBookmark(String select, String keyword, String loginID, int cpage){
		Map<String, Object> param = new HashMap<>();
		param.put("select", select);
		param.put("keyword", "%"+keyword+"%");
		param.put("member_id", (String) session.getAttribute("loginID"));
		param.put("start", cpage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		return dao.searchMyBookmark(param);
	}
	
	// 마이페이지 > 내 북마크 게시글 검색 총 개수
	public int selectSearchMyBookmarkCnt(String select, String keyword, String loginID){
		Map<String, Object> param = new HashMap<>();
		param.put("select", select);
		param.put("keyword", "%"+keyword+"%");
		param.put("member_id", (String) session.getAttribute("loginID"));
		return dao.selectSearchMyBookmarkCnt(param);
	}
}