<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 확인</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/mypage/mypageMyInfo.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="/js/mypage/mypageMyInfo.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	  <div class="container">
        <div class="body__guide">
            <div class="body__title d-flex">회원 정보</div>
            <div class="body__infoCover">
                <div class="body__info">
                    <div class="info__firstLine d-flex">
                        <div class="info__profileImg">
                            <img src="/images/profileImg.png" class="profileImg__img" alt="프로필 이미지">
                        </div>
                        <div class="info__nickname info__cover">
                            <div class="nickname__title info__title">닉네임</div>
                            <div class="nickname__nickname info__content">현재 닉네임</div>
                        </div>
                    </div>
            
                    <div class="info__line d-flex">
                        <div class="info__halfLine">
                            <div class="info__cover">
                                <div class="info__title">아이디</div>
                                <div class="info__content">teolaegi</div>
                            </div>
                        </div>
                        <div class="info__margin"></div>
                        <div class="info__halfLine info__halfLineBottom">
                            <div class="info__title">이름</div>
                            <div class="info__content">터래기</div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__halfLine">
                            <div class="info__cover">
                                <div class="info__title">성별</div>
                                <div class="info__content">여성</div>
                            </div>
                        </div>
                        <div class="info__margin"></div>
                        <div class="info__halfLine info__halfLineBottom">
                            <div class="info__title">생년월일</div>
                            <div class="info__content">2020년 05월 06일생</div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">휴대전화</div>
                                <div class="info__content">010 - 9256 - 1248</div>
                            </div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">이메일</div>
                                <div class="info__content">teolaegi@003oper.com</div>
                            </div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">가입일자</div>
                                <div class="info__content">2023년 11월 01일 12:48</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body__footerBtn align-center">
                <button class="footerBtn__modify">수정하기</button>
            </div>
          
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>