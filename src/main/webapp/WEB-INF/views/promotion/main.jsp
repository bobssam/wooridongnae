<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <style type="text/css">
    	h4 {
    		font-size: 14px;
    		color:#000;
    		margin-bottom: -10px;
    	}
    	.left_menu {
    		overflow-y:auto;
    		overflow-x:hidden;
    		max-height: 600px;
    		float:left;
    	}
    	.left_menu img{
    		margin-bottom: 15px;
    	}
    	.right_cotainer {
    	}
    	.right_cotainer img{
    	}

    	.right_cotainer .btn-u{
    		display: block;
    		text-align: center;
    	}


    	@media (max-width: 768px) {
		  .hidden-sm {
		    display: none !important;
		  }
		  .blog {
		  	display: block !important;
		  }
		}

		.blog {
			display: none;
		}
		.blog.selected {
			display: block;
		}
    </style>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">PROMOTION</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">자동차 견적</a></li>
                <li class="active">PROMOTION</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

	<div class="container content margin-bottom-60">
		<div class="row">
			<div class="left_menu col-md-3 hidden-sm">
				Loading..
			</div>
			<div class="right_cotainer  col-md-9">

			</div>
		</div>
	</div>




<script type="text/html" id="tmpl_list">
<img src="${root}/api/file/download/\${fileNo}" alt="" width="210" height="130"><br/>
</script>
<script type="text/html" id="tmpl_blog">
<div class="blog">
	<div class="margin-bottom-10">
		<img src="${root}/api/file/download/\${fileNo}" alt="" class="img-responsive">
	</div>
	<h4>\${model}</h4>
	<h2>\${title}</h2>
	<div class="margin-bottom-30">
		{{html comment}}
	</div>
</div>
</script>
<script type="text/javascript">

$(function(){

	// 핫딜 게시판 처리
	var $tar = $('.left_menu');
	var isFirst = true;
	var selectedData = null;
	var board = $tar.board({
		page: 1,
		len: 100,
		api: '${root}/api/promotion/',
		total : $('#total'),
		template: $('#tmpl_list'),
		onRender : function( $tar, datas ) {

			var $newEl = $('#tmpl_blog').tmpl(datas);
			$newEl.data('data', datas);
			$('.right_cotainer').append($newEl);


			// 블러그형중 자신것을 보이게 한다.
			$tar.off('click').on('click', function(){

				$('.blog').removeClass('selected');
				$newEl.addClass('selected');

			});

			if( isFirst ) {
				$tar.trigger('click');
				isFirst = false;
			}
		}
	});
	board.load();



});


</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>