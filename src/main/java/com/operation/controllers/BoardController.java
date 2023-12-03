package com.operation.controllers;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
import com.operation.services.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService bservice;
	
	@Autowired
	private HttpSession session;
	
	// 게시글 작성 페이지로 이동
	@RequestMapping("/goWritePost/{catogory}")
	public String goWritePost(@PathVariable String catogory, Model model) {
		if(catogory.equals("question"))
			model.addAttribute("isQuestion",true);
		return "board/writePost";
	}
	
	// 게시글 목록 불러오기
	@ResponseBody
	@RequestMapping("/selectPostAll")
	public Map<String, Object> loadPostAll(String category,  @RequestParam(value="cpage", required=false) String cpage){
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		if(category.equals("notice")) {
			list = bservice.selectAll(category);
		}else {
			int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
			list = bservice.selectAll(category, currentPage);
			int recordTotalCount = bservice.selectToalCnt(category);
			result.put("recordTotalCount", recordTotalCount);
			result.put("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
			result.put("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		}
		result.put("postCurPage", cpage);
		result.put("list", list);
		return result;
		
	}

	
	// 게시글 작성
	@ResponseBody
	@RequestMapping("/writePost")
	public void writePost(BoardDTO dto, @RequestParam(value = "attachFiles", required = false) MultipartFile[] attachFiles,@RequestParam(value = "deleteFileList", required = false) Integer[] deleteFileList) throws Exception {
		dto.setMember_id(((String)session.getAttribute("loginID")));
		dto.setMember_nickname(((String)session.getAttribute("loginNickName")));

		bservice.insert(dto, attachFiles, deleteFileList);
	}
	
	// 게시글 목록으로 이동
	@RequestMapping("/listBoard/{catogory}")
	public String listBoard(@PathVariable String catogory, Model model) {
		if(catogory.equals("question"))
			model.addAttribute("isQuestion",true);
		
		//int currentPage = (cpage == null || cpage.isEmpty()) ? 1 : Integer.parseInt(cpage);
		
		//bservice.selectAll(catogory, currentPage);
		return "board/boardList";
	}
	
	@RequestMapping("/viewPost")
	public void viewPost() {
		// 게시글 출력
	}
	
	@RequestMapping("/updatePost")
	public void updatePost() {
		// 게시글 수정
	}
	
	@RequestMapping("/deletePost")
	public void deletePost() {
		// 게시글 삭제
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
	public void deleteImage(@RequestParam("src") String src) throws Exception {
		Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src); // String을 Path 객체로 변환
		Files.deleteIfExists(path);
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
