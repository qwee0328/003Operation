$(document).ready(function(){
    $(".progressBar__fill").css("width","30%");
    
    $.ajax({
		url:"/kiosk/getBestRecord",
		data:{kiosk_category_id:$(".kiosk__kioskName").attr("data-id")},
		type:"post"
	}).done(function(resp){
		for(let i=0; i<resp.length; i++){
			let sec=resp[i].play_time;
			let play_time = sec+"초";
			if(sec>60) {
				let min = Math.floor(sec/60);
				sec%=60;
				play_time=min+"분 "+sec+"초";
			} 
			$(".myRecord__time.step"+resp[i].play_stage).text(play_time);
		}
	});
});