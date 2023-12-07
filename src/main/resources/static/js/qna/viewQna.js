$(document).ready(function(){

});

let deleteFileList = [];
let deleteExisingFileList = [];
	
// 목록으로
$(document).on("click",".goList",function(){
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
		console.log("게시글 등록 ok");
	});
});