<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<button id="gg">클릭</button>
	<script>
		$("#gg").on("click",function(){
			$.ajax({
				url:"/kiosk/test",
				contentType: "application/json",
				data:JSON.stringify({
					is_game:1,
					is_sucess:1,
					kiosk_category_id:"TOrder",
					member_id:"id",
					play_date:"2023-12-09T08:29:32Z",
					play_stage:1,
					play_time:5
				}),
				type:"post"
			}).done(function(){
				console.log("흠");
			})
		});
	
	</script>
</body>
</html>