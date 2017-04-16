<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>
	<style type="text/css">
    	.reg-header {
			margin-bottom: 10px;
    	}
    	.breadcrumbs-v3 {
    		padding: 20px 0;
    	}

		.row-fluid {
			padding-left: 0;
			padding-right: 0;
		}
	</style>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">마이페이지
				<a href="${root}/mypage/modify" type="button" class="btn-u btn-u-xs">내정보 수정하기</a>
            </h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">견적</a></li>
                <li class="active">마이페이지</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->

	<div class="breadcrumbs-v3 img-v2 text-center">
        <div class="container">
        	<img src="${root}/assets/img/icons/medal-${gradeName}.png">
            <p>${memberVO.name}님은 ${gradeKrName} 회원입니다.<br/>
            	<small>입찰 ${stats.cnt+0}건, 댓글수 ${stats.total+0}, 낙찰수 ${stats.successCnt+0}</small></p>
        </div><!--/end container-->
    </div>


    <!--=== Cube-Portfdlio ===-->
    <div class="cube-portfolio container margin-bottom-60">
    	<div class="row-fluid col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">

			<div class="reg-header">
            	<h2>나의 거래 현황</h2>
		    	<p class="text-center"><a href="${root}/infos/appdown"><b>어플리케이션</b></a>을 이용하면 소식을 바로 바로 확인 할 수 있습니다.</p>
            </div>

            <div class="text-center margin-bottom-30">
	            보유 포인트 <b>${memberVO.point}</b>
				<button type="button" class="btn-u btn-u-xs">충전하기</button>
				<a href="${root}/mypage/payment" type="button" class="btn-u btn-u-xs" data-toggle="modal" data-target="#paymentModal" data-backdrop="static">결제내역</a>
            </div>


			<div class="tab-v1">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tender-T" data-toggle="tab">입찰중 (<span id="total-T">${tenderStats.T+0}</span>)</a></li>
					<li><a href="#tender-S" data-toggle="tab">입찰 완료 (<span id="total-S">${tenderStats.S+0}</span>)</a></li>
					<li><a href="#tender-D" data-toggle="tab">입찰 만료 (<span id="total-D">${tenderStats.D+0}</span>)</a></li>
				</ul>

				<div class="tab-content">

					<div class="tab-pane fade in active" id="tender-T">
						<table class="table table-hover table-bordered table-striped">
						<caption>자동차 - 최근 입찰 <span class="total"></span>
						</caption>
						<thead>
						    <tr>
						        <th style="width: 50px;">번호</th>
						        <th>입찰 제목</th>
						        <th style="width: 70px;" class="hidden-sm">특이사항</th>
						        <th style="width: 90px;">작성일</th>
						        <th style="width: 90px;"></th>
						    </tr>
						</thead>
						<tbody>
						</tbody>
						</table>

						<div class="text-center"><div class="pagination"></div></div>

					</div>
					<div class="tab-pane fade" id="tender-S">

						<table class="table table-hover table-bordered table-striped">
						<caption>자동차 - 최근 입찰 <span class="total"></span>
						</caption>
						<thead>
						    <tr>
						        <th style="width: 50px;">번호</th>
						        <th>입찰 제목</th>
						        <th style="width: 70px;" class="hidden-sm">특이사항</th>
						        <th style="width: 90px;">작성일</th>
						        <th style="width: 90px;"></th>
						    </tr>
						</thead>
						<tbody>
						</tbody>
						</table>

						<div class="text-center"><div class="pagination"></div></div>

					</div>

					<div class="tab-pane fade" id="tender-D">

						<table class="table table-hover table-bordered table-striped">
						<caption>자동차 - 최근 입찰 <span class="total"></span>
						</caption>
						<thead>
						    <tr>
						        <th style="width: 50px;">번호</th>
						        <th>입찰 제목</th>
						        <th style="width: 70px;" class="hidden-sm">특이사항</th>
						        <th style="width: 90px;">작성일</th>
						        <th style="width: 90px;"></th>
						    </tr>
						</thead>
						<tbody>
						</tbody>
						</table>

						<div class="text-center"><div class="pagination"></div></div>

					</div>
				</div>
			</div>
		</div>
    </div>

   <div id="paymentModal" class="modal fade" role="dialog"  aria-labelledby="myLargeModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">결제내역</h4>
	      </div>
	      <div class="modal-body">

			</div>
		</div>
		</div>
    </div>

    <script type="text/html" id="tmpl_tender_list">
<tr>
    <td>\${tenderNo}</td>
    <td>\${title}</td>
    <td class="hidden-sm">특이사항</td>
    <td>\${dateConv(wantDate)}</td>
    <td><button type="button" class="btn-u btn-u-sm pull-right">보러가기</button></td>
</tr>
<tr class='hidden-md hidden-lg'>
	<td colspan="4">특이사항</td>
</tr>
    </script>
    <script type="text/html" id="tmpl_tender_empty">
<tr>
    <td colspan="5" class="text-center">입찰이 없습니다.</td>
</tr>
    </script>
    <script type="text/javascript">

    	var $tarT = $('#tender-T');
    	var $tarS = $('#tender-S');
    	var $tarD = $('#tender-D');

    	var boardT = $tarT.find('tbody').board({
			page: 1,
			len: 10,
			api: '${root}/api/tender/myList/',
			total : $('#tender-T .total,#total-T'),
			template: {list:$('#tmpl_tender_list'), empty:$('#tmpl_tender_empty')},
			paging: $tarT.find('.pagination'),
			onRender : function( $tar, datas ) {
				$tar.find('button').on('click', function(){
					location.href = '${root}/tender/lists?categoryNo2='+datas.categoryNo2+'&tenderNo='+datas.tenderNo;
				});
			},
			onLoaded : function( datas ) {
			}
		});
    	var boardS = $tarS.find('tbody').board({
			page: 1,
			len: 10,
			api: '${root}/api/tender/myList/',
			total : $('#tender-S .total,#total-S'),
			template: {list:$('#tmpl_tender_list'), empty:$('#tmpl_tender_empty')},
			paging: $tarS.find('.pagination'),
			onRender : function( $tar, datas ) {
				$tar.find('button').on('click', function(){
					location.href = '${root}/tender/lists?categoryNo2='+datas.categoryNo2+'&tenderNo='+datas.tenderNo;
				});
			},
			onLoaded : function( datas ) {
			}
		});
    	var boardD = $tarD.find('tbody').board({
			page: 1,
			len: 10,
			api: '${root}/api/tender/myList/',
			total : $('#tender-D .total,#total-D'),
			template: {list:$('#tmpl_tender_list'), empty:$('#tmpl_tender_empty')},
			paging: $tarD.find('.pagination'),
			onRender : function( $tar, datas ) {
				$tar.find('button').on('click', function(){
					location.href = '${root}/tender/lists?categoryNo2='+datas.categoryNo2+'&tenderNo='+datas.tenderNo;
				});
			},
			onLoaded : function( datas ) {
			}
		});

    	boardT.setFilter({status:'T'});
    	boardS.setFilter({status:'S'});
    	boardD.setFilter({status:'D'});

    	boardT.load();

    	$('.nav-tabs li').eq(0).data('board', boardT);
    	$('.nav-tabs li').eq(1).data('board', boardS);
    	$('.nav-tabs li').eq(2).data('board', boardD);

    	$('.nav-tabs li').on('click', function(){
    		$(this).data('board').load();
    	});


    </script>


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>