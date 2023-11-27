<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 확인</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/mypage/mypageChkInfo.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="/js/mypage/mypageChkInfo.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
    <div class="container">
        <div class="body__guide">
            <div class="body__title">회원 정보 확인</div>
            <div class="body__content">
                <div class="content__title">비밀번호를 입력해주세요</div>
                <div class="content__explain d-flex">
                    <div>회원 정보 변경을 원하신다면&nbsp;</div><div>비밀번호를 입력해 본인 인증을 진행해주세요.</div>
                </div>
                
                <input type="password" name="password" class="content_pw">

                <div class="content__bottomBtn align-center">
                    <button class="bottomBtn__back">돌아가기</button>
                    <div class="bottomBtn__margin"></div>
                    <button class="bottomBtn__check">비밀번호 확인</button>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
</body>
</html>