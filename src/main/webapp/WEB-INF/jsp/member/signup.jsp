<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css" />

<!-- singup js/css -->
<link rel="stylesheet" href="/css/member/signup.css">
<script type="text/javascript" src="/js/member/signup.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
		<div class="signup_guide">
			<div class="title">회원가입</div>
			<div class="stepOneBox">
				<div class="stepBox">
					<div class="stepBox__step currentStep">
						<div class="step__info">STEP 01</div>
						<div class="step__title">약관동의</div>
					</div>
					<div class="stepBox__arrow">
						<i class="fa-solid fa-chevron-right"></i>
					</div>
					<div class="stepBox__step">
						<div class="step__title">정보입력</div>
					</div>
					<div class="stepBox__arrow">
						<i class="fa-solid fa-chevron-right"></i>
					</div>
					<div class="stepBox__step">
						<div class="step__title">가입완료</div>
					</div>
				</div>
				<div class="totallyAgree">
					<input type="checkbox" id="chk_all"> <label for="chk_all">약관
						전체 동의</label>
				</div>
				<div class="terms">
					<div class="terms__use">
						<div class="use__agree">
							<input type="checkbox" id="chk_use"> <label for="chk_use"><span class="fontLogo signupLogo">003</span>
								이용약관<span>(필수)</span>
							</label>
						</div>
						<div class="use__detail">
							<div class="article">
								<div class="article__title">여러분을 환영합니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span> 서비스를 이용해 주셔서 감사합니다. 본 약관은
									다양한 <span class="fontLogo signupLogo">003</span> 서비스의 이용과 관련하여 <span class="fontLogo signupLogo">003</span> 서비스를 제공하는 003작전팀과 이를 이용하는 <span class="fontLogo signupLogo">003</span> 서비스
									회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 <span class="fontLogo signupLogo">003</span> 서비스 이용에 도움이 될 수 있는
									유익한 정보를 포함하고 있습니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span> 서비스를 이용하시거나 <span class="fontLogo signupLogo">003</span> 서비스 회원으로
									가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게
									살펴봐 주시기 바랍니다.</div>
							</div>
							<div class="article">
								<div class="article__title">다양한 <span class="fontLogo signupLogo">003</span> 서비스를 즐겨보세요.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>은 키오스크 환경 연습, 키오스크 환경 게임,
									다른 이용자와의 커뮤니케이션 등 여러분의 키오스크 이용에 편리함을 더할 수 있는 다양한 서비스를 제공하고
									있습니다. 여러분은 PC, 휴대폰 등 인터넷 이용이 가능한 각종 단말기를 통해 각양각색의 <span class="fontLogo signupLogo">003</span> 서비스를
									자유롭게 이용하실 수 있으며, 개발 환경 상 PC를 이용하는 것을 추천드립니다. 개별 서비스들의 구체적인 내용은
									공지사항, QNA, 웹 챗봇 등에서 쉽게 확인하실 수 있습니다.</div>
							</div>
							<div class="article">
								<div class="article__title">회원으로 가입하시면 <span class="fontLogo signupLogo">003</span> 서비스를 보다 편리하게
									이용할 수 있습니다.</div>
								<div class="article__text">여러분은 본 약관을 읽고 동의하신 후 회원 가입을
									신청하실 수 있으며, <span class="fontLogo signupLogo">003</span>은 이에 대한 승낙을 통해 회원 가입 절차를 완료하고 여러분께 <span class="fontLogo signupLogo">003</span> 서비스 이용
									계정(이하 ‘계정’)을 부여합니다. 계정이란 회원이 <span class="fontLogo signupLogo">003</span> 서비스에 로그인한 이후 이용하는 각종 서비스 이용
									이력을 회원 별로 관리하기 위해 설정한 회원 식별 단위를 말합니다. 회원은 자신의 계정을 통해 좀더 다양한
									<span class="fontLogo signupLogo">003</span> 서비스를 보다 편리하게 이용할 수 있습니다.</div>
							</div>
							<div class="article">
								<div class="article__title">여러분이 제공한 콘텐츠를 소중히 다룰 것입니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>은 여러분이 게재한 게시물이 <span class="fontLogo signupLogo">003</span> 서비스를
									통해 다른 이용자들에게 전달되어 우리 모두의 삶을 더욱 풍요롭게 해줄 것을 기대합니다. 게시물은 여러분이 타인
									또는 자신이 보게 할 목적으로 <span class="fontLogo signupLogo">003</span> 서비스 상에 게재한 부호, 문자, 음성, 음향, 그림, 사진, 동영상,
									링크 등으로 구성된 각종 콘텐츠 자체 또는 파일을 말합니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>은 여러분의 생각과 감정이 표현된 콘텐츠를 소중히
									보호할 것을 약속 드립니다. 여러분이 제작하여 게재한 게시물에 대한 지식재산권 등의 권리는 당연히 여러분에게
									있습니다.</div>
								<div class="article__text">여러분은 <span class="fontLogo signupLogo">003</span> 서비스 내에 콘텐츠 삭제, 비공개 등의
									관리기능이 제공되는 경우 이를 통해 직접 타인의 이용 또는 접근을 통제할 수 있고, 고객센터를 통해서도 콘텐츠의
									삭제, 비공개, 검색결과 제외 등의 조치를 요청할 수 있습니다. 다만, 일부 <span class="fontLogo signupLogo">003</span> 서비스의 경우 삭제, 비공개
									등의 처리가 어려울 수 있으며, 이러한 내용은 각 서비스 상의 안내, 공지사항, 고객센터 도움말 등에서 확인해
									주시길 부탁 드립니다.</div>
							</div>
							<div class="article">
								<div class="article__title">여러분의 개인정보를 소중히 보호합니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>은 서비스의 원활한 제공을 위하여 회원이 동의한
									목적과 범위 내에서만 개인정보를 수집∙이용하며, 개인정보 보호 관련 법령에 따라 안전하게 관리합니다.</div>
							</div>
							<div class="article">
								<div class="article__title"><span class="fontLogo signupLogo">003</span> 서비스 이용과 관련하여 몇 가지 주의사항이
									있습니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>은 여러분이 <span class="fontLogo signupLogo">003</span> 서비스를 자유롭고 편리하게
									이용할 수 있도록 최선을 다하고 있습니다. 다만, 여러분이 <span class="fontLogo signupLogo">003</span> 서비스를 보다 안전하게 이용하고 <span class="fontLogo signupLogo">003</span>
									서비스에서 여러분과 타인의 권리가 서로 존중되고 보호받으려면 여러분의 도움과 협조가 필요합니다. 여러분의 안전한
									서비스 이용과 권리 보호를 위해 부득이 아래와 같은 경우 여러분의 게시물 게재나 <span class="fontLogo signupLogo">003</span> 서비스 이용이 제한될 수
									있으므로, 이에 대한 확인 및 준수를 요청 드립니다.</div>
								<div class="article__text">회원 가입 시 이름, 생년월일, 휴대전화번호 등의 정보를
									허위로 기재해서는 안 됩니다. 회원 계정에 등록된 정보는 항상 정확한 최신 정보가 유지될 수 있도록 관리해
									주세요. 자신의 계정을 다른 사람에게 판매, 양도, 대여 또는 담보로 제공하거나 다른 사람에게 그 사용을
									허락해서는 안 됩니다. 아울러 자신의 계정이 아닌 타인의 계정을 무단으로 사용해서는 안 됩니다.</div>
								<div class="article__text">타인에 대해 직접적이고 명백한 신체적 위협을 가하는
									내용의 게시물, 타인의 자해 행위 또는 자살을 부추기거나 권장하는 내용의 게시물, 타인의 신상정보, 사생활 등
									비공개 개인정보를 드러내는 내용의 게시물, 타인을 지속적으로 따돌리거나 괴롭히는 내용의 게시물, 성매매를 제안,
									알선, 유인 또는 강요하는 내용의 게시물, 공공 안전에 대해 직접적이고 심각한 위협을 가하는 내용의 게시물은
									제한될 수 있습니다. 관련 법령상 금지되거나 형사처벌의 대상이 되는 행위를 수행하거나 이를 교사 또는 방조하는
									등의 범죄 관련 직접적인 위험이 확인된 게시물, 관련 법령에서 홍보, 광고, 판매 등을 금지하고 있는 물건 또는
									서비스를 홍보, 광고, 판매하는 내용의 게시물, 타인의 지식재산권 등을 침해하거나 모욕, 사생활 침해 또는
									명예훼손 등 타인의 권리를 침해하는 내용이 확인된 게시물은 제한될 수 있습니다.</div>
								<div class="article__text">자극적이고 노골적인 성행위를 묘사하는 등 타인에게 성적
									수치심을 유발시키거나 왜곡된 성 의식 등을 야기할 수 있는 내용의 게시물, 타인에게 잔혹감 또는 혐오감을 일으킬
									수 있는 폭력적이고 자극적인 내용의 게시물, 본인 이외의 자를 사칭하거나 허위사실을 주장하는 등 타인을 기만하는
									내용의 게시물, 과도한 욕설, 비속어 등을 계속하여 반복적으로 사용하여 심한 혐오감 또는 불쾌감을 일으키는 내용의
									게시물은 제한될 수 있습니다.</div>
							</div>
							<div class="article">
								<div class="article__title">부득이 서비스 이용을 제한할 경우 합리적인 절차를
									준수합니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>은 다양한 정보와 의견이 담긴 여러분의 콘텐츠를
									소중히 다룰 것을 약속 드립니다만, 여러분이 게재한 게시물이 관련 법령, 본 약관, 게시물 운영정책, 각 개별
									서비스에서의 약관, 운영정책 등에 위배되는 경우, 부득이 이를 비공개 또는 삭제 처리하거나 게재를 거부할 수
									있습니다. 다만, 이것이 <span class="fontLogo signupLogo">003</span>이 모든 콘텐츠를 검토할 의무가 있다는 것을 의미하지는 않습니다.</div>
								<div class="article__text">또한 여러분이 관련 법령, 본 약관, 계정 및 게시물
									운영정책, 각 개별 서비스에서의 약관, 운영정책 등을 준수하지 않을 경우, <span class="fontLogo signupLogo">003</span>은 여러분의 관련 행위 내용을
									확인할 수 있으며, 그 확인 결과에 따라 <span class="fontLogo signupLogo">003</span> 서비스 이용에 대한 주의를 당부하거나, <span class="fontLogo signupLogo">003</span> 서비스 이용을
									일부 또는 전부, 일시 또는 영구히 정지시키는 등 그 이용을 제한할 수 있습니다. 한편, 이러한 이용 제한에도
									불구하고 더 이상 <span class="fontLogo signupLogo">003</span> 서비스 이용계약의 온전한 유지를 기대하기 어려운 경우엔 부득이 여러분과의 이용계약을
									해지할 수 있습니다.</div>
								<div class="article__text">부득이 여러분의 서비스 이용을 제한해야 할 경우 명백한
									법령 위반이나 타인의 권리침해로서 긴급한 위험 또는 피해 차단이 요구되는 사안 외에는 위와 같은 단계적 서비스
									이용제한 원칙을 준수 하겠습니다. 명백한 법령 위반 등을 이유로 부득이 서비스 이용을 즉시 영구 정지시키는 경우
									서비스 이용을 통해 획득한 포인트 및 기타 혜택 등은 모두 소멸되고 이에 대해 별도로 보상하지 않으므로 유의해
									주시기 바랍니다.</div>
							</div>
							<div class="article">
								<div class="article__title">언제든지 <span class="fontLogo signupLogo">003</span> 서비스 이용계약을 해지하실 수
									있습니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span>에게는 참 안타까운 일입니다만, 회원은 언제든지
									<span class="fontLogo signupLogo">003</span> 서비스 이용계약 해지를 신청하여 회원에서 탈퇴할 수 있으며, 이 경우 <span class="fontLogo signupLogo">003</span>은 관련 법령 등이 정하는
									바에 따라 이를 지체 없이 처리하겠습니다.</div>
								<div class="article__text">또한 여러분이 관련 법령, 본 약관, 계정 및 게시물
									운영정책, 각 개별 서비스에서의 약관, 운영정책 등을 준수하지 않을 경우, <span class="fontLogo signupLogo">003</span>은 여러분의 관련 행위 내용을
									확인할 수 있으며, 그 확인 결과에 따라 <span class="fontLogo signupLogo">003</span> 서비스 이용에 대한 주의를 당부하거나, <span class="fontLogo signupLogo">003</span> 서비스 이용을
									일부 또는 전부, 일시 또는 영구히 정지시키는 등 그 이용을 제한할 수 있습니다. 한편, 이러한 이용 제한에도
									불구하고 더 이상 <span class="fontLogo signupLogo">003</span> 서비스 이용계약의 온전한 유지를 기대하기 어려운 경우엔 부득이 여러분과의 이용계약을
									해지할 수 있습니다.</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span> 서비스 이용계약이 해지되면, 관련 법령 및
									개인정보처리방침에 따라 <span class="fontLogo signupLogo">003</span>이 해당 회원의 정보를 보유할 수 있는 경우를 제외하고, 해당 회원 계정에 부속된
									게시물 일체를 포함한 회원의 모든 데이터는 소멸됨과 동시에 복구할 수 없게 됩니다.</div>
							</div>
							<div class="article">
								<div class="article__text">
									공지 일자: 2023년 11월 12일<br> 적용 일자: 2018년 11월 12일<br>
									<span class="fontLogo signupLogo">003</span> 서비스와 관련하여 궁금하신 사항이 있으시면 고객센터(대표 메일: 0qwee0328@gamil.com/
									평일 09:00~18:00)로 문의 주시기 바랍니다.
								</div>
							</div>
						</div>
					</div>
					<div class="terms__privacy">
						<div class="privacy__agree">
							<input type="checkbox" id="chk_privacy"> <label
								for="chk_privacy">개인정보 수집 및 이용에 대한 안내<span>(필수)</span></label>
						</div>
						<div class="privacy__detail">
							<div class="article">
								<div class="article__text">개인정보보호법에 따라 <span class="fontLogo signupLogo">003</span>에 회원가입 신청하시는 분께
									수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부
									시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.</div>
							</div>
							<div class="article">
								<div class="article__title">1. 수집하는 개인정보</div>
								<div class="article__text">이용자는 회원가입을 하지 않아도 게임 이용 등 <span class="fontLogo signupLogo">003</span>
									서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 게임 이용, 게시판 글 작성 등과 같이 개인화 혹은 회원제
									서비스를 이용하기 위해 회원가입을 할 경우, <span class="fontLogo signupLogo">003</span>은 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.
								</div>
								<div class="article__text">회원가입 시점에 <span class="fontLogo signupLogo">003</span>이 이용자로부터 수집하는
									개인정보는 아래와 같습니다.</div>
								<div class="article__text">- 회원 가입 시 필수항목으로 아이디, 비밀번호, 이름,
									생년월일, 성별, 휴대전화번호, 본인확인 이메일주소를 수집합니다. 서비스 이용 과정에서 IP 주소, 쿠키, 서비스
									이용 기록이 생성되어 수집될 수 있습니다.</div>
								<div class="article__text">구체적으로 1) 서비스 이용 과정에서 이용자에 관한
									정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나, 2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지
									못 하도록 안전하게 변환하여 수집합니다.</div>
								<div class="article__text">이와 같이 수집된 정보는 개인정보와의 연계 여부 등에
									따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.</div>
							</div>
							<div class="article">
								<div class="article__title">2. 수집한 개인정보의 이용</div>
								<div class="article__text"><span class="fontLogo signupLogo">003</span> 및 <span class="fontLogo signupLogo">003</span> 관련 제반 서비스(모바일 웹/앱
									포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를
									이용합니다.</div>
								<div class="article__text">- 회원 가입 의사의 확인, 이용자 식별, 회원탈퇴
									의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.</div>
								<div class="article__text">- 법령 및 <span class="fontLogo signupLogo">003</span> 이용약관을 위반하는 회원에 대한
									이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재,
									계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호
									및 서비스 운영을 위하여 개인정보를 이용합니다.</div>
								<div class="article__text">- 보안, 프라이버시, 안전 측면에서 이용자가 안심하고
									이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.</div>
							</div>
							<div class="article">
								<div class="article__title">3. 개인정보의 보관기간</div>
								<div class="article__text">회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시
									지체없이 파기하고 있습니다.</div>
								<div class="article__text">단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를
									얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게
									보관합니다.</div>
							</div>
							<div class="article">
								<div class="article__title">4. 개인정보 수집 및 이용 동의를 거부할 권리</div>
								<div class="article__text">이용자는 개인정보의 수집 및 이용 동의를 거부할 권리가
									있습니다. 회원가입 시 수집하는 최소한의 개인정보, 즉, 필수 항목에 대한 수집 및 이용 동의를 거부하실 경우,
									회원가입이 어려울 수 있습니다.</div>
							</div>
						</div>
					</div>
				</div>
				<div class="nextBtnBox">
					<button class="nextBtn" id="stepOneNextBnt" disabled>다음</button>
				</div>
			</div>
			<script>
				
			</script>
			<div class="stepTwoBox">
				<div class="stepBox">
					<div class="stepBox__step ">
						<div class="step__title">약관동의</div>
					</div>
					<div class="stepBox__arrow">
						<i class="fa-solid fa-chevron-right"></i>
					</div>
					<div class="stepBox__step currentStep">
						<div class="step__info">STEP 02</div>
						<div class="step__title">정보입력</div>
					</div>
					<div class="stepBox__arrow">
						<i class="fa-solid fa-chevron-right"></i>
					</div>
					<div class="stepBox__step">
						<div class="step__title">가입완료</div>
					</div>
				</div>
				<div class="basicInfo">
					<div class="basicInfo__id">
						<div class="basicInfo__title">
							아이디<span class="essential">*</span>
						</div>
						<div class="basicInfo__inputBox">
							<input type="text" id="idInput">
							<div class="basicInfo__check" id="id_check"></div>
						</div>

					</div>
					<div class="basicInfo__pw">
						<div class="basicInfo__title">
							비밀번호<span class="essential">*</span>
						</div>
						<div class="basicInfo__inputBox">
							<input type="password" id="pwInput">
							<div class="pw__info">8~30자의 영문 대소문자, 숫자 및 특수문자를 사용하세요.</div>
							<div class="basicInfo__check" id="pw_check"></div>
						</div>

					</div>
					<div class="basicInfo__pwCheck">
						<div class="basicInfo__title">
							비밀번호 확인<span class="essential">*</span>
						</div>
						<div class="basicInfo__inputBox">
							<input type="password" id="pwCheckInput">
							<div class="basicInfo__check" id="pwCheck_check"></div>
						</div>

					</div>
					<div class="basicInfo__name">
						<div class="basicInfo__title">
							이름<span class="essential">*</span>
						</div>
						<div class="basicInfo__inputBox">
							<input type="text" id="nameInput">
							<div class="basicInfo__check" id="name_check"></div>
						</div>

					</div>
					<div class="basicInfo__phone">
						<div class="basicInfo__title">
							휴대전화<span class="essential">*</span>
						</div>
						<div class="basicInfo__inputBox">
							<div>
								<div class="selectorType">
									<div class="typeName">선택해주세요.</div>
									<div class="selectorArrow">
										<i class="fa-solid fa-chevron-down"></i>
									</div>
								</div>
								<div class="selector__option">
									<div class="option__item">010</div>
									<div class="option__item">011</div>
									<div class="option__item">016</div>
									<div class="option__item">017</div>
									<div class="option__item">018</div>
									<div class="option__item">019</div>
									<div class="option__item" value="directInput">직접입력</div>
								</div>
								<input type="text" maxlength="3" id="directInput"> <input
									type="text" id="phoneInput">
							</div>

							<div class="basicInfo__check" id="phone_check"></div>
						</div>
					</div>
					<div class="basicInfo__ssn">

						<div class="basicInfo__title">
							주민등록번호<span class="essential">*</span>
						</div>
						<div class="basicInfo__inputBox">
							<div class="ssnBox">
								<div class="ssnBox__birth">
									<input type="text" id="birthInput">
								</div>
								<div class="ssnBox__separator">-</div>
								<div class="ssnBox__gender">
									<input type="password" id="backId" maxlength="1" required>
									<div class="circles"><i class="fa-solid fa-circle"></i><i class="fa-solid fa-circle"></i><i class="fa-solid fa-circle"></i><i class="fa-solid fa-circle"></i><i class="fa-solid fa-circle"></i></div>
								</div>
							</div>
							<div class="basicInfo__check" id="ssn_check"></div>
						</div>

					</div>
					<div class="basicInfo__nickName">
						<div class="basicInfo__title">
							닉네임
						</div>
						<div class="basicInfo__inputBox">
							<input type="text">
							<div class="basicInfo__check" id="nickName_check"></div>
						</div>

					</div>
				</div>


				<div class="nextBtnBox">
					<button class="nextBtn" id="stepOneNextBnt" disabled>다음</button>
				</div>
			</div>
			<div class="stepThreeBox"></div>
		</div>
		<%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
	</div>
</body>
</html>