$(document).ready(function() {
	
	let realTimeRanking = () => {
		$.ajax({
			url:"/kiosk/realTimeRank"
		}).done(function(ranking){
			$(".popular__contents").html("");
			
			for(let i=0; i<ranking.length; i++){
				let popularBox = $("<div>").attr("class","popular__Box");
				let BoxLeft = $("<div>").attr("class","Box__left");
				let popularRank = $("<div>").attr("class","popular__rank").text(ranking[i].ranking);
				
				let popularInfo = $("<div>").attr("class","popular__info");
				let infoCategory = $("<div>").attr("class","info__category").text(ranking[i].name);
				let infoPlayCount = $("<div>").attr("class","info__playCount");
				let playCountPrac = $("<div>").text(`연습 플레이 횟수 : ${ranking[i].prac_cnt}번`);
				let playCountGame = $("<div>").text(`게임 플레이 횟수 : ${ranking[i].game_cnt}번`);
				infoPlayCount.append(playCountPrac).append(playCountGame)
				let infoPlayCountTab = $("<div>").attr("class","info__playCount-tab");
				let playCountPracTab = $("<div>").text(`연습 : ${ranking[i].prac_cnt}번`);
				let playCountGameTab = $("<div>").text(`게임 : ${ranking[i].game_cnt}번`);
				infoPlayCountTab.append(playCountPracTab).append(playCountGameTab);
				popularInfo.append(infoCategory).append(infoPlayCount).append(infoPlayCountTab);
				BoxLeft.append(popularRank).append(popularInfo);	
					
				let BoxRight = $("<div>").attr("class","Box__right");
				let popularBtns = $("<div>").attr("class","popular__btns");
				let goPrac = $("<a>").append($("<button>").html("연습하러가기").attr("class","popularGoKiosk").attr("data-id",ranking[i].id).attr("data-isGame",0));
				let goGame = $("<a>").append($("<button>").html("게임하러가기").attr("class","popularGoKiosk").attr("data-id",ranking[i].id).attr("data-isGame",1));
				popularBtns.append(goPrac).append(goGame);
				let popularBtnsTab = $("<div>").attr("class","popular__btns-tab");
				let goPracTab = $("<a>").append($("<button>").html("연습").attr("class","popularGoKiosk").attr("data-id",ranking[i].id).attr("data-isGame",0));
				let goGameTab = $("<a>").append($("<button>").html("게임").attr("class","popularGoKiosk").attr("data-id",ranking[i].id).attr("data-isGame",1));
				popularBtnsTab.append(goPracTab).append(goGameTab);
				BoxRight.append(popularBtns).append(popularBtnsTab);
				
				popularBox.append(BoxLeft).append(BoxRight);
				$(".popular__contents").append(popularBox);
			}
			
		})
	}
	
	realTimeRanking();
	
	$(".realTimeRanking").on("click",function(){
		realTimeRanking();
	});
});

$(document).on("click",".popularGoKiosk",function(){
	location.href = "/kiosk/viewKiosk/"+$(this).attr("data-id")+"?is_game="+$(this).attr("data-isGame");
});