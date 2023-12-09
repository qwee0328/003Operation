function postLoad(result){
	let recordTotalCount = result.recordTotalCount;
	$(".postCnt__txt").text(recordTotalCount);
	let recordCountPerPage = result.recordCountPerPage;
	let naviCountPerPage = result.naviCountPerPage;
	let postCurPage = result.postCurPage;
	let list = result.list;
	let user = result.userNick;
	
	for(let i=0; i<list.length; i++){
        let board__post = $("<div>").attr("class","board__post d-flex");
        let post__chkBox = $("<div>").attr("class","post__chkBox");
		let chkBox = $("<input>").attr("type","checkbox").attr("class","postChk");
        let post__seq = $("<div>").attr("class","post__seq").text(list[i].id);

        let post__cover = $("<div>").attr("class","post__cover");
        
        let post__title = $("<div>").attr("class","post__title d-flex");

		let title__name = $("<div>").attr("class","title__name").attr("data-id", list[i].id);
		
		// 비밀글이면 넣고 아니면 빼기
		if(list[i].is_secret==1){
			  let title__secret = $("<div>").attr("class","title__secret").html("<i class='fa-solid fa-lock'></i>");
			  post__title.append(title__secret);
		}
		
		title__name.text(list[i].title);
        let title__answerStateCover = $("<div>").attr("class","title__answerStateCover");

		// 여부에 따라 bColorMainPink colorWhite / bColorLightGray colorGray
		let title__answerState = $("<div>");
		if(list[i].answer==undefined){
			title__answerState.attr("class","title__answerState align-center bColorLightGray colorGray").text("답변대기");
		}else{
			title__answerState.attr("class","title__answerState align-center bColorMainPink colorWhite").text("답변완료");
		}
        
        post__title.append(title__name).append(title__answerStateCover.append(title__answerState));

		let board__postAwswer = $("<div>").attr("class","board__postAwswer");
        let board__postMini = $("<div>").attr("class","board__postMini d-flex");
       
        let postMini__writeDate = $("<div>").attr("class","postMini__writeDate").html("<i class='fa-regular fa-calendar-days'></i>"+list[i].write_date.slice(0,11));
        board__postMini.append(postMini__writeDate);
        if(list[i].file_cnt > 0){
			let postMini__file = $("<div>").attr("class","postMini__file").html("<i class='fa-solid fa-paperclip'></i>");
			board__postMini.append(postMini__file);
		}
        
    	
		post__cover.append(post__title);
		
        if(list[i].answer!=undefined){
			if(list[i].is_secret==0 || (list[i].is_secret==1 && list[i].member_nickname==user)){
				let postAwswer__area = $("<div>").attr("class","postAwswer__area colorMainBlue").html("답변 미리보기 <i class='fa-solid fa-chevron-down colorMainBlue'></i>");
				let regex = /<[^>]*>/gi;
				let answerContent = list[i].answer;
				answerContent = answerContent.replaceAll(regex,"");
				
				let postAnswer__content = $("<div>").attr("class","postAnswer__content").text(answerContent)
				board__postAwswer.append(board__postMini).append(postAwswer__area).append(postAnswer__content);
				post__cover.append(board__postAwswer);
			}
		}else{
			board__postAwswer.append(board__postMini);
			post__cover.append(board__postAwswer);
		}

        
        let post__answerState = $("<div>");
		if(list[i].answer==undefined){
			post__answerState.attr("class","post__answerState").text("답변대기");
		}else{
			post__answerState.attr("class","post__answerState ColorMainPink").text("답변완료");
		}

        let post__writeDate = $("<div>").attr("class","post__writeDate").text(list[i].write_date.slice(0,11));
        board__post.append(post__chkBox.append(chkBox)).append(post__seq).append(post__cover);
        board__post.append(post__answerState).append(post__writeDate);
        if(list[i].file_cnt > 0){
			let post__file = $("<div>").attr("class","post__file").html("<i class='fa-solid fa-paperclip'></i>")
			board__post.append(post__file);
		}
        $(".board__posts").append(board__post);
    }
    drawPagination(recordTotalCount, postCurPage, recordCountPerPage, naviCountPerPage);
}

function getPost(cpage){
	$.ajax({
		url:"/qna/selectMyQnaAll",
		data:{cpage:cpage},
		type:"post"
	}).done(function(resp){
		$(".board__posts").html("");
		postLoad(resp);
	});
}

$(document).ready(function(){
	getPost(1);
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
function drawPagination(recordTotalCount, postCurPage, recordCountPerPage, naviCountPerPage) {
	let pagination = $(".board__pagination");
	pagination.html("");
	if (recordTotalCount == 0) {
		let empty = $("<div class='empty align-center'>").html("검색 결과가 존재하지 않습니다.");
		$(".board__posts").append(empty);
	}
	else {
		let pageTotalCount = 0;
		pageTotalCount = Math.ceil(recordTotalCount / recordCountPerPage);

		// 비정상 접근 차단
		if (postCurPage < 1) {
			postCurPage = 1;
		} else if (postCurPage > pageTotalCount) {
			postCurPage = pageTotalCount;
		}

		let startNavi = Math.floor((postCurPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
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
			aTag.attr("class", "fontEnglish");
			aTag.on("click", function() {
				getPost(1);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (needPrev) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-chevron-left'></i>");
			aTag.on("click", function() {
				getPost((startNavi - 1));
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		for (let i = startNavi; i <= endNavi; i++) {
			let aTag = $("<a>");
			aTag.html(i);
			aTag.attr("class", "fontEnglish");
			aTag.on("click", function() {
				getPost(i);
			});
			if (i == postCurPage) {
				aTag.addClass("colorWhite bColorMainBlue fontEnglish");
			} else {
				aTag.addClass("bColorLightBlue fontEnglish");
			}
			pagination.append(aTag);
		}

		if (needNext) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-chevron-right'></i>");
			aTag.on("click", function() {
				getPost(endNavi + 1);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (endNavi != pageTotalCount) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-angles-right'></i>");
			aTag.on("click", function() {
				getPost(pageTotalCount);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}
	}
}


// 게시글 보기 페이지로 이동
$(document).on("click", ".title__name", function() {
	location.href = "/qna/viewQnaConf/" + $(this).attr("data-id");
})

// 낱개 선택 시 전체 선택 중이면 취소
$(document).on("click",".postChk",function(){
	if($(".board__allSelect").hasClass("allSelect")){
		$(".board__allSelect").removeClass("allSelect");
		$(".board__allSelect").text("전체선택");
	}
});

// 전체 선택
$(document).on("click",".board__allSelect",function(){
	if($(this).hasClass("allSelect")){
		$(".postChk").prop("checked",false);
		$(".board__allSelect").removeClass("allSelect");
		$(".board__allSelect").text("전체선택");
	}else{	
		$(".postChk").prop("checked",true);
		$(this).addClass("allSelect");
		$(this).text("취소");
	}
	
});

// 선택된 게시글 삭제
$(document).on("click",".board__selectDelete",function(){
	if(confirm("정말로 삭제하시겠습니까?")){
		let formData = new FormData();
		$(".postChk:checked").map((i,e)=>{
			formData.append("deleteIds",$(e).closest(".board__post").find(".post__seq").text());
		})
		$.ajax({
			url:"/qna/deleteSelectQna",
			type:"post",
            data: formData,
			contentType: false,
			processData: false
		}).done(function(){
			location.reload();
		});
	}
})
