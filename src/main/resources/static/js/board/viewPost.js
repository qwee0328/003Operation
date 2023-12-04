$(document).ready(function() {
	$("#postUpdate").on("click", function() {
		let url = "/board/goUpdatePost/free";
		if ($(".postTitle").html().slice(0, 2) == "질문") {
			url = "/board/goUpdatePost/question";
		}
		url += "/" + $(this).attr('data-id');
		location.href = url;
	})

	$("#postDelete").on("click", function() {
		let url = "/board/deletePost/free";
		if ($(".postTitle").html().slice(0, 2) == "질문") {
			url = "/board/deletePost/question";
		}
		url += "/" + $(this).attr('data-id');

		let check = confirm("게시물을 정말 삭제하시겠습니까?")
		if (check) {
			location.href = url;
		}
	})

	// 프로필 이미지 불러오기
	$.ajax({
		url: "/member/selectProfileImgByIdBoard",
		data: { writerId: $("#writerId").val() },
		type: "post"
	}).done(function(resp) {
		if (resp !== "") {
			$("#writer_profile").attr("src", "/profileImgs/" + resp);
		} else {
			$("#writer_profile").attr("src", "/images/profileImg.png");
		}
	})

	// 추천 수, 북마크 수, 댓글 수 불러오기
	postInfo();

	// 파일 불러오기
	let fileUrl = "/board/selectFileById";
	if ($("#boardType").val() === "자유") {
		fileUrl += "/free";
	} else {
		fileUrl += "/question";
	}
	console.log(fileUrl);
	$.ajax({
		url: fileUrl,
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		$(".files__conf").empty();
		for (let i = 0; i < resp.length; i++) {
			let fileLine = $("<div>").attr("class", "files__Line").attr("sysName", resp[i].system_name).attr("oriName", resp[i].origin_name);
			let fileDown = $("<a>").attr("class","fileDown").attr("href","/board/downloadFile?sysName="+resp[i].system_name+"&oriName="+resp[i].origin_name);
			let fileIcon = $("<i>").attr("class", "fa-regular fa-file");
			let fileName = $("<div>").html(resp[i].origin_name);
			fileDown.append(fileIcon).append(fileName);
			$(".files__conf").append(fileLine.append(fileDown));
		}
	})

	// 게시글 추천, 북마크
	$("#recommendBtn, #bookmarkBtn").on("click", function() {
		let url = "/board/insertPostInfoById";

		if ($(this) === $("#recommendBtn")) {
			url = url + "/recommend";
		} else {
			url = url + "/bookmark";
		}

		$.ajax({
			url: url,
			data: { postId: $("#postId").val() },
			type: "post"
		}).done(function(resp) {
			// 추천 수, 북마크 수, 댓글 수 불러오기
			postInfo();


		})
	});

	$("#boardListBtn").on("click", function() {
		let url = "/board/listBoard";
		let category = "free";
		if ($(".board__title").text().slice(0, 2) == "질문") category = "question";
		url += "/" + category;
		location.href = url;
	})




});

function postInfo() {

	$.ajax({
		url: "/board/selectPostInfoById",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		$(".recommendCount").html(resp.recommend);
		$(".bookmarkCount").html(resp.bookmark);
		$(".replyCount").html(resp.reply);
	})
}