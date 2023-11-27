<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/mypage/mypageMyInfo.css">
<link rel="stylesheet" href="/css/mypage/mypageUpdateInfo.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="/js/mypage/mypageUpdateInfo.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
    <div class="container">
        <div class="body__guide">
            <div class="body__title">회원 정보</div>
            <div class="body__infoCover">
                <div class="body__info">
                    <div class="info__firstLine d-flex">
                        <div class="info__profileImg">
                            <img src="/images/profileImg.png" class="profileImg__img" alt="프로필 이미지">
                            <div class="profileImg__icon"><i class="fa-solid fa-pen"></i></div>
                        </div>
                        <div class="info__nickname info__cover">
                            <div class="nickname__title info__title">닉네임</div>
                            <div class="nickname__nickname info__content d-flex">
                                <input type="text" value="현재 닉네임" class="info__inputNick">
                                <div><button class="info__modifyBtn">수정</button></div>
                            </div>
                            <div class="info__explain info__dupResult">&nbsp;</div>
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
                                <div class="info__content d-flex">
                                    <input type="text" value="010-9256-1248" class="info__input">
                                    <div><button class="info__modifyBtn">수정</button></div>
                                </div>
                                <div class="info__explain info__compareResult">&nbsp;</div>
                            </div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">이메일</div>
                                <div class="info__content d-flex">
                                    <input type="text" value="teolaegi@003oper.com" class="info__input">
                                    <div><button class="info__modifyBtn">수정</button></div>
                                </div>
                                <div class="info__explain info__compareResult">&nbsp;</div>
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


                    <div class="info__pwModify">
                        <div class="pwModify__title d-flex">비밀번호 변경 <div><button class="info__modifyBtn">수정</button></div></div>
                        <div class="pwModify__explain">8~30자의 영문 대소문자, 숫자 및 특수문자를 사용하세요.</div>

                        <div class="pwModify__inputPw">
                            <div class="inputPw__title">변경할 비밀번호</div>
                            <div class="inputPW__content"><input type="password" id="password" name="password" class="inputPw__pw" placeholder="비밀번호를 입력하세요."></div>
                            <div class="inputPw__explain inputPw__regResult">&nbsp;</div>
                        </div>

                        <div class="pwModify__inputPw">
                            <div class="inputPw__title">변경할 비밀번호 확인</div>
                            <div class="inputPW__content"><input type="password" id="password_confirm" name="password_confirm" class="inputPw__pw" placeholder="비밀번호를 입력하세요."></div>
                            <div class="inputPw__explain inputPw__compareResult">&nbsp;</div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="body__footerBtn align-center">
                <button class="footerBtn__back">돌아가기</button>
            </div>
          
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
</body>
</html>