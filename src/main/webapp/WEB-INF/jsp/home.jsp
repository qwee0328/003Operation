<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/index.css" />
</head>
<body>
<div class="container-fluid p-0">
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	<div class="container">
        <div class="banner">
            <div class="index_guide">
                <div class="banner__contents">
                    <div class="contents__title">키오스크의 첫 걸음은 logo와 함께!</div>
                    <div class="contents__sub">키오스크를 처음 사용하는 사용자도 logo를 통해 누구나 쉽고 재미있게!!<br>
                        메뉴 주문, 구성 변경, 포인트 적립까지 단계별로<br>
                        키오스크 이용 실력을 향상 시켜보세요.</div>
                    <div class="kioskBtns">
                        <a href=""><button class="bannerBtn">연습하러가기</button></a>
                        <a href=""><button class="bannerBtn">게임하러가기</button></a>

                    </div>
                </div>
                <div class="banner_img">
                    <img src="/images/indexImg.png" alt="">
                </div>
            </div>
        </div>
        <div class="index_guide">

        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</div>
</body>
</html>