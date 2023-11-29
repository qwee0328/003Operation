$(document).ready(function(){
	 $("#password").on("keyup",function(){
            let regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[\W_])[A-Za-z\d\W_]{8,30}$/;
			$(".info__pwModify .info__modifyBtn").attr("disabled",true);
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
			$(".info__pwModify .info__modifyBtn").attr("disabled",true);
            let pw = $("#password").val();
            if(pw!="" &&  $(".inputPw__regResult").html()=="올바른 비밀번호 형식 입니다."){
                if(pw == $(this).val()) {$(".inputPw__compareResult").html("비밀번호가 일치합니다.").css("color","#375ABB");$(".info__pwModify .info__modifyBtn").removeAttr("disabled");}
                else $(".inputPw__compareResult").html("비밀번호가 일치하지 않습니다.").css("color","#FB8F8A");
            }
        });

        $(".profileImg__icon").on("click",function(){
            console.log("프사변경");
        })

        
		// 닉네임 입력 (중복 확인)
        $(".info__inputNick").on("keyup",function(){
          	if($(this).val()==""){
				  $(".info__dupResult").text("");
				  $(this).next().children().attr("disabled",true);
				  return;
			}
            $.ajax({
				url:"/member/chkNickname",
				data:{nickname:$(".info__inputNick").val()},
				type:"post"
			}).done(function(dup){
				if($(".info__inputNick").val()==""){$(".info__dupResult").text("");}
				else{
					if(dup){
					 	$(".info__dupResult").html("중복된 닉네임입니다.").css("color","#FB8F8A");
					 	$(".info__inputNick").next().children().attr("disabled",true);
					}else{
						$(".info__dupResult").html("사용 가능한 닉네임입니다.").css("color","#375ABB");
						$(".info__inputNick").next().children().removeAttr("disabled");
					}
				}
			})
        })
        
        // 이메일 입력 (정규식 확인)
        $(".info__email").on("keyup",function(){
			let email = $(this);
			if($(this).val()==""){
			  $(".info__emailRegexResult").text("");
			  $(this).next().children().attr("disabled",true);
			  return;
			}
			// 정규식
			let emailRegex = /^[a-zA-Z0-9_]+@[a-z]+\.[a-z]+(\.*[a-z])*$/;
			let result = emailRegex.test($(this).val());
			if(result){
				
				// 중복 검사
				
				$.ajax({
					url:"/member/emailDuplicationCheck",
					data:{email:$(this).val()},
					type:"post"
				}).done(function(dup){
					if(dup){ //중복
						$(".info__emailRegexResult").text("중복된 이메일입니다.").css("color","#FB8F8A");
						$(email).next().children().attr("disabled",true);
					}else{ // 사용 가능
						$(".info__emailRegexResult").text("사용 가능한 이메일입니다.").css("color","#375ABB");
						$(email).next().children().removeAttr("disabled");
					}
				});
				
				$(".info__emailRegexResult").text("올바른 이메일 형식 입니다.").css("color","#375ABB");
				$(this).next().children().removeAttr("disabled");
			}else{
				$(".info__emailRegexResult").text("이메일 형식이 맞지 않습니다.").css("color","#FB8F8A");
				$(this).next().children().attr("disabled",true);
			}
		});
		

		
		// 전화번호 입력 (정규식 확인)
		$(".info__phone").on("input",function(){
			let phone = $(this);
			
			if($(this).val()==""){
			  $(".info__phoneRegexResult").text("");
			  $(this).next().children().attr("disabled",true);
			  return;
			}
			
			$(this).val($(this).val().replace(/[^0-9]/g, '')); // 숫자만 입력
			$(this).val($(this).val().slice(0,11)); // 11글자 까지만 입력
			
			let phoneRegex = /^[0-9]{11}$/;
			if(phoneRegex.test($(this).val())){
				// 중복 검사
				$.ajax({
					url:"/phone/emailDuplicationCheck",
					data:{phone:$(this).val()},
					type:"post"
				}).done(function(dup){
					if(dup){ //중복
						$(".info__phoneRegexResult").text("중복된 전화번호입니다.").css("color","#FB8F8A");
						$(phone).next().children().attr("disabled",true);
					}else{ // 사용 가능
						$(".info__phoneRegexResult").text("사용 가능한 전화번호입니다.").css("color","#375ABB");
						$(phone).next().children().removeAttr("disabled");
					}
				})
			}else{
				$(".info__phoneRegexResult").text("전화번호 형식이 맞지 않습니다.").css("color","#FB8F8A");
				$(this).next().children().attr("disabled",true);
			}
			
		});
        
        
        // 돌아가기
        $(".footerBtn__back").on("click",function(){
			location.href="/member/viewMypage"
		})
		
		// 개인정보 변경
		$(".info__modifyBtn").on("click",function(){
			let key = $(this).attr("data-id");
			let value = $(this).parent().prev().val();
			if($(this).attr("data-id")=="pw"){
				value=$(".inputPw__pw").val();
			}
			$.ajax({
				url:"/member/updateInfo",
				data:{
					key: key, 
					value: value
				},
				type:"post"
			}).done(function(isUpdate){
				if(isUpdate==1){
					alert("정보가 수정되었습니다.");
				}else{
					alert("문제가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
				location.href="/member/viewMypage";
			});
			
		});
});