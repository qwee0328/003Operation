<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${not empty post.id}">
		<title>게시글 수정</title>
	</c:when>
	<c:otherwise>
		<title>게시글 작성</title>
	</c:otherwise>
</c:choose>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/board/writePost.css">

<script src="/js/board/writePost.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	<div class="container">
		<div class="body__guide">
			<c:choose>
				<c:when test="${not empty isQna}">
					<c:choose>
						<c:when test="${not empty post.id}">
							<div class="titleArea">Q&A 게시글 수정</div>
						</c:when>
						<c:otherwise>
							<div class="titleArea">Q&A 게시글 작성</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${not empty isQuestion}"> 
							<c:choose>
								<c:when test="${not empty post.id}">
									<div class="titleArea">질문 게시글 수정</div>
								</c:when>
								<c:otherwise>
									<div class="titleArea">질문 게시글 작성</div>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${not empty post.id}">
									<div class="titleArea">자유 게시글 수정</div>
								</c:when>
								<c:otherwise>
									<div class="titleArea">자유 게시글 작성</div>
								</c:otherwise>
							</c:choose>	
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		
			
			<div class="postArea">
				<div class="postArea__title">
					<input type="text" class="postArea__titleInput" value="${post.title}" placeholder="제목을 입력해주세요">
				</div>
				<div class="postArea__file">
					<div class="d-flex">
						<label class="fileInput__label" for="fileInput">파일 첨부</label>
							<div class="fileNameList d-flex">
								<c:choose>	
									<c:when test="${not empty post.file_names}">
										
										<script>
											// 기존 파일 목록
											let fileNames = `${post.file_names}`.split(",");
											let fileIds = `${post.file_ids}`.split(",");
											
											for(let i=0; i<fileNames.length; i++){
												let fileNameTag = $("<div>").attr("class","fileNameTag d-flex");
												let fileName = $("<div>").attr("class","fileName").text(fileNames[i]);
												let fileIcon = $("<div>").attr("class","ml5").html("<i class='fa-solid fa-xmark deleteFileBtn' data-seq="+fileIds[i]+"></i>")
												$(".fileNameList").append(fileNameTag.append(fileName).append(fileIcon))		
											}
										</script>
										
									</c:when>
									
								</c:choose>
								
							</div>
					</div>
					<input type="file" class="postArea__fileInput" id="fileInput" multiple>
					
				</div>
				<%@ include file="/WEB-INF/jsp/commons/summernote.jsp" %>	
				
				<div class="postArea__bottom d-flex">
					<!-- 질문 게시판의 경우 비밀글 설정 -->
					<div class="postArea__secret d-flex">
					<c:choose>
						<c:when test="${not empty isQna}"> 
							<input type="checkbox" class="postArea__secretChk" id="secretChk">
							<label for="secretChk">비밀글 설정</label>
							<script>
								if(`${post.is_secret}` == 1) $("#secretChk").prop("checked",true);
							</script>
						</c:when>
					</c:choose>		
					</div>			
					<div class="postArea__btns d-flex">
						<c:choose>
							<c:when test="${not empty post}">
								<button class="goPost bColorGray colorWhite">수정취소</button>
								<button class="update bColorMainPink colorWhite" data-id="${post.id}">수정완료</button>
							</c:when>
							<c:otherwise>
								<button class="goList bColorGray">목록으로</button>
								<button class="write bColorMainPink">작성완료</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
    <input type="hidden" id="select" value="${select }">
    <input type="hidden" id="keyword" value="${keyword }">
<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>