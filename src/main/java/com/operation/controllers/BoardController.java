package com.operation.controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.operation.constants.Constants;
import com.operation.dto.BoardDTO;
import com.operation.dto.ReplyDTO;
import com.operation.services.BoardService;
import com.operation.services.MemberService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService bservice;

	@Autowired
	private MemberService mservice;
	
	@Autowired
	private HttpSession session;

	// 게시글 작성 페이지로 이동
	@RequestMapping("/goWritePost/{catogory}")
	public String goWritePost(@PathVariable String catogory, String select, String keyword, Model model) {
		if (catogory.equals("qna"))
			model.addAttribute("isQna", true);
		else if (catogory.equals("question"))
			model.addAttribute("isQuestion", true);
		
		model.addAttribute("select", select);
		model.addAttribute("keyword", keyword);
		return "board/writePost";
	}

	// 게시글 목록 불러오기
	@ResponseBody
	@RequestMapping("/selectPostAll")
	public Map<String, Object> loadPostAll(String category,
			@RequestParam(value = "cpage", required = false) String cpage) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		if (category.equals("notice")) {
			list = bservice.selectAll(category);
		} else {
			
			list = bservice.selectAll(category, currentPage);
			int recordTotalCount = bservice.selectTotalCnt(category);
			result.put("recordTotalCount", recordTotalCount);
			result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
			result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		}
		result.put("postCurPage", currentPage);
		result.put("list", list);
		return result;
	}

	// 게시글 검색 목록 불러오기
	@ResponseBody
	@RequestMapping("/selectByKeyword")
	public Map<String, Object> selectByKeyword(String category, String select, String keyword,
			@RequestParam(value = "cpage", required = false) String cpage) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		list = bservice.selectByKeyword(category, select, keyword, currentPage);
		int recordTotalCount = bservice.selectSearchCnt(category, select, keyword);
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);

		result.put("postCurPage", currentPage);
		result.put("list", list);
		return result;
	}

	// 게시글 작성
	@ResponseBody
	@RequestMapping("/writePost")
	public void writePost(BoardDTO dto,
			@RequestParam(value = "attachFiles", required = false) MultipartFile[] attachFiles,
			@RequestParam(value = "deleteFileList", required = false) Integer[] deleteFileList) throws Exception {
		dto.setMember_id(((String) session.getAttribute("loginID")));
		dto.setMember_nickname(((String) session.getAttribute("loginNickName")));
		bservice.insert(dto, attachFiles, deleteFileList);
	}

	// 게시글 목록으로 이동
	@RequestMapping("/listBoard/{catogory}")
	public String listBoard(@PathVariable String catogory,
			@RequestParam(value = "select", required = false) String select,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		if (catogory.equals("qna"))
			model.addAttribute("isQna", true);
		else if (catogory.equals("question"))
			model.addAttribute("isQuestion", true);

		if (keyword != null && !keyword.equals("none")) {
			model.addAttribute("select", select);
			model.addAttribute("keyword", keyword);
		}
		return "board/boardList";
	}

	// 게시글 출력
	@RequestMapping("/viewPostConf/{type}/{select}/{dataId}")
	public String viewPostConf(@PathVariable String type, @PathVariable String select, @PathVariable String dataId,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		int postId = Integer.parseInt(dataId);
		Map<String, Object> post = bservice.selectPostByIdJustView(postId);
		model.addAttribute("post", post);
		if (type.equals("question")) {
			type = "질문";
		} else {
			type = "자유";
		}
		model.addAttribute("type", type);
		model.addAttribute("select", select);
		model.addAttribute("keyword", keyword);
		model.addAttribute("profile", mservice.selectProfileImgById((String) session.getAttribute("loginID")));
		return "board/viewPost";
	}

	// 본인이 작성한 게시글인지 확인
	@ResponseBody
	@RequestMapping("/isMyPost")
	public boolean isMyPost(@RequestParam String memberNickname) {
		String loginNicname = (String) session.getAttribute("loginNickName");
		return loginNicname.equals(memberNickname);
	}

	// 게시글 관련 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectPostInfoById")
	public Map<String, Object> selectPostInfoById(@RequestParam String postId) {
		int id = Integer.parseInt(postId);
		return bservice.selectPostInfoById(id);
	}

	// 내가 추천 혹은 북마크를 했는지 불러오기
	@ResponseBody
	@RequestMapping("/selectPostInfoFromMy")
	public Map<String, Object> selectPostInfoFromMy(@RequestParam String postId) {
		int id = Integer.parseInt(postId);
		return bservice.selectPostInfoFromMy(id);
	}

	// 게시글 추천, 북마크
	@ResponseBody
	@RequestMapping("/insertPostInfoById/{type}")
	public void insertRecommendById(@PathVariable String type, @RequestParam String postId) {
		int id = Integer.parseInt(postId);
		Map<String, Object> param = new HashMap<>();
		param.put("postId", id);
		param.put("userId", (String) session.getAttribute("loginID"));

		if (type.equals("recommend")) {
			bservice.insertRecommendById(param);
		} else {
			bservice.insertBookmarkById(param);
		}
	}

	// 게시글 추천, 북마크 취소
	@ResponseBody
	@RequestMapping("/deletePostInfoById/{type}")
	public void deletePostInfoById(@PathVariable String type, @RequestParam String postId) {
		int id = Integer.parseInt(postId);
		Map<String, Object> param = new HashMap<>();
		param.put("postId", id);
		param.put("userId", (String) session.getAttribute("loginID"));

		if (type.equals("recommend")) {
			bservice.deleteRecommendById(param);
		} else {
			bservice.deleteBookmarkById(param);
		}
	}

	// 게시글 파일 불러오기
	@ResponseBody
	@RequestMapping("/selectFileById/{type}")
	public List<Map<String, Object>> selectFileById(@PathVariable String type, @RequestParam String postId) {
		int id = Integer.parseInt(postId);
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("type", type);
		List<Map<String, Object>> result = bservice.selectFileById(param);
		return result;
	}

	// 파일 다운로드
	@RequestMapping("/downloadFile")
	public void downloadFile(@RequestParam String sysName, @RequestParam String oriName, HttpServletResponse response)
			throws Exception {
		String realPath = "C:/003Operation/uploads";
		File targetFile = new File(realPath + "/" + sysName);

		oriName = new String(oriName.getBytes("utf8"), "ISO-8859-1");
		response.setHeader("content-disposition", "attachement;filename=" + oriName);
		byte[] fileContents = new byte[(int) targetFile.length()];

		try (DataInputStream dis = new DataInputStream(new FileInputStream(targetFile))) {
			ServletOutputStream sos = response.getOutputStream();
			dis.readFully(fileContents);
			sos.write(fileContents);
			sos.flush();
		}
	}

	// 이전글 다음 글 불러오기
	@ResponseBody
	@RequestMapping("/selectPrevNextPost")
	public Map<String, Object> selectPrevNextPost(@RequestParam String postId, @RequestParam String category,
			@RequestParam String keyword, @RequestParam String select) {
		int id = Integer.parseInt(postId);
		Map<String, Object> param = new HashMap<>();
		param.put("postId", id);
		param.put("type", category);
		param.put("keyword", "%" + keyword + "%");
		param.put("select", select);
		Map<String, Object> result = bservice.selectPrevNextPost(param);
		return result;
	}

	// 게시글 댓글 작성하기
	@ResponseBody
	@RequestMapping("/insertPostReply")
	public boolean insertPostReply(@RequestParam String postId, @RequestParam String reply) {
		System.out.println(postId);
		int id = Integer.parseInt(postId);
		return bservice.insertPostReply(id, reply);
	}

	// 게시글 댓글 목록 불러오기
	@ResponseBody
	@RequestMapping("/selectPostReplyAll")
	public Map<String, Object> selectPostReplyAll(@RequestParam String postId, @RequestParam String replyCpage) {
		int id = Integer.parseInt(postId);
		int currentPage = (replyCpage == null || replyCpage.isEmpty()) ? 1 : Integer.parseInt(replyCpage);

		Map<String, Object> list = bservice.selectAllReply(id, currentPage);
		Map<String, Object> result = new HashMap<>();
		result.put("replyList", list.get("replyList"));
		System.out.println(list.get("replyList"));
		result.put("replyCpage", currentPage);
		result.put("recordTotalCount", list.get("recordTotalCount"));
		result.put("recordCountPerPage", Constants.REPLY_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);

		return result;
	}

	// 댓글 추천하기
	@ResponseBody
	@RequestMapping("/insertReplyRecommend")
	public int insertReplyRecommend(@RequestParam String replyId) {
		int id = Integer.parseInt(replyId);
		return bservice.insertReplyRecommend(id);
	}

	// 댓글 추천 삭제
	@ResponseBody
	@RequestMapping("/deleteReplyRecommend")
	public int deleteReplyRecommend(@RequestParam String replyId) {
		int id = Integer.parseInt(replyId);
		return bservice.deleteReplyRecommend(id);
	}

	// 댓글 삭제하기
	@ResponseBody
	@RequestMapping("/deleteReply")
	public int deleteReply(@RequestParam String replyId) {
		int id = Integer.parseInt(replyId);
		return bservice.deleteReply(id);
	}

	// 댓글 업데이트
	@ResponseBody
	@RequestMapping("/updateReply")
	public int updateReply(@RequestParam String replyId, @RequestParam String replyContents) {
		int id = Integer.parseInt(replyId);
		return bservice.updateReply(id, replyContents);
	}

	// 대댓글 작성하기
	@ResponseBody
	@RequestMapping("/insertReReply")
	public boolean insertReReply(@RequestParam String postId, @RequestParam String parentId,
			@RequestParam String content) {
		System.out.println(("대댓글 작성"));
		int postid = Integer.parseInt(postId);
		int parentid = Integer.parseInt(parentId);
		return bservice.insertReReply(postid, parentid, content);
	}

	// 대댓글 불러오기
	@ResponseBody
	@RequestMapping("/selectReReplyAll")
	public List<ReplyDTO> selectReReplyAll(@RequestParam String parentId) {
		System.out.println(parentId + "test");
		int parentid = Integer.parseInt(parentId);
		return bservice.selectReReplyAll(parentid);
	}

	// 게시글 작성 페이지로 이동
	@RequestMapping("/goUpdatePost/{catogory}/{post_id}")
	public String goUpdatePost(@PathVariable String catogory, Model model, @PathVariable int post_id, String select,
			String keyword) {
		if (catogory.equals("question"))
			model.addAttribute("isQuestion", true);
		model.addAttribute("post", bservice.selectPostById(post_id));
		model.addAttribute("select", select);
		model.addAttribute("keyword", keyword);
		return "board/writePost";
	}

	// 게시글 수정
	@ResponseBody
	@RequestMapping("/updatePost")
	public void updatePost(BoardDTO dto,
			@RequestParam(value = "attachFiles", required = false) MultipartFile[] attachFiles,
			@RequestParam(value = "deleteFileList", required = false) Integer[] deleteFileList,
			@RequestParam(value = "deleteExisingFileList", required = false) Integer[] deleteExisingFileList,
			@RequestParam(value = "deleteImgs", required = false) String[] deleteImgs) throws Exception {
		bservice.update(dto, attachFiles, deleteFileList, deleteExisingFileList, deleteImgs);
	}

	// 게시글 삭제
	@RequestMapping("/deletePost/{category}/{dataId}")
	public String deletePost(@PathVariable String category, @PathVariable String dataId) {
		int postId = Integer.parseInt(dataId);
		bservice.deletePost(postId);
		return "redirect:/board/listBoard/" + category;
	}

	// summernote 이미지 경로 설정
	@ResponseBody
	@RequestMapping("/uploadImage")
	public List<String> uploadImage(@RequestParam("imgs") MultipartFile[] imgs) throws Exception {
		List<String> fileList = bservice.saveImage(imgs);
		return fileList;
	}

	// summernote 이미지 경로에서 삭제
	@ResponseBody
	@RequestMapping("/deleteImage")
	public void deleteImage(@RequestParam(value = "src", required = false) String src,
			@RequestParam(value = "srcList", required = false) String[] srcList) throws Exception {
		if (src != null && !src.isBlank()) {
			bservice.deleteImage(src);
		} else if (srcList != null && srcList.length >= 1) {
			bservice.deleteImage(srcList);
		}
	}
	
	// 마이페이지 > 내 게시글에서 선택한 게시글 일괄 삭제
	@ResponseBody
	@RequestMapping("/mypage/deleteSelectPost")
	public void deleteSelectPost(@RequestParam(value = "deleteIds", required = false) String[] deleteIds) {
		if (deleteIds != null && deleteIds.length>=1) {
			bservice.deleteSelectPost(deleteIds);
		}
	}
	
	// 마이페이지 > 내 게시글 검색
	@ResponseBody
	@RequestMapping("/mypage/searchMyPost")
	public Map<String, Object> searchMyPost(@RequestParam(value = "cpage", required = false) String cpage, String select, String keyword){
		Map<String, Object> result = new HashMap<>();
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		List<Map<String, Object>> list = bservice.searchMyPost(select, keyword, (String) session.getAttribute("loginID"), currentPage);
		int recordTotalCount = bservice.selectSearchMyPostCnt(select, keyword, (String) session.getAttribute("loginID"));

		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", currentPage);
		result.put("post", list);
		return result;
	}
	
	// 마이페이지 > 내 북마크 게시글 검색
	@ResponseBody
	@RequestMapping("/mypage/searchMyBookmark")
	public Map<String, Object> searchMyBookmark(@RequestParam(value = "cpage", required = false) String cpage, String select, String keyword){
		Map<String, Object> result = new HashMap<>();
		int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		List<Map<String, Object>> list = bservice.searchMyBookmark(select, keyword, (String) session.getAttribute("loginID"), currentPage);
		int recordTotalCount = bservice.selectSearchMyBookmarkCnt(select, keyword, (String) session.getAttribute("loginID"));
		
		result.put("recordTotalCount", recordTotalCount);
		result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		result.put("postCurPage", currentPage);
		result.put("post", list);
		return result;
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
