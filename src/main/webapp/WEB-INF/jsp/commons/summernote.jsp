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

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="/js/summernote/summernote-ko-KR.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
	    ]
	});
</script>
</body>
</html>