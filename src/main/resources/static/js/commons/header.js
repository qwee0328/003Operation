$(document).ready(function() {
	// 햄버거 메뉴바
	$("#hamNav").on("click", function() {
		$(".tabNav").css("display", "block")
	});

	$("#xBtns").on("click", function() {
		$(".tabNav").css("display", "none")
	});
	
	// tabNavi
	$(".tabNav").on("click", function(e) {
		if (e.target.className === "tabNav") {
			$(".tabNav").css("display", "none")
		}
	});

	$(".board").on("mouseover", function(){
		$("#boradMenu").css("display", "block");
		$("#mypageMenu").css("display", "none");
	});
	
	$(".mypage").on("mouseover", function(){
		$("#mypageMenu").css("display", "block");
		$("#boradMenu").css("display", "none");
	});
	
	$("#boradMenu").on("mouseout", function(){
		$(this).css("display", "none");
	});
	
	$("#mypageMenu").on("mouseout", function(){
		$(this).css("display", "none");
	});
	
	$(".signupBtn").on("click",function(){
		location.href="/member/signup";
	})
	window.onresize = function() {
		const width = window.innerWidth;
		if (width > 786) {
			$(".tabNav").css("display", "none");
		}
	}
	
	// 로고 누르면 홈화면으로 이동
	$(".header_left .logo").on("click",function(){
		location.href="/";
	});
	
	// 로그인 페이지로 이동
	$(".loginBtn").on("click",function(){
		location.href="/member/goLogin";
	});
	
	// 로그아웃
	$(".logoutBtn").on("click",function(){
		location.href="/member/logout";
	});
});

