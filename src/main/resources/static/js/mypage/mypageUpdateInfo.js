$(document).ready(function(){
	 $("#password").on("keyup",function(){
            let regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[\W_])[A-Za-z\d\W_]{8,30}$/;

            if(regex.test($(this).val())){
                $(".inputPw__regResult").html("올바른 비밀번호 형식 입니다.").css("color","#375ABB");
                let pw = $("#password_confirm").val();
                if(pw!=""){
                    if(pw == $(this).val()) $(".inputPw__compareResult").html("비밀번호가 일치합니다.").css("color","#375ABB");
                    else $(".inputPw__compareResult").html("비밀번호가 일치하지 않습니다.").css("color","#FB8F8A");
                }
            }else{
                $(".inputPw__regResult").html("비밀번호 형식이 올바르지 않습니다.").css("color","#FB8F8A");
                $(".inputPw__compareResult").html("&nbsp;");
            }

        });

        $("#password_confirm").on("keyup",function(){
            let pw = $("#password").val();
            if(pw!="" &&  $(".inputPw__regResult").html()=="올바른 비밀번호 형식 입니다."){
                if(pw == $(this).val()) $(".inputPw__compareResult").html("비밀번호가 일치합니다.").css("color","#375ABB");
                else $(".inputPw__compareResult").html("비밀번호가 일치하지 않습니다.").css("color","#FB8F8A");
            }
        });

        $(".profileImg__icon").on("click",function(){
            console.log("프사변경");
        })

        $(document).on("click",".info__input",function(){
            $(this).on("keyup",function(){
                $(this).closest(".info__cover").find(".info__compareResult").html("정규식 결과");
            })
        })

        $(".info__inputNick").on("keyup",function(){
            $(".info__dupResult").html("중복 여부");
        })
        
        
        // 돌아가기
        $(".footerBtn__back").on("click",function(){
			location.href="/member/viewMypage"
		})
});