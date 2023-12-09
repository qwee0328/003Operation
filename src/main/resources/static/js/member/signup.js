// 회원가입 정보 체크 변수
let idValidation = false;
let pwValidation = false;
let pwCheckValidation = false;
let nameValidation = false;
let phoneValidation = false;
let birthValidation = false;
let genderValidation = false;
let nickValidation = true;
let emailValidation = true;
let recommenderValidation = true;
let signupValidation = false;

$(document).ready(function() {
	// step 1
	// 전체 동의 눌렀을 때
	$('#chk_all').on("change", function() {
		if (this.checked) {
			$('#chk_use, #chk_privacy').prop('checked', true);
			$('#stepOneNextBnt').prop('disabled', false);
			$('#stepOneNextBnt').css('backgroundColor', '#FB8F8A');
		} else {
			$('#chk_use, #chk_privacy').prop('checked', false);
			$('#stepOneNextBnt').prop('disabled', true);
			$('#stepOneNextBnt').css('backgroundColor', '#7d7d7d');
		}
	});

	// 약관 동의 눌렀을 때
	$('#chk_use, #chk_privacy').on("change", function() {
		if ($('#chk_use').prop('checked') && $('#chk_privacy').prop('checked')) {
			$('#chk_all').prop('checked', true);
			$('#stepOneNextBnt').prop('disabled', false);
			$('#stepOneNextBnt').css('backgroundColor', '#FB8F8A');
		} else {
			$('#chk_all').prop('checked', false);
			$('#stepOneNextBnt').prop('disabled', true);
			$('#stepOneNextBnt').css('backgroundColor', '#7d7d7d');
		}
	});

	// step 1 다음 버튼 눌렀을 때
	$('#stepOneNextBnt').on("click", function() {
		if (($('#chk_use').prop('checked') && $('#chk_privacy').prop('checked')) || $('#chk_all').prop('checked')) {
			$('.stepOneBox').hide();
			$('.stepTwoBox').show();
		} else {
			$('.stepOneBox').show();
			$('.stepTwoBox').hide();
		}
	});

	// step2
	// selector Box 선택
	// selector 커스텀 해서 만들기
	let showSelector = false;
	$(document).on("click", ".selectorType", function() {
		console.log("click")
		if (!showSelector) {
			$(this)
				.parent()
				.find(".selectorArrow")
				.children()
				.attr("class", "fa-solid fa-chevron-up");
			$(this).parent().find(".selector__option").css("display", "block");
			console.log($(this)
				.parent()
				.find(".selectorArrow")
				.children())
			// 선택한 타입 업데이트
			$(".option__item").on("click", function() {
				$(this).parent().parent().find(".typeName").html($(this).html());
				$(this)
					.parent().parent()
					.find(".selectorArrow")
					.children()
					.attr("class", "fa-solid fa-chevron-down");
				$(this).parent().css("display", "none");

				if ($(this).attr("value") === "directInput") {
					$(this).parent().parent().find(".selectorType").css("display", "none");
					$("#directInput").css("display", "inline-block");
				}

				if ($(this).parent().parent().find(".typeName").html() !== "선택해주세요." && $(this).parent().parent().find(".typeName").html() !== "직접입력") {

					if ($("#phoneInput").val() !== "") {
						$.ajax({
							url: "/member/phoneDuplicationCheck",
							type: "POST",
							data: { phone: $(this).parent().parent().find(".typeName").html() + $("#phoneInput").val() }
						}).done(function(resp) {
							if (resp) {
								$("#phone_check").html("중복된 전화번호 입니다. 다른 번호를 사용해주세요.");
								$("#phone_check").css("color", "#FB8F8A");
								phoneValidation = false;
							} else {
								phoneValidation = true;
								$("#phone_check").html("");
							}
							checkStepTwoVariables();
						})
					} else {
						phoneValidation = false;
					}
					checkStepTwoVariables();
				} else {
					phoneValidation = false;
				}
				checkStepTwoVariables();
			});
			showSelector = true;
		} else {
			$(this)
				.parent()
				.find(".selectorArrow")
				.children()
				.attr("class", "fa-solid fa-chevron-down");
			$(this).parent().find(".selector__option").css("display", "none");
			showSelector = false;
		}

	});

	// selector외부를 클릭하면 selector가 닫히도록 설정
	$(document).on("click", function(event) {
		let clickElement = $(event.target);
		if (clickElement.closest(`.selectorType`).length > 0) {
			// 모든 .selector__option 숨기기
			console.log("a")
			// 클릭된 .selectorType에 속한 .selector__option만 보이게 설정
			if (showSelector) {
				clickElement
					.closest(`.selectorType`)
					.parent()
					.find(".selector__option")
					.css("display", "block");
				clickElement
					.closest(`.selectorType`)
					.parent()
					.find(".selectorArrow")
					.children()
					.attr("class", "fa-solid fa-chevron-up");
				showSelector = true;
			}
		} else {
			$(".selector__option").css("display", "none");
			$(".selector__option")
				.parent()
				.find(".selectorArrow")
				.children()
				.attr("class", "fa-solid fa-chevron-down");
			showSelector = false;
		}
	});

	// 아이디 input 입력 시 중복 확인

	$("#idInput").on("input", function() {
		if ($(this).val() === "") {
			$("#id_check").html("");
			$("#id_check").css("color", "black");
		} else {
			$.ajax({
				url: "/member/idDuplicationCheck",
				type: "POST",
				data: { id: $(this).val() }
			}).done(function(resp) {
				console.log(resp === "true")
				console.log(resp === true)
				console.log(resp == "true")
				if (resp === true) {
					console.log("1")
					$("#id_check").html("사용 불가능한 아이디 입니다.");
					$("#id_check").css("color", "#fb8f8a");
					idValidation = false;
					checkStepTwoVariables();
				} else { // 중복되는 아이디가 없다면 유효성 검사 확인
					let regexId = /^[\w]{8,14}$/; // 8~14자로 구성, 알파벳 대소문자, 숫자, _로만 구성
					let resultId = regexId.test($("#idInput").val());
					if (!resultId) {
						if ($("#idInput").val() === "") {
							console.log("2")
							$("#id_check").html("");
							$("#id_check").css("color", "black");
							idValidation = false;
							checkStepTwoVariables();
						} else {
							console.log("3")
							$("#id_check").html("아이디 형식이 맞지 않습니다. (영어 대소문자, 숫자, _로 구성된 8~14자)");
							$("#id_check").css("color", "#fb8f8a");
							idValidation = false;
							checkStepTwoVariables();
						}

					} else {
						console.log("4")
						$("#id_check").html("사용 가능한 아이디 입니다.");
						$("#id_check").css("color", "forestgreen");
						idValidation = true;
						checkStepTwoVariables();
					}
				}
			});
		}

	});

	// 비밀번호 정규식 확인
	let startPwCheck = false;
	$("#pwInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#pw_check").html("");
			$("#pw_check").css("color", "black");
			$("#pwCheck_check").html("");
			$("#pwCheck_check").css("color", "black");
			pwValidation = false;
			checkStepTwoVariables();
		} else {
			let regexPw = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_])[A-Za-z\d\W_]{8,30}$/;
			let resultPw = regexPw.test($("#pwInput").val());
			if (!resultPw) {
				$("#pw_check").html("비밀번호 형식이 올바르지 않습니다.");
				$("#pw_check").css("color", "#FB8F8A");
				pwValidation = false;
				checkStepTwoVariables();
				if ($("#pwCheckInput").val() !== "") {
					$("#pwCheck_check").html("");
					$("#pwCheck_check").css("color", "black");
				}
			} else {
				$("#pw_check").html("올바른 비밀번호 형식 입니다.");
				$("#pw_check").css("color", "forestgreen");
				console.log($("#pwCheckInput").val())
				console.log($("#pwCheckInput").val() !== "")
				startPwCheck = true;
				pwValidation = true;
				checkStepTwoVariables();
				if ($("#pwInput").val() === $("#pwCheckInput").val()) {
					$("#pwCheck_check").html("비밀번호가 일치합니다.");
					$("#pwCheck_check").css("color", "forestgreen");
					pwCheckValidation = true;
					checkStepTwoVariables();
				} else {
					if ($("#pwCheckInput").val() !== "") {
						$("#pwCheck_check").html("비밀번호가 일치하지 않습니다.");
						$("#pwCheck_check").css("color", "#FB8F8A");
						pwCheckValidation = false;
						checkStepTwoVariables();
					}
				}
			}
		}
	});

	// 비밀번호 확인
	$("#pwCheckInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#pwCheck_check").html("");
			$("#pwCheck_check").css("color", "black");
			pwCheckValidation = false;
			checkStepTwoVariables();
		} else {
			if (startPwCheck) {
				if ($("#pwCheckInput").val() !== "") {
					if ($("#pwInput").val() === $("#pwCheckInput").val()) {
						$("#pwCheck_check").html("비밀번호가 일치합니다.");
						$("#pwCheck_check").css("color", "forestgreen");
						pwCheckValidation = true;
						checkStepTwoVariables();
					} else {
						$("#pwCheck_check").html("비밀번호가 일치하지 않습니다.");
						$("#pwCheck_check").css("color", "#FB8F8A");
						pwCheckValidation = false;
						checkStepTwoVariables();
					}
				} else {
					$("#pwCheck_check").html("");
					$("#pwCheck_check").css("color", "black");
					pwCheckValidation = false;
					checkStepTwoVariables();
				}
			} else {
				$("#pwCheck_check").html("비밀번호를 입력해주세요");
				$("#pwCheck_check").css("color", "#FB8F8A");
				pwCheckValidation = false;
				checkStepTwoVariables();
			}
		}

	});

	// 이름 한글 2~5글자로 구성
	$("#nameInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#name_check").html("");
			nameValidation = false;
			checkStepTwoVariables();
		} else {
			let regexName = /^[가-힣]{2,5}$/;
			let resultName = regexName.test($("#nameInput").val());
			if (!resultName) {
				$("#name_check").html("이름 형식이 올바르지 않습니다. 한글 2~5글자로 입력해주세요.");
				$("#name_check").css("color", "#FB8F8A");
				nameValidation = false;
				checkStepTwoVariables();
			} else {
				$("#name_check").html("");
				nameValidation = true;
				checkStepTwoVariables();
			}
		}

	});

	// 전화번호 형식
	$("#phoneInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#phone_check").html("");
			phoneValidation = false;
			checkStepTwoVariables();
		} else {
			let regexPhone = /^([0-9]{4}){2}$/;
			let resultPhone = regexPhone.test($("#phoneInput").val());
			if (!resultPhone) {
				$("#phone_check").html("전화번호 형식이 올바르지 않습니다. 숫자만 입력해주세요.");
				$("#phone_check").css("color", "#FB8F8A");
				phoneValidation = false;
				checkStepTwoVariables();
			} else {
				$("#phone_check").html("");
				if ($(this).parent().parent().find(".typeName").html() !== "선택해주세요." && $(this).parent().parent().find(".typeName").html() !== "직접입력" && resultPhone) {

					$.ajax({
						url: "/member/phoneDuplicationCheck",
						type: "POST",
						data: { phone: $(this).parent().parent().find(".typeName").html() + $("#phoneInput").val() }
					}).done(function(resp) {
						if (resp) {
							$("#phone_check").html("중복된 전화번호 입니다. 다른 번호를 사용해주세요.");
							$("#phone_check").css("color", "#FB8F8A");
							phoneValidation = false;
						} else {
							phoneValidation = true;
							$("#phone_check").html("");
						}
						checkStepTwoVariables();
					})
				} else {
					phoneValidation = false;
				}
				checkStepTwoVariables();
			}
		}
	});



	// 전화번호 앞자리 선택사항
	$("#directInput").on("keyup", function() {
		let regexPhone = /^([0-9]{3})$/;
		let resultPhone = regexPhone.test($("#directInput").val());
		if (!resultPhone) {
			$("#phone_check").html("전화번호 형식이 올바르지 않습니다.");
			$("#phone_check").css("color", "#FB8F8A");
			phoneValidation = false;
			checkStepTwoVariables();
		} else {
			$("#phone_check").html("");
			phoneValidation = true;
			checkStepTwoVariables();
		}
	});

	// 주민번호 생일 형식
	$("#birthInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#ssn_check").html("");
			birthValidation = false;
			checkStepTwoVariables();
		} else {
			let regexBirth = /^([0-9]{6})$/;
			let resultBirth = regexBirth.test($("#birthInput").val());
			if (!resultBirth) {
				$("#ssn_check").html("생년월일 형식이 올바르지 않습니다.");
				$("#ssn_check").css("color", "#FB8F8A");
				birthValidation = false;
				checkStepTwoVariables();
			} else {
				$("#ssn_check").html("");
				birthValidation = true;
				checkStepTwoVariables();
			}
		}

	});

	// 주민번호 성별 형식
	$("#backId").on("keyup", function() {
		if ($(this).val() === "") {
			$("#ssn_check").html("");
			genderValidation = false;
			checkStepTwoVariables();
		} else {
			let regexGender = /^([1-4]{1})$/;
			let resultGender = regexGender.test($("#backId").val());
			if (!resultGender) {
				$("#ssn_check").html("주민번호 앞자리 형식이 올바르지 않습니다.");
				$("#ssn_check").css("color", "#FB8F8A");
				genderValidation = false;
				checkStepTwoVariables();
			} else {
				console.log($("#birthInput").val()[0])
				if ($("#birthInput").val()[0] !== "9" && ($(this).val() === "3" || $(this).val() === "4") || $("#birthInput").val()[0] == "9" && ($(this).val() === "1" || $(this).val() === "2")) {
					console.log("성별 형식 맞음")
					$("#ssn_check").html("");
					genderValidation = true;
					checkStepTwoVariables();
				}else{alert("주민번호 첫 자리가 생년월일과 맞지 않습니다.")}

			}
		}
	});
	// 닉네임 중복검사
	$("#nickNameInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#nickName_check").html("");
			$("#nickName_check").css("color", "black");
			nickValidation = true;
			checkStepTwoVariables();
		} else {
			$.ajax({
				url: "/member/chkNickname",
				type: "POST",
				data: { nickname: $(this).val() }
			}).done(function(resp) {
				if (resp) {
					$("#nickName_check").html("중복된 닉네임입니다. 다른 닉네임을 사용해주세요.");
					$("#nickName_check").css("color", "#FB8F8A");
					nickValidation = false;
				} else {
					$("#nickName_check").html("");
					$("#nickName_check").css("color", "black");
					nickValidation = true;
				}
				checkStepTwoVariables();
			})
		}
	})

	// 이메일 중복검사 및 정규식
	$("#emailInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#email_check").html("");
			$("#email_check").css("color", "black");
			emailValidation = true;
			checkStepTwoVariables();
		} else {
			let regexEmail = /^[a-zA-Z0-9_]+@[a-z]+\.[a-z]+(\.*[a-z])*$/;;
			let resultEmail = regexEmail.test($("#emailInput").val());
			console.log(resultEmail)
			if (resultEmail) {
				$.ajax({
					url: "/member/emailDuplicationCheck",
					type: "POST",
					data: { email: $(this).val() }
				}).done(function(resp) {
					if (resp) {
						$("#email_check").html("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
						$("#email_check").css("color", "#FB8F8A");
						emailValidation = false;
						checkStepTwoVariables();
					} else {
						$("#email_check").html("");
						$("#email_check").css("color", "black");
						emailValidation = true;
						checkStepTwoVariables();
					}
				});
			} else {
				$("#email_check").html("이메일 형식이 맞지 않습니다.");
				$("#email_check").css("color", "#FB8F8A");
			}
		}
	});

	$("#recommenderInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#recommender_check").html("");
			$("#recommender_check").css("color", "black");
			recommenderValidation = true;
			checkStepTwoVariables();
		} else {
			$.ajax({
				url: "/member/recommenderDuplicationCheck",
				type: "POST",
				data: { id: $(this).val() }
			}).done(function(resp) {
				console.log(resp)
				if (!resp) {
					$("#recommender_check").html("존재하지 않는 사용자입니다.");
					$("#recommender_check").css("color", "#FB8F8A");
					recommenderValidation = false;
					checkStepTwoVariables();
				} else {
					$("#recommender_check").html("");
					$("#recommender_check").css("color", "black");
					recommenderValidation = true;
					checkStepTwoVariables();
				}
			});

		}
	})

	// step 2 다음 버튼 눌렀을 때
	$('#stepTwoNextBnt').on("click", function() {

		let phoneFirst = "";
		if ($(".basicInfo__phone .typeName").html() !== "직접입력") {
			phoneFirst = $(".basicInfo__phone .typeName").html();
		} else {
			phoneFirst = $("#directInput").val();
		}

		$.ajax({
			url: "/member/signupUser",
			dataType: "json",
			type: "POST",
			data: { id: $("#idInput").val(), pw: $("#pwInput").val(), name: $("#nameInput").val(), phoneFirst: phoneFirst, phone: $("#phoneInput").val(), birth: $("#birthInput").val(), gender: $("#backId").val(), nickName: $("#nickNameInput").val(), email: $("#emailInput").val(), recommender: $("#recommenderInput").val() },
		}).done(function(resp) {
			console.log("회원가입 결과" + resp)
			if (resp) {
				// step3
				$.ajax({
					url: "/member/selectUserName",
					dataType: "json",
					type: "POST",
					data: { id: $("#idInput").val() }
				}).done(function(resp) {
					console.log(resp)
					if (signupValidation) {
						$('.stepOneBox').hide();
						$('.stepTwoBox').hide();
						$('.stepThreeBox').show();
					} else {
						$('.stepOneBox').hide();
						$('.stepTwoBox').show();
						$('.stepThreeBox').hide();
					}
					$(".userWelcome__conf").html(resp.name + "님 (" + $("#idInput").val() + ")의 회원가입이 성공적으로 완료되었습니다.")
				});
			}
		})
	});

	$("#goHome").on("click", function() {
		location.href = "/";
	})

	$("#goLogin").on("click", function() {
		location.href = "/member/goLogin";
	})
});

// 변수 값 변경을 감지하는 함수
function checkStepTwoVariables() {
	if (idValidation && pwValidation && pwCheckValidation && nameValidation && phoneValidation && birthValidation && genderValidation && nickValidation && emailValidation && recommenderValidation) {
		$("#stepTwoNextBnt").prop("disabled", false);
		$('#stepTwoNextBnt').css('backgroundColor', '#FB8F8A');
		signupValidation = true;

	} else {
		$("#stepOneNextBnt").prop("disabled", true);
		$('#stepTwoNextBnt').css('backgroundColor', '#7D7D7D');
		signupValidation = false;
	}
}

