<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
<c:choose>
	<c:when test="${not empty isUpdate}">
		회원 정보 수정
	</c:when>
	<c:otherwise>
		개인 정보 확인
	</c:otherwise>
</c:choose>
</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/mypage/mypageMyInfo.css">
<link rel="stylesheet" href="/css/mypage/mypageUpdateInfo.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="/js/mypage/mypageMyInfo.js"></script>
<script src="/js/mypage/mypageUpdateInfo.js"></script>
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
                            <c:choose>
								<c:when test="${not empty isUpdate}">
									<div class="profileImg__icon"><i class="fa-solid fa-pen"></i></div>
								</c:when>
							</c:choose>
                        </div>
                        <div class="info__nickname info__cover">
                            <div class="nickname__title info__title">닉네임</div>
                            <div class="nickname__nickname info__content d-flex">
	                            <c:choose>
									<c:when test="${not empty isUpdate}">
											<input type="text" value="${info.nickname}" class="info__inputNick">
			                           	    <div><button class="info__modifyBtn" data-id="nickname" disabled>수정</button></div>
		                           		</div>
		                           	     <div class="info__explain info__dupResult">&nbsp;</div>
									</c:when>
									<c:otherwise>
										${info.nickname}</div>
									</c:otherwise>
								</c:choose>	
                        </div>
                    </div>
            
                    <div class="info__line d-flex">
                        <div class="info__halfLine">
                            <div class="info__cover">
                                <div class="info__title">아이디</div>
                                <div class="info__content">${info.id}</div>
                            </div>
                        </div>
                        <div class="info__margin"></div>
                        <div class="info__halfLine info__halfLineBottom">
                            <div class="info__title">이름</div>
                            <div class="info__content">${info.name}</div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__halfLine">
                            <div class="info__cover">
                                <div class="info__title">성별</div>
                                <div class="info__content">${info.formedGender}</div>
                            </div>
                        </div>
                        <div class="info__margin"></div>
                        <div class="info__halfLine info__halfLineBottom">
                            <div class="info__title">생년월일</div>
                            <div class="info__content">${info.formedBirthday}</div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">휴대전화</div>
                                <div class="info__content d-flex">
                                <c:choose>
									<c:when test="${not empty isUpdate}">
		                                    <input type="text" value="${info.phone}" class="info__input info__phone">
		                                    <div><button class="info__modifyBtn" data-id="phone" disabled>수정</button></div>
		                                </div>
		                                <div class="info__explain info__compareResult info__phoneRegexResult">&nbsp;</div>
									</c:when>
									<c:otherwise>
										${info.phone}</div>
									</c:otherwise>
								</c:choose>	
                            </div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">이메일</div>
                                <div class="info__content d-flex">
                                 <c:choose>
									<c:when test="${not empty isUpdate}">
		                                   <input type="text" value="${info.email}" class="info__email info__input">
		                                    <div><button class="info__modifyBtn" data-id="email" disabled>수정</button></div>
		                                </div>
		                                <div class="info__explain info__compareResult info__emailRegexResult">&nbsp;</div>
									</c:when>
									<c:otherwise>
										${info.email}</div>
									</c:otherwise>
								</c:choose>	
                            </div>
                        </div>
                    </div>
                    <div class="info__line d-flex">
                        <div class="info__fullLine">
                            <div class="info__cover">
                                <div class="info__title">가입일자</div>
                                <div class="info__content">${info.formedSignup_date}</div>
                            </div>
                        </div>
                    </div>
                    
                    
                     <c:choose>
						<c:when test="${not empty isUpdate}">
							<div class="info__pwModify">
		                        <div class="pwModify__title d-flex">비밀번호 변경 <div><button class="info__modifyBtn" data-id="pw" disabled>수정</button></div></div>
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
						</c:when>
					</c:choose>	
                    
                </div>
            </div>
             <div class="body__footerBtn align-center">
	           <c:choose>
					<c:when test="${not empty isUpdate}">
						<button class="footerBtn__back">돌아가기</button>		            
					</c:when>
					<c:otherwise>
						<button class="footerBtn__modify">수정하기</button>
					</c:otherwise>
				</c:choose>	
			</div>
		
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>