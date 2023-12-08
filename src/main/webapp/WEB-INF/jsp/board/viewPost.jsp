<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${post.title }</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="stylesheet" href="/css/commons/common.css" />

<link rel="stylesheet" href="/css/board/viewPost.css">
<script type="text/javascript" src="/js/board/viewPost.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
		<div class="boardPost_guide">
			<div class="boardType">${type }게시판</div>
			<input type="hidden" id="boardType" value="${type }"> <input
				type="hidden" id="select" value="${select }"> <input
				type="hidden" id="keyword" value="${keyword }">
			<div class="boardPost">
				<div class="naviBackground">
					<div class="stickyContainer">
						<div class="boardPost__navi">
							<input type="hidden" id="myRecommendRecord"> <input
								type="hidden" id="myBookmarkRecord">
							<c:if test="${post.bulletin_category_id eq 'free' }">
								<div class="navi__conf">
									<div class="conf__circle" id="recommendBtn">
										<i class="fa-regular fa-thumbs-up"></i>
										<div>
											추천 <span class="conf__miniCount recommendCount"></span>
										</div>
									</div>
									<div class="conf__count recommendCount"></div>
								</div>
								<div class="navi__conf">
									<div class="conf__circle" id="bookmarkBtn">
										<i class="fa-regular fa-bookmark"></i>
										<div>
											북마크<span class="conf__miniCount bookmarkCount"></span>
										</div>
									</div>
									<div class="conf__count bookmarkCount"></div>
								</div>
								<div class="navi__conf">
									<div class="conf__circle">
										<i class="fa-regular fa-comment"></i>
										<div>
											댓글<span class="conf__miniCount replyCount"></span>
										</div>
									</div>
									<div class="conf__count replyCount"></div>
								</div>
							</c:if>
							<div class="navi__conf">
								<div class="conf__circle" id="boardListBtn">
									<i class="fa-solid fa-list"></i>
									<div>목록</div>
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="boardPost__guide">
					<input type="hidden" value="${post.id }" id="postId">

					<div class="postTitle">${post.title }</div>
					<div class="postInfo">
						<div class="postInfo__left">
							<div class="postInfo__profile">
								<input type="hidden" value="${post.member_nickname }" id="memberNickname">
								<img src="" alt="프로필 이미지" id="writer_profile">
							</div>
							<div class="postInfo__userInfo">
								<div class="userInfo__nickName fontEN">${post.member_nickname }</div>
								<div class="userInfo__writeInfo">
									<span class="writeInfo__title">작성일</span> <span>${post.timeCal }</span>
									<span class="writeInfo__title">조회수</span> <span>${post.view_count }</span>
								</div>
							</div>
						</div>
						<div class="postInfo__right"></div>
					</div>
					<div class="postConf">${post.content }</div>
					<div class="pageBtns"></div>
					<c:if test="${post.bulletin_category_id eq 'free' }">
						<div class="replyBox">
							<div class="replyBox__title">
								댓글 <span id="replyCount"></span>
							</div>
							<div class="replyBox__replyInput">
								<div class="replyInput__userProfile">
									<img alt="사용자 프로필" src="/images/profileImg.png"
										id="userProfileImg">
								</div>
								<div class="replyInput__input">
									<input type="text" id="replyInput" placeholder="댓글을 입력해주세요.">
									<button id="replyInputSubmit">입력</button>
								</div>
							</div>
							<div id="replyList">

								<div class="replyLine">
									<div class="replyWriterInfo">
										<div class="writerProfile">
											<img alt="" src="/images/profileImg.png"> <span>닉네임</span></span><span
												class="writer">글쓴이</span><span class="time">10시간전</span>
										</div>
										<div>
											<i class="fa-solid fa-ellipsis-vertical"></i>
										</div>
									</div>
									<div class="replyConf">댓글 내용</div>
									<div class="replyInfo">
										<span><i class="fa-regular fa-thumbs-up"></i> 추천수 1</span><span>답글달기</span><span>신고하기</span>
									</div>
									<!-- 
									<div class="rereplyInput">
										<div class="replyInput__userProfile">
											<img alt="사용자 프로필" src="/images/profileImg.png"
												id="userProfileImg">
										</div>
										<div class="replyInput__input">
											<input type="text" id="replyInput" placeholder="답글을 입력해주세요.">
											<button id="replyInputSubmit">입력</button>
										</div>
									</div>
								-->
								</div>
								<div class="RereplyLine">
									<div class="replyWriterInfo">
										<div class="writerProfile">
											<img alt="" src="/images/profileImg.png"> <span>닉네임</span></span><span
												class="writer">글쓴이</span><span class="time">10시간전</span>
										</div>
										<div>
											<i class="fa-solid fa-ellipsis-vertical"></i>
										</div>
									</div>
									<div class="replyConf">댓글 내용</div>
									<div class="replyInfo">
										<span><i class="fa-regular fa-thumbs-up"></i> 추천수 1</span>
									</div>
								</div>
							</div>
							<div id="pagination"></div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
	</div>
</body>
</html>