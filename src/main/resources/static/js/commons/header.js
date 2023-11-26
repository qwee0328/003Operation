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
	});
	
	$("#boradMenu").on("mouseout", function(){
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
});

