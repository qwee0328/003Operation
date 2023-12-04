$(document).ready(function(){
	//답변있으면
});

$(document).ready(function(){

	//답변없으면
    for(let i=0; i<10; i++){
        let board__post = $("<div>").attr("class","board__post d-flex");
        let post__seq = $("<div>").attr("class","post__seq").text("1");

        let post__cover = $("<div>").attr("class","post__cover");
        
        let post__title = $("<div>").attr("class","post__title d-flex");

		// 비밀글이면 넣고 아니면 빼기
        let title__secret = $("<div>").attr("class","title__secret").html("<i class='fa-solid fa-lock'></i>");

		let title__name = $("<div>").attr("class","title__name").text("자유게시판 제목인데용ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ");
        let title__answerStateCover = $("<div>").attr("class","title__answerStateCover");

		// 여부에 따라 bColorMainPink colorWhite / bColorLightGray colorGray
        let title__answerState = $("<div>").attr("class","title__answerState align-center bColorMainPink colorWhite").text("답변여부");
        post__title.append(title__secret).append(title__name).append(title__answerStateCover.append(title__answerState));

		let board__postAwswer = $("<div>").attr("class","board__postAwswer");
        let board__postMini = $("<div>").attr("class","board__postMini d-flex");
        let postMini__writer = $("<div>").attr("class","postMini__writer").text("작성자");
        let postMini__writeDate = $("<div>").attr("class","postMini__writeDate").html("<i class='fa-regular fa-calendar-days'></i>"+"2023-11-10");
        let postMini__file = $("<div>").attr("class","postMini__file").html("<i class='fa-solid fa-paperclip'></i>");
		board__postMini.append(postMini__writer).append(postMini__writeDate).append(postMini__file);

		let postAwswer__area = $("<div>").attr("class","postAwswer__area colorMainBlue").html("답변 미리보기 <i class='fa-solid fa-chevron-down colorMainBlue'></i>");
		let postAnswer__content = $("<div>").attr("class","postAnswer__content").text("해당 문제는 오랜 시간 창을 켜둔 채로");

        board__postAwswer.append(board__postMini).append(postAwswer__area).append(postAnswer__content);

	

        post__cover.append(post__title).append(board__postAwswer);

		let post__anwserState = $("<div>").attr("class","post__anwserState").text("답변대기");
        let post__writer = $("<div>").attr("class","post__writer").text("작성자");
        let post__writeDate = $("<div>").attr("class","post__writeDate").text("2023-11-10");
        let post__file = $("<div>").attr("class","post__file").html("<i class='fa-solid fa-paperclip'></i>")
        board__post.append(post__seq).append(post__cover);
        board__post.append(post__anwserState).append(post__writer).append(post__writeDate).append(post__file);

        $(".board__posts").append(board__post);
    }

	
	
});
$(document).on("click",".postAwswer__area",function(){		
	if($(this).next().hasClass("abled")){
		$(this).next().removeClass("abled");
		$(this).children().removeClass("fa-chevron-up").addClass("fa-chevron-down");
		
	}else{
		$(this).next().addClass("abled");
		$(this).children().removeClass("fa-chevron-down").addClass("fa-chevron-up");
	}
});

// pagination
$(document).ready(function(){

	function drawPagination() {
		if(window.innerWidth > 768){
			pagination(1, 110, 1, 10, 10);
		}
		else{
			pagination(1, 110, 1, 5, 5);
		}
	}

    function pagination(postSeq, recordTotalCount, replyCurPage, recordCountPerPage, naviCountPerPage) {
		if (recordTotalCount != 0) {
			let pagination = $(".board__pagination");
			pagination.html("");
			
			let pageTotalCount = 0;
			pageTotalCount = Math.ceil(recordTotalCount / recordCountPerPage);

			// 비정상 접근 차단
			if (replyCurPage < 1) {
				replyCurPage = 1;
			} else if (replyCurPage > pageTotalCount) {
				replyCurPage = pageTotalCount;
			}

			let startNavi = Math.floor((replyCurPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
			let endNavi = startNavi + (naviCountPerPage - 1);
			
			
			if (endNavi > pageTotalCount) {
				endNavi = pageTotalCount;
			}
			let needPrev = true;
			let needNext = true;
		

			if (startNavi == 1) {
				needPrev = false;
			}
			if (endNavi == pageTotalCount) {
				needNext = false;
			}


			if (startNavi != 1) {
				let aTag = $("<a>");
				let iTag = $("<i class='fa-solid fa-angles-left'></i>");
				aTag.attr("class","fontEnglish");
				aTag.on("click",function(){
					$("#replys").html("");
					replyReload(postSeq,1);
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}

			if (needPrev) {
				let aTag = $("<a>");
				let iTag = $("<i class='fa-solid fa-chevron-left'></i>");
				aTag.on("click",function(){
					$("#replys").html("");
					replyReload(postSeq,(startNavi - 1));
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}

			for (let i = startNavi; i <= endNavi; i++) {
				let aTag = $("<a>");
				aTag.html(i);
				aTag.attr("class","fontEnglish");
				aTag.on("click",function(){
					$("#replys").html("");
					replyReload(postSeq,i);
				});
				if (i == replyCurPage) {
					aTag.addClass("colorWhite bColorMainBlue fontEnglish");
				}else{
                    aTag.addClass("bColorLightBlue fontEnglish");
                }
				pagination.append(aTag);
			}

			if (needNext) {
				let aTag = $("<a>");
				let iTag = $("<i class='fa-solid fa-chevron-right'></i>");
				aTag.on("click",function(){
					$("#replys").html("");
					replyReload(postSeq,(endNavi + 1));
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}

			if (endNavi != pageTotalCount) {
				let aTag = $("<a>");
				let iTag = $("<i class='fa-solid fa-angles-right'></i>");
				aTag.on("click",function(){
					$("#replys").html("");
					replyReload(postSeq,pageTotalCount);
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}
		}
	}
    drawPagination();

	window.onresize = function(){
		// 에이젝스로 값 다시 불러오든가.. 하기..?
		drawPagination();
	}
	
})