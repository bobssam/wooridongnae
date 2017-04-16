<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="공지사항" />
</c:import>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />


    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">공지사항</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">공지사항</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

     <!--=== Content Part ===-->
    <div class="container content">
    
		<table class="table table-hover table-bordered table-striped">
		<tbody>
		    <tr>
		        <th>번호</th>
		        <td></td>
		    </tr>
		    <!-- 
		        <th>제목</th>
		        <th style="width: 100px;" class="hidden-sm">작성자</th>
		        <th style="width: 70px;">읽은수</th>
		        <th style="width: 90px;">등록일</th>
		        -->		    
		</tbody>
		</table>

		<div class="row text-center">
	    	<ul class="pagination">
	        </ul>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->



</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>