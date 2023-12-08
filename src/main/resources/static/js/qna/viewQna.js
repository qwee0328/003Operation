let deleteFileList = [];
let deleteExisingFileList = [];

$(document).ready(function(){

	// 답변 작성 시에 파일 삽입 시 목록 출력
	$("#fileInput").on("change",function(e){
		$(".fileNameList").html("");
		deleteFileList = [];
		deleteExisingFileList = [-1];
		if(e.target.files.length>=6){
			alert("파일은 최대 5개까지 올릴 수 있습니다.");
		}
		for(let i=0; i<e.target.files.length; i++){
			if(i==5) break;
			let fileNameTag = $("<div>").attr("class","fileNameTag d-flex");
			let fileName = $("<div>").attr("class","fileName").text(e.target.files[i].name);
			let fileIcon = $("<div>").attr("class","ml5").html(`<i class='fa-solid fa-xmark deleteFileBtn' data-id=${i}></i>`)
			$(".fileNameList").append(fileNameTag.append(fileName).append(fileIcon))		
		}
	});
	
});

// 파일 삭제
$(document).on("click",".deleteFileBtn",function(){
	
	if($(this).attr("data-id") == undefined){ // 수정중 기존 파일 삭제
		deleteExisingFileList.push(parseInt($(this).attr("data-seq")));
	}else{ // 새로 등록한 파일 삭제
		deleteFileList.push(parseInt($(this).attr("data-id")));
	}
	
	$(this).closest(".fileNameTag").remove();
});


	
// 목록으로
$(document).on("click",".goList",function(){
	// 수정중이었으면 삽입된 이미지 다시 삭제
	$.ajax({
		url: "/board/deleteImage",
		type: "POST",
		traditional: true,
		data: { srcList : insertImgs }
	});
		
	location.href="/qna/listBoard";
});


	
// 답변 작성
$(document).on("click",".write",function(){
	// 내용 입력 검사
	if($(".note-editable").html()==""){
		alert("내용을 입력하세요.");
		$(".note-editable").focus();
		return;
	}
		
	let formData = new FormData();
	formData.append("content",$(".note-editable").html());	
	formData.append("qna_question_board_id",$(this).attr("data-id"));	
	for(let i=0; i<deleteFileList.length; i++){
		formData.append("deleteFileList",deleteFileList[i]);
	};
		
	for(let i=0; i<$(".qnaAnswerWrite__fileInput")[0].files.length; i++){
		if(i==5) break;
		formData.append("attachFiles",$(".qnaAnswerWrite__fileInput")[0].files[i]);
	}
	
	$.ajax({
		url:"/qna/writeAnswer",
		type:"post",
		data:formData,
		contentType: false,
		processData: false
	}).done(function(){
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
$(document).on("click",".answerUpdate",function(){
	$.ajax({
		url:"/qna/goUpdateAnswer/"+parseInt($(this).attr("data-id"))
	}).done(function(answer){
		console.log(answer);
		
		let qnaAnswerWrite__title = $("<div>").attr("class","qnaAnswerWrite__title").html(`<b>Q&A 게시글 답변 수정</b>`);
		let qnaAnswerWrite__hr = $("<hr>").attr("class","qnaAnswerWrite__hr");
		let qnaAnswerWrite__file = $("<div>").attr("class","qnaAnswerWrite__file");
		let dflex = $("<div>").attr("class","d-flex");
		let fileInput__label = $("<label>").attr("class","fileInput__label").attr("for","fileInput").text("파일 첨부");
		let fileNameList = $("<div>").attr("class","fileNameList d-flex");

		if(answer.file_names!=""){			
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
		let summernote = $("<div>").html(`<%@ include file="/WEB-INF/jsp/commons/summernote.jsp" %>`);
		qnaAnswerWrite__file.append(dflex.append(fileInput__label).append(fileNameList).append(qnaAnswerWrite__fileInput));
		let qnaAnswerWrite__btns = $("<div>").attr("class","qnaAnswerWrite__btns d-flex");
		let goList = $("<button>").attr("class","goList bColorGray colorWhite").text("목록으로");
		let write = $("<button>").attr("class","write bColorMainPink colorWhite").text("수정완료").attr("data-id",answer.id);
		qnaAnswerWrite__btns.append(goList).append(write);
		
		$(".qnaAnswerWrite").append(qnaAnswerWrite__title).append(qnaAnswerWrite__hr).append(qnaAnswerWrite__file).append(summernote).append(qnaAnswerWrite__btns);

	});

});


// 질문 수정
$(document).on("click","#questionUpdate",function(){
	location.href="/qna/goUpdateQuestion/"+parseInt($(this).attr("data-id"));
});