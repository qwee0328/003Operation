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
            <div class="kiosk__kioskStep">[ 1단계 ]</div>
            <div class="kiosk__kioskName">키오스크 이름</div>
            <div class="kiosk__progressBar d-flex">
                <div class="progressBar__name">진행도</div>
                <div class="progressBar__bar"><div class="progressBar__fill"></div></div>
                <div class="progressBar__per">100%</div>
            </div>
            <div class="kiosk__area">
            	<iframe id="kioskFrame" title="kiosk frame" width="100%" height="100%" src="https://magiclampjin.github.io/unityKiostTest/"></iframe>
            </div>
            
            <!-- 기록 영역 ( 게임용 ) -->
            <div class="kiosk__myRecord">
                <div class="kiosk__recordName">내 최고 기록</div>
                <div class="myRecord__cover">
                    <div class="myRecord__record d-flex">
                        <div class="myRecord__step align-center">1단계</div>
                        <div class="myRecord__time align-center">1분 30초</div>
                        <div class="myRecord__ranking align-center">상위 50%</div>
                    </div>
                    <div class="myRecord__record d-flex">
                        <div class="myRecord__step align-center">1단계</div>
                        <div class="myRecord__time align-center">1분 30초</div>
                        <div class="myRecord__ranking align-center">상위 50%</div>
                    </div>
                    <div class="myRecord__record d-flex">
                        <div class="myRecord__step align-center">1단계</div>
                        <div class="myRecord__time align-center">1분 30초</div>
                        <div class="myRecord__ranking align-center">상위 50%</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>