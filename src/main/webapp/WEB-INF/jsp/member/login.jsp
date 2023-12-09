<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css" />
<link rel="stylesheet" href="/css/member/login.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="/js/member/login.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
	<c:choose>
		<c:when test="${empty loginID}">
			<div class="container">
				<form method="post">
					<div class="guide">
						<div class="login__title">로그인</div>
						<div class="login__id d-flex">
							<div class="login__icon align-center">
								<i class="fa-regular fa-user"></i>
							</div>
							<input type="text" class="login__inputId" name="username"
								placeholder="아이디 입력">
						</div>
						<div class="login__pw d-flex">
							<div class="login__icon align-center">
								<i class="fa-solid fa-key"></i>
							</div>
							<input type="password" class="login__inputPw" name="password"
								placeholder="비밀번호 입력">
						</div>
						<div class="login__saveId d-flex">
							<div class="login__saveIdChk align-center">
								<input type="checkbox" class="login__saveIdChkBox">
							</div>
							<div class="login__saveIdTxt">아이디 기억하기</div>
						</div>
						<div class="login__btns">
							<button class="login__btn">로그인</button>
						</div>
						<div class="login__menu d-flex">
							<div class="login__findId align-center">아이디 찾기</div>
							<div class="login__findPw align-center">비밀번호 찾기</div>
							<div class="login__signup align-center">회원 가입</div>
						</div>
					</div>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<script>
				location.href = "/";
			</script>
		</c:otherwise>
	</c:choose>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
</body>
</html>