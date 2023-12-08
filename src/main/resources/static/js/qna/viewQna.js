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
	});
});