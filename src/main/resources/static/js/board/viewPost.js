$(document).ready(function() {
	let category = "free";
	if ($(".board__title").text().slice(0, 2) == "질문") category = "question";

	$("#postUpdate").on("click", function() {
		let url = "/board/goUpdatePost/free";
		if ($(".postTitle").html().slice(0, 2) == "질문") {
			url = "/board/goUpdatePost/question";
		}
		url += "/" + $(this).attr('data-id') + "?select="+$("#select").val()+"&keyword="+$("#keyword").val();
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
	$.ajax({
		url: fileUrl,
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		$(".files__conf").empty();
		for (let i = 0; i < resp.length; i++) {
			let fileLine = $("<div>").attr("class", "files__Line").attr("sysName", resp[i].system_name).attr("oriName", resp[i].origin_name);
			let fileDown = $("<a>").attr("class", "fileDown").attr("href", "/board/downloadFile?sysName=" + resp[i].system_name + "&oriName=" + resp[i].origin_name);
			let fileIcon = $("<i>").attr("class", "fa-regular fa-file");
			let fileName = $("<div>").html(resp[i].origin_name);
			fileDown.append(fileIcon).append(fileName);
			$(".files__conf").append(fileLine.append(fileDown));
		}
	})

	// 게시글 추천, 북마크
	$("#recommendBtn, #bookmarkBtn").on("click", function() {

		// 추천 혹은 북마크 하기
		if (($("#myRecommendRecord").val() === "false" && $(this).attr("id") === "recommendBtn") || ($("#myBookmarkRecord").val() === "false" && $(this).attr("id") === "bookmarkBtn")) {
			let url = "/board/insertPostInfoById";
			console.log($(this).attr("id") === "recommendBtn")
			if ($(this).attr("id") === "recommendBtn") {
				url = url + "/recommend";
			} else {
				url = url + "/bookmark";
			}

			let icon = $(this).find("i");

			$.ajax({
				url: url,
				data: { postId: $("#postId").val() },
				type: "post"
			}).done(function(resp) {
				// 추천 수, 북마크 수, 댓글 수 불러오기
				postInfo();
				console.log($(this))
				icon.css("color", "#FB8F8A");
			})
		}
		if (($("#myRecommendRecord").val() === "true" && $(this).attr("id") === "recommendBtn") || ($("#myBookmarkRecord").val() === "true" && $(this).attr("id") === "bookmarkBtn")) {
			let url = "/board/deletePostInfoById";
			if ($(this).attr("id") === "recommendBtn") {
				url = url + "/recommend";
			} else {
				url = url + "/bookmark";
			}

			let icon = $(this).find("i");

			$.ajax({
				url: url,
				data: { postId: $("#postId").val() },
				type: "post"
			}).done(function(resp) {
				// 추천 수, 북마크 수, 댓글 수 불러오기
				postInfo();
				icon.css("color", "black");
			})
		}

	});

	$("#boardListBtn").on("click", function() {
		let url = "/board/listBoard";

		url += "/" + category + "?select=" + $("#select").val() + "&keyword=" + $("#keyword").val();
		location.href = url;
	})

	// 이전글 다음글 불러오기
	console.log(category)
	console.log($("#keyword").val())
	$.ajax({
		url: "/board/selectPrevNextPost",
		data: { postId: $("#postId").val(), category: category, keyword: $("#keyword").val(), select: $("#select").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		
		if(resp.NextId){
			console.log("asdf")
			let downPage = $("<div>").attr("class","pageBtns__pageLine").attr("id","downPage");
			let iconBox = $("<div>").attr("class","pageLine__icon").attr("data-id",resp.NextId);
			let icon = $("<i>").attr("class","fa-solid fa-chevron-up");
			let text = $("<div>").html("다음글");
			iconBox.append(icon).append(text);
			
			let upPageTitle = $("<div>").attr("class","pageLine__title").html(resp.NextTitle).attr("data-id",resp.NextId);;
			downPage.append(iconBox).append(upPageTitle);
			$(".pageBtns").append(downPage);
		}
		if(resp.PrevId){
			console.log("dlwjsrmf")
			let upPage = $("<div>").attr("class","pageBtns__pageLine").attr("id","upPage");
			let iconBox = $("<div>").attr("class","pageLine__icon").attr("data-id",resp.PrevId);
			let icon = $("<i>").attr("class","fa-solid fa-chevron-down");
			let text = $("<div>").html("이전글");
			iconBox.append(icon).append(text);
			
			let upPageTitle = $("<div>").attr("class","pageLine__title").html(resp.PrevTitle).attr("data-id",resp.PrevId);
			upPage.append(iconBox).append(upPageTitle);
			$(".pageBtns").append(upPage);
		}
	});
	
	$(document).on("click", "#upPage > .pageLine__icon, #upPage > .pageLine__title, #downPage > .pageLine__icon, #downPage > .pageLine__title", function() {
		console.log("dlqpsxm")
		let url = "/board/viewPostConf/"+category+"/"+$("#select").val()+"/"+$(this).attr("data-id")+"?keyword="+$("#keyword").val();
		location.href=url;
	})

});

// 추천 수, 북마크 수, 댓글 수 불러오기
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

	myPostInfo();
}

function myPostInfo() {
	// 내가 추천 혹은 북마크를 했는지 불러오기
	$.ajax({
		url: "/board/selectPostInfoFromMy",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		$("#myRecommendRecord").val(resp.recommend !== null ? resp.recommend : false);
		$("#myBookmarkRecord").val(resp.bookmark !== null ? resp.bookmark : false);

		if (resp.recommend) {
			$("#recommendBtn").find("i").css("color", "#FB8F8A");
		}

		if (resp.bookmark) {
			$("#bookmarkBtn").find("i").css("color", "#FB8F8A");
		}
	});
}