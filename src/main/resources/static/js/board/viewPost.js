let userNick;
$(document).ready(function() {
	let category = "free";
	if ($(".boardType").text().slice(0, 2) == "질문") category = "question";


	$.ajax({
		url: "/member/selectUserNick",
		type: "post"
	}).done(function(resp) {
		userNick = resp;
	})

	$.ajax({
		url: "/board/isMyPost",
		data: { memberNickname: $("#memberNickname").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		if (resp) {
			let postUpdate = $("<button>").attr("id", "postUpdate").attr("data-id", $("#postId").val()).html("수정")
			let postDelete = $("<button>").attr("id", "postDelete").attr("data-id", $("#postId").val()).html("삭제")
			$(".postInfo__right").append(postUpdate).append(postDelete);
		}
	});

	// 게시글 수정
	$(document).on("click", "#postUpdate", function() {
		let url = "/board/goUpdatePost/free";
		if ($(".postTitle").html().slice(0, 2) == "질문") {
			url = "/board/goUpdatePost/question";
		}
		url += "/" + $(this).attr('data-id') + "?select=" + $("#select").val() + "&keyword=" + $("#keyword").val();
		location.href = url;
	});

	// 게시글 삭제
	$(document).on("click", "#postDelete", function() {
		let url = "/board/deletePost/free";
		if ($(".postTitle").html().slice(0, 2) == "질문") {
			url = "/board/deletePost/question";
		}
		url += "/" + $(this).attr('data-id');

		let check = confirm("게시물을 삭제하시겠습니까?\n삭제한 게시글은 되돌릴 수 없습니다.")
		if (check) {
			location.href = url;
		}
	});

	// 프로필 이미지 불러오기
	$.ajax({
		url: "/member/selectProfileImgByNickBoard",
		data: { memberNickname: $("#memberNickname").val() },
		type: "post"
	}).done(function(resp) {
		if (resp !== "") {
			$("#writer_profile").attr("src", "/profileImgs/" + resp);
		} else {
			$("#writer_profile").attr("src", "/images/profileImg.png");
		}
	});

	// 추천 수, 북마크 수, 댓글 수 불러오기
	postInfo();

	// 파일 불러오기
	let fileUrl = "/board/selectFileById";
	if ($("#boardType").val() === "자유") {
		fileUrl += "/free";
	} else {
		fileUrl += "/question";
	}

	$.ajax({
		url: fileUrl,
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)

		if (resp.length > 0) {
			let files = $("<div>").attr("class", "files");
			let title = $("<div>").attr("class", "files__title").html("첨부파일");
			let conf = $("<div>").attr("calss", "files__conf");
			for (let i = 0; i < resp.length; i++) {
				let fileLine = $("<div>").attr("class", "files__Line").attr("sysName", resp[i].system_name).attr("oriName", resp[i].origin_name);
				let fileDown = $("<a>").attr("class", "fileDown").attr("href", "/board/downloadFile?sysName=" + resp[i].system_name + "&oriName=" + resp[i].origin_name);
				let fileIcon = $("<i>").attr("class", "fa-regular fa-file");
				let fileName = $("<div>").html(resp[i].origin_name);
				fileDown.append(fileIcon).append(fileName);
				conf.append(fileLine.append(fileDown));
			}
			files.append(title).append(conf);
			$(".postConf").after(files);
		}

	});

	// 게시글 추천, 북마크
	$("#recommendBtn, #bookmarkBtn").on("click", function() {

		// 추천 혹은 북마크 하기
		if (($("#myRecommendRecord").val() === "false" && $(this).attr("id") === "recommendBtn") || ($("#myBookmarkRecord").val() === "false" && $(this).attr("id") === "bookmarkBtn")) {
			let url = "/board/insertPostInfoById";
			console.log($(this).attr("id") === "recommendBtn")
			if ($(this).attr("id") === "recommendBtn") {
				url = url + "/recommend";
			} else {
				url = url + "/bookmark";
			}

			let icon = $(this).find("i");

			$.ajax({
				url: url,
				data: { postId: $("#postId").val() },
				type: "post"
			}).done(function(resp) {
				// 추천 수, 북마크 수, 댓글 수 불러오기
				postInfo();
				console.log($(this))
				icon.css("color", "#FB8F8A");
			})
		}
		if (($("#myRecommendRecord").val() === "true" && $(this).attr("id") === "recommendBtn") || ($("#myBookmarkRecord").val() === "true" && $(this).attr("id") === "bookmarkBtn")) {
			let url = "/board/deletePostInfoById";
			if ($(this).attr("id") === "recommendBtn") {
				url = url + "/recommend";
			} else {
				url = url + "/bookmark";
			}

			let icon = $(this).find("i");

			$.ajax({
				url: url,
				data: { postId: $("#postId").val() },
				type: "post"
			}).done(function(resp) {
				// 추천 수, 북마크 수, 댓글 수 불러오기
				postInfo();
				icon.css("color", "black");
			})
		}

	});

	// 목록으로 이동 버튼
	$("#boardListBtn").on("click", function() {
		let url = "/board/listBoard";

		url += "/" + category + "?select=" + $("#select").val() + "&keyword=" + $("#keyword").val();
		location.href = url;
	});

	// 이전글 다음글 불러오기
	console.log(category)
	console.log($("#keyword").val())
	$.ajax({
		url: "/board/selectPrevNextPost",
		data: { postId: $("#postId").val(), category: category, keyword: $("#keyword").val(), select: $("#select").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)

		if (resp.NextId) {
			console.log("asdf")
			let downPage = $("<div>").attr("class", "pageBtns__pageLine").attr("id", "downPage");
			let iconBox = $("<div>").attr("class", "pageLine__icon").attr("data-id", resp.NextId);
			let icon = $("<i>").attr("class", "fa-solid fa-chevron-up");
			let text = $("<div>").html("다음글");
			iconBox.append(icon).append(text);

			let upPageTitle = $("<div>").attr("class", "pageLine__title").html(resp.NextTitle).attr("data-id", resp.NextId);;
			downPage.append(iconBox).append(upPageTitle);
			$(".pageBtns").append(downPage);
		}
		if (resp.PrevId) {
			console.log("dlwjsrmf")
			let upPage = $("<div>").attr("class", "pageBtns__pageLine").attr("id", "upPage");
			let iconBox = $("<div>").attr("class", "pageLine__icon").attr("data-id", resp.PrevId);
			let icon = $("<i>").attr("class", "fa-solid fa-chevron-down");
			let text = $("<div>").html("이전글");
			iconBox.append(icon).append(text);

			let upPageTitle = $("<div>").attr("class", "pageLine__title").html(resp.PrevTitle).attr("data-id", resp.PrevId);
			upPage.append(iconBox).append(upPageTitle);
			$(".pageBtns").append(upPage);
		}
	});

	// 이전, 다음 글로 이동하기
	$(document).on("click", "#upPage > .pageLine__icon, #upPage > .pageLine__title, #downPage > .pageLine__icon, #downPage > .pageLine__title", function() {
		console.log("dlqpsxm")
		let url = "/board/viewPostConf/" + category + "/" + $("#select").val() + "/" + $(this).attr("data-id") + "?keyword=" + $("#keyword").val();
		location.href = url;
	});

	// 댓글 목록 불러오기
	let replyCpage = 1;
	selectreplyList(replyCpage);

	// 댓글 작성하기
	$("#replyInputSubmit").on("click", function() {
		insertReply();
		selectreplyList(replyCpage);
	});

	// 댓글 작성중 엔터 누르면 작성 시도
	$("#replyInput").on("keydown", function(e) {
		console.log("enter")
		if (e.keyCode == 13&&$(this)) {
			
			insertReply();
			selectreplyList(replyCpage);
		}
	});

	// 댓글 추천하기
	$(document).on("click", ".replyRecommendBtn", function() {
		console.log($(this).attr("isrecommed"))
		console.log($(this).attr("isrecommed") === "false")
		if ($(this).attr("isrecommed") === "false") {
			$.ajax({
				url: "/board/insertReplyRecommend",
				data: { replyId: $(this).attr("data-id") },
				type: "post"
			}).done(function(resp) {
				selectreplyList(replyCpage);
			})
		} else {
			$.ajax({
				url: "/board/deleteReplyRecommend",
				data: { replyId: $(this).attr("data-id") },
				type: "post"
			}).done(function(resp) {
				selectreplyList(replyCpage);
			})
		}
	});

	// 댓글 삭제하기
	$(document).on("click", ".replyDeleteBtn", function() {
		if (replyBackup != null) { // 수정중인 댓글이 있으면?
			if (confirm("수정중인 댓글이 있습니다.\n댓글 수정을 취소하시겠습니까?")) {
				replyObj.closest(".replyLine").find(".replyConf").html(replyBackup);
				replyObj.closest(".replyLine").find(".replyConf").attr("contenteditable", false);
			}
		} else if (nestedReplyObj != null) {
			if (confirm("답글 작성을 취소하시겠습니가?")) {
				nestedReplyObj.remove();
				nestedReplyParentSeq = null;
				nestedReplyObj = null;
			} else {
				return false;
			}
		} else {
			let isdelete = confirm("댓글을 삭제하시겠습니까?\n삭제한 댓글은 되돌릴 수 없습니다.");
			if (isdelete) {
				$.ajax({
					url: "/board/deleteReply",
					data: { replyId: $(this).attr("data-id") },
					type: "post"
				}).done(function(resp) {
					selectreplyList(replyCpage);
				})
			}
		}
	});

	let replyBackup = null; // 수정중인 댓글 원본 내용
	let replyObj = null; // 수정중이던 댓글
	let nestedReplyParentSeq = null; // 수정중이던 답글의 부모 댓글 번호
	let nestedReplyObj = null; // 수정중이던 답글

	// 댓글 수정 눌렀을 때
	$(document).on("click", ".replyModifyBtn", function() {
		if (replyBackup != null) { // 수정중인 댓글이 있으면?
			if (confirm("수정중인 댓글이 있습니다.\n댓글 수정을 취소하시겠습니까?")) {
				replyObj.closest(".replyLine").find(".replyConf").html(replyBackup);
				replyObj.closest(".replyLine").find(".replyConf").attr("contenteditable", false);
			}
		}

		// 수정중인 답글이 있으면?
		if (nestedReplyObj != null) {
			if ($(this).attr("parent-id") !== nestedReplyParentSeq) {
				if (confirm("작성중인 답글이 있습니다.\n답글 수정을 취소하시겠습니가?")) {
					nestedReplyObj.remove();
					nestedReplyParentSeq = null;
					nestedReplyObj = null;
				}
			}
		}

		$(this).closest(".replyLine").find(".replyConf").attr("contenteditable", true).focus();
		$(this).parent().find("button").css("display", "none");

		let modify = $("<button>").attr("class", "modifySubmit").attr("data-id", $(this).attr("data-id")).html("수정완료").css("margin-right", "20px");
		let cancle = $("<button>").attr("class", "modifyCancle").attr("data-id", $(this).attr("data-id")).html("수정취소");
		$(this).parent().append(modify).append(cancle)

		replyBackup = $(this).closest(".replyLine").find(".replyConf").html();
		replyObj = $(this);
	});

	// 댓글 수정 취소
	$(document).on("click", ".modifyCancle", function() {
		$(this).closest(".replyLine").find(".replyConf").html(replyBackup);
		$(this).closest(".replyLine").find(".replyConf").attr("contenteditable", false);
		$(this).parent().find("button").css("display", "none");
		$(this).parent().find(".replyModifyBtn").css("display", "inline-block");
		$(this).parent().find(".replyDeleteBtn").css("display", "inline-block");

		replyBackup = null;
		replyObj = null;
	});

	// 댓글 수정 완료
	$(document).on("click", ".modifySubmit", function() {
		let replyContents = $(this).closest(".replyLine").find(".replyConf").html();
		$.ajax({
			url: "/board/updateReply",
			data: { replyId: $(this).attr("data-id"), replyContents: replyContents },
			type: "post"
		}).done(function(resp) {
			console.log(resp);
			if (resp !== 0) {
				alert("댓글 수정이 완료되었습니다.")
			}
		})
	});

	// 답글 달기 버튼
	$(document).on("click", ".replyRe", function() {
		let rereplyInput = $("<div>").attr("class", "rereplyInput");
		let replyInput__userProfile = $("<div>").attr("class", "replyInput__userProfile");
		let userProfileImg = $("<img>").attr("id", "userProfileImg");
		$.ajax({
			url: "/member/selectProfileImgById",
			type: "post"
		}).done(function(resp) {
			if (resp !== null) {
				userProfileImg.attr("src", "/profileImgs/" + resp);
			} else {
				userProfileImg.attr("src", "/images/profileImg.png");
			}
		})

		let replyInput__input = $("<div>").attr("class", "replyInput__input");
		let replyInput = $("<input>").attr("type", "text").attr("id", "reReplyInputTag").attr("placeholder", "답글을 입력해주세요.");
		let replyInputSubmit = $("<button>").attr("id", "replyInputSubmit").html("입력").attr("parent-id", $(this).attr("data-id"));
		let replyInputCancle = $("<button>").attr("id", "replyInputCancle").html("취소");

		replyInput__userProfile.append(userProfileImg);
		replyInput__input.append(replyInput).append(replyInputSubmit).append(replyInputCancle);
		rereplyInput.append(replyInput__userProfile).append(replyInput__input);
		$(this).closest(".replyLine").append(rereplyInput);

	})

	// 답글 작성 완료
	$(document).on("click", "#replyInputSubmit", function() {
		let content = $(this).parent().find("#replyInput").val();
		let thisDiv = $(this);
		console.log(content)
		$.ajax({
			url: "/board/insertReReply",
			data: { postId: $("#postId").val(), parentId: $(this).attr("parent-id"), content: content },
			type: "post"
		}).done(function(resp) {
			if (resp) {
				alert("댓글이 작성되었습니다.");
				console.log(thisDiv)
				thisDiv.closest(".rereplyInput").remove();
				selectreplyList(replyCpage);
			}
		})
	})
	
	

	// 답글 작성 취소
	$(document).on("click", "#replyInputCancle", function() {
		$(this).closest(".rereplyInput").remove();
	})
});

