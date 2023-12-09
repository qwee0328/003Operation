<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>003</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css"/>

<!-- index js/css -->
<link rel="stylesheet" href="/css/index.css" />
<script type="text/javascript" src="/js/index.js"></script>
</head>
<body>
<div class="container-fluid p-0">
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	<div class="container">
        <div class="banner">
            <div class="banner_guide">
                <div class="banner__contents">
                    <div class="contents__title">키오스크의 첫 걸음은 <span class="fontLogo">003</span>과 함께!</div>
                    <div class="contents__sub">키오스크를 처음 사용하는 사용자도 <span class="fontLogo">003</span>을 통해 누구나 쉽고 재미있게!!<br>
                        메뉴 주문, 구성 변경, 포인트 적립까지 단계별로<br>
                        키오스크 이용 실력을 향상 시켜보세요.</div>
                    <div class="kioskBtns">
                        <a href="/kiosk/goKioskList/0"><button class="bannerBtn">연습하러가기</button></a>
                        <a href="/kiosk/goKioskList/1"><button class="bannerBtn">게임하러가기</button></a>

                    </div>
                </div>
                <div class="banner_img">
                    <img src="/images/indexImg.png" alt="">
                </div>
            </div>
        </div>
        <div class="index_guide">
			<div class="practice">
                <div class="practice__title">
                    카테고리별 연습으로<span class="title__br">기본부터 튼튼하게</span>
                </div>
                <div class="practice__contents"></div>
                <div class="practice__contents-tab"></div>
            </div>
            <div class="popular">
                <div class="popular__title">
                    실시간 인기 카테고리
                   	<button><i class="fa-solid fa-arrow-rotate-right realTimeRanking"></i></button>
                </div>
                <div class="popular__contents"></div>
            </div>
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</div>
</body>
</html>