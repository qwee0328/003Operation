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
	$("#idInput").on("keyup", function() {
		if ($(this).val() === "") {
			$("#id_check").html("");
			$("#id_check").css("color", "black");
		} else {
			console.log("머라도 있음")
			$.ajax({
				url: "/member/idDuplicationCheck",
				type: "POST",
				data: { id: $(this).val() }
			}).done(function(resp) {
				console.log(resp);
				if (resp === "true") {
					$("#id_check").html("사용 불가능한 아이디 입니다.");
					$("#id_check").css("color", "#fb8f8a");
				} else { // 중복되는 아이디가 없다면 유효성 검사 확인
					let regexId = /^[\w]{8,14}$/; // 8~14자로 구성, 알파벳 대소문자, 숫자, _로만 구성
					let resultId = regexId.test($("#idInput").val());
					if (!resultId) {
						if ($("#idInput").val() === "") {
							$("#id_check").html("");
							$("#id_check").css("color", "black");
						} else {
							$("#id_check").html("아이디 형식이 맞지 않습니다. (영어 대소문자, 숫자, _로 구성된 8~14자)");
							$("#id_check").css("color", "#fb8f8a");
						}

					} else {
						$("#id_check").html("사용 가능한 아이디 입니다.");
						$("#id_check").css("color", "forestgreen");
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
		} else {
			let regexPw = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_])[A-Za-z\d\W_]{8,30}$/;
			let resultPw = regexPw.test($("#pwInput").val());
			if (!resultPw) {
				$("#pw_check").html("비밀번호 형식이 올바르지 않습니다.");
				$("#pw_check").css("color", "#FB8F8A");
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
				if ($("#pwInput").val() === $("#pwCheckInput").val()) {
					$("#pwCheck_check").html("비밀번호가 일치합니다.");
					$("#pwCheck_check").css("color", "forestgreen");
				} else {
					if ($("#pwCheckInput").val() !== "") {
						$("#pwCheck_check").html("비밀번호가 일치하지 않습니다.");
						$("#pwCheck_check").css("color", "#FB8F8A");
					}
				}
			}
		}
	});

	// 비밀번호 확인
	$("#pwCheckInput").on("keyup", function() {
		console.log("durl")
		if (startPwCheck) {
			if ($("#pwCheckInput").val() !== "") {
				if ($("#pwInput").val() === $("#pwCheckInput").val()) {
					$("#pwCheck_check").html("비밀번호가 일치합니다.");
					$("#pwCheck_check").css("color", "forestgreen");
				} else {
					$("#pwCheck_check").html("비밀번호가 일치하지 않습니다.");
					$("#pwCheck_check").css("color", "#FB8F8A");
				}
			} else {
				$("#pwCheck_check").html("");
				$("#pwCheck_check").css("color", "black");
			}
		} else {
			$("#pwCheck_check").html("비밀번호를 입력해주세요");
			$("#pwCheck_check").css("color", "#FB8F8A");
		}
	});

	// 이름 한글 2~5글자로 구성
	$("#nameInput").on("keyup", function() {
		let regexName = /^[가-힣]{2,5}$/;
		let resultName = regexName.test($("#nameInput").val());
		if (!resultName) {
			$("#name_check").html("이름 형식이 올바르지 않습니다. 한글 2~5글자로 입력해주세요.");
			$("#name_check").css("color", "#FB8F8A");
		} else {
			$("#name_check").html("");
		}
	});
	
	// 전화번호 형식
	$("#phoneInput").on("keyup",function(){
		let regexPhone = /^(-?[0-9]{4}){2}$/;
		let resultPhone = regexPhone.test($("#phoneInput").val());
		if(!resultPhone){
			$("#phone_check").html("전화번호 형식이 올바르지 않습니다.");
			$("#phone_check").css("color", "#FB8F8A");
		}else{
			$("#phone_check").html("");
		}
	});
	
	// 전화번호 앞자리 선택사항
	$("#directInput").on("keyup",function(){
		let regexPhone = /^(-?[0-9]{3})$/;
		let resultPhone = regexPhone.test($("#directInput").val());
		if(!resultPhone){
			$("#phone_check").html("전화번호 형식이 올바르지 않습니다.");
			$("#phone_check").css("color", "#FB8F8A");
		}else{
			$("#phone_check").html("");
		}
	});
	
	// 주민번호 생일 형식
	$("#birthInput").on("keyup",function(){
		let regexBirth = /^(-?[0-9]{6})$/;
		let resultBirth = regexBirth.test($("#birthInput").val());
		if(!resultBirth){
			$("#ssn_check").html("생년월일 형식이 올바르지 않습니다.");
			$("#ssn_check").css("color", "#FB8F8A");
		}else{
			$("#ssn_check").html("");
		}
	});
	
	// 주민번호 생일 형식
	$("#backId").on("keyup",function(){
		let regexGender = /^(-?[1-4]{1})$/;
		let resultGender = regexGender.test($("#backId").val());
		if(!resultGender){
			$("#ssn_check").html("주민번호 앞자리 형식이 올바르지 않습니다.");
			$("#ssn_check").css("color", "#FB8F8A");
		}else{
			$("#ssn_check").html("");
		}
	});
});