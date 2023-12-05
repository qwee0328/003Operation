<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키오스크 목록</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/kiosk/kioskList.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/kiosk/kioskList.css">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	<input type="hidden" class="is_game" value="${is_game}">
	<div class="container">
		<div class="guide">
			<div class="kioskCategory__practice">
				<div class="kioskCategory__name">키오스크 카테고리</div>
				<div class="kioskCategory__selectCover d-flex">
					<select class="kioskCateogy__select">
						<option value="name">이름 순</option>
						<option value="playCnt">최다 플레이 순</option>
					</select>
				</div>
				<div class="kioskCategory__kioskList"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>