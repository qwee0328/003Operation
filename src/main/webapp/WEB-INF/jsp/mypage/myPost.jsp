<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 작성한 글</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/css/mypage/mypost.css"/>
<link rel="stylesheet" href="/css/commons/common.css"/>
<script src="/js/mypage/myPost.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
    <div class="container">	
        <div class="guide">
            <div class="board__header"> 	
            	<div class="mypage__tap d-flex">
            		<div class="tap__myPost active"><a href="/member/goMyPost">내가 작성한 글</a></div>
            		<div class="tap__myReply"><a href="/member/goMyReply">내가 작성한 댓글</a></div>
            		<div class="tap__myBookmark"><a href="/member/goMyBookmark">내가 북마크한 글</a></div>
            	</div>
                <div class="board__search align-center">
                    <div class="search__key align-center">
  	                	<div class="search__selectCover">
                            <select class="search__select">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="member_nickname">작성자</option>
                            </select>
                            <span class="search__arrow"><i class="fa-solid fa-chevron-down"></i></span>
                        </div>
                    </div>
                    <div class="search__value align-center">
                        <input type="text" placeholder="검색어를 입력해주세요.">
                    </div>
                    <div class="search__btnCover align-center">
                        <button class="search__btn bColorMainPink colorWhite">검색</button>
                    </div>
                </div>
            </div>
            <div class="board__body">
 				<div class="board__selectAndTotalCnt d-flex">
 					<div class="board__selectBtns">
 						<button class="board__allSelect">전체선택</button>
 						<button class="board__selectDelete">삭제</button>
 					</div>
 					<div class="board__postCnt">전체 <span class="postCnt__txt colorMainBlue"></span>건</div>
 				</div>
               
                <div class="board__postHeader align-center">
                	<div class="postHeader__chk">선택</div>
                    <div class="postHeader__seq">번호</div>
                    <div class="postHeader__title">제목</div>
                    <div class="postHeader__writeDate">작성일</div>
                    <div class="postHeader__view">조회수</div>
                    <div class="postHeader__recommend">추천수</div>
                    <div class="postHeader__file">파일</div>
                </div>
                <div class="board__posts">
                	<!-- <div class="board__post d-flex">
                		<div class="post__chkBox"><input type="checkbox" class="postChk"></div>
	                	<div class="post__seq">1</div>
	                	<div class="post__tacCover d-flex">
	               	 		<div class="post__titleAndCategory">
	               	 			<div class="post__titleAndReply d-flex">
	               	 				<div class="post__title">자유게시판 제목입니dddddsssssssssssssssssssdddddddddddddddd다.</div>
	               	 				<div class="post__reply">댓글 3</div>
	               	 				<div class="post__replyMini"><i class='fa-regular fa-comment align-center'></i>3</div>
	               	 			</div>
	               	 			
	                			<div class="post__category">게시판: 자유게시판</div>
	                		</div>
	                	</div>
	                	<div class="post__writeDate">2023-12-08</div>
	                	<div class="post__viewCount">10</div>
	                	<div class="post__recomCount">10</div>
	                	<div class="post__isFile"><i class='fa-solid fa-paperclip'></i></div>
                	</div>       -->  	
                </div>
            </div>
            <div class="board__footer d-flex">
                <div class="board__pagination align-center"></div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>