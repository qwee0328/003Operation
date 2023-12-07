<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키오스크</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/kiosk/kiosk.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/kiosk/kiosk.css">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
	<div class="container">
        <div class="guide">
            <div class="kiosk__kioskStep">[ ${info.play_stage}단계 ]</div>
            <div class="kiosk__kioskName" data-id="${info.kiosk_category_id}">${info.name}</div>
<%--             <c:choose>
            	<c:when test="${info.is_game}">
           			<div class="kiosk__progressBar d-flex">
		                <div class="progressBar__name">진행도</div>
		                <div class="progressBar__bar"><div class="progressBar__fill"></div></div>
		                <div class="progressBar__per">100%</div>
		            </div>
            	</c:when>
            </c:choose> --%>
            <hr class="kiosk__line">
            <div class="kiosk__area">
            	<iframe id="kioskFrame" title="kiosk frame" width="100%" height="100%" src="https://kiosk003.github.io/${info.url}"></iframe>
            </div>
            
            <!-- 기록 영역 ( 게임용 ) -->
            <c:choose>
            	<c:when test="${info.is_game}">
           			<div class="kiosk__myRecord">
		                <div class="kiosk__recordName">내 최고 기록</div>
		                <div class="myRecord__cover">
		                    <div class="myRecord__record d-flex">
		                        <div class="myRecord__step align-center">1단계</div>
		                        <div class="myRecord__time step1 align-center">-</div>
		                        <div class="myRecord__ranking step1 align-center">-</div>
		                    </div>
		                    <div class="myRecord__record d-flex">
		                        <div class="myRecord__step align-center">2단계</div>
		                        <div class="myRecord__time step2 align-center">-</div>
		                        <div class="myRecord__ranking step2 align-center">-</div>
		                    </div>
		                    <div class="myRecord__record d-flex">
		                        <div class="myRecord__step align-center">3단계</div>
		                        <div class="myRecord__time step3 align-center">-</div>
		                        <div class="myRecord__ranking step3 align-center">-</div>
		                    </div>
		                </div>
		            </div>
            	</c:when>
            </c:choose>
          
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>