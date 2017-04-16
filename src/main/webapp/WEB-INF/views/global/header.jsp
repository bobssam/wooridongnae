<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="kr"> <!--<![endif]-->
<head>
	<title><c:out value="${param.title}"/> - SUBA</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="shortcut icon" href="favicon.ico">
    <!-- Web Fonts -->
    <link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

    <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href="${root}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${root}/assets/css/style.css">
    <!-- CSS Header and Footer -->
    <link rel="stylesheet" href="${root}/assets/css/headers/header-default.css">
    <link rel="stylesheet" href="${root}/assets/css/footers/footer-v1.css">
    <link rel="stylesheet" href="${root}/assets/plugins/toast/toastr.css">

	<c:choose>
		<c:when test="${param.title eq '메인'}">

		    <!-- CSS Implementing Plugins -->
		    <link rel="stylesheet" href="${root}/assets/plugins/animate.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/line-icons/line-icons.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/font-awesome/css/font-awesome.min.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/parallax-slider/css/parallax-slider.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/owl-carousel/owl-carousel/owl.carousel.css">

		    <!-- CSS Customization -->
		    <link rel="stylesheet" href="${root}/assets/css/custom.css">
	    </c:when>
	    <c:otherwise>


		    <!-- CSS Implementing Plugins -->
		    <link rel="stylesheet" href="${root}/assets/plugins/animate.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/line-icons/line-icons.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/font-awesome/css/font-awesome.min.css">
		    <!-- CSS Page Style -->
		    <link rel="stylesheet" href="${root}/assets/css/pages/page_log_reg_v1.css">

		    <!-- CSS Customization -->
		    <link rel="stylesheet" href="${root}/assets/css/custom.css">
	    	<link rel="stylesheet" href="${root}/assets/plugins/sky-forms-pro/skyforms/css/sky-forms.css">
    		<link rel="stylesheet" href="${root}/assets/plugins/sky-forms-pro/skyforms/custom/custom-sky-forms.css">
		    <link rel="stylesheet" href="${root}/assets/plugins/fancybox/source/jquery.fancybox.css">



	    </c:otherwise>
	</c:choose>
	<script type="text/javascript">
		var root = "${root}";
	</script>
	<script type="text/javascript" src="${root}/assets/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${root}/assets/js/jquery.tmpl.min.js"></script>
	<script type="text/javascript" src="${root}/assets/plugins/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${root}/assets/js/admin.js"></script>
	<script type="text/javascript" src="${root}/assets/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${root}/assets/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${root}/assets/plugins/toast/toastr.js"></script>
	<script type="text/javascript" src="${root}/assets/js/jquery.form.js"></script>


</head>

