$(document).ready(function(){
    let kioskList_line;
    let kioskList_lineClone =  $("<div>").attr("class","kioskList_line d-flex");
	
	let reploadList = (order) => {
		$.ajax({
			url:"/kiosk/getKioskList",
			data:{
				order:order,
				is_game :parseInt($(".is_game").val())
			}
		}).done(function(list){
			console.log(list);
			
			$(".kioskCategory__kioskList").html("");
			let btnTxt = "연습하기";
			if($(".is_game").val() == 1){
				btnTxt = "게임하기";
			}
			for(let i=0; i<list.length; i++){
				console.log(list[i]);
		        if(i%3==0){
		            kioskList_line = $(kioskList_lineClone).clone();
		            $(".kioskCategory__kioskList").append(kioskList_line);
		        }
		
		        let kioskCategory__kiosk = $("<div>").attr("class","kioskCategory__kiosk").attr("data-id",list[i].id);
		        let kiosk__name = $("<div>").attr("class","kiosk__name align-center").html(list[i].name);
		        let kiosk__imageCover =  $("<div>").attr("class","kiosk__imageCover align-center");
		        let kiosk__image = $("<img>").attr("class","kiosk__image").attr("src",`/images/kiosk_logo/${list[i].id}_logo.png`);
		        kiosk__imageCover.append(kiosk__image);
		        let kiost__btn = $("<div>").attr("class","kiost__btn align-center").html(btnTxt);
		        $(kioskList_line).append(kioskCategory__kiosk.append(kiosk__name).append(kiosk__imageCover).append(kiost__btn));   
		        
		        $(kioskCategory__kiosk).on("click",function(){
					let id = $(this).closest(".kioskCategory__kiosk").attr("data-id");
					location.href = "/kiosk/viewKiosk/"+id;
				});
	    	}
		});	
	}
	reploadList();

	
	
	// 정렬
	$(".kioskCateogy__select").on("change",function(){
		reploadList($(".kioskCateogy__select option:selected").val())
	});
    
});