// 추천 수, 북마크 수, 댓글 수 불러오기
function postInfo() {
	$.ajax({
		url: "/board/selectPostInfoById",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		$(".recommendCount").html(resp.recommend);
		$(".bookmarkCount").html(resp.bookmark);
		$(".replyCount").html(resp.reply);
	})

	myPostInfo();
}

function myPostInfo() {
	// 내가 추천 혹은 북마크를 했는지 불러오기
	$.ajax({
		url: "/board/selectPostInfoFromMy",
		data: { postId: $("#postId").val() },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		$("#myRecommendRecord").val(resp.recommend !== null ? resp.recommend : false);
		$("#myBookmarkRecord").val(resp.bookmark !== null ? resp.bookmark : false);

		if (resp.recommend) {
			$("#recommendBtn").find("i").css("color", "#FB8F8A");
		}

		if (resp.bookmark) {
			$("#bookmarkBtn").find("i").css("color", "#FB8F8A");
		}
	});
}

// 댓글 작성
function insertReply() {

	if ($("#replyInput").val() !== "") {
		$.ajax({
			url: "/board/insertPostReply",
			data: { postId: $("#postId").val(), reply: $("#replyInput").val() },
			type: "post"
		}).done(function(resp) {
			if (resp) {
				alert("댓글이 작성되었습니다.");
				$("#replyInput").val("");
			}
		})
	} else {
		alert("댓글 내용을 입력해주세요.");
		$("#replyInput").focus();
	}
}

