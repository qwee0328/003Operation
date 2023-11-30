// 글쓰기 버튼
$(document).ready(function(){
	$(".board__writeBtn").on("click",function(){
		location.href ="/board/writePost";
	})
});

$(document).ready(function() {
	let board__post = $("<div>").attr("class", "board__post d-flex");
	let post__seq = $("<div>").attr("class", "post__seq");

	// if 공지글이면
	board__post.addClass("board__noticePost")
	post__seq.addClass("align-center");
	let noticePost__seq = $("<div>").attr("class", "noticePost__seq align-center").text("공지");
	post__seq.append(noticePost__seq);
	let post__cover = $("<div>").attr("class", "post__cover");

	let post__title = $("<div>").attr("class", "post__title d-flex");
	let title__name = $("<div>").attr("class", "title__name").text("공지 제목인데용ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ");
	let title_replyCntMini = $("<div>").attr("class", "title__replyCntMini align-center").html("<i class='fa-regular fa-comment align-center'></i>&nbsp" + "3");
	let title__replyCnt = $("<div>").attr("class", "title__replyCnt align-center").text("댓글 " + "3");
	post__title.append(title__name).append(title_replyCntMini).append(title__replyCnt);

	let board__postMini = $("<div>").attr("class", "board__postMini d-flex");
	let postMini__writer = $("<div>").attr("class", "postMini__writer").text("관리자");
	let postMini__writeDate = $("<div>").attr("class", "postMini__writeDate").html("<i class='fa-regular fa-calendar-days'></i>" + "2023-11-10");
	let postMini__view = $("<div>").attr("class", "postMini__view").html("<i class='fa-regular fa-eye'></i>" + "10");
	let postMini__recommend = $("<div>").attr("class", "postMini__recommend").html("<i class='fa-regular fa-thumbs-up'></i>" + "5");
	let postMini__file = $("<div>").attr("class", "postMini__file").html("<i class='fa-solid fa-paperclip'></i>");
	board__postMini.append(postMini__writer).append(postMini__writeDate).append(postMini__view).append(postMini__recommend).append(postMini__file);

	post__cover.append(post__title).append(board__postMini);

	let post__writer = $("<div>").attr("class", "post__writer").text("관리자");
	let post__writeDate = $("<div>").attr("class", "post__writeDate").text("2023-11-10");
	let post__view = $("<div>").attr("class", "post__view").text("10");
	let post__recommend = $("<div>").attr("class", "post__recommend").text("5");
	let post__file = $("<div>").attr("class", "post__file").html("<i class='fa-solid fa-paperclip'></i>")
	board__post.append(post__seq).append(post__cover);
	board__post.append(post__writer).append(post__writeDate).append(post__view).append(post__recommend).append(post__file);

	$(".board__posts").append(board__post);
});

$(document).ready(function() {
	for (let i = 0; i < 10; i++) {
		let board__post = $("<div>").attr("class", "board__post d-flex");
		let post__seq = $("<div>").attr("class", "post__seq");
		post__seq.text("1");


		let post__cover = $("<div>").attr("class", "post__cover");

		let post__title = $("<div>").attr("class", "post__title d-flex");
		let title__name = $("<div>").attr("class", "title__name").text("자유게시판 제목인데용ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ");
		let title_replyCntMini = $("<div>").attr("class", "title__replyCntMini align-center").html("<i class='fa-regular fa-comment align-center'></i>&nbsp" + "3");
		let title__replyCnt = $("<div>").attr("class", "title__replyCnt align-center").text("댓글 " + "3");
		post__title.append(title__name).append(title_replyCntMini).append(title__replyCnt);

		let board__postMini = $("<div>").attr("class", "board__postMini d-flex");
		let postMini__writer = $("<div>").attr("class", "postMini__writer").text("작성자");
		let postMini__writeDate = $("<div>").attr("class", "postMini__writeDate").html("<i class='fa-regular fa-calendar-days'></i>" + "2023-11-10");
		let postMini__view = $("<div>").attr("class", "postMini__view").html("<i class='fa-regular fa-eye'></i>" + "10");
		let postMini__recommend = $("<div>").attr("class", "postMini__recommend").html("<i class='fa-regular fa-thumbs-up'></i>" + "5");
		let postMini__file = $("<div>").attr("class", "postMini__file").html("<i class='fa-solid fa-paperclip'></i>");
		board__postMini.append(postMini__writer).append(postMini__writeDate).append(postMini__view).append(postMini__recommend).append(postMini__file);

		post__cover.append(post__title).append(board__postMini);

		let post__writer = $("<div>").attr("class", "post__writer").text("작성자");
		let post__writeDate = $("<div>").attr("class", "post__writeDate").text("2023-11-10");
		let post__view = $("<div>").attr("class", "post__view").text("10");
		let post__recommend = $("<div>").attr("class", "post__recommend").text("5");
		let post__file = $("<div>").attr("class", "post__file").html("<i class='fa-solid fa-paperclip'></i>")
		board__post.append(post__seq).append(post__cover);
		board__post.append(post__writer).append(post__writeDate).append(post__view).append(post__recommend).append(post__file);

		$(".board__posts").append(board__post);
	}
});


// pagination
$(document).ready(function() {

	function drawPagination() {
		if (window.innerWidth > 768) {
			pagination(1, 110, 1, 10, 10);
		}
		else {
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
				aTag.attr("class", "fontEnglish");
				aTag.on("click", function() {
					$("#replys").html("");
					replyReload(postSeq, 1);
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}

			if (needPrev) {
				let aTag = $("<a>");
				let iTag = $("<i class='fa-solid fa-chevron-left'></i>");
				aTag.on("click", function() {
					$("#replys").html("");
					replyReload(postSeq, (startNavi - 1));
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
					replyReload(postSeq, i);
				});
				if (i == replyCurPage) {
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
					$("#replys").html("");
					replyReload(postSeq, (endNavi + 1));
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}

			if (endNavi != pageTotalCount) {
				let aTag = $("<a>");
				let iTag = $("<i class='fa-solid fa-angles-right'></i>");
				aTag.on("click", function() {
					$("#replys").html("");
					replyReload(postSeq, pageTotalCount);
				});
				aTag.append(iTag);
				pagination.append(aTag);
			}
		}
	}
	drawPagination();

	window.onresize = function() {
		// 에이젝스로 값 다시 불러오든가.. 하기..?
		drawPagination();
	}

})