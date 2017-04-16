<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="SUBA는 무엇인가요?" />
</c:import>

<style type="text/css">
	.service-or {
		cursor: pointer;
	}
</style>
    <link rel="stylesheet" href="${root}/assets/css/pages/page_job.css">


    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">SUBA는 무엇인가요?</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">SUBA는 무엇인가요?</li>
            </ul>
        </div>
    </div>
	    
	<div class="job-img">
        <div class="job-banner">
            <h2>SUBA<br/>딜러와 고격을 연결해주는 서비스</h2>
        </div>
        <div class="job-img-inputs">
            <div class="container">
            </div>
        </div>
    </div>    

    <div class="container content">
    	<div class="row-fluid col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
    	
	    	<div class="row margin-bottom-40">
	            <div class="col-md-4 col-sm-6">
	                <div class="service-block service-block-sea service-or">
	                    <div class="service-bg"></div>
	                    <i class="icon-custom icon-color-light rounded-x fa fa-lightbulb-o"></i>
	                    <h2 class="heading-md">일반회원</h2>
	                    <p>물건 구매나, 서비스를 받는것이 주 목적인 회원입니다.</p>
	                </div>
	            </div>
	            <div class="col-md-4 col-sm-6">
	                <div class="service-block service-block-red service-or">
	                    <div class="service-bg"></div>
	                    <i class="icon-custom icon-color-light rounded-x icon-line icon-fire"></i>
	                    <h2 class="heading-md">딜러회원</h2>
	                    <p>물건이나 서비스를 제공하는 회원입니다.</p>
	                </div>
	            </div>
	            <div class="col-md-4 col-sm-12">
	                <div class="service-block service-block-blue service-or">
	                    <div class="service-bg"></div>
	                    <i class="icon-custom icon-color-light rounded-x icon-line icon-rocket"></i>
	                    <h2 class="heading-md">어플다운로드</h2>
	                    <p>어플리케이션을 통해 SUBA를 200% 활용해보세요</p>
	                </div>
	            </div>
	        </div>
    	
    	
    		<h4><strong>SUBA란?</strong></h4>
    		<p class="text-center"><img src="${root}/assets/img/pic-guide1.png" class="img-responsive center-block" ></p>
    		<p class="margin-bottom-40 col-sm-offset-1">
    			구매자의 요구를 판매자에게 공유하고, 판매자는 유저게 제시를합니다.<br/>
				유저는 판매자의 제시중 만족스러운 제시를 선택하여서<br/>
				합리적인 거래를 성립 시켜줍니다. 
			</p>
			
    		<h4><strong>어떤점이 나을까요?</strong></h4>
    		<p class="text-center"><img src="${root}/assets/img/pic-guide2.png" class="img-responsive center-block" ></p>
    		<p class="margin-bottom-40 col-sm-offset-1">
				거래가 모일수록 선택의 폭은 넓어집니다.<br/>
				구매자에겐 더많은 제시를,판매자에겐 더많은 구매자를 연결하여주어서<br/> 
				구매자, 판매자 모두 만족스럽습니다    
			</p>
			
			
    	
    	

        </div><!--/row-fluid-->
    </div><!--/container-->
    <!--=== End Content Part ===-->
<script type="text/javascript">
var links = [ '${root}/guide/member', '${root}/guide/dealer', '${root}/infos/appdown' ];
$('.service-or').on('click', function(){
	var number = $('.service-or').index(this);
	location.href= links[number];
});
</script>    

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>