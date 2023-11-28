<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- 공통 css -->
<link rel="syplesheet" href="/css/commons/common.css"/>

<!-- index js/css -->
<link rel="stylesheet" href="/css/index.css" />
<script type="text/javascript" src="/js/index.js"></script>
</head>
<body>
<div class="container-fluid p-0">
	<%@ include file="/WEB-INF/jsp/commons/header.jsp" %>
	<div class="container">
        <div class="banner">
            <div class="banner_guide">
                <div class="banner__contents">
                    <div class="contents__title">키오스크의 첫 걸음은 <span class="fontLogo">003</span>과 함께!</div>
                    <div class="contents__sub">키오스크를 처음 사용하는 사용자도 <span class="fontLogo">003</span>을 통해 누구나 쉽고 재미있게!!<br>
                        메뉴 주문, 구성 변경, 포인트 적립까지 단계별로<br>
                        키오스크 이용 실력을 향상 시켜보세요.</div>
                    <div class="kioskBtns">
                        <a href="/practice/listPractice"><button class="bannerBtn">연습하러가기</button></a>
                        <a href=""><button class="bannerBtn">게임하러가기</button></a>

                    </div>
                </div>
                <div class="banner_img">
                    <img src="/images/indexImg.png" alt="">
                </div>
            </div>
        </div>
        <div class="index_guide">
			<div class="practice">
                <div class="practice__title">
                    카테고리별 연습으로<span>기본부터 튼튼하게</span>
                </div>
                <div class="practice__contents">
                    <div class="practice_row">
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    티오더
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    패스트푸드
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    카페
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="practice_row">
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    기차
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    버스
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    영화
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    마트
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="practice__contents-tab">
                    <div class="practice_row">
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    티오더
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    패스트푸드
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    카페
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="practice_row">

                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    기차
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    버스
                                </div>
                            </div>
                        </a>
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    영화
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="practice_row">
                        <a href="">
                            <div class="practice__box">
                                <div class="subtitle">
                                    키오스크 연습
                                </div>
                                <div class="title">
                                    마트
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="popular">
                <div class="popular__title">
                    실시간 인기 카테고리
                    <a href=""><button><i class="fa-solid fa-arrow-rotate-right"></i></button></a>
                </div>
                <div class="popular__contents">
                    <div class="popular__Box">
                        <div class="Box__left">
                            <div class="popular__rank">1</div>
                            <div class="popular__info">
                                <div class="info__category">패스트푸드</div>
                                <div class="info__playCount">
                                    <div>연습 플레이 횟수 : 10번</div>
                                    <div>게임 플레이 횟수 : 10번</div>
                                </div>
                                <div class="info__playCount-tab">
                                    <div>연습 : 10번</div>
                                    <div>게임 : 10번</div>
                                </div>
                            </div>
                        </div>
                        <div class="Box__right">
                            <div class="popular__btns">
                                <a href=""><button>연습하러가기</button></a>
                                <a href=""><button>게임하러가기</button></a>
                            </div>
                            <div class="popular__btns-tab">
                                <a href=""><button>연습</button></a>
                                <a href=""><button>게임</button></a>
                            </div>
                        </div>
                    </div>
                    <div class="popular__Box">
                        <div class="Box__left">
                            <div class="popular__rank">1</div>
                            <div class="popular__info">
                                <div class="info__category">패스트푸드</div>
                                <div class="info__playCount">
                                    <div>연습 플레이 횟수 : 10번</div>
                                    <div>게임 플레이 횟수 : 10번</div>
                                </div>
                                <div class="info__playCount-tab">
                                    <div>연습 : 10번</div>
                                    <div>게임 : 10번</div>
                                </div>
                            </div>
                        </div>
                        <div class="Box__right">
                            <div class="popular__btns">
                                <a href=""><button>연습하러가기</button></a>
                                <a href=""><button>게임하러가기</button></a>
                            </div>
                            <div class="popular__btns-tab">
                                <a href=""><button>연습</button></a>
                                <a href=""><button>게임</button></a>
                            </div>
                        </div>
                    </div>
                    <div class="popular__Box">
                        <div class="Box__left">
                            <div class="popular__rank">1</div>
                            <div class="popular__info">
                                <div class="info__category">패스트푸드</div>
                                <div class="info__playCount">
                                    <div>연습 플레이 횟수 : 10번</div>
                                    <div>게임 플레이 횟수 : 10번</div>
                                </div>
                                <div class="info__playCount-tab">
                                    <div>연습 : 10번</div>
                                    <div>게임 : 10번</div>
                                </div>
                            </div>
                        </div>
                        <div class="Box__right">
                            <div class="popular__btns">
                                <a href=""><button>연습하러가기</button></a>
                                <a href=""><button>게임하러가기</button></a>
                            </div>
                            <div class="popular__btns-tab">
                                <a href=""><button>연습</button></a>
                                <a href=""><button>게임</button></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/commons/footer.jsp" %>
</div>
</body>
</html>