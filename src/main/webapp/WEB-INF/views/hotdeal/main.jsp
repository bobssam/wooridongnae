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
            <h1 class="pull-left">HOT DEAL</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">자동차 견적</a></li>
                <li class="active">HOT DEAL</li>
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




	<div class="modal fade container content" role="dialog" id="orderForm">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<form class="reg-page sky-form" method="POST" action="${root}/api/hotdealask/add">
				<input type="hidden" name="hotdealNo" value="">

				<div class="reg-header">
					<h2>
						<span data-name="title">SMS3 333 프로젝트</span>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</h2>
				</div>

				<label>연락처 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="mobile" id="mobile" placeholder="연락처를 적어주세요" required title="연락처를 적어주시기 바랍니다.">
					</div>
					<div class="col-sm-3 col-xs-4">ex) 010-1111-2222</div>
				</div>

				<label>지역 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="area" placeholder="ex) 충청남도 공주시" required title="지역을 적어주시기 바랍니다.">
					</div>
					<div class="col-sm-3 col-xs-4">
					</div>
				</div>

				<label>구입 시기 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">

						<label class="input"> <i class="icon-append fa fa-calendar"></i> <input type="text" name="wantDate" id="wantDate" maxlength="10" placeholder="희망 구입시기를 선택해주세요" required title="희망 구입시기를 선택해 주시기 바랍니다.">
						</label>
					</div>
					<div class="col-sm-3 col-xs-4"></div>
				</div>

				<label>구입방법 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<select class="form-control margin-bottom-10" name="buyType" id="buyType">
							<option value="1">할부</option>
							<option value="2">리스</option>
							<option value="3">현금</option>
							<option value="4">장기렌탈</option>
						</select>
					</div>
					<div class="col-sm-3 col-xs-4"></div>
				</div>


				<label>선납금 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="prePrice" id="prePrice" placeholder="선납금을 적어주세요" required title="선납금을 적어주시기 바랍니다.">
					</div>
					<div class="col-sm-3 col-xs-4">ex) 5천만원</div>
				</div>

				<label>계약기간 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="contractDuration" id="contractDuration" placeholder="계약기간 적어주세요" required title="계약기간 적어주시기 바랍니다.">
					</div>
					<div class="col-sm-3 col-xs-4">ex) 1년</div>
				</div>

				<label>요청사항</label>
				<textarea class="form-control" name="ask"></textarea>
				<div class="note margin-bottom-10">시승 및 견적에 대해서 궁금하시거나, 요청하실것을 적어주세요</div>

				<hr>

				<div class="row text-right">
					<button class="btn-u btn submit" type="submit">견적 넣기</button>
				</div>

			</form>
		</div>
	</div>




	<div class="modal fade container content" role="dialog" id="orderedForm">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<form class="reg-page sky-form" method="POST" action="">

				<div class="reg-header">
					<h2>
						<span data-name="title">SMS3 333 프로젝트</span>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</h2>
				</div>

				<label>연락처 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="mobile" readonly="readonly">
					</div>
				</div>

				<label>지역 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="area"  readonly="readonly">
					</div>
				</div>

				<label>구입 시기 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="wantDate"  readonly="readonly">
					</div>
				</div>

				<label>구입방법 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="buyType"  readonly="readonly">
					</div>
				</div>


				<label>선납금 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="prePrice"  readonly="readonly">
					</div>
				</div>

				<label>계약기간 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-8">
						<input type="text" class="form-control margin-bottom-10" name="contractDuration"  readonly="readonly">
					</div>
				</div>

				<label>요청사항</label>
				<div data-name="ask" data-formatter="brConv"></div>

				<div class="row text-right">
					<button class="btn-u btn submit" type="submit">확인 완료</button>
				</div>
			</form>
		</div>
	</div>


<script type="text/html" id="tmpl_list">
<img src="${root}/api/file/download/\${fileNo}" alt="" class="img-responsive">
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

	<c:if test="${sessionScope.memberVO==null}">
		<div class="alert alert-info" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	   		시승 및 견적 요청을 위해선 <a href="${root}/member/login?rurl=/hotdeal/">로그인</a> 필요합니다.
		</div>
		<a href="#" class="btn btn-u btn-u-lg disabled margin-bottom-30"><i class="fa fa-chevron-circle-right"></i> 시승 및 견적 요청하기</a>
	</c:if>
	<c:if test="${sessionScope.memberVO!=null}">
		<div class="order ordered">
			<div class="alert alert-info" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	   			이미 시승 및 견적 요청을 하셨습니다. 빠른 시일안에 연락을 드리겠습니다.<br>
	   			<a href="#" class="show_ordered">신청 내역 보기</a>
			</div>
		</div>
		<div class="order write margin-bottom-30" >
			<a href="#" class="btn btn-u btn-u-lg show_order" data-toggle="modal" data-target="#orderForm" ><i class="fa fa-chevron-circle-right"></i> 시승 및 견적 요청하기</a>
		</div>
	</c:if>
