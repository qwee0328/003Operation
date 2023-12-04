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
import org.springframework.web.bind.annotation.RequestParam;
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

	// 게시글 업로드 (+ 파일 업로드)
	@Transactional
	public void insert(BoardDTO dto, MultipartFile[] files, Integer[] deleteFileList) throws Exception {
		int bulletin_board_id = dao.insert(dto);
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
		Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src);
		Files.deleteIfExists(path);
	}

	// 이미지 일괄 삭제 ( 수정중 이미지 삽입하고 목록으로 이동한 경우 )
	@Transactional
	public void deleteImage(String[] srcList) throws Exception {
		for (String src : srcList) {
			Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src);
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
				Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src);
				Files.deleteIfExists(path);
			}
		}

		// 기존 이미지 삭제
		if (deleteImgsSrc != null && deleteImgsSrc.length >= 1) {
			for (String src : deleteImgsSrc) {
				Path path = FileSystems.getDefault().getPath("c:/003Operation/" + src);
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

	// 게시글 정보 불러오기 ( 출력용 )
	@Transactional
	public Map<String, Object> selectPostByIdJustView(int id) {
		dao.updateViewCountById(id);
		return dao.selectPostById(id);
	}

	// 게시글 추천 수 불러오기
	public int selectRecommendById(int id) {
		return dao.selectRecommendById(id);
	}

	// 게시글 북마크 수 불러오기
	public int selectBookmarkById(int id) {
		return dao.selectBookmarkById(id);
	}

	// 게시글 댓글 수 불러오기
	public int selectReplyById(int id) {
		return dao.selectBookmarkById(id);
	}

	// 게시글 추천
	public void insertRecommendById(Map<String, Object> param) {
		dao.insertRecommendById(param);
	}

	// 게시글 북마크
	public void insertBookmarkById(Map<String, Object> param) {
		dao.insertBookmarkById(param);
	}
	
	// 게시판 파일 불러오기
	public List<Map<String, Object>> selectFileById(Map<String, Object> param) {
		return dao.selectFileById(param);
	}

	// 게시글 삭제
	public void deletePost(int id) {
		dao.deletePost(id);
	}
}
