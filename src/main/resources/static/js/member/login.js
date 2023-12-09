$(document).ready(function(){
	
	// 아이디 입력란에 숫자, 영문자만 입력 가능하도록
	/*$(".login__inputId").on("input",function(){
		$(this).val($(this).val().replace(/[^[a-z\dA-Z]/g,""));
	});
	
	// enter 누르면 로그인 시도
	$(document).on("keydown",function(e){
		if(e.keyCode == 13){
			login();
		}
	});
	
	// 로그인 버튼 클릭 시 로그인 시도
	$(".login__btn").on("click",function(){
		login();
	});*/
	
	// login 함수
	function login(){
		if($(".login__inputId").val()==""){
			alert("아이디를 입력하세요.");
			return;
		}
		if($(".login__inputPw").val()==""){
			alert("비밀번호를 입력하세요.");
			return;
		}
		
		$.ajax({
			url:"/member/login",
			data:{
				id:$(".login__inputId").val(),
				pw:$(".login__inputPw").val()
			},
			type:"post"
		}).done(function(loginResult){
			if(loginResult){
				location.href="/";
			}else{
				alert("아이디나 비밀번호를 다시 확인해주세요.");
			}
		});
	}
	
	// 로그인 cookie 에 기억
	$(".login__inputId").val(Cookies.get("loginId"));
	
	// cookie 내용을 반영하여 값이 들어갔다면 아이디 기억하기 체크
	if($(".login__inputId").val() != ""){
		$(".login__saveIdChkBox").attr("checked",true);
	}
    	
    // 아이디 기억하기 체크박스 변동
	$(".login__saveIdChkBox").change(function(){
		// 체크 시 쿠키에 아이디 저장 (기한 7일)
		if($(".login__saveIdChkBox").is(":checked")){
			Cookies.set("loginId",$(".login__inputId").val(),{expires:7});
		// 체크 해제 시 쿠키에서 아이디 삭제
		}else{
			Cookies.remove("loginId");
		}
	});
	
	// 아이디 입력 시
	$(".login__inputId").keyup(function(){
		// 아이디 기억하기 체크 되어있으면 새로 저장
		if($(".login__saveIdChkBox").is(":checked")){
			Cookies.set("loginId",$(".login__inputId").val(),{expires:7});
		}
	});
});