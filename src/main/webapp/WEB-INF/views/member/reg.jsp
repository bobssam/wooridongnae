<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

<style type="text/css">
.reg-page {
	padding: 20px !important;
}
</style>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">개인정보취급안내</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">개인정보취급안내</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

     <!--=== Content Part ===-->
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page">
					<div class="reg-header">
                        <h2>회원종류 선택</h2>
                        <p>계정이 이미 있으시다면 <a href="${root}/member/login" class="color-green">로그인</a> 을 눌러 로그인하세요.</p>
                    </div>
                    <div class="row">
                        <div class="col-sm-6  text-center">
		                    <p>구매를 중심으로 하며<br/>간단한 정보만으로도 가입이 가능합니다.</p>
							<a href="${root}/member/reg_member" class="btn-u btn-u-lg margin-bottom-20"><i class="fa fa-child"></i> 일반 회원 가입</a>
	                    </div>
                        <div class="col-sm-6  text-center">
		                    <p>판매를 중심으로 하며<br/>사업자 번호등 인증 절차가 있습니다.</p>
							<a href="${root}/member/reg_dealer" class="btn-u btn-u-lg btn-u btn-u-purple margin-bottom-20"><i class="fa fa-users"></i> 딜러 회원 가입</a>
	                    </div>
                    </div>
                    
                    <div class="row margin-top-20">
                        <div class="col-sm-6 col-xs-6 text-center">
                        </div>
                        <div class="col-sm-6 col-xs-6 text-center">
                        </div>
					</div>

                </form>
            </div>
        </div>
	</div>
    
    
</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>