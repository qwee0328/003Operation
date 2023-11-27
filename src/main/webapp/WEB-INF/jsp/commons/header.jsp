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
				<div class="logo fontEN colorWhite">logo</div>
				<div class="navBar">
					<div class="practice">
						<a href="/practice/listPractice" class="colorWhite">키오스크 연습</a>
					</div>
					<div class="game">
						<a href="#" class="colorWhite">키오스크 게임</a>
					</div>
					<div class="board" id="board">
						<a href="#" class="colorWhite">게시판&nbsp;<i
							class="fa-solid fa-chevron-down colorWhite"></i></a>
						<ul class="bColorLightBlue" id="boradMenu">

							<li><a href="#">자유게시판</a></li>
							<li><a href="#">질문게시판</a></li>


							<li><a href="#">자주 묻는 질문</a></li>


							<li><a href="#">Q&A</a></li>

						</ul>
					</div>
				</div>
			</div>

			<div class="header_right">
				<input type="button" class="blueBtn" value="로그인"></input>
				<input type="button" class="blueBtn signupBtn" value="회원가입"></input>
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
							<input type="button" class="blueBtn" value="로그인"></input>
							<input type="button" class="blueBtn signupBtn" value="회원가입"></input>
						</div>
					</div>
					<div class="tabNab_body">
						<div class="ham-practice"><a href="#">키오스크 연습</a></div>
						<div class="ham-game"><a href="#">키오스크 게임</a></div>
						<div class="ham-board"><a href="#">게시판</a></div>
						<div class="ham-board-sub"><a href="#">자유게시판</a></div>
						<div class="ham-board-sub"><a href="#">질문게시판</a></div>
						<div class="ham-board-sub"><a href="#">자주 묻는 질문</a></div>
						<div class="ham-board-sub"><a href="#">Q&A</a></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>