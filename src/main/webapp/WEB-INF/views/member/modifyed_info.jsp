<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />
    

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="개인 정보 수정" />
</c:import>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">개인 정보 수정</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">개인 정보 수정</li>
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
                        <h2>개인 정보 변경 완료</h2>
                        <p>
                        	고객님의 정보가 변경되었습니다.<br/>
                        </p>
                    </div>

                    <div class="row text-center">
						<a href="${root}/mypage/" class="btn-u btn btn-u-lg"><i class="fa fa-chevron-circle-right"></i> 마이페이지로 가기</a><br/>
                    </div>

                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>