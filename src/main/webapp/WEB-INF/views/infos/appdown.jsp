<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="어플다운로드" />
</c:import>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">어플다운로드</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">어플다운로드</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <!--=== Content Part ===-->
    <div class="container content">
    	<div class="row-fluid col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1">
			<div class="reg-header">
				<p>어플을 통해 소식을 실시간으로 받고<br />
				모바일로도 서비스를 이용하세요!</p>
			</div>
    	
    	
    		<h4><strong>일반 회원</strong></h4>
    		<p>
    			푸시를 통해 입찰 소식을 받아보세요
				실시간을 입찰의 소식이 전달됩니다.
			</p>
			
    		<h4><strong>딜러 회원</strong></h4>
    		<p>
				푸시를 통해 결정의 결과와 
				관심 지역의 견적 요청을 소식을 받아보세요
			</p>
			
			<div class="row margin-top-20">
				<div class="col-sm-6 text-center" style="margin-bottom: 10px">
					<a href="#" class="btn-u btn-u-lg btn-block"><i class="fa fa-android"></i> 안드로이드 앱<br/>다운로드</a>
				</div>
				<div class="col-sm-6 text-center" style="margin-bottom: 10px">
					<a href="#" class="btn-u btn-u-lg btn-block"><i class="fa fa-apple"></i> 아이폰 앱<br/>다운로드</a>
				</div>
			</div>
			
    	
    	

        </div><!--/row-fluid-->
    </div><!--/container-->
    <!--=== End Content Part ===-->

<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>