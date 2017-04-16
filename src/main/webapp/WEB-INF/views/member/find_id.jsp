<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />


<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">아이디 찾기</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">아이디 찾기</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->

	<c:if test="${result.status == 'fail'}">
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page">
                    <div class="reg-header">
                        <h2>아이디 확인</h2>
                        <p>일치하는 계정 정보가 없습니다.<br/>
                        	다시 확인해주시기 바랍니다.
                        </p>
                    </div>

                    <div class="row text-center">
						<a href="${root}/member/lost_id" class="btn-u btn btn-u-lg"><i class="fa fa-chevron-circle-right"></i> 다시 아이디 찾기</a><br/>
                    </div>

                </form>
            </div>
        </div>
    </div>
	</c:if>

	<c:if test="${result.status == 'ok'}">
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page">
                    <div class="reg-header">
                        <h2>아이디 확인</h2>
                        <p>고객님이 사용하시는 아이디는 <br/>
                        	<b>${result.data}</b> 입니다
                        </p>
                    </div>

                    <div class="row text-center">
						<a href="${root}/member/login" class="btn-u btn btn-u-lg"><i class="fa fa-chevron-circle-right"></i> 로그인 하러 가기</a><br/>
						<a href="${root}/member/lost_password">비밀번호 찾기</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
    </c:if>


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>