package com.operation.services;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
}
