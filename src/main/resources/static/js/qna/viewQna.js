let deleteFileList = [];
let deleteExisingFileList = [];
deleteImgs = [];
insertImgs = [];

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
	
	// 답변 파일 불러오기
	$.ajax({
		url: "/qna/selectAnswerFileById",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp){
		console.log(resp)
		if(resp.length>0){
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
			$(".answerConf").after(files);
		}
	})
		

	// 답변 작성 시에 파일 삽입 시 목록 출력
	$(document).on("change","#fileInput", function(e) {
		$(".fileNameList").html("");
		deleteFileList = [];
		deleteExisingFileList = [-1];
		if (e.target.files.length >= 6) {
			alert("파일은 최대 5개까지 올릴 수 있습니다.");
		}
		for (let i = 0; i < e.target.files.length; i++) {
			if (i == 5) break;
			if(e.target.files[i].size >= 10 * 1024 * 1024){
				alert("파일은 10MB까지만 업로드할 수 있습니다.");
				$("#fileInput").remove();
				$(".fileInput__label").parent().after($("<input>").attr("type","file").attr("class","postArea__fileInput").attr("id","fileInput").attr("multiple",true));
				break;
			}else{
				let fileNameTag = $("<div>").attr("class","fileNameTag d-flex");
				let fileName = $("<div>").attr("class","fileName").text(e.target.files[i].name);
				let fileIcon = $("<div>").attr("class","ml5").html(`<i class='fa-solid fa-xmark deleteFileBtn' data-id=${i}></i>`)
				$(".fileNameList").append(fileNameTag.append(fileName).append(fileIcon));
			}	
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
		url: "/qna/admin/writeAnswer",
		type: "post",
		data: formData,
		contentType: false,
		processData: false
	}).done(function() {
		location.reload();
	});
});


// 답변 수정
$(document).on("click", ".answerUpdate", function() {
	$.ajax({
		url:"/qna/admin/goUpdateAnswer/"+parseInt($(this).attr("data-id"))
	}).done(function(answer){	
		$(".answerContents").css("display","none");
		$(".answerUpdate").css("display","none");
		
		let qnaAnswerWrite__title = $("<div>").attr("class","qnaAnswerWrite__title").html(`<b>Q&A 게시글 답변 수정</b>`);
		let qnaAnswerWrite__hr = $("<hr>").attr("class","qnaAnswerWrite__hr");
		let qnaAnswerWrite__file = $("<div>").attr("class","qnaAnswerWrite__file");
		let dflex = $("<div>").attr("class","d-flex");
		let fileInput__label = $("<label>").attr("class","fileInput__label").attr("for","fileInput").text("파일 첨부");
		let fileNameList = $("<div>").attr("class","fileNameList d-flex");

		if(answer.file_names != undefined){
			let fileNames = answer.file_names.split(",");
			let fileIds = answer.file_ids.split(",");
			for(let i=0; i<fileNames.length; i++){
				let fileNameTag = $("<div>").attr("class","fileNameTag d-flex");
				let fileName = $("<div>").attr("class","fileName").text(fileNames[i]);
				let fileIcon = $("<div>").attr("class","ml5").html("<i class='fa-solid fa-xmark deleteFileBtn' data-seq="+fileIds[i]+"></i>")
				fileNameList.append(fileNameTag.append(fileName).append(fileIcon))		
			}
		}
		
		let qnaAnswerWrite__fileInput = $("<input>").attr("type","file").attr("class","qnaAnswerWrite__fileInput").attr("id","fileInput").prop("multiple","true");
		qnaAnswerWrite__file.append(dflex.append(fileInput__label).append(fileNameList).append(qnaAnswerWrite__fileInput));
		let qnaAnswerWrite__btns = $("<div>").attr("class","qnaAnswerWrite__btns d-flex");
		let goList = $("<button>").attr("class","updateCancel bColorGray colorWhite").text("수정취소");
		let write = $("<button>").attr("class","updateAnswer bColorMainPink colorWhite").text("수정완료").attr("data-id",answer.id);
		qnaAnswerWrite__btns.append(goList).append(write);
	
		let summernote = $("<div>").attr("id","summernote");

		$(".qnaAnswerWrite").append(qnaAnswerWrite__title).append(qnaAnswerWrite__hr).append(qnaAnswerWrite__file).append(summernote).append(qnaAnswerWrite__btns);
		$('#summernote').summernote({
		    placeholder:"내용을 입력해주세요",
		    height:500,
		    disableResizeEditor: true,
		    lang: 'ko-KR',
		    maximumFileSize: 10485760,
		    toolbar: [
		        ['style', ['style']],
		        ['font', ['bold', 'underline', 'clear']],
		        ['color', ['color']],
		        ['para', ['ul', 'ol', 'paragraph']],
		        ['table', ['table']],
		        ['insert', ['picture']],
		        ['view', ['help']]
		    ],
			callbacks: {
				onImageUpload: function(files) {
					uploadImg(files);			
				},
				onMediaDelete: function($target, editor, $editable) {     
					if('${post}'==''){
						// 수정이 아닌 새 글일 때
						$.ajax({
			    			url: "/board/deleteImage",
			    			type: "POST",
			    			data: { src : $target.attr("src") }
			    		});
					}else{
						// 수정 중
						deleteImgs.push($target.attr("src"));
					}
				}
			}
		});
		$('#summernote').summernote("code",answer.content);
	});
});


