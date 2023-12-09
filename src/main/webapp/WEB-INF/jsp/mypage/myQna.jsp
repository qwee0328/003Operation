<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 Q&A 글</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/mypage/myQna.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/css/mypage/myQna.css"/>
<link rel="stylesheet" href="/css/commons/common.css"/> 
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
    <div class="container">
        <div class="guide">
         	<div class="board__header">
                <div class="board__title align-center">내 Q&A</div>
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
                    <div class="postHeader__anwserState">답변상태</div>
                    <div class="postHeader__writeDate">작성일</div>
                    <div class="postHeader__file">파일</div>
                </div>
                <div class="board__posts"></div>
            <div class="board__footer d-flex">
                <div class="board__pagination align-center"></div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>