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
    <!--=== End Breadcrumbs ===-->

     <!--=== Content Part ===-->
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page">
                    <div class="reg-header">
                        <h2>비밀번호 재 발급</h2>
                        <p>
                        	고객님의 핸드폰으로 임시 비밀번호가 전송 되었습니다.<br/>
                        	임시 비밀번호는 로그인후 <b>마이페이지 > 내정보 수정하기</b><br/> 에서 변경해주시기 바랍니다.  
                        </p>
                    </div>

                    <div class="row text-center">
						<a href="${root}/member/login" class="btn-u btn btn-u-lg"><i class="fa fa-chevron-circle-right"></i> 로그인 하러 가기</a><br/>
                    </div>

                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>