<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css" />

<link rel="stylesheet" href="/css/mypage/mypagePoint.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
	<div class="container">
		<div class="guide">
			<div class="userInfo">
				<div class="title">내 적립 포인트</div>
				<div class="pointInfo">1,506 P</div>
				<div class="nextLevel">다음 레벨까지 남은 포인트 <span class="nextPoint">1,000 p</span></div>
			</div>
			<div class="pointTable">
				<div class="pointTable__header">
					<div class="header__th">상태</div>
					<div class="header__th">적립내용</div>
					<div class="header__th">포인트</div>
					<div class="header__th">적립일</div>
				</div>
				<div class="pointTable__body">
					<div class="body__line">
						<div class="body__td">적립</div>
						<div class="body__td">게임 플레이 - 키오스크명 n 단계</div>
						<div class="body__td">+1,000 P</div>
						<div class="body__td">2023-10-23</div>
					</div>
				</div>
			</div>
			<div class="pagination"></div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
</body>
</html>