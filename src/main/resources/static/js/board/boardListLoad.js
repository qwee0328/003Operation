// 글쓰기 버튼
$(document).ready(function() {
	$(".board__writeBtn").on("click", function() {
		if ($(".board__title").text().slice(0, 2) == "질문") location.href = "/board/goWritePost/question";
		else location.href = "/board/goWritePost/free";
	})
	postLoad(1);
});

function postLoad(cpage) {
	let category = "free";
	if ($(".board__title").text().slice(0, 2) == "질문") category = "question";

	$.ajax({
		url: "/board/selectPostAll",
		data: { category: "notice" },
		type: "post"
	}).done(function(result) {
		let noticeList = result.list;
		$(".board__posts").html("");

		for (let i = 0; i < noticeList.length; i++) {

			let board__post = $("<div>").attr("class", "board__post d-flex");
			let post__seq = $("<div>").attr("class", "post__seq");

			// if 공지글이면
			board__post.addClass("board__noticePost");
			post__seq.addClass("align-center");
			let noticePost__seq = $("<div>").attr("class", "noticePost__seq align-center").text("공지");
			post__seq.append(noticePost__seq);
			let post__cover = $("<div>").attr("class", "post__cover");

			let post__title = $("<div>").attr("class", "post__title d-flex");
			let title__name = $("<div>").attr("class", "title__name").text(noticeList[i].title).attr("data-id", noticeList[i].id);
			let title_replyCntMini = $("<div>").attr("class", "title__replyCntMini align-center").html("<i class='fa-regular fa-comment align-center'></i>&nbsp" + noticeList[i].reply_cnt);
			let title__replyCnt = $("<div>").attr("class", "title__replyCnt align-center").text("댓글 " + noticeList[i].reply_cnt);
			post__title.append(title__name).append(title_replyCntMini).append(title__replyCnt);


			let board__postMini = $("<div>").attr("class", "board__postMini d-flex");
			let postMini__writer = $("<div>").attr("class", "postMini__writer").text("관리자");
			let postMini__writeDate = $("<div>").attr("class", "postMini__writeDate").html("<i class='fa-regular fa-calendar-days'></i>" + noticeList[i].write_date.slice(0, 11));
			let postMini__view = $("<div>").attr("class", "postMini__view").html("<i class='fa-regular fa-eye'></i>" + noticeList[i].view_count);
			let postMini__recommend = $("<div>").attr("class", "postMini__recommend").html("<i class='fa-regular fa-thumbs-up'></i>" + noticeList[i].recom_cnt);
			board__postMini.append(postMini__writer).append(postMini__writeDate).append(postMini__view).append(postMini__recommend);
			
			if(noticeList[i].file_cnt > 0){
				let postMini__file = $("<div>").attr("class", "postMini__file").html("<i class='fa-solid fa-paperclip'></i>");
				board__postMini.append(postMini__file);
			}
			
			post__cover.append(post__title).append(board__postMini);

			let post__writer = $("<div>").attr("class", "post__writer").text("관리자");
			let post__writeDate = $("<div>").attr("class", "post__writeDate").text(noticeList[i].write_date.slice(0, 11));
			let post__view = $("<div>").attr("class", "post__view").text(noticeList[i].view_count);
			let post__recommend = $("<div>").attr("class", "post__recommend").text(noticeList[i].recom_cnt);
			
			board__post.append(post__seq).append(post__cover);
			board__post.append(post__writer).append(post__writeDate).append(post__view).append(post__recommend);
			
			if(noticeList[i].file_cnt > 0){
				let post__file = $("<div>").attr("class", "post__file").html("<i class='fa-solid fa-paperclip'></i>")
				board__post.append(post__file);
			}
			

			$(".board__posts").append(board__post);
		}

		$.ajax({
			url: "/board/selectPostAll",
			data: { category: category, cpage: cpage },
			type: "post"
		}).done(function(result) {
			
			console.log(result)
			
			let recordTotalCount = result.recordTotalCount;
			let recordCountPerPage = result.recordCountPerPage;
			let naviCountPerPage = result.naviCountPerPage;
			let postCurPage = result.postCurPage;
			let postList = result.list;
			
			for (let i = 0; i < postList.length; i++) {
				let board__post = $("<div>").attr("class", "board__post d-flex");
				let post__seq = $("<div>").attr("class", "post__seq");
				post__seq.text(postList[i].id);


				let post__cover = $("<div>").attr("class", "post__cover");

				let post__title = $("<div>").attr("class", "post__title d-flex");
				let title__name = $("<div>").attr("class", "title__name").text(postList[i].title).attr("data-id", postList[i].id);
				let title_replyCntMini = $("<div>").attr("class", "title__replyCntMini align-center").html("<i class='fa-regular fa-comment align-center'></i>&nbsp" + postList[i].reply_cnt);
				let title__replyCnt = $("<div>").attr("class", "title__replyCnt align-center").text("댓글 " + postList[i].reply_cnt);
				post__title.append(title__name).append(title_replyCntMini).append(title__replyCnt);

				let board__postMini = $("<div>").attr("class", "board__postMini d-flex");
				let postMini__writer = $("<div>").attr("class", "postMini__writer").text(postList[i].member_nickname);
				let postMini__writeDate = $("<div>").attr("class", "postMini__writeDate").html("<i class='fa-regular fa-calendar-days'></i>" + postList[i].write_date.slice(0, 11));
				let postMini__view = $("<div>").attr("class", "postMini__view").html("<i class='fa-regular fa-eye'></i>" + postList[i].view_count);
				let postMini__recommend = $("<div>").attr("class", "postMini__recommend").html("<i class='fa-regular fa-thumbs-up'></i>" + postList[i].recom_cnt);
				board__postMini.append(postMini__writer).append(postMini__writeDate).append(postMini__view).append(postMini__recommend)
				
				if(postList[i].file_cnt > 0){
					let postMini__file = $("<div>").attr("class", "postMini__file").html("<i class='fa-solid fa-paperclip'></i>");
					board__postMini.append(postMini__file);
				}
				
				post__cover.append(post__title).append(board__postMini);

				let post__writer = $("<div>").attr("class", "post__writer").text(postList[i].member_nickname);
				let post__writeDate = $("<div>").attr("class", "post__writeDate").text(postList[i].write_date.slice(0, 11));
				let post__view = $("<div>").attr("class", "post__view").text(postList[i].view_count);
				let post__recommend = $("<div>").attr("class", "post__recommend").text(postList[i].recom_cnt);
				
				board__post.append(post__seq).append(post__cover);
				board__post.append(post__writer).append(post__writeDate).append(post__view).append(post__recommend)
				
				if(postList[i].file_cnt > 0){
					let post__file = $("<div>").attr("class", "post__file").html("<i class='fa-solid fa-paperclip'></i>")
					board__post.append(post__file);
				}
				
				$(".board__posts").append(board__post);
			}

			drawPagination(recordTotalCount, postCurPage, recordCountPerPage, naviCountPerPage);
		});
	});
}


// pagination


/*function drawPagination(recordTotalCount, postCurPage, recordCountPerPage, naviCountPerPage) {
	pagination(recordTotalCount, postCurPage, recordCountPerPage, naviCountPerPage);
}*/

function drawPagination(recordTotalCount, postCurPage, recordCountPerPage, naviCountPerPage) {
	if (recordTotalCount != 0) {
		let pagination = $(".board__pagination");
		pagination.html("");

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
				postLoad(1);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (needPrev) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-chevron-left'></i>");
			aTag.on("click", function() {
				postLoad((startNavi - 1));
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		for (let i = startNavi; i <= endNavi; i++) {
			let aTag = $("<a>");
			aTag.html(i);
			aTag.attr("class", "fontEnglish");
			aTag.on("click", function() {
				$("#replys").html("");
				postLoad(i);
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
				postLoad(endNavi + 1);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (endNavi != pageTotalCount) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-angles-right'></i>");
			aTag.on("click", function() {
				postLoad(pageTotalCount);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}
	}
}

/*
window.onresize = function() {
	// 에이젝스로 값 다시 불러오든가.. 하기..?
	console.log(resize);
	drawPagination(recordTotalCount, postCurPage,10,10);
}
*/