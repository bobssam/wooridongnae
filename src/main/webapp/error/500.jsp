<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page isErrorPage="true" %>

<c:import url="../WEB-INF/views/global/header.jsp" charEncoding="UTF-8"></c:import>
    <!-- CSS Page Style -->
    <link rel="stylesheet" href="/assets/css/pages/page_404_error.css">
    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">500에러</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="/">Home</a></li>
                <li class="active">500에러</li>
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
                    <span class="error-v1-title">500</span>
                    <span>서버 내부 오류</span>
                    <p>요청하신 작업엔 오류가 있습니다. 지속적으로 발생시 관리자에게 요청하세요</p>
                    <a class="btn-u btn-bordered" href="/">메인으로</a>
                </div>
            </div>
        </div>
        <!--End Error Block-->
    </div>
    <!--=== End Content Part ===-->
    
    <div>
    에러 타입 <%= exception.getClass().getName() %><br/><br/>
    에러 타입 <%= exception.getCause().toString() %><br/><br/>
    <%
    	
		StackTraceElement[] error = exception.getStackTrace();
		for( int i=0; i<error.length; i++ ) {
			out.print( error[i] );
			out.print( "<br>" );
		}
    
    %>
    
    </div>
    
<c:import url="../WEB-INF/views/global/footer.jsp" charEncoding="UTF-8"></c:import>
