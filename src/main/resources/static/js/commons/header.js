$(document).ready(function() {
	$("#hamNav").on("click", function() {
		$(".tabNav").css("display", "block")
	})

	$("#xBtns").on("click", function() {
		$(".tabNav").css("display", "none")
	})

	window.onresize = function() {
		const width = window.innerWidth;
		if (width > 786) {
			$(".tabNav").css("display", "none");
		}
	}
});

