<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css" />
</head>
<body>
	<div>테스트 창</div>
	<script>
        // b.com/b.html
        window.addEventListener('message', function (event) {
            var receivedData = event.data;
            alert(receivedData);
        });
    </script>
</body>
</html>