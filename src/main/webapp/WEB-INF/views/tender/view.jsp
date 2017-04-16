<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>
<style type="text/css">
.btn-u-xs, a.btn-u-xs {
	padding: 2px 7px;
}
.vcenter {
    display: inline-block;
    vertical-align: middle;
    float: none;
}
.search-block-v2 {
	padding: 20px 0 !important;
}

</style>

    <link rel="stylesheet" href="/assets/css/pages/page_search_inner.css">

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">견적 상세 보기</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">견적</a></li>
                <li class="active">견적 상세 보기</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->


    <div class="search-block-v2">
    	<div class="container">
	    	<div class="col-md-8 col-md-offset-2 row">


    			<div class="row margin-bottom-5">
    				<div class="col-sm-3 col-xs-3">
		    			<label>글번호</label>
    				</div>
    				<div class="col-sm-9 col-xs-9">
	    				${tender.tenderNo}
    				</div>
   				</div>

    			<div class="row margin-bottom-5">
    				<div class="col-sm-3 col-xs-3">
		    			<label>구입 희망</label>
    				</div>
    				<div class="col-sm-9 col-xs-9">
	    				${tender.title}
    				</div>
   				</div>

    			<div class="row margin-bottom-5">
    				<div class="col-sm-3 col-xs-3">
		    			<label>구입시기</label>
    				</div>
    				<div class="col-sm-9 col-xs-9">
	    				<fmt:formatDate pattern="yyyy-MM-dd" value="${tender.regDate}" ></fmt:formatDate>
    				</div>
   				</div>

    			<div class="row margin-bottom-5">
    				<div class="col-sm-3 col-xs-3">
		    			<label>지역</label>
    				</div>
    				<div class="col-sm-9 col-xs-9">
	    				서울 강성구
    				</div>
   				</div>

    			<div class="row margin-bottom-5">
    				<div class="col-sm-3 col-xs-3">
		    			<label>특이사항</label>
    				</div>
    				<div class="col-sm-9 col-xs-9">
	    				${tender.content}
    				</div>
   				</div>
   				<div class="row">
   					<img src="${root}/assets/img/beautiful-view-and-car.jpg" class="img-responsive center-block">
   				</div>	    			
	    	</div>

    	</div>
   	</div>
    <!--=== Cube-Portfdlio ===-->
    <div class="container margin-bottom-60">

    	<h2>입찰 리스트</h2>
		<table class="table table-hover table-bordered table-striped">
		<caption>Total 10</caption>
		<thead>
		    <tr>
		        <th style="width: 50px;">번호</th>
		        <th style="width: 70px;">입찰자</th>
		        <th>입찰 제목</th>
		        <th style="width: 70px;" class="hidden-sm">특이사항</th>
		        <th style="width: 90px;">작성일</th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${reply}" var="list">
			<tr>
		        <td>${list.tenderSeq}</td>
		        <td>****</td>
		        <td>넥서스</td>
		        <td class="hidden-sm">특이사항</td>
		        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.regDate}" ></fmt:formatDate>
		        </td>
		    </tr>
		    <tr class='hidden-md hidden-lg'>
		    	<td colspan="4">${list.contents}</td>
		    </tr>
			</c:forEach>
		</tbody>
		</table>

		<div class="row text-center">
	    	<ul class="pagination">
	        </ul>
        </div>

    	<h2>입찰하기</h2>
		<form class="reg-page sky-form" method="POST" action="doRegMember">
			<label>입찰 제목 <span class="color-red">*</span></label>
		    <div class="row">
		    	<div class="col-sm-10">
		        	<input type="text" class="form-control margin-bottom-10" name="corpName" id="corpName" placeholder="구매자에게 제시할 조건이나 옵션을 기술해 주세요">
	        	</div>
		  	</div>
			<label>사진 등록 <span class="color-red">*</span></label>
		    <div class="row">
		    	<div class="col-sm-6">
		        	<input type="file" class="form-control margin-bottom-10" name="picture" id="picture">
	        	</div>
		    	<div class="col-sm-6">
		    		차량의 사진이나 인테리어 사진을 올려주세요.
		    	</div>
		  	</div>
		  	<div class="row">
			    <div class="col-sm-12 text-center">
					<button type="button" class="btn-u">추가 입찰하기</button>
				</div>
		  	</div>
		</form>

    </div>
    
 


<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>