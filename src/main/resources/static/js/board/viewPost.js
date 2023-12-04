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
	
	// 추천 수 불러오기
	$.ajax({
		url: "/board/selectPostInfoById",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp){
		console.log(resp)
		$(".recommendCount").html(resp.recommend);
		$(".bookmarkCount").html(resp.bookmark);
		$(".replyCount").html(resp.reply);
	})
});