</div>
</script>
<script type="text/javascript">

$(function(){

	// 핫딜 게시판 처리
	var $tar = $('.left_menu');
	var selectedData = null;
	var $selectedTar = null;
	var board = $tar.board({
		page: 1,
		len: 100,
		api: '${root}/api/hotdeal/',
		total : $('#total'),
		template: $('#tmpl_list'),
		onRender : function( $tar, datas ) {

			var $newEl = $('#tmpl_blog').tmpl(datas);
			$newEl.data('data', datas);

			if( datas.hotdealAskNo > 0 )
				$newEl.find('.order.write').hide();
			else
				$newEl.find('.order.ordered').hide();

			// 신청서 내역 보기
			$newEl.find('.show_ordered').on('click', function(){

				$.ajax({
					url : '${root}/api/hotdealask/view',
					data: { hotdealNo:datas.hotdealNo },
					dataType:'json',
					success:function(datas) {

						$('#orderedForm').modal();
						$('#orderedForm').formData(datas);
					}
				});

				return false;
			});
			$('.right_cotainer').append($newEl);


			// 블러그형중 자신것을 보이게 한다.
			$tar.off('click').on('click', function(){

				$('.blog').removeClass('selected');
				$newEl.addClass('selected');

			});

			$newEl.find('.show_order').on('click', function(){
				selectedData = datas;
				$selectedTar = $newEl;
			});

		},
		onRendered : function( ) {
			var hotdealNo = $.getParam("hotdealNo");
			if( hotdealNo ) {
				$('.left_menu img').each(function(){
					var data = $(this).data('data');
					if( data.hotdealNo == hotdealNo ) $(this).trigger('click');
				});
			} else {
				$('.left_menu img').eq(0).trigger('click');
			}
		}
	});
	board.load();


	/*****************************************
				서브밋 처리
	*****************************************/
	var $form = $('form').eq(0);
	$form.validate();
	$form.on('submit', function(){

		var valided = $form.valid();
		if( !valided ) return false;

		var formData = $form.formData();
		formData.wantDate = new Date(formData.wantDate);
		$.ajax({
			type : "POST",
			url :  $form.attr('action'),
			cache : false,
			data : formData,
			dataType : 'json',
			success : function(datas){
				if( datas.status == "ok" ){
					toastr.success('시승 및 견적 요청이 등록되었습니다.\n 빠른 시일안에 연락을 드리겠습니다.');
					$('#orderForm').modal('hide');
					$selectedTar.find('.order').hide();
					$selectedTar.find('.order.ordered').show();

				} else {
					toastr.error('시승 및 견적 요청이 실패\n'+datas.reason);
				}
			},
			error : function(){
				toastr.error('시승 및 견적 요청이 실패');
			}
		});
		return false;

	});
	/*****************************************
				날짜 컴포넌트
	*****************************************/
	$('#wantDate').datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '<i class="fa fa-caret-left"></i>',
		nextText: '<i class="fa fa-caret-right"></i>',
		onSelect: function( selectedDate )
		{

		},
		minDate:0,
		maxDate:"+3m"
	});


	/*****************************************
				팝업 처리
	*****************************************/
	$('#orderForm').on('show.bs.modal', function(){
		selectedData.buyType = 1;
		$('#orderForm').formData(selectedData, true);
	});

	/*****************************************
				신청 내역 보기
	*****************************************/
	$('form').eq(1).on('submit', function(){
		$('#orderedForm').modal('hide');
		return false;
	});

	$('.show_oredered').on('click', function(){
		$.ajax({
			url : '${root}/api/hotdealask/view',
			data: { hotdealNo:selectedData.hotdealNo },
			dataType:'json',
			success:function(datas) {
				$('#orderedForm').modal();
				$('#orderedForm').formData(datas);
			}
		});

		return false;
	});

});


</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>