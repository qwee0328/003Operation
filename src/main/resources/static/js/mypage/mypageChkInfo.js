$(document).ready(function(){
	$(".bottomBtn__back").on("click",function(){
		location.href="/member/viewMypage";
	});
	
	function chkInfo(){
		if($(".content_pw").val()==""){
			alert("비밀번호를 이력해주세요.");
			return;
		}
		
		$.ajax({
			url:"/member/mypage/chkInfo",
			data:{pw:$(".content_pw").val()},
			type:"post"
		}).done(function(result){
			if(result){
				location.href="/member/mypage/goUpdateInfo";
			}else{
				alert("비밀번호를 다시 확인해주세요.");
				$(".content_pw").val("").focus();	
			}
		});
	}
	
	$(".bottomBtn__check").on("click",function(){
		chkInfo();
	});
	
	$(document).on("keydown",function(e){
		if(e.keyCode == 13){
			chkInfo();
		}
	})
});