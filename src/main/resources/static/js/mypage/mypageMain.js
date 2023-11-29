$(document).ready(function(){
	$(".profile__logoutBtn").on("click",function(){
		location.href="/member/logout";
	});
	
	$(".goMypage").on("click",function(){
		location.href="/member/viewMypage";
	});
});