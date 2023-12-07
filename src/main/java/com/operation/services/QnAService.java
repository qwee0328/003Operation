package com.operation.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.operation.constants.Constants;
import com.operation.dao.FileDAO;
import com.operation.dao.QnADAO;
import com.operation.dto.QnaQuestionDTO;
import com.operation.dto.QnaQuestionFileDTO;

@Service
public class QnAService {
	@Autowired
	private QnADAO dao;

	@Autowired
	private FileDAO fdao;
	
	// qna 업로드 (+ 파일 업로드)
	@Transactional
	public void insert(QnaQuestionDTO dto, MultipartFile[] files, Integer[] deleteFileList) throws Exception {
		int qna_question_board_id = dao.insert(dto);
		if (files != null && files.length >= 1) {
			String path = "c:/003Operation/uploads";
			File uploadPath = new File(path);
			if (!uploadPath.exists())
				uploadPath.mkdir();

			if (deleteFileList != null && deleteFileList.length >= 1) {
				int idx = 0;
				System.out.println("길이: :" + deleteFileList.length);
				for (int i = 0; i < files.length; i++) {
					if (idx < deleteFileList.length && deleteFileList[idx] == i) {
						idx++;
						continue;
					}
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new QnaQuestionFileDTO(0, qna_question_board_id, sysName, oriName));
				}
			} else {
				for (int i = 0; i < files.length; i++) {
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new QnaQuestionFileDTO(0, qna_question_board_id, sysName, oriName));
				}
			}

		}
	}
	
	// qna 게시글 불러오기 
	public List<Map<String,Object>> selectAll(int currentPage){
		Map<String, Object> param = new HashMap<>();
		param.put("start", currentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1);
		param.put("count", Constants.RECORD_COUNT_PER_PAGE);
		return dao.selectAll(param);
	}
	
	// qna 게시글 총 개수 불러오기
	public int selectTotalCnt(){
		return dao.selectTotalCnt();
	}
}
