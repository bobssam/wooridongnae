<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>
	<style type="text/css">
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
    </div>


    <div class="cube-portfolio container margin-bottom-60">
    	<div class="row-fluid col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">

			<div class="reg-header">
            	<h2>나의 거래 현황</h2>
		    	<p class="text-center"><a href="${root}/infos/appdown"><b>어플리케이션</b></a>을 이용하면 소식을 바로 바로 확인 할 수 있습니다.</p>
            </div>

			<div class="tab-v1">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tender-T" data-toggle="tab">입찰중 (<span id="total-T">${tenderStats.T+0}</span>)</a></li>
					<li><a href="#tender-S" data-toggle="tab">입찰 완료 (<span id="total-S">${tenderStats.S+0}</span>)</a></li>
					<li><a href="#tender-D" data-toggle="tab">입찰 만료 (<span id="total-D">${tenderStats.D+0}</span>)</a></li>
				</ul>

				<div class="tab-content">

					<div class="tab-pane fade in active" id="tender-T">
					</div>


					<div class="tab-pane fade" id="tender-S">

						<table class="table table-hover table-bordered table-striped">
						<caption>최근 완료</caption>
						<thead>
						    <tr>
						        <th style="width: 50px;">번호</th>
						        <th>견적 제목</th>
						        <th style="width: 150px;" class="hidden-sm">특이사항</th>
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
						<caption>최근 만료</caption>
						<thead>
						    <tr>
						        <th style="width: 50px;">번호</th>
						        <th>견적 제목</th>
						        <th style="width: 150px;" class="hidden-sm">특이사항</th>
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


<script type="text/html" id="tmpl_tender_list">
<table class="table table-hover table-bordered table-striped">
<caption>\${categoryMap[categoryNo2]} - \${title}
	<button type="button" class="btn-u btn-u-sm pull-right">보러가기</button>
</caption>
<thead>
    <tr>
        <th style="width: 50px;">번호</th>
        <th style="width: 70px;">입찰자</th>
        <th class="hidden-sm">입찰 제목</th>
        <th style="width: 90px;">작성일</th>
    </tr>
</thead>
<tbody>
</tbody>
</table>
</script>
<script type="text/html" id="tmpl_tender_reply_list">
<tr>
    <td>\${tenderSeq}</td>
    <td>\${id}</td>
    <td class="hidden-sm">\${contents}</td>
    <td>\${diffDateConv(regDate)}</td>
</tr>
<tr class='hidden-md hidden-lg'>
	<td colspan="4">\${contents}</td>
</tr>
</script>
<script type="text/html" id="tmpl_tender_list2">
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
<script type="text/javascript">
    	var categoryMap = {1: '자동차', 2:'인테리어' };

    	var $tarT = $('#tender-T');
    	var $tarS = $('#tender-S');
    	var $tarD = $('#tender-D');

    	var boardT = $tarT.board({
			page: 1,
			len: 10,
			api: '${root}/api/tender/myList/',
			total : $('#tender-T .total'),
			template: {list:$('#tmpl_tender_list'), empty:$('#tmpl_tender_empty')},
			paging: $tarT.find('.pagination'),
			onRender : function( $tar, datas ) {

				var boardIn = $tar.find('tbody').board({
					page: 1,
					len: 10,
					paging: $('<div>'),
					api: '${root}/api/tender/tendering/',
					template: {list:$('#tmpl_tender_reply_list'), empty:$('#tmpl_tender_empty')}
				});
				boardIn.setFilter({tenderNo:datas.tenderNo});
				boardIn.load();

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
			total : $('#tender-S .total'),
			template: {list:$('#tmpl_tender_list2'), empty:$('#tmpl_tender_empty')},
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
			total : $('#tender-D .total'),
			template: {list:$('#tmpl_tender_list2'), empty:$('#tmpl_tender_empty')},
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




<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>