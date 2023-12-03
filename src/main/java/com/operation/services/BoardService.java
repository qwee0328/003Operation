package com.operation.services;

import java.io.File;
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
import com.operation.dto.BoardDTO;
import com.operation.dto.FileDTO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private FileDAO fdao;
	
	// 게시글에 파일 업로드
	@Transactional 
	public int insert(BoardDTO dto, MultipartFile[] files) throws Exception {
		int bulletin_board_id = dao.insert(dto);
		if(files != null && files.length >= 1) {
			String path = "c:/003Operation/uploads";
			File uploadPath = new File(path);
			if(!uploadPath.exists()) uploadPath.mkdir();
			for(MultipartFile f:files) {
				String oriName = f.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				f.transferTo(new File(uploadPath,sysName));
				fdao.insert(new FileDTO(0, bulletin_board_id, sysName, oriName));
			}
		}
		return 0;
	}
	
	// 게시글 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id, int currentPage){
		Map<String, Object> param = new HashMap<>();
		param.put("bulletin_category_id", bulletin_category_id);
		param.put("start", currentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE -1) -1 );
		param.put("count", currentPage * Constants.RECORD_COUNT_PER_PAGE);
		return dao.selectAll(param);
	}
	
	// 공지 목록 불러오기
	public List<Map<String, Object>> selectAll(String bulletin_category_id){
		return dao.selectAll(bulletin_category_id);
	}
	
	// 게시글 개수 불러오기
	public int selectToalCnt(String bulletin_category_id) {
		return dao.selectToalCnt(bulletin_category_id);
	}
	
	// 이미지 업로드
	@Transactional 
	public List<String>  saveImage(MultipartFile[] files) throws Exception {
		List<String> list = new ArrayList<>();
		if(files != null && files.length >= 1) {
			String path = "c:/003Operation/uploads";
			File uploadPath = new File(path);
			if(!uploadPath.exists()) uploadPath.mkdir();
			for(MultipartFile f:files) {
				String oriName = f.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				f.transferTo(new File(uploadPath,sysName));
				list.add("/uploads/"+sysName);
			}
		}
		return list;
	}
}
