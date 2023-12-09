let select;
let keyword;

$(document).ready(function(){
	postLoad(1);
});

// 게시글 목록 불러오기
function postLoad(cpage){
	$.ajax({
		url:"/member/selectMyPost",
		type:"post",
		data:{cpage:cpage}
	}).done(function(result){
		postPrint(result);
		select ="";
		keyword="";
	})
}

function postPrint(result){
	$(".board__posts").html("");	
	let post = result.post;

	for(let i=0; i<post.length; i++){
		let board__post = $("<div>").attr("class","board__post d-flex");
		let post__chkBox = $("<div>").attr("class","post__chkBox");
		let chkBox = $("<input>").attr("type","checkbox").attr("class","postChk");
		let post__seq = $("<div>").attr("class","post__seq").text(post[i].id);
		let post__tacCover = $("<div>").attr("class","post__tacCover d-flex");
		let post__titleAndCategory = $("<div>").attr("class","post__titleAndCategory");
		let post__titleAndReply = $("<div>").attr("class","post__titleAndReply d-flex");
		let post__title = $("<div>").attr("class","post__title").text(post[i].title).attr("data-id",post[i].id).attr("data-category",post[i].bulletin_category_id);
		post__titleAndReply.append(post__title);
		if(post[i].bulletin_category_id == "free"){
			let post__reply = $("<div>").attr("class","post__reply").text("댓글 "+post[i].reply_count);
			let post__replyMini = $("<div>").attr("class","post__replyMini").html(`<i class='fa-regular fa-comment align-center'></i>`+post[i].reply_count);
			post__titleAndReply.append(post__reply).append(post__replyMini);
		}
		let post__category = $("<div>").attr("class","post__category");
		if(post[i].bulletin_category_id == "free"){
			post__category.text("게시판: 자유게시판");
		}else{
			post__category.text("게시판: 질문게시판");
		}
		post__tacCover.append(post__titleAndCategory.append(post__titleAndReply).append(post__category));
		let post__writeDate = $("<div>").attr("class","post__writeDate").text(post[i].write_date.slice(0,10));
		let post__viewCount = $("<div>").attr("class","post__viewCount").text(post[i].view_count);
		let post__recomCount = $("<div>").attr("class","post__recomCount").text(post[i].recom_count);
		
		board__post.append(post__chkBox.append(chkBox)).append(post__seq).append(post__tacCover).append(post__writeDate).append(post__viewCount).append(post__recomCount);
		if(post[i].file_count>0){
			let post__isFile = $("<div>").attr("class","post__isFile").html(`<i class='fa-solid fa-paperclip'></i>`);
			board__post.append(post__isFile);
		}
		
		$(".board__posts").append(board__post);	
	}
	
	$(".postCnt__txt").text(result.recordTotalCount);
	$(".pagination").html("");
	drawPagination(result.recordTotalCount, result.postCurPage, result.recordCountPerPage, result.naviCountPerPage)
}

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
				if (select != "") {
					search(1);
				} else {
					postLoad(1);
				}
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (needPrev) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-chevron-left'></i>");
			aTag.on("click", function() {
				if (select != "") {
					search((startNavi - 1));
				} else {
					postLoad((startNavi - 1));
				}
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		for (let i = startNavi; i <= endNavi; i++) {
			let aTag = $("<a>");
			aTag.html(i);
			aTag.attr("class", "fontEnglish");
			aTag.on("click", function() {
				if (select != "") {
					search(i);
				} else {
					postLoad(i);
				}
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
				if (select != "") {
					search(endNavi + 1);
				} else {
					postLoad(endNavi + 1);
				}
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (endNavi != pageTotalCount) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-angles-right'></i>");
			aTag.on("click", function() {
				if (select != "") {
					search(pageTotalCount);
				} else {
					postLoad(pageTotalCount);
				}
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}
	}
}

// 게시글 보기 페이지로 이동
$(document).on("click", ".post__title", function() {
	let url = "/board/viewPostConf";
	let category = $(this).attr("data-category");
	
	keyword = $(".search__value").children().val() !== "" ? $(".search__value").children().val() : "";
	select = $(".search__select option:selected").val() != +"" ? $(".search__select option:selected").val() : "";
	
	url += "/" + category + "/" + select + "/" + $(this).attr("data-id")+"?keyword="+keyword;
	location.href = url;
})


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

// 낱개 선택 시 전체 선택 중이면 취소
$(document).on("click",".postChk",function(){
	if($(".board__allSelect").hasClass("allSelect")){
		$(".board__allSelect").removeClass("allSelect");
		$(".board__allSelect").text("전체선택");
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
			url:"/board/deleteSelectPost",
			type:"post",
            data: formData,
			contentType: false,
			processData: false
		}).done(function(){
			location.reload();
		});
	}
})

// 내 게시글 검색
function search(cpage){
	keyword = $(".search__value").children().val() !== "" ? $(".search__value").children().val() : "";
	select = $(".search__select option:selected").val() != +"" ? $(".search__select option:selected").val() : "";

	$.ajax({
		url:"/board/searchMyPost",
		data:{
			select:select,
			keyword:keyword,
			cpage:cpage
		}
	}).done(function(result){
		postPrint(result);
	});
}
$(document).on("click",".search__btn",function(){
	search(1)
});

$(document).on("keydown", function(e) {
	if (e.keyCode == 13) {
		search(1);
	}
});