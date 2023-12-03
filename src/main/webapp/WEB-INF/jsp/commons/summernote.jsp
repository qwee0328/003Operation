<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- include summernote css/js -->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="/js/summernote/summernote-ko-KR.js"></script>

</head>
<body>
<div id="summernote"></div>
<script>

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
			let formData = new FormData();
			
			for (let i = 0; i < files.length; i++) {
		        formData.append("imgs", files[i]);
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
					}
				}
			})
		},
		onMediaDelete: function($target, editor, $editable) {     
			// 수정일 때
			if($("#temporary").val() == "true") {
				let deleteUrl = $target.attr("src");
				let prev = $("#deleteUrl").val();
				$("#deleteUrl").val(prev + ":" + deleteUrl);
			// 새 글일 때
			} else {
				$.ajax({
	    			url: "/board/deleteImage",
	    			type: "POST",
	    			data: { src : $target.attr("src") }
	    		})
			}
        
		}
	}
});

	/* function uploadImage(imgs) {
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
				}
			}
		})
	}
	
	// 이미지 삭제
	function deleteImage(imageSrc) {
		
		$.ajax({
			url: "/board/deleteImage",
			type: "POST",
			data: { src : imageSrc }
		})
	} */

	
	
</script>
</body>
</html>