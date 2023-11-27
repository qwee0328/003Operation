$(document).ready(function(){
    let kioskList_line;
    let kioskList_lineClone =  $("<div>").attr("class","kioskList_line d-flex");
    let kioskImags = ["cgv", "emart","letteria","megaCoffee","sangmuSushi"];
    for(let i=0; i<10; i++){
        if(i%3==0){
            kioskList_line = $(kioskList_lineClone).clone();
            $(".kioskCategory__kioskList").append(kioskList_line);
        }

        let kioskCategory__kiosk = $("<div>").attr("class","kioskCategory__kiosk");
        let kiosk__name = $("<div>").attr("class","kiosk__name align-center").html("카테고리 이름");
        let kiosk__imageCover =  $("<div>").attr("class","kiosk__imageCover align-center");
        let kiosk__image = $("<img>").attr("class","kiosk__image").attr("src",`/images/${kioskImags[i%5]}_logo.png`);
        kiosk__imageCover.append(kiosk__image);
        let kiost__btn = $("<div>").attr("class","kiost__btn align-center").html("연습하기");
        $(kioskList_line).append(kioskCategory__kiosk.append(kiosk__name).append(kiosk__imageCover).append(kiost__btn));   
        
        $(kioskCategory__kiosk).on("click",function(){
			location.href = "/practice/viewPractice";
		});
    }
});