<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../WEB-INF/views/global/header.jsp" charEncoding="UTF-8"></c:import>
    <!-- CSS Page Style -->
    <link rel="stylesheet" href="/assets/css/pages/page_404_error.css">
    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">404에러</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="/">Home</a></li>
                <li class="active">404에러</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->
    
    <!--=== Content Part ===-->
    <div class="container content">
        <!--Error Block-->
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="error-v1">
                    <span class="error-v1-title">404</span>
                    <span>없는 페이지 입니다</span>
                    <p>요청하신 경로를 찾을수 없습니다. 다시 확인해 주시기 바랍니다.</p>
                    <a class="btn-u btn-bordered" href="/">메인으로</a>
                </div>
            </div>
        </div>
        <!--End Error Block-->
    </div>
    <!--=== End Content Part ===-->
    
<c:import url="../WEB-INF/views/global/footer.jsp" charEncoding="UTF-8"></c:import>
