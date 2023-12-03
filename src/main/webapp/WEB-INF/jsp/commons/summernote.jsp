<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- include summernote css/js -->
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
	var $j = jQuery.noConflict();
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
					$j("#summernote").summernote("insertNode", img[0]);
				}
			}
		})
	}


	$j('#summernote').summernote({
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
				// 수정일 때
	
				// 새 글일 때
				$.ajax({
	    			url: "/board/deleteImage",
	    			type: "POST",
	    			data: { src : $target.attr("src") }
	    		})
				
			}
		}
	});
</script>
</body>
</html>