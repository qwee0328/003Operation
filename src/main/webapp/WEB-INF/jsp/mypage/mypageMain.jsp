<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/commons/common.css" />
<link rel="stylesheet" href="/css/mypage/mypageMain.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="/js/mypage/mypageMain.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
	<div class="container">
		<div class="profile">
			<div class="profile__cover">
				<div class="profile__img align-center">
					<c:choose>
                   		<c:when test="${empty info.profile_image}">
                   			<img src="/images/profileImg.png" class="profileImg__img" alt="프로필 이미지">
                   		</c:when>
                   		<c:otherwise>
                   			<img src="/profileImgs/${info.profile_image}" class="profileImg__img" alt="프로필 이미지">
                   		</c:otherwise>
                   	</c:choose>
				</div>
				<div class="profile__nickname align-center">
					${loginNickName}&nbsp;&nbsp;<i class="fa-solid fa-chevron-right colorGray goMypage"></i>
				</div>
				<div class="profile__logoutBtnCover align-center">
					<button class="profile__logoutBtn bColorWhite">로그아웃</button>
				</div>
			</div>
		</div>



		<div class="mypageMenu">
			<div class="mypageMenu__cover">
				<div class="profile__md">
					<div class="profile__cover">
						<div class="profile__img align-center">
							<c:choose>
                        		<c:when test="${empty info.profile_image}">
                        			<img src="/images/profileImg.png" class="profileImg__img" alt="프로필 이미지">
                        		</c:when>
                        		<c:otherwise>
                        			<img src="/profileImgs/${info.profile_image}" class="profileImg__img" alt="프로필 이미지">
                        		</c:otherwise>
                        	</c:choose>
						</div>
						<div class="profile__nickname align-center">
							${loginNickName}&nbsp;&nbsp;<i class="fa-solid fa-chevron-right colorGray goMypage"></i>
						</div>
						<div class="profile__logoutBtnCover align-center">
							<button class="profile__logoutBtn bColorWhite">로그아웃</button>
						</div>
					</div>
				</div>
			<%-- 	<div class="profile__mini">
					<div class="profile__cover">
						<div class="profile__img align-center">
							<img src="/images/profileImg.png">
						</div>
						<div class="profile__nickname align-center">
							${loginNickName}&nbsp;&nbsp;<i class="fa-solid fa-chevron-right colorGray"></i>
						</div>
						<div class="profile__logoutBtnCover align-center">
							<button class="profile__logoutBtn bColorWhite">로그아웃</button>
						</div>
					</div>
				</div> --%>
				<div class="mypageMenu__top">
					<div class="mypageMenu__topLeft">
						<div class="topLeft__cover">
							<div class="mypageMenu__level d-flex">
								<div class="level__icon d-flex">
									<i class="fa-solid fa-award colorGray"></i>
									<div class="icon__txt colorGray">레벨</div>
								</div>
								<div class="level__txt colorGray">Lv.${info.level_id}</div>
							</div>

							<div class="mypageMenu__point d-flex">
								<div class="point__icon d-flex">
									<i class="fa-solid fa-coins colorGray"></i>
									<div class="icon__txt colorGray">포인트</div>
								</div>
								<div class="point__txt colorGray">${info.point} P</div>
							</div>
						</div>
					</div>
					<!-- <div class="mypageMenu__topRight">
						<div class="topRight__cover">
							<div class="title__top">
								<div class="title__icon d-flex">
									<i class="fa-solid fa-chess-queen colorGray"></i>
									<div class="icon__txt colorGray">칭호</div>
								</div>
								<div class="title__modify colorGray">
									변경하기&nbsp;&nbsp;<i class="fa-solid fa-chevron-right colorGray"></i>
								</div>
							</div>
							<div class="title__bottom align-center colorGray">현재 선택한
								칭호명</div>
						</div>
					</div> -->
				</div>
			</div>
			<div class="mypageMenu__bottom">
				<div class="mypageMenu__myActivity">
					<div class="myActivity__title">나의 활동</div>
					<div class="myActivity__menu">
						<div class="myActivity__menu1 d-flex">					
							<div class="myActivity__game d-flex">
								<a href="#">
									<div class="myActivity__icon">
										<i class="fa-solid fa-gamepad colorGray"></i>
									</div>
									<div class="myActivity__txt colorGray">키오스크 게임 이용 내역</div>
									<div class="myActivity__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>
								</a>
							</div>
							<div class="myActivity__point d-flex">
								<a href="#">
									<div class="myActivity__icon">
										<i class="fa-solid fa-coins colorGray"></i>
									</div>
									<div class="myActivity__txt colorGray">포인트 적립 내역</div>
									<div class="myActivity__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>
								</a>
							</div>				
						</div>
						<div class="myActivity__menu2 d-flex">
							<div class="myActivity__game d-flex">
								<a href="#">
									<div class="myActivity__icon">
										<i class="fa-solid fa-file colorGray"></i>
									</div>
									<div class="myActivity__txt colorGray">작성한 게시글 내역</div>
									<div class="myActivity__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>
								</a>
							</div>						
							<div class="myActivity__point d-flex">	
								<a href="#">
									<div class="myActivity__icon">
										<i class="fa-solid fa-bookmark colorGray"></i>
									</div>
									<div class="myActivity__txt colorGray">게시글 북마크 내역</div>
									<div class="myActivity__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>		
								</a>						
							</div>					
						</div>
						<div class="myActivity__menu3 d-flex">
							<div class="myActivity__game d-flex">
								<a href="#">
									<div class="myActivity__icon">
										<i class="fa-solid fa-comment-dots colorGray"></i>
									</div>
									<div class="myActivity__txt colorGray">작성한 댓글 내역</div>
									<div class="myActivity__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>
								</a>
							</div>						
						</div>
					</div>
				</div>

				<div class="mypageMenu__myAsk">
					<div class="myAsk__title">나의 문의 내역</div>
					<div class="myAsk__menu">
						<div class="myAsk__menu1 d-flex">					
							<div class="myAsk__qna d-flex">
								<a href="#">
									<div class="myAsk__icon">
										<i class="fa-solid fa-circle-question colorGray"></i>
									</div>
									<!-- <div class="myAsk__txt colorGray">Q&A 문의 내역</div> -->
									<div class="myAsk__txt colorGray">Q&A 문의 내역</div>
									<div class="myAsk__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>
								</a>
							</div>						
						</div>
					</div>
				</div>

				<div class="mypageMenu__myInfo">
					<div class="myInfo__title">나의 정보</div>
					<div class="myInfo__menu">
						<div class="myInfo__menu1 d-flex">
							<div class="myInfo__setting d-flex">
								<a href="/member/viewMypage">
									<div class="myInfo__icon">
										<i class="fa-solid fa-gear colorGray"></i>
									</div>
									<div class="myInfo__txt colorGray">개인 정보 확인 및 수정</div>
									<div class="myInfo__move">
										<i class="fa-solid fa-chevron-right colorGray"></i>
									</div>		
								</a>						
							</div>							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
</body>

</html>