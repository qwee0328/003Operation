$(document).ready(function(){
	let deleteFileList = [];
	let deleteExisingFileList = [];
	
	// 파일 삽입 시 목록 출력
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
	
	// 파일 삭제
	$(document).on("click",".deleteFileBtn",function(){
		
		if($(this).attr("data-id") == undefined){ // 수정중 기존 파일 삭제
			deleteExisingFileList.push(parseInt($(this).attr("data-seq")));
		}else{ // 새로 등록한 파일 삭제
			deleteFileList.push(parseInt($(this).attr("data-id")));
		}
		
		$(this).closest(".fileNameTag").remove();
	});
	
	
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
		
		for(let i=0; i<deleteFileList.length; i++){
			formData.append("deleteFileList",deleteFileList[i]);
		};
		
		// 자유 vs 질문
		let bulletin_category_id = "free";
		if($(".titleArea").text().slice(0,2)=="질문") bulletin_category_id = "question";
		formData.append("bulletin_category_id",bulletin_category_id);
		
		for(let i=0; i<$(".postArea__fileInput")[0].files.length; i++){
			if(i==5) break;
			formData.append("attachFiles",$(".postArea__fileInput")[0].files[i]);
		}
		
		// 공지글인지도 판단 
		$.ajax({
			url:"/board/writePost",
			type:"post",
			data:formData,
			contentType: false,
			processData: false
		}).done(function(){
			location.href="/board/listBoard/"+bulletin_category_id;
		});
	});
	
	// 수정 완료 버튼
	$(".update").on("click",function(){
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
		formData.append("id",$(this).attr("data-id"))
		formData.append("title",$(".postArea__titleInput").val());
		formData.append("content",$(".note-editable").html());
		
		for(let i=0; i<deleteFileList.length; i++){
			formData.append("deleteFileList",deleteFileList[i]);
		}

		for(let i=0; i<deleteExisingFileList.length; i++){
			formData.append("deleteExisingFileList",deleteExisingFileList[i]);
		};
		
		// 자유 vs 질문
		let bulletin_category_id = "free";
		if($(".titleArea").text().slice(0,2)=="질문") bulletin_category_id = "question";
		
		for(let i=0; i<$(".postArea__fileInput")[0].files.length; i++){
			if(i==5) break;
			formData.append("attachFiles",$(".postArea__fileInput")[0].files[i]);
		}
		
		// 공지글인지도 판단 
		$.ajax({
			url:"/board/updatePost",
			type:"post",
			data:formData,
			contentType: false,
			processData: false
		}).done(function(){
			location.href="/board/listBoard/"+bulletin_category_id;
		});
	});
	
	// 목록으로 버튼
	$(".goList").on("click",function(){
		let bulletin_category_id = "free";
		if($(".titleArea").text().slice(0,2)=="질문") bulletin_category_id = question;
		location.href="/board/listBoard/"+bulletin_category_id;
	});
	
});