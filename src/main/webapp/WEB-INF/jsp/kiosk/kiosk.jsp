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
	<div class="container">
        <div class="guide">
            <div class="kiosk__kioskStep">[ 1단계 ]</div>
            <div class="kiosk__kioskName">키오스크 이름</div>
            <div class="kiosk__progressBar d-flex">
                <div class="progressBar__name">진행도</div>
                <div class="progressBar__bar"><div class="progressBar__fill"></div></div>
                <div class="progressBar__per">100%</div>
            </div>
            <div class="kiosk__area"></div>
        </div>
    </div>
</body>
</html>