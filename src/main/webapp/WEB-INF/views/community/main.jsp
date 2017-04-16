<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>
<style type="text/css">

	.community img{
		margin-left: -1px;
		margin-bottom: -1px;
	}

</style>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">커뮤니티</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li class="active"><a href="">커뮤니티</a></li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <div class="container content col-sm-offset-3">
		
		<div class="row">
			<div class="col-md-10 text-center community">
				<a href="${root}/community/1"><img src="${root}/assets/img/board/board1.png" alt="유머게시판"></a><!-- 
				 --><a href="${root}/community/2"><img src="${root}/assets/img/board/board2.png" alt="신차소식"></a><!--
				 --><a href="${root}/community/3"><img src="${root}/assets/img/board/board3.png" alt="차량 구입 노하우"></a><!--
				 --><a href="${root}/community/4"><img src="${root}/assets/img/board/board4.png" alt="출고 체크리스트"></a><!--
				 --><a href="${root}/community/5"><img src="${root}/assets/img/board/board5.png" alt="자동차 정비상식"></a><!--
				 --><a href="${root}/community/6"><img src="${root}/assets/img/board/board6.png" alt="차량시승기"></a>
			</div>
		</div>
		
		
	</div>


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>