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
<link rel="stylesheet" href="/css/commons/common.css"/>

<link rel="stylesheet" href="/css/board/viewPost.css">
<link rel="stylesheet" href="/css/qna/qnaAnswerWrite.css"/>

<script src="/js/qna/viewQna.js"></script>
</head>
<body>
 <div class="container__body">
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
    <div class="boardPost_guide">
        <div class="boardType">Q&A 게시판</div>
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
            	<input type="hidden" value="${post.question.id}" id="postId">

                <div class="postTitle">
                    ${post.question.title }
                </div>
                <div class="postInfo">
                    <div class="postInfo__left">
                        <div class="postInfo__profile">
                        	<input type="hidden" value="${post.question.member_id }" id="writerId">
                            <img src="" alt="프로필 이미지" id="writer_profile">
                        </div>
                        <div class="postInfo__userInfo">
                            <div class="userInfo__nickName fontEN">${post.question.member_nickname }</div>
                            <div class="userInfo__writeInfo">
                                <span class="writeInfo__title">작성일</span>
                                <span>${post.question.write_date }</span>
                                <span class="writeInfo__title">조회수</span>
                                <span>${post.question.view_count }</span>
                            </div>
                        </div>
                    </div>
                    <div class="postInfo__right">
                        <button id="questionUpdate" data-id="${post.question.id }">수정</button>
                        <button id="postDelete" data-id="${post.question.id }">삭제</button>
                    </div>
                </div>
                <div class="postConf">${post.question.content }
                </div>
                <div class="files">
                    <div class="files__title">
                        첨부파일
                    </div>
                    <div class="files__conf">
						post.question.file_names, post.question.file_ids 에 ,로 구분되어 파일 목록 불러오니 참고해서 사용 !
                    </div>
                </div>
                <div class="pageBtns">

                </div>
            </div>
        </div>
    	<div class="qnaAnswerWrite writeBox_guide">
    	<c:choose>
    		<c:when test="${empty post.answer}">
	    		<div class="qnaAnswerWrite__title"><b>Q&A 게시글 답변 작성</b></div>
	    		<hr class="qnaAnswerWrite__hr">
	    		<div class="qnaAnswerWrite__file">    		
		   			<div class="d-flex">
						<label class="fileInput__label" for="fileInput">파일 첨부</label>
						<div class="fileNameList d-flex">
							<c:choose>	
								<c:when test="${not empty post.answer.file_names}">										
									<script>
										// 기존 파일 목록
										let fileNames = `${post.answer.file_names}`.split(",");
										let fileIds = `${post.answer.file_ids}`.split(",");
										
										for(let i=0; i<fileNames.length; i++){
											let fileNameTag = $("<div>").attr("class","fileNameTag d-flex");
											let fileName = $("<div>").attr("class","fileName").text(fileNames[i]);
											let fileIcon = $("<div>").attr("class","ml5").html("<i class='fa-solid fa-xmark deleteFileBtn' data-seq="+fileIds[i]+"></i>")
											$(".fileNameList").append(fileNameTag.append(fileName).append(fileIcon))		
										}
									</script>								
								</c:when>								
							</c:choose>							
						</div>
					</div>
					<input type="file" class="qnaAnswerWrite__fileInput" id="fileInput" multiple>
				</div>
	    		<%@ include file="/WEB-INF/jsp/commons/summernote.jsp" %>
	    		<div class="qnaAnswerWrite__btns d-flex">
	    			<button class="goList bColorGray colorWhite">목록으로</button>
	    			<button class="write bColorMainPink colorWhite" data-id="${post.question.id}">작성완료</button>
	    		</div>
	    	
   			</c:when>
    		
    		<c:otherwise>
				답변내용
            <button class="answerUpdate" data-id="${post.question.id}">수정</button>
                  
    		</c:otherwise>
    	</c:choose>
    	</div>
    </div>
    
    
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</div>
</body>
</html>