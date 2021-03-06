<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

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
                        <h2>가입 실패</h2>
                        <p><i class="fa fa-exclamation-circle"></i> 이런 이런 사유로 실패</p>
                    </div>

                    <div class="row text-center">
						<a href="javascript:void( history.back() )" class="btn btn-u-lg btn-danger"><i class="fa fa-chevron-circle-right"></i> 다시 작성</a>
                    </div>

                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->



<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>