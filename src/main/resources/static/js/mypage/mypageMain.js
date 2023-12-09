$(document).ready(function(){
	$(".profile__logoutBtn").on("click",function(){
		location.href="/member/logout";
	});
	
	$(".goMypage").on("click",function(){
		location.href="/member/mypage/viewMypage";
	});
	
	// 마이페이지 메인 화면 정보 불러오기 (프로필 이미지, 레벨, 포인트)
	/*$.ajax({
		url:"/member/selectMainMypageInfoById"
	}).done(function(info){
		if(typeof info.profile_img !== undefined){
			$(".profileImg__img").attr("src",`/profileImgs/${info.profile_image}`)
		}
		$(".level__txt").text(`Lv.${info.level_id}`);
		$(".point__txt").text(`Lv.${info.point}`);
	});*/
});