// 시간 계산
function timeAgo(timestamp) {
	const date = new Date(timestamp);
	const now = new Date();

	const seconds = Math.floor((now - date) / 1000);
	const minutes = Math.floor(seconds / 60);
	const hours = Math.floor(minutes / 60);
	const days = Math.floor(hours / 24);

	if (seconds < 60) {
		return seconds + "초 전";
	} else if (minutes < 60) {
		return minutes + "분 전";
	} else if (hours < 24) {
		return hours + "시간 전";
	} else {
		const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
		return date.toLocaleDateString('ko-KR', options);
	}
}

// 댓글 페이지네이션
function pagination(postSeq, recordTotalCount, replyCurPage, recordCountPerPage, naviCountPerPage) {
	if (recordTotalCount != 0) {
		let pagination = $("#pagination");
		pagination.html("");

		let pageTotalCount = 0;
		pageTotalCount = Math.ceil(recordTotalCount / recordCountPerPage);

		console.log(pageTotalCount)

		// 비정상 접근 차단
		if (replyCurPage < 1) {
			replyCurPage = 1;
		} else if (replyCurPage > pageTotalCount) {
			replyCurPage = pageTotalCount;
		}

		let startNavi = Math.floor((replyCurPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		let endNavi = startNavi + (naviCountPerPage - 1);


		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		let needPrev = true;
		let needNext = true;
		console.log(startNavi)
		console.log(endNavi)

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		if (startNavi != 1) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-angles-left'></i>");
			aTag.attr("class", "colorBlack fontEnglish");
			aTag.on("click", function() {
				$("#replyList").html("");
				selectreplyList(1);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (needPrev) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-chevron-left'></i>");
			aTag.on("click", function() {
				$("#replyList").html("");
				selectreplyList((startNavi - 1));
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		for (let i = startNavi; i <= endNavi; i++) {
			let aTag = $("<a>");
			aTag.html(i);
			aTag.on("click", function() {
				$("#replyList").html("");
				selectreplyList(i);
			});
			if (i == replyCurPage) {
				//	aTag.addClass("colorWhite bColorBlue fontEnglish");
			}
			pagination.append(aTag);
		}

		if (needNext) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-chevron-right'></i>");
			aTag.on("click", function() {
				$("#replyList").html("");
				selectreplyList((endNavi + 1));
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}

		if (endNavi != pageTotalCount) {
			let aTag = $("<a>");
			let iTag = $("<i class='fa-solid fa-angles-right'></i>");
			aTag.on("click", function() {
				$("#replyList").html("");
				selectreplyList(pageTotalCount);
			});
			aTag.append(iTag);
			pagination.append(aTag);
		}
	}
}

// 댓글 내용 불러오기
function selectreplyList(replyCpage) {
	$.ajax({
		url: "/board/selectPostReplyAll",
		data: { postId: $("#postId").val(), replyCpage: replyCpage },
		type: "post"
	}).done(function(resp) {
		console.log(resp)
		$("#replyList").empty();
		$("#replyCount").html(resp.recordTotalCount);
		for (let i = 0; i < resp.replyList.length; i++) {
			/*let replyLine = $("<div>").attr("class", "replyLine");
			let replyWriterInfo = $("<div>").attr("class", "replyWriterInfo");

			let writerProfile = $("<div>").attr("class", "writerProfile");
			let profileImg = $("<img>");
			if (resp.replyList[i].profile_image !== null) {
				profileImg.attr("src", "/profileImgs/" + resp.replyList[i].profile_image);
			} else {
				profileImg.attr("src", "/images/profileImg.png");
			}
			let nick = $("<span>").html(resp.replyList[i].member_nickname);
			let isWriter = $("<span>").attr("class", "writer").html("글쓴이");
			let writeDate = $("<span>").attr("class", "time").html(timeAgo(resp.replyList[i].write_date));

			writerProfile.append(profileImg).append(nick);
			if (resp.replyList[i].member_id === $("#writerId").val()) {
				writerProfile.append(isWriter);
			}
			writerProfile.append(writeDate);

			let iconDiv = $("<div>");
			let modifyBtn = $("<button>").html("수정").attr("class", "replyModifyBtn").attr("data-id", resp.replyList[i].id);
			let deleteBtn = $("<button>").html("삭제").attr("class", "replyDeleteBtn").attr("data-id", resp.replyList[i].id);
			iconDiv.append(modifyBtn).append(deleteBtn);

			replyWriterInfo.append(writerProfile)
			if (resp.replyList[i].member_id === $("#writerId").val()) {
				replyWriterInfo.append(iconDiv);
			}

			let replyConf = $("<div>").attr("class", "replyConf").html(resp.replyList[i].content);
			let replyInfo = $("<div>").attr("class", "replyInfo");
			let recommend = $("<span>").attr("data-id", resp.replyList[i].id).attr("class", "replyRecommendBtn").attr("isrecommed", resp.replyList[i].isrecommend);

			let thumbsIcon = $("<i>").attr("class", "fa-regular fa-thumbs-up");
			if (resp.replyList[i].isrecommend) {
				recommend.css("color", "#FB8F8A");
				thumbsIcon.css("color", "#FB8F8A");
			}
			recommend.append(thumbsIcon).append(" 추천 ").append(resp.replyList[i].count);
			let replyBtn = $("<span>").attr("data-id", resp.replyList[i].id).html("답글달기").attr("class", "replyRe").attr("replyWriter", resp.replyList[i].member_nickname);
			replyInfo.append(recommend).append(replyBtn);

			replyLine.append(replyWriterInfo).append(replyConf).append(replyInfo);
			$("#replyList").append(replyLine);
			console.log("i"+i)*/

			$.ajax({
				url: "/board/selectReReplyAll",
				data: { parentId: resp.replyList[i].id },
				type: "post"
			}).done(function(data) {
				console.log(data)
				console.log("test" + i)

				let replyLine = $("<div>").attr("class", "replyLine");
				let replyWriterInfo = $("<div>").attr("class", "replyWriterInfo");

				let writerProfile = $("<div>").attr("class", "writerProfile");
				let profileImg = $("<img>");
				if (resp.replyList[i].profile_image !== null) {
					profileImg.attr("src", "/profileImgs/" + resp.replyList[i].profile_image);
				} else {
					profileImg.attr("src", "/images/profileImg.png");
				}
				let nick = $("<span>").html(resp.replyList[i].member_nickname);
				let isWriter = $("<span>").attr("class", "writer").html("글쓴이");
				let writeDate = $("<span>").attr("class", "time").html(timeAgo(resp.replyList[i].write_date));

				writerProfile.append(profileImg).append(nick);
				if (resp.replyList[i].member_nickname === $("#memberNickname").val()) {
					writerProfile.append(isWriter);
				}
				writerProfile.append(writeDate);

				let iconDiv = $("<div>");
				let modifyBtn = $("<button>").html("수정").attr("class", "replyModifyBtn").attr("data-id", resp.replyList[i].id);
				let deleteBtn = $("<button>").html("삭제").attr("class", "replyDeleteBtn").attr("data-id", resp.replyList[i].id);
				iconDiv.append(modifyBtn).append(deleteBtn);

				replyWriterInfo.append(writerProfile)
				if (resp.replyList[i].member_nickname === userNick) {
					replyWriterInfo.append(iconDiv);
				}

				let replyConf = $("<div>").attr("class", "replyConf").html(resp.replyList[i].content);
				let replyInfo = $("<div>").attr("class", "replyInfo");
				let recommend = $("<span>").attr("data-id", resp.replyList[i].id).attr("class", "replyRecommendBtn").attr("isrecommed", resp.replyList[i].isrecommend);

				let thumbsIcon = $("<i>").attr("class", "fa-regular fa-thumbs-up");
				if (resp.replyList[i].isrecommend) {
					recommend.css("color", "#FB8F8A");
					thumbsIcon.css("color", "#FB8F8A");
				}
				recommend.append(thumbsIcon).append(" 추천 ").append(resp.replyList[i].count);
				let replyBtn = $("<span>").attr("data-id", resp.replyList[i].id).html("답글달기").attr("class", "replyRe").attr("replyWriter", resp.replyList[i].member_nickname);
				replyInfo.append(recommend).append(replyBtn);

				replyLine.append(replyWriterInfo).append(replyConf).append(replyInfo);
				$("#replyList").append(replyLine);


				for (let j = 0; j < data.length; j++) {
					let replyLine = $("<div>").attr("class", "RereplyLine");
					let replyWriterInfo = $("<div>").attr("class", "replyWriterInfo");

					let writerProfile = $("<div>").attr("class", "writerProfile");
					let profileImg = $("<img>");
					if (data[j].profile_image !== null) {
						profileImg.attr("src", "/profileImgs/" + data[j].profile_image);
					} else {
						profileImg.attr("src", "/images/profileImg.png");
					}
					let nick = $("<span>").html(data[j].member_nickname);
					let isWriter = $("<span>").attr("class", "writer").html("글쓴이");
					let writeDate = $("<span>").attr("class", "time").html(timeAgo(data[j].write_date));

					writerProfile.append(profileImg).append(nick);
					if (data[j].member_nickname === $("#memberNickname").val()) {
						writerProfile.append(isWriter);
					}
					writerProfile.append(writeDate);

					let iconDiv = $("<div>");
					let modifyBtn = $("<button>").html("수정").attr("class", "replyModifyBtn").attr("data-id", data[j].id);
					let deleteBtn = $("<button>").html("삭제").attr("class", "replyDeleteBtn").attr("data-id", data[j].id);
					iconDiv.append(modifyBtn).append(deleteBtn);
					replyWriterInfo.append(writerProfile)
					console.log()
					if (data[j].member_nickname === userNick) {
						replyWriterInfo.append(iconDiv);
					}

					let replyConf = $("<div>").attr("class", "replyConf").html(data[j].content);
					let replyInfo = $("<div>").attr("class", "replyInfo");
					let recommend = $("<span>").attr("data-id", data[j].id).attr("class", "replyRecommendBtn").attr("isrecommed", data[j].isrecommend);

					let thumbsIcon = $("<i>").attr("class", "fa-regular fa-thumbs-up");
					if (data.isrecommend) {
						recommend.css("color", "#FB8F8A");
						thumbsIcon.css("color", "#FB8F8A");
					}
					recommend.append(thumbsIcon).append(" 추천 ").append(data[j].count);
					replyInfo.append(recommend)

					replyLine.append(replyWriterInfo).append(replyConf).append(replyInfo);
					$("#replyList").append(replyLine);
					console.log("j" + i)
				}
			})
		}
		pagination($("#postId").val(), resp.recordTotalCount, resp.replyCpage, resp.recordCountPerPage, resp.naviCountPerPage);
		console.log($("#pagination"))
		for (let i = 0; i < $("#pagination").find("a").length; i++) {
			if ($("#pagination").find("a")[i].innerText == replyCpage) {
				$($("#pagination").find("a")[i]).css("background-color", "#375abb").css("color", "white");
			}
		}
	})
}