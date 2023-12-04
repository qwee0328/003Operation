<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css" />
<link rel="stylesheet" href="/css/commons/header.css" />
<script type="text/javascript" src="/js/commons/header.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
</head>
<body>
	<div class="header bColorMainBlue">
		<div class="header_guide">
			<div class="header_left">
				<div class="logo fontLogo colorWhite">003</div>
				<div class="navBar">
					<div class="practice">
						<a href="/kiosk/goKioskList/0" class="colorWhite">키오스크 연습</a>
					</div>
					<div class="game">
						<a href="/kiosk/goKioskList/1" class="colorWhite">키오스크 게임</a>
					</div>
					<div class="board" id="board">
						<a href="/board/listBoard/free" class="colorWhite">게시판&nbsp;<i
							class="fa-solid fa-chevron-down colorWhite"></i></a>
						<ul class="bColorLightBlue" id="boradMenu">
							<li><a href="/board/listBoard/free">자유게시판</a></li>
							<li><a href="/board/listBoard/question">질문게시판</a></li>
					<!-- 		<li><a href="#">자주 묻는 질문</a></li> -->
							<li><a href="/qna/listBoard">Q&A</a></li>
						</ul>
					</div>
					<c:choose>
						<c:when test="${not empty loginID}">
							<div class="mypage" id="mypage">
								<a href="/member/goMypage" class="colorWhite">마이페이지&nbsp;<i
									class="fa-solid fa-chevron-down colorWhite"></i></a>
								<ul class="bColorLightBlue" id="mypageMenu">
									<li><a href="/member/viewMypage">내 정보</a></li>
									<li><a href="#">게임 기록</a></li>
									<li><a href="#">포인트 적립 내역</a></li>
									<li><a href="#">내 게시글</a></li>
								</ul>
							</div>
						</c:when>
					</c:choose>
					
				</div>
			</div>

			<div class="header_right">
				<c:choose>
					<c:when test="${empty loginID}">
						<input type="button" class="blueBtn loginBtn" value="로그인"></input>
						<input type="button" class="blueBtn signupBtn" value="회원가입"></input>
					</c:when>
					<c:otherwise>
						<input type="button" class="blueBtn logoutBtn" value="로그아웃"></input>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="hamNav" id="hamNav">
				<div></div>
				<div></div>
				<div></div>
			</div>
			<div class="tabNav">
				<div class="tabNav__contents">
					<div class="tabNav-header bColorMainBlue">
						<div class="xBtns" id="xBtns">
							<div></div>
							<div></div>
						</div>
						<div class="memberBtns">
							<c:choose>
								<c:when test="${empty loginID}">
									<input type="button" class="blueBtn loginBtn" value="로그인"></input>
									<input type="button" class="blueBtn signupBtn" value="회원가입"></input>
								</c:when>
								<c:otherwise>
									<input type="button" class="blueBtn logoutBtn" value="로그아웃"></input>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="tabNab_body">
						<div class="ham-practice"><a href="/kiosk/goKioskList/0">키오스크 연습</a></div>
						<div class="ham-game"><a href="/kiosk/goKioskList/1">키오스크 게임</a></div>
						<div class="ham-board"><a href="/board/listBoard/free">게시판</a></div>
						<div class="ham-board-sub"><a href="/board/listBoard/free">자유게시판</a></div>
						<div class="ham-board-sub"><a href="/board/listBoard/question">질문게시판</a></div>
<!-- 						<div class="ham-board-sub"><a href="#">자주 묻는 질문</a></div> -->
						<div class="ham-board-sub"><a href="/qna/listBoard">Q&A</a></div>
						
						<c:choose>
							<c:when test="${not empty loginID}">
								<div class="ham-mypage"><a href="/member/goMypage">마이페이지</a></div>
								<div class="ham-mypage-sub"><a href="/member/viewMypage">내 정보</a></div>
								<div class="ham-mypage-sub"><a href="#">게임 기록</a></div>
								<div class="ham-mypage-sub"><a href="#">포인트 적립 내역</a></div>
								<div class="ham-mypage-sub"><a href="#">내 게시글</a></div>
							</c:when>
						</c:choose>	
						

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>