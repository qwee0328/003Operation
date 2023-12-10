<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<input type="hidden" class="is_game" value="${info.is_game}">
	<div class="container">
        <div class="guide">
            <div class="kiosk__kioskStep">[ ${info.play_stage}단계 ]</div>
            <div class="kiosk__kioskName" data-id="${info.kiosk_category_id}">${info.name}</div>
            <hr class="kiosk__line">
            <div class="kiosk__area">
            	<iframe id="kioskFrame" title="kiosk frame" width="100%" height="100%" src="https://kiosk003.github.io/${info.url}"></iframe>
            </div>
            <c:choose>
            	<c:when test="${info.is_game == true}">
            		<c:choose>
		            	<c:when test="${not empty loginID}">
		            		<script>/*getBestRecord();*/</script>
		            	</c:when>
		            </c:choose>
            	</c:when>
            </c:choose>
			<script type="text/javascript">
	        	$(document).ready(function() {
	        		$(".kiosk__area").css({"width":(${info.width}+20+"px"),"height":(${info.height}+60+"px")});

	        		$('#kioskFrame').on('load', function() {
					$.ajax({
							url: "/member/userId",
							type: "post",
							async:"false"
						}).done(function(resp) {
							var iframe = document.querySelector('iframe');
							iframe.contentWindow.postMessage(JSON.stringify(resp), 'https://kiosk003.github.io/');
						})
					});
				})
            </script>
            <!-- 기록 영역 ( 게임용 ) -->
            <!-- 
            <c:choose>
            	<c:when test="${info.is_game}">
            		<c:choose>
            			<c:when test="${not empty loginID}">
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
            	</c:when>
            </c:choose>
           -->
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>