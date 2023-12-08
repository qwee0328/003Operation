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
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="/js/summernote/summernote-ko-KR.js"></script>

<!-- 공통 css -->
<link rel="stylesheet" href="/css/commons/common.css" />

<link rel="stylesheet" href="/css/board/viewPost.css">
<link rel="stylesheet" href="/css/qna/qnaAnswerWrite.css" />

<script src="/js/qna/viewQna.js"></script>
</head>
<body>
   <div class="container__body">
      <%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
      <div class="boardPost_guide">
         <div class="boardType">Q&A 게시판</div>
         <div class="boardPost">
            <div class="naviBackground">
               <div class="stickyContainer">
                  <div class="boardPost__navi">
                     <input type="hidden" id="myRecommendRecord"> <input
                        type="hidden" id="myBookmarkRecord">
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

               <div class="postTitle">${post.question.title }</div>
               <div class="postInfo">
                  <div class="postInfo__left">
                     <div class="postInfo__profile">
                        <input type="hidden" value="${post.question.member_id }"
                           id="writerId"> <img src="" alt="프로필 이미지"
                           id="writer_profile">
                     </div>
                     <div class="postInfo__userInfo">
                        <input type="hidden" value="${post.question.member_nickname }"
                           id="memberNickname"> <input type="hidden"
                           value="${post.question.id }" id="qnaId">
                        <div class="userInfo__nickName fontEN">${post.question.member_nickname }</div>
                        <div class="userInfo__writeInfo">
                           <span class="writeInfo__title">작성일</span> <span>${post.question.timeCal }</span>
                        </div>
                     </div>
                  </div>
                  <div class="postInfo__right"></div>
               </div>
               <div class="postConf">${post.question.content }</div>
               <div class="pageBtns"></div>
            </div>
         </div>
         
		<div class="qnaAnswerWrite writeBox_guide"></div>
         <c:choose>
            <c:when test="${empty post.answer}">
               <div class="qnaAnswerWrite writeBox_guide">
                  <div class="qnaAnswerWrite__title">
                     <b>Q&A 게시글 답변 작성</b>
                  </div>
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

                                    for (let i = 0; i < fileNames.length; i++) {
                                       let fileNameTag = $("<div>").attr("class","fileNameTag d-flex");
                                       let fileName = $("<div>").attr("class", "fileName").text(fileNames[i]);
                                       let fileIcon = $("<div>").attr("class", "ml5").html("<i class='fa-solid fa-xmark deleteFileBtn' data-seq="+fileIds[i]+"></i>")
                                       $(".fileNameList").append(fileNameTag.append(fileName).append(fileIcon))
                                    }
                                 </script>
                              </c:when>
                           </c:choose>
                        </div>
                     </div>
                     <input type="file" class="qnaAnswerWrite__fileInput" id="fileInput" multiple>
                  
                  <%@ include file="/WEB-INF/jsp/commons/summernote.jsp"%>
                  <div class="qnaAnswerWrite__btns d-flex">
                     <button class="goList bColorGray colorWhite">목록으로</button>
                     <button class="write bColorMainPink colorWhite"
                        data-id="${post.question.id}">작성완료</button>
                  </div>
               </div>
               </div>
            </c:when>
            <c:otherwise>
               <div class="answerContents">
                  <div class="boardPost__guide">
                     <div class="answerTitle">QNA 게시글 답변</div>
                     <div class="postInfo">
                        <div class="postInfo__left">
                           <div class="postInfo__profile">
                           <img src="/images/profileImg.png" alt="프로필 이미지"
                                 id="writer_profile">
                           </div>
                           <div class="postInfo__userInfo">
                              <div class="userInfo__nickName fontEN">관리자</div>
                              <div class="userInfo__writeInfo">
                                 <span class="writeInfo__title">작성일</span> <span>${post.answer.timeCal }</span>
                              </div>
                           </div>
                        </div>
                        <div class="postInfo__right"></div>
                     </div>
                     <div class="postConf">${post.answer.content }</div>
                  </div>
               </div>

            <button class="answerUpdate" data-id="${post.question.id}">수정</button>

            </c:otherwise>
         </c:choose>
         

      </div>


      <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
   </div>
</body>
</html>