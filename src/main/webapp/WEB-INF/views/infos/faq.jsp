<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="어플다운로드" />
</c:import>



    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">FAQ</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">FAQ</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

     <!--=== Content Part ===-->
    <div class="container content">
    	<div class="row">
            <div class="col-md-9">
    			<!-- General Questions -->
                <div class="headline"><h2>상위 10개 FAQ</h2></div>
                <div class="panel-group acc-v1 margin-bottom-40" id="accordion">
                </div><!--/acc-v1-->

                <div class="headline"><h2>기타 FAQ</h2></div>
                <div class="panel-group acc-v1" id="accordion-1">
                </div><!--/acc-v1-->
                <!-- End Other Questions -->
    		</div><!--/col-md-9-->

    		<div class="col-md-3">
            	<!-- Contacts -->
                <div class="headline"><h2>연락처</h2></div>
                <ul class="list-unstyled who margin-bottom-30">
                    <li><a href="#"><i class="fa fa-home"></i>충청남도 공주시 금학동길 35-23 5층 502호</a></li>
                    <li><a href="#"><i class="fa fa-envelope"></i>info@example.com</a></li>
                    <li><a href="#"><i class="fa fa-phone"></i>1333-1222</a></li>
                </ul>
                <!-- End Contacts -->

            	<!-- Business Hours -->
                <div class="headline"><h2>업무시간</h2></div>
                <ul class="list-unstyled margin-bottom-30">
                	<li><strong>월 - 금:</strong> 오전 10시 ~ 오후 8시</li>
                	<li><strong>토요일:</strong> 오전 11시 ~ 오후 3시</li>
                </ul>
                <!-- End Business Hours -->
                <!-- End Social -->
            </div><!--/col-md-3-->
        </div><!--/row-->
    </div><!--/container-->
    <!--=== End Content Part ===-->

<script type="text/html" id="tmpl_list">                    
<div class="panel panel-default">
<div class="panel-heading">
<h4 class="panel-title">
    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse\${index}">
        \${index}. \${title}
    </a>
</h4>
</div>
<div id="collapse\${index}" class="panel-collapse collapse {{if index==1}}in{{/if}}">
<div class="panel-body">
    {{html answer}}
</div>
</div>
</div>
</script>
<script type="text/javascript">

var tbody = $('#accordion');
var board = tbody.board({
	page: 1,
	len: 10,
	api: '${root}/api/faq/',
	template: $('#tmpl_list'),
	paging: $('.pageing'),
	onRender : function( $tar, datas ) {
		
	}
});

board.load();

</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>