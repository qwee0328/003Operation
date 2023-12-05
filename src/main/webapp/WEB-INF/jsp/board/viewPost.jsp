<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${post.title }</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css"/>

<link rel="stylesheet" href="/css/commons/common.css">
<link rel="stylesheet" href="/css/board/viewPost.css">
<script type="text/javascript" src="/js/board/viewPost.js"></script>
</head>
<body>
 <div class="container">
 <%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
        <div class="boardPost_guide">
            <div class="boardType">${type } 게시판</div>
            <input type="hidden" id="boardType" value="${type }">
            <input type="hidden" id="select" value="${select }">
            <input type="hidden" id="keyword" value="${keyword }">
            <div class="boardPost">
            	<div class="naviBackground">
	            	<div class="stickyContainer">
	            		<div class="boardPost__navi">
	            			<input type="hidden" id="myRecommendRecord">
	            			<input type="hidden" id="myBookmarkRecord">
		                    <div class="navi__conf">
		                        <div class="conf__circle" id="recommendBtn">
		                            <i class="fa-regular fa-thumbs-up"></i>
		                            <div>추천 <span class="conf__miniCount recommendCount"></span></div>
		                        </div>
		                        <div class="conf__count recommendCount"></div>
		                    </div>
		                    <div class="navi__conf">
		                        <div class="conf__circle" id="bookmarkBtn">
		                            <i class="fa-regular fa-bookmark"></i>
		                            <div>북마크<span class="conf__miniCount bookmarkCount"></span></div>
		                        </div>
		                        <div class="conf__count bookmarkCount"></div>
		                    </div>
		                    <div class="navi__conf">
		                        <div class="conf__circle">
		                            <i class="fa-regular fa-comment"></i>
		                            <div>댓글<span class="conf__miniCount replyCount"></span></div>
		                        </div>
		                        <div class="conf__count replyCount"></div>
		                    </div>
		                    <div class="navi__conf">
		                        <div class="conf__circle">
		                            <i class="fa-solid fa-arrow-up-right-from-square"></i>
		                            <div>공유</div>
		                        </div>
		                    </div>
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

                    <div class="postTitle">
                        ${post.title }
                    </div>
                    <div class="postInfo">
                        <div class="postInfo__left">
                            <div class="postInfo__profile">
                            	<input type="hidden" value="${post.member_id }" id="writerId">
                                <img src="" alt="프로필 이미지" id="writer_profile">
                            </div>
                            <div class="postInfo__userInfo">
                                <div class="userInfo__nickName fontEN">${post.member_nickname }</div>
                                <div class="userInfo__writeInfo">
                                    <span class="writeInfo__title">작성일</span>
                                    <span>${post.write_date }</span>
                                    <span class="writeInfo__title">조회수</span>
                                    <span>${post.view_count }</span>
                                </div>
                            </div>
                        </div>
                        <div class="postInfo__right">
                            <button id="postUpdate" data-id="${post.id }">수정</button>
                            <button id="postDelete" data-id="${post.id }">삭제</button>
                        </div>
                    </div>
                    <div class="postConf">${post.content }
                    </div>
                    <div class="files">
                        <div class="files__title">
                            첨부파일
                        </div>
                        <div class="files__conf">
                            
                        </div>
                    </div>
                    <div class="pageBtns">
                    <!-- 
                        <div class="pageBtns__pageLine" id="upPage">
                            <div class="pageLine__icon">
                                <i class="fa-solid fa-chevron-up"></i>
                                <div>이전글</div>
                            </div>
                            <div class="pageLine__title">이전글 제목</div>
                        </div>
                        <div class="pageBtns__pageLine" id="downPage">
                            <div class="pageLine__icon">
                                <i class="fa-solid fa-chevron-down"></i>
                                <div>이전글</div>
                            </div>
                            <div class="pageLine__title">다음글 제목</div>
                        </div>
                         -->
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
    </div>
</body>
</html>