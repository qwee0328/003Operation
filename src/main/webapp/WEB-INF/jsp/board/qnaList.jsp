<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/board/qnaListLoad.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/css/board/qnaList.css"/>
<link rel="stylesheet" href="/css/commons/common.css"/> 
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
    <div class="container">
        <div class="guide">
            <div class="board__header">
                <div class="board__title align-center">Q&A 게시판</div>
                <div class="board__serach align-center">
                    <div class="search__key align-center">
                        <div class="search__selectCover">
                            <select class="search__select">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="writer">작성자</option>
                            </select>
                            <span class="search__arrow"><i class="fa-solid fa-chevron-down"></i></span>
                        </div>
                    </div>
                    <div class="search__value align-center">
                        <input type="text" placeholder="검색어를 입력해주세요.">
                    </div>
                    <div class="search__btnCover  align-center">
                        <button class="search__btn bColorMainPink colorWhite">검색</button>
                    </div>
                </div>
            </div>
            <div class="board__body">
                <div class="board__postCnt">전체 <span class="colorMainBlue">20건</span></div>
                <div class="board__postHeader align-center">
                    <div class="postHeader__seq">번호</div>
                    <div class="postHeader__title">제목</div>
                    <div class="postHeader__anwserState">답변상태</div>
                    <div class="postHeader__writer">작성자</div>
                    <div class="postHeader__writeDate">작성일</div>
                    <div class="postHeader__file">파일</div>
                </div>
                <div class="board__posts"></div>
            <div class="board__footer d-flex">
                <div class="board__pagination align-center">
                   
                </div>
                <div class="board__writeBtnCover"><button class="board__writeBtn bColorMainPink colorWhite">글쓰기</button></div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>