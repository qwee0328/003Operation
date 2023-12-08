let deleteFileList = [];
let deleteExisingFileList = [];

$(document).ready(function() {
	// 내가쓴 QNA인지 확인
	$.ajax({
		url: "/qna/isMyQna",
		data: { memberNickname: $("#memberNickname").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		if (resp) {
			let postUpdate = $("<button>").attr("id", "questionUpdate").attr("data-id", $("#qnaId").val()).html("수정")
			let postDelete = $("<button>").attr("id", "postDelete").attr("data-id", $("#qnaId").val()).html("삭제")
			$(".postInfo__right").append(postUpdate).append(postDelete);
		}
	});
	
	// 프로필 이미지 불러오기
	$.ajax({
		url: "/member/selectProfileImgByNickBoard",
		data: { memberNickname: $("#memberNickname").val() },
		type: "post"
	}).done(function(resp) {
		if (resp !== "") {
			$("#writer_profile").attr("src", "/profileImgs/" + resp);
		} else {
			$("#writer_profile").attr("src", "/images/profileImg.png");
		}
	});
	
	// 수정으로 넘어가기
	$(document).on("click", "#questionUpdate", function() {
		let url = "/qna/goUpdateQuestion/"+$("#qnaId").val();
		location.href = url;
	});
	
	// 삭제하기
	$(document).on("click", "#postDelete", function() {
		let url = "/qna/deletePost/"+$("#qnaId").val();

		let check = confirm("게시물을 삭제하시겠습니까?\n삭제한 게시글은 되돌릴 수 없습니다.")
		if (check) {
			location.href = url;
		}
	});
	
	// 목록으로 이동 버튼
	$("#boardListBtn").on("click", function() {
		let url = "/qna/listBoard";
		location.href = url;
	});
	
	// 파일 목록 불러오기
	$.ajax({
		url: "/qna/selectFileById",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)

		if (resp.length > 0) {
			let files = $("<div>").attr("class", "files");
			let title = $("<div>").attr("class", "files__title").html("첨부파일");
			let conf = $("<div>").attr("calss", "files__conf");
			for (let i = 0; i < resp.length; i++) {
				let fileLine = $("<div>").attr("class", "files__Line").attr("sysName", resp[i].system_name).attr("oriName", resp[i].origin_name);
				let fileDown = $("<a>").attr("class", "fileDown").attr("href", "/board/downloadFile?sysName=" + resp[i].system_name + "&oriName=" + resp[i].origin_name);
				let fileIcon = $("<i>").attr("class", "fa-regular fa-file");
				let fileName = $("<div>").html(resp[i].origin_name);
				fileDown.append(fileIcon).append(fileName);
				conf.append(fileLine.append(fileDown));
			}
			files.append(title).append(conf);
			$(".postConf").after(files);
		}

	});

	// 답변 작성 시에 파일 삽입 시 목록 출력
	$("#fileInput").on("change", function(e) {
		$(".fileNameList").html("");
		deleteFileList = [];
		deleteExisingFileList = [-1];
		if (e.target.files.length >= 6) {
			alert("파일은 최대 5개까지 올릴 수 있습니다.");
		}
		for (let i = 0; i < e.target.files.length; i++) {
			if (i == 5) break;
			let fileNameTag = $("<div>").attr("class", "fileNameTag d-flex");
			let fileName = $("<div>").attr("class", "fileName").text(e.target.files[i].name);
			let fileIcon = $("<div>").attr("class", "ml5").html(`<i class='fa-solid fa-xmark deleteFileBtn' data-id=${i}></i>`)
			$(".fileNameList").append(fileNameTag.append(fileName).append(fileIcon))
		}
	});

});

// 파일 삭제
$(document).on("click", ".deleteFileBtn", function() {

	if ($(this).attr("data-id") == undefined) { // 수정중 기존 파일 삭제
		deleteExisingFileList.push(parseInt($(this).attr("data-seq")));
	} else { // 새로 등록한 파일 삭제
		deleteFileList.push(parseInt($(this).attr("data-id")));
	}

	$(this).closest(".fileNameTag").remove();
});



// 목록으로
$(document).on("click", ".goList", function() {
	// 수정중이었으면 삽입된 이미지 다시 삭제
	$.ajax({
		url: "/board/deleteImage",
		type: "POST",
		traditional: true,
		data: { srcList: insertImgs }
	});

	location.href = "/qna/listBoard";
});



// 답변 작성
$(document).on("click", ".write", function() {
	// 내용 입력 검사
	if ($(".note-editable").html() == "") {
		alert("내용을 입력하세요.");
		$(".note-editable").focus();
		return;
	}

	let formData = new FormData();
	formData.append("content", $(".note-editable").html());
	formData.append("qna_question_board_id", $(this).attr("data-id"));
	for (let i = 0; i < deleteFileList.length; i++) {
		formData.append("deleteFileList", deleteFileList[i]);
	};

	for (let i = 0; i < $(".qnaAnswerWrite__fileInput")[0].files.length; i++) {
		if (i == 5) break;
		formData.append("attachFiles", $(".qnaAnswerWrite__fileInput")[0].files[i]);
	}

	$.ajax({
		url: "/qna/writeAnswer",
		type: "post",
		data: formData,
		contentType: false,
		processData: false
	}).done(function() {
		location.reload();
		// 내용 불러오는 기능 구현하면 reload 보다는 직접 요소 뿌려주는 게 나을 것 같아요
		//
		//
		//
		//
		//
		//
		//
		// 이렇게 하면 확인하겠지...?
		//
	});
});


// 답변 수정
$(document).on("click", ".answerUpdate", function() {
	$.ajax({
		url: "/qna/goUpdateAnswer/" + parseInt($(this).attr("data-id"))
	}).done(function() {

	});

});


// 질문 수정
$(document).on("click", "#questionUpdate", function() {
	location.href = "/qna/goUpdateQuestion/" + parseInt($(this).attr("data-id"));
});