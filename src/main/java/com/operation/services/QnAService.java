package com.operation.services;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
import com.operation.dao.FileDAO;
import com.operation.dao.QnADAO;
import com.operation.dto.BoardDTO;
import com.operation.dto.FileDTO;
import com.operation.dto.QnaAnswerDTO;
import com.operation.dto.QnaAnswerFileDTO;
import com.operation.dto.QnaQuestionDTO;
import com.operation.dto.QnaQuestionFileDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class QnAService {
	@Autowired
	private QnADAO dao;

	@Autowired
	private FileDAO fdao;
	
	@Autowired
	private HttpSession session;
	
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
	
	// qna 답변 업로드 (+ 파일 업로드)
	@Transactional
	public void insert(QnaAnswerDTO dto, MultipartFile[] files, Integer[] deleteFileList) throws Exception {
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
					fdao.insert(new QnaAnswerFileDTO(0, qna_question_board_id, sysName, oriName));
				}
			} else {
				for (int i = 0; i < files.length; i++) {
					String oriName = files[i].getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					files[i].transferTo(new File(uploadPath, sysName));
					fdao.insert(new QnaAnswerFileDTO(0, qna_question_board_id, sysName, oriName));
				}
			}

		}
	}
	
	// qna 게시글 목록 불러오기 
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
	
	// qna 게시글 질문 + 답글 정보 불러오기 (출력용)
	public Map<String, Object> selectById(int id){
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> question = dao.selectQustionById(id);

		String loginID = (String)session.getAttribute("loginID");
		if(Integer.parseInt(question.get("is_secret").toString())==1 && !question.get("member_id").toString().equals(loginID)){
			// 권한이 없는 게시글에 접근 (비밀글인데 작성자가 아닌 경우)
			// 관리자는 접근이 가능하도록 role 설정 추가필요!!!
			//
			//
			//
			//
			//
			//
			//
			//
			result.put("permission", "no");
		}else {
			result.put("question", question);
			result.put("answer", dao.selectAnswerById(id));
		}
		return result;
	}
	
	// qna 게시글 질문 정보 불러오기 (수정용)
	public Map<String, Object> selectQuestionById(int id){
		Map<String, Object> question = dao.selectQustionById(id);
		String loginID = (String)session.getAttribute("loginID");
		if(Integer.parseInt(question.get("is_secret").toString())==1 && !question.get("member_id").toString().equals(loginID)){
			// 권한이 없는 게시글에 접근 (비밀글인데 작성자가 아닌 경우)
			question.put("permission", "no");
			return question;
		}else {
			return question;
		}
	}
	
	
	// 질문 게시글 수정
	// 게시글 정보 수정
	@Transactional
	public void update(QnaQuestionDTO dto, MultipartFile[] files, Integer[] deleteFileList, Integer[] deleteExisingFileList,
			String[] deleteImgsSrc) throws Exception {
		dao.update(dto);
//
//		// 기존 파일 삭제
//		if (deleteExisingFileList != null && deleteExisingFileList.length >= 1) {
//			List<String> deleteFileNameList = new ArrayList<>();
//			if (deleteExisingFileList[0] == -1) {
//				deleteFileNameList = fdao.selectAllByPostId(dto.getId());
//				fdao.deleteAllByPostId(dto.getId());
//			} else {
//				deleteFileNameList = fdao.selectByIds(deleteExisingFileList);
//				fdao.delete(deleteExisingFileList);
//			}
//
//			for (String src : deleteFileNameList) {
//				Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src);
//				Files.deleteIfExists(path);
//			}
//		}
//
//		// 기존 이미지 삭제
//		if (deleteImgsSrc != null && deleteImgsSrc.length >= 1) {
//			for (String src : deleteImgsSrc) {
//				Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src);
//				Files.deleteIfExists(path);
//			}
//		}
//
//		// 새로운 파일 삽입
//		if (files != null && files.length >= 1) {
//			String path = "c:/003Operation/uploads";
//			File uploadPath = new File(path);
//			if (!uploadPath.exists())
//				uploadPath.mkdir();
//
//			// 삽입했다가 취소한 파일 제외
//			if (deleteFileList != null && deleteFileList.length >= 1) {
//				int idx = 0;
//				for (int i = 0; i < files.length; i++) {
//					if (idx < deleteFileList.length && deleteFileList[idx] == i) {
//						idx++;
//						continue;
//					}
//					String oriName = files[i].getOriginalFilename();
//					String sysName = UUID.randomUUID() + "_" + oriName;
//					files[i].transferTo(new File(uploadPath, sysName));
//					fdao.insert(new QnaQuestionFileDTO(0, dto.getId(), sysName, oriName));
//				}
//			} else {
//				for (int i = 0; i < files.length; i++) {
//					String oriName = files[i].getOriginalFilename();
//					String sysName = UUID.randomUUID() + "_" + oriName;
//					files[i].transferTo(new File(uploadPath, sysName));
//					fdao.insert(new QnaQuestionFileDTO(0, dto.getId(), sysName, oriName));
//				}
//			}
//
//		}
	}
	
}
