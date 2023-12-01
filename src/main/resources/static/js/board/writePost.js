$(document).ready(function(){
    // 글 작성 버튼
    $(".write").on("click",function(){
		// 제목 입력 검사
		if($(".postArea__titleInput").val()==""){
			alert("제목을 입력하세요.");
			$(".postArea__titleInput").focus();
			return;
		}
		
		// 내용 입력 검사
		if($(".note-editable").html()==""){
			alert("내용을 입력하세요.");
			$(".note-editable").focus();
			return;
		}
		
		let formData = new FormData();
		formData.append("title",$(".postArea__titleInput").val());
		formData.append("content",$(".note-editable").html());
		
		// 자유 vs 질문
		if($(".titleArea").text().slice(0,2)=="질문") formData.append("bulletin_category_id","question");
		else formData.append("bulletin_category_id","free");
		
		for(let i=0; i<$(".postArea__fileInput")[0].files.length; i++){
			formData.append("attachFiles",$(".postArea__fileInput")[0].files[i]);
		}
		
		// 공지글인지도 판단 is_fix
		$.ajax({
			url:"/board/writePost",
			type:"post",
			data:formData,
			contentType: false,
			processData: false
		});
	});
	
	// 목록으로 버튼
	$(".goList").on("click",function(){
		location.href="/board/listBoard";
	});
	
});