<body>
<div class="wrapper">

    <div class="header">
        <div class="container">
            <!-- Logo -->
            <a class="logo" href="${root}/">
                <img src="${root}/assets/img/logo1-default.png" alt="Logo">
            </a>

            <div class="topbar">
                <ul class="loginbar pull-right">
                <c:if test="${sessionScope.memberVO == null}">
                    <li><a href="${root}/member/login?rurl=${root}${requestScope['javax.servlet.forward.servlet_path']}?${pageContext.request.queryString}">로그인</a></li>
                    <li class="topbar-devider"></li>
                    <li><a href="${root}/member/reg">회원가입</a></li>
                </c:if>
                <c:if test="${sessionScope.memberVO != null}">
                    <li><a href="${root}/mypage/memo" data-toggle="modal" data-target="#memoModal" data-backdrop="static"><i class="fa fa-envelope-o"></i> <span id="memo_count"></span>건의 신규 메세지</a></li>
                    <li class="topbar-devider"></li>
                    <li class="hoverSelector">
                        <a href="${root}/mypage/">마이 페이지</a>
                    </li>
                    <li class="topbar-devider"></li>
                    <li><a href="javascript:void(doLogout('${root}/member/doLogout'))" class="logout">로그아웃</a></li>

				</c:if>

                </ul>
            </div>

            <!-- Toggle get grouped for better mobile display -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="fa fa-bars"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="${root}/" >
                            Home
                        </a>
                    </li>

                    <li class="dropdown car">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            자동차 견적
                        </a>
                        <ul class="dropdown-menu">
							<li><a href="${root}/hotdeal/">HOT DEAL</a></li>
							<li><a href="${root}/promotion/">PROMOTION</a></li>

							<li><a href="${root}/tender/lists?categoryNo2=3">신차구입 비교 견적 리스트</a></li>
							<li><a href="${root}/tender/make?categoryNo2=3">신차구입 비교 견적 등록하기</a></li>

							<li><a href="${root}/tender/lists?categoryNo2=4">자동차 수리 비교 견적 리스트</a></li>
							<li><a href="${root}/tender/make?categoryNo2=4">자동차 수리 비교 견적 등록하기</a></li>

							<!-- <li><a href="${root}/tender/make?categoryNo=1">견적 등록하기</a></li>  -->

							<!-- <li><a href="${root}/tender/?categoryNo=1">상위 업체 안내</a></li> -->
                            <!-- End Sub Level Menu -->
                        </ul>
                    </li>

                    <li class="corp">
                        <a href="${root}/crop/" >
                            협력 업체
                        </a>
                    </li>

                    <li class="dropdown out">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            출고 후기
                        </a>
                    </li>

					<!--
                    <li class="dropdown interior">
                        <a href="${root}/tender/lists?categoryNo=2" class="dropdown-toggle">
                            인테리어 견적
                        </a>
                        <ul class="dropdown-menu">
							<li><a href="${root}/tender/lists?categoryNo=2">견적 리스트</a></li>
							<li><a href="${root}/tender/make?categoryNo=2">견적 등록하기</a></li>
							<li><a href="${root}/tender/?categoryNo=2">상위 업체 안내</a></li>
                        </ul>
                    </li>
                     -->

                    <li class="dropdown community">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            커뮤니티
                        </a>
                        <ul class="dropdown-menu">
							<li><a href="${root}/community/">커뮤니티 메인</a></li>
							<li><a href="${root}/community/1">유머 게시판</a></li>
							<li><a href="${root}/community/2">신차 소식</a></li>
							<li><a href="${root}/community/3">차량 구입 노하우</a></li>
							<li><a href="${root}/community/4">출고 체크리스트</a></li>
							<li><a href="${root}/community/5">자동차 정비 상식</a></li>
							<li><a href="${root}/community/6">차량 시승기</a></li>
                        </ul>
                    </li>


                    <li class="dropdown infos">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            이용안내
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${root}/infos/faq">FAQ</a></li>
                            <li><a href="${root}/infos/notice">공지사항</a></li>
                            <li><a href="${root}/infos/qna">QNA</a></li>

                            <li><a href="${root}/guide/">SUBA는 무엇인가요?</a></li>
							<li><a href="${root}/guide/member">일반 회원 안내</a></li>
                            <li><a href="${root}/guide/dealer">딜러 회원 안내</a></li>

                            <li><a href="${root}/infos/appdown">어플다운로드 안내</a></li>
                            <li><a href="${root}/infos/terms">이용약관</a></li>
                            <li><a href="${root}/infos/privacy">개인정보수집정책</a></li>
                            <li><a href="${root}/infos/partner">제휴/광고문의</a></li>
                        </ul>
                    </li>

                    <li>
                        <i class="search fa fa-search search-btn"></i>
                        <div class="search-open">
                            <div class="input-group animated fadeInDown">
                                <input type="text" class="form-control" placeholder="Search">
                                <span class="input-group-btn">
                                    <button class="btn-u" type="button">Go</button>
                                </span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <script type="text/javascript">

    	var urls = (location.pathname+"").split("/");
    	if( urls[1] == '' )
    		$('.navbar-nav.nav>').eq(0).addClass('active');
    	else $('.navbar-nav.nav>.'+urls[1]).addClass('active');

    	function loadMemoCount() {

	    	$.ajax({
	    		url : '${root}/mypage/user/memo/total',
	    		dataType :'text',
	    		success:function(total) {
					$('#memo_count').text(total);
	    		}
	    	});
    	}


    </script>

	<c:if test="${sessionScope.memberVO != null}">
   <div id="memoModal" class="modal fade" role="dialog"  aria-labelledby="myLargeModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">쪽지함</h4>
	      </div>
	      <div class="modal-body">

			</div>
		</div>
		</div>
    </div>
    <script type="text/javascript">
        loadMemoCount();
	</script>
	</c:if>

</fmt:bundle>