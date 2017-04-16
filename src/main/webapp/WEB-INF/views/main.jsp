<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="메인"></c:param>
</c:import>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<style type="text/css">
@media (max-width: 768px) {
  .hidden-sm
  {
    display: none !important;
  }
}
.slider-inner .da-slider {
	background: #ccc;
}
.da-slider {
    max-height: 400px;
    height: auto;
}
.da-slider .img-responsive{
	display: inline-block;
}
.img-container {
	position: relative;
	text-align: center;
}
.img-box {
	position: absolute;
	left:0;
	top:0;
	width: 100%;
}
.img-box {
</style>
<script type="text/javascript">
// 메인 이미지 스크롤
$(function(){

	var now = 0;
	var len = 0;

	var $imgContainer = $('.img-container');
	var $boxs = $imgContainer.find('.img-box');

	len = $boxs.length;
	now = len-1;

	function prev() {
		now--;
		if( now < 0 ) now = len-1;
		// 다시 append 하여서 최상위로 이동
		$imgContainer.append( $boxs.eq(now) );
		$boxs.eq(now).hide().fadeIn(300);
	}

	function next() {
		now++;
		if( now >= len ) now = 0;
		// 다시 append 하여서 최상위로 이동
		$boxs.eq(now).hide().fadeIn(300);
		$imgContainer.append( $boxs.eq(now) );
	}

	// 5초마다 롤링
	var timer =  null;


	$boxs.hover(rollstop, rollstart);

	function rollstart() {
		timer = setInterval( next, 5000 );
	}

	function rollstop() {
		clearInterval(timer);
	}

	rollstart();


	$('.da-arrows-next').on('click', next);
	$('.da-arrows-prev').on('click', prev);
});
</script>


    <!--=== Slider ===-->
    <div class="slider-inner hidden-sm" >

        <div id="da-slider" class="da-slider">
        	<div class="img-container">
	    		<img class="img-responsive" src="${root}/assets/img/main/dummy.png" alt="">
	    		<c:forEach var="banner" items="${banners}" >
		        	<div class="text-center img-box">
		        		<c:if test="${banner.url != null and banner.url != ''}">
			    			<a href="${banner.url}"><img class="img-responsive" src="${root}/api/file/download/${banner.fileNo}" alt="${banner.title}"></a>
			    		</c:if>
			    		<c:if test="${banner.url == null or banner.url == ''}">
			    			<img class="img-responsive" src="${root}/api/file/download/${banner.fileNo}" alt="${banner.title}">
			    		</c:if>
		        	</div>
	    		</c:forEach>
        	</div>

            <div class="da-arrows">
                <span class="da-arrows-prev"></span>
                <span class="da-arrows-next"></span>
            </div>
        </div>
    </div><!--/slider-->
    <!--=== End Slider ===-->

    <!--=== Purchase Block ===-->
    <div class="purchase">
        <div class="container">
            <div class="row">
                <div class="col-md-9 animated fadeInLeft">
                    <span>합리적인 거래 SUBA</span>
                    <p>구매를 원하는 고객과 딜러를 쉽게 연결하여 주고<br/>
                    	중간 마진이나 수수료 없이 최적의 조건으로 거래할 수 있게 합니다.
                    </p>
                </div>
                <div class="col-md-3 btn-buy animated fadeInRight">
                    <a href="${root}/guide/" class="btn-u btn-u-lg"><i class="fa fa-question-circle"></i> 좀더 알아볼까요?</a>
                </div>
            </div>
        </div>
    </div><!--/row-->
    <!-- End Purchase Block -->

    <!--=== Content Part ===-->
    <div class="container content-sm">
    	<!-- Service Blocks -->
    	<div class="row margin-bottom-30">
        	<div class="col-md-4">
        		<div class="service">
                    <i class="fa fa-car service-icon"></i>
        			<div class="desc">
        				<h4>HOT DEAL</h4>
                        <p>당신을 위한 추천 핫딜 </p>
                        <a href="${root}/hotdeal/" class="btn-u btn-u-lg"><i class="fa fa-chevron-circle-right"></i> 핫딜 바로가기</a>
        			</div>
        		</div>
        	</div>
        	<div class="col-md-4">
        		<div class="service">
                    <i class="fa fa-university service-icon"></i>
        			<div class="desc">
        				<h4>PROMOTION</h4>
                        <p>특별한 혜택, 놀라운 제안</p>
                        <a href="${root}/promotion/" class="btn-u btn-u-lg"><i class="fa fa-chevron-circle-right"></i> 프로모션 바로가기</a>
        			</div>
        		</div>
        	</div>
        	<div class="col-md-4">
        		<div class="service">
                    <i class="fa fa-android service-icon"></i>
        			<div class="desc">
        				<h4>어플 다운로드 안내</h4>
                        <p>어플을 이용하시면 입찰이나, 입찰 결정에 대한 알림을 받을수있습니다. 또한 모바일 웹을 통해 언제나 편리하게 이용하세요 </p>
        			</div>
        		</div>
        	</div>
    	</div>
    	<!-- End Service Blokcs -->

    	<!-- Recent Works -->
        <div class="headline"><h2>최근 핫딜</h2></div>
        <div class="row margin-bottom-20">

        	<c:forEach var="deal" items="${hotDeal}" >
        	<div class="col-md-3 col-sm-6">
                <div class="thumbnails thumbnail-style thumbnail-kenburn">
                	<div class="thumbnail-img">
                        <div class="overflow-hidden">
                        	<c:if test="${deal.fileNo == 0 }" >
                            <img class="img-responsive" src="assets/img/main/img18.jpg" alt="">
                            </c:if>
                            <c:if test="${deal.fileNo > 0 }" >
                            <img class="img-responsive" src="${root}/api/file/download/${deal.fileNo}" alt="">
                            </c:if>
                        </div>
                        <a class="btn-more hover-effect" href="${root }/hotdeal/?hotdealNo=${deal.hotdealNo}">더보기 +</a>
                    </div>
                    <div class="caption">
                        <h3><a class="hover-effect" href="#">${deal.title}</a></h3>
                    </div>
                </div>
           	</div>
        	</c:forEach>
        </div>
    	<!-- End Recent Works -->

    	<!-- Info Blokcs -->
    	<div class="row margin-bottom-30">
        	<!-- Welcome Block -->
    		<div class="col-md-8 md-margin-bottom-40">
    			<div class="headline"><h2>공지사항</h2></div>
                <div class="row">
                    <div class="col-sm-4">
                        <img class="img-responsive margin-bottom-20" src="assets/img/main/img18.jpg" alt="">
                    </div>
                    <div class="col-sm-8">
                        <p>SUBA에선 계좌를 통해 입금을요구하는 딜러는 없습니다.<br/>혹시라도 거래를 위해 입금을 요구하는 딜러가있다면 신고하여주십시요.  </p>
                        <ul class="list-unstyled margin-bottom-20">
                        	<c:forEach var="list" items="${notice}" >
                        		<li><i class="fa fa-check color-green"></i> <a href="${root}/infos/notice?boardNo=${list.boardNo}">${list.title}</a></li>
                        	</c:forEach>
                        </ul>
                    </div>
                </div>

            </div>

            <!-- Latest Shots -->
            <div class="col-md-4">
    			<div class="headline"><h2>추천 협력 업체</h2></div>
    			<div id="myCarousel" class="carousel slide carousel-v1">
                    <div class="carousel-inner">
                    	<c:set var="cropFirst" value="true" />
			        	<c:forEach var="crop" items="${crops}" >
                        <div class="item<c:if test="${cropFirst}"> active</c:if>">
                            <a href="${root}/crop/#cropNo=${crop.memberNo}"><img src="${root}/api/file/download/${crop.fileNo}" alt=""></a>
                            <div class="carousel-caption">
                                <p>${crop.corpName}</p>
                            </div>
                            <c:set var="cropFirst" value="false" />
                        </div>
			        	</c:forEach>
                    </div>

                    <div class="carousel-arrow">
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                            <i class="fa fa-angle-left"></i>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
    			</div>
            </div><!--/col-md-4-->
    	</div>

    	<!-- End Info Blokcs -->

        <!-- Owl Clients v1 -->
        <div class="headline"><h2>최근 성사 거래</h2></div>
        <div class="owl-clients-v1">
		<c:if test="${fn:length(finishDeal)>0}">
		<c:forEach  var="deal" items="${finishDeal}">
       	<div class="item">
       		<c:if test="${deal.fileNo1 > 0 }">
            <img src="${root}/api/file/download/${deal.fileNo1}" alt="">
            </c:if>
            <c:if test="${deal.fileNo1 == 0 }">
            <img src="${root}/assets/img/main/img18.jpg" alt="">
            </c:if>
            <br/>
            ${deal.categoryNo3Name} - ${deal.categoryNo4Name}
        </div>
       	</c:forEach>
		</c:if>

		</div>
        <!-- End Owl Clients v1 -->
    </div><!--/container-->
    <!-- End Content Part -->

</fmt:bundle>

<c:import url="global/footer.jsp" charEncoding="UTF-8">
	<c:param name="title" value="메인"></c:param>
</c:import>