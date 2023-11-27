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
		console.log($(this).val())
		if ($(this).val() === "") {
			console.log("비어있음")
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
						if ($($("#idInput").val()).val() === "") {
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

	})
});