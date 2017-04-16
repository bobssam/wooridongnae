<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />
    

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="일반 회원 안내" />
</c:import>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">일반 회원 안내</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">일반 회원 안내</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <!--=== Content Part ===-->
    <div class="container content">
    	<div class="row-fluid col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
			<div class="reg-header">
				<p>많은 딜러로 부터, 많은 입찰을 받아보시고<br/>
				선택하십시요!</p>
			</div>
    	
    	
    		<h4><strong>견적 등록</strong></h4>
    		<p>
    			1. 구매를 희망하시는 품목과 지역을 결정하세요.<br/>
				2. 구매를 희망하는 내역을 적고 견적등록을 하세요.
			</p>
			
    		<h4><strong>입찰 진행</strong></h4>
    		<p>
				견적 등록 후 거래를 희망하는 딜러들이<br/>
				비공개 입찰을 통해 가격과 조건을 제시합니다.
			</p>
			
    		<h4><strong>결정</strong></h4>
    		<p>
				견적 중 만족하는 입찰에 결정을 하시면 거래가 성사됩니다.<br/>
				이후 과정은 딜러와 직접 협의 하십시요.
			</p>			
			
    	
    	

        </div><!--/row-fluid-->
    </div><!--/container-->
    <!--=== End Content Part ===-->

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>