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
	
	// 키오스크 전체목록 불러오기
	$.ajax({
		url:"/kiosk/getKioskList",
		data:{is_game:0},
		type:"post"
	}).done(function(kioskList){	
		let practice_row = $("<div>").attr("class","practice_row");
		let practice_row_clone;
		let practice_row_tab_clone;	
		
		for(let i=0; i<kioskList.length; i++){
			
			if(i%3==0){
				practice_row_clone = practice_row.clone()
				practice_row_tab_clone = practice_row.clone();
				
				$(".practice__contents").append(practice_row_clone);
				$(".practice__contents-tab").append(practice_row_tab_clone);		
			}			
			let aTag = $("<a>").attr("href",`/kiosk/viewKiosk/${kioskList[i].id}?is_game=0`);
			let practice__box = $("<div>").attr("class","practice__box");
			let subtitle = $("<div>").attr("class","subtitle").text("키오스크 연습");
			let title = $("<div>").attr("class","title").text(kioskList[i].name);
			aTag.append(practice__box.append(subtitle).append(title));
			practice_row_clone.append(aTag.clone());
			practice_row_tab_clone.append(aTag.clone());
		}

	})
});

$(document).on("click",".popularGoKiosk",function(){
	location.href = "/kiosk/viewKiosk/"+$(this).attr("data-id")+"?is_game="+$(this).attr("data-isGame");
});