// 질문 수정
$(document).on("click", "#questionUpdate", function() {
	location.href = "/qna/goUpdateQuestion/" + parseInt($(this).attr("data-id"));
});



// summernote 이미지 업로드
function uploadImg(imgs){
	let formData = new FormData();
	
	for (let i = 0; i < imgs.length; i++) {
        formData.append("imgs", imgs[i]);
    }
	
	$.ajax({
		url: "/board/uploadImage",
		type: "POST",
		data: formData,
		contentType: false,
    	processData: false,
    	success: function(data) {
			for (let i = 0; i < data.length; i++) {
				let img = $("<img>");
				img.css('width', '100%');
				img.attr("src", data[i]);
				$("#summernote").summernote("insertNode", img[0]);		
				insertImgs.push(data[i]);
			}
		}
	})
}


// 답변 수정
$(document).on("click",".updateAnswer",function(){
	// 내용 입력 검사
	if($(".note-editable").html()==""){
		alert("내용을 입력하세요.");
		$(".note-editable").focus();
		return;
	}
	
	let id = $(this).attr("data-id");
		
	let formData = new FormData();
	formData.append("id", id)
	formData.append("content",$(".note-editable").html());
	
	// 새로 등록한 파일 중 삭제한 파일
	for(let i=0; i<deleteFileList.length; i++){
		formData.append("deleteFileList",deleteFileList[i]);
	}
	
	// 기존 파일 중 삭제한 파일
	for(let i=0; i<deleteExisingFileList.length; i++){
		formData.append("deleteExisingFileList",deleteExisingFileList[i]);
	};
	
	// 기존 이미지 중 삭제한 이미지
	for(let i=0; i<deleteImgs.length; i++){
		formData.append("deleteImgs",deleteImgs[i]);
	}
	
	
	for(let i=0; i<$(".qnaAnswerWrite__fileInput")[0].files.length; i++){
		if(i==5) break;
		formData.append("attachFiles",$(".qnaAnswerWrite__fileInput")[0].files[i]);
		console.log($(".qnaAnswerWrite__fileInput")[0].files[i]);
	}
		
	$.ajax({
		url:"/qna/admin/updateAnswerPost",
		type:"post",
		data:formData,
		contentType: false,
		processData: false
	}).done(function(){
		location.reload();
	});
});

$(document).on("click",".updateCancel",function(){
	$(".answerContents").css("display","block");
	$(".answerUpdate").css("display","block");
	$(".qnaAnswerWrite").html("");
	
	// 수정중이었으면 삽입된 이미지 다시 삭제
	$.ajax({
		url: "/board/deleteImage",
		type: "POST",
		traditional: true,
		data: { srcList : insertImgs }
	});
})