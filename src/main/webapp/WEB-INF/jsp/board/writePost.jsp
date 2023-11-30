<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/board/writePost.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/board/writePost.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	
	<div class="container">
		<div class="body__guide">
			<c:choose>
				<c:when test="${not empty question}"> 
					<div class="titleArea">질문 게시글 작성</div>
				</c:when>
				<c:otherwise>
					<div class="titleArea">자유 게시글 작성</div>
				</c:otherwise>
			</c:choose>
			<div class="postArea">
				<div class="postArea__title">
					<input type="text" class="postArea__titleInput" placeholder="제목을 입력해주세요">
				</div>
				<div class="postArea__file">
					<label class="fileInput__label" for="fileInput">파일 첨부</label>
					<input type="file" class="postArea__fileInput" id="fileInput" multiple>
				</div>
				<%@ include file="/WEB-INF/jsp/commons/summernote.jsp" %>
				<div class="postArea__bottom d-flex">
					<!-- 질문 게시판의 경우 비밀글 설정 -->
					<div class="postArea__secret d-flex">
					<c:choose>
						<c:when test="${not empty question}"> 
							<input type="checkbox" class="postArea__secretChk" id="secretChk">
							<label for="secretChk">비밀글 설정</label>
						</c:when>
					</c:choose>		
					</div>			
					<div class="postArea__btns d-flex">
						<button class="goList bColorGray">목록으로</button>
						<button class="write bColorMainPink">작성완료</button>
					</div>
				</div>

			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>