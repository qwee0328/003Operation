<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 게임 기록</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css" />

<link rel="stylesheet" href="/css/mypage/myGameRecord.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
	<div class="container">
		<div class="title">나의 게임 기록</div>
		<div class="recent">
			<div class="subTitle">최신 게임 기록</div>
			<div class="selectBox">
				<div class="selectorType">
					<div class="typeName">선택해주세요.</div>
					<div class="selectorArrow">
						<i class="fa-solid fa-chevron-down"></i>
					</div>
				</div>
				<div class="selector__option">
					<div class="option__item">010</div>
					<div class="option__item">011</div>
					<div class="option__item">016</div>
					<div class="option__item">017</div>
					<div class="option__item">018</div>
					<div class="option__item">019</div>
					<div class="option__item" value="directInput">직접입력</div>
				</div>
			</div>
			<div class="recordTable">
				<div class="recordTalbe__header">
					<div class="record__th">키오스크 카테고리</div>
					<div class="record__th">단계</div>
					<div class="record__th">기록</div>
					<div class="record__th">시작시간</div>
				</div>
				<div class="recordTable__body">
					<div class="body__line">
						<div class="body__td">카페</div>
						<div class="body__td">3단계</div>
						<div class="body__td">-</div>
						<div class="body__td">시작시간</div>
					</div>
				</div>
			</div>
		</div>
		<div class="shortest">
			<div class="subTitle">최단 게임 기록</div>
			<div class="recordTable">
				<div class="recordTalbe__header">
					<div class="record__th">키오스크 카테고리</div>
					<div class="record__th">단계</div>
					<div class="record__th">최단기록</div>
					<div class="record__th">상위</div>
				</div>
				<div class="recordTable__body">
					<div class="body__line">
						<div class="body__td">카페</div>
						<div class="body__td">3단계</div>
						<div class="body__td">-</div>
						<div class="body__td">시작시간</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
</body>
</html>