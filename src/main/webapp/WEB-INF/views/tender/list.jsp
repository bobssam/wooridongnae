<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />


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
	padding: 10px 0 !important;
	margin-bottom: 20px !important;
}
.col-xs-12 {
	padding-left: 0;
	padding-right: 0;
}
table tbody th {
	background: #f1f1f1;
}

</style>


	<script src="${root}/assets/plugins/fileupload/js/jquery.iframe-transport.js"></script>
	<script src="${root}/assets/plugins/fileupload/js/jquery.fileupload.js"></script>
    <link rel="stylesheet" href="${root}/assets/css/pages/page_search_inner.css">

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">최근 견적 리스트</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">견적</a></li>
                <li class="active">최근 견적 리스트</li>
            </ul>
        </div>
    </div>

	<div class="tender_list list${param.categoryNo2}">
		<div class="container">
			<h2><c:if test="${param.categoryNo2==3}">자동차 비교 견적</c:if><c:if test="${param.categoryNo2==4}">자동차 수리 비교 견적</c:if></h2>
			<div class="object"></div>
		</div>
    </div>


	<!----------------------------------------------------------------------------
								list
	 ---------------------------------------------------------------------------->
	<div class="content-body list" id="list">

	    <div class="search-block-v2">
	    	<form name="tender_list_form" id="tender_list_form">
	    		<input type="hidden" name="categoryNo2" value="${param.categoryNo2}">
		    	<div class="col-md-8 col-md-offset-2">

		    		<div class="col-sm-10 col-xs-12">

		    			<div class="row margin-bottom-10">

		    				<div class="col-sm-3 col-xs-3"><label>희망 지역</label> <a href="${root}/area/select" type="button" class="btn-u btn-u-xs" data-toggle="modal" data-target="#areaModal" data-backdrop="static">지역 변경</a></div>
		    				<div class="col-sm-9 col-xs-9" id="selected_areas">
		    					<c:forEach var="area" items="${wantArea}" >
		    					<small>${area.bigAreaName}>${area.smallAreaName}<button type="button" class="btn-u btn-u-xs btn-u-default remove"><i class="fa fa-times"></i></button><input type="hidden" name="areaCode" value="${area.areaCode}"> </small>
		    					</c:forEach>

		    					<c:if test="${wantArea==null || wantArea == ''}" >
		    						<p>좌측의 지역변경버튼을 눌러 희망 지역을 선택해주세요</p>
		    					</c:if>
			    			</div>
		    			</div>

		    			<div class="row margin-bottom-10">
		    				<div class="col-sm-3 col-xs-3">
				    			<label>키워드</label>
		    				</div>
		    				<div class="col-sm-5 col-xs-9">
			    				<input type="text" class="form-control input-sm" name="keyword">
		    				</div>
		   				</div>

		    			<div class="row margin-bottom-10">
		    				<div class="col-sm-3 col-xs-3">
			    				<label>희망날짜</label>
		    				</div>
		    				<div class="col-sm-2 col-xs-4">
								<input type="text" class="form-control input-sm datepicker" data-date-format="mm/dd/yyyy" name="startDate">
		    				</div>
		    				<div class="col-sm-1 col-xs-1 text-center">
		    					~
		    				</div>
		    				<div class="col-sm-2 col-xs-4">
		    					<input type="text" class="form-control input-sm datepicker" data-date-format="mm/dd/yyyy" name="endDate">
		   					</div>
		   				</div>



		    		</div>
		    		<div class="col-sm-2 col-xs-12 text-center">
		    			<button type="submit" class="btn-u btn-u-lg">검색</button>

		    			<c:choose>
		    			<c:when test="${tenderNo==0}">
		    			<a href="${root}/tender/make?categoryNo2=${param.categoryNo2}" class="btn-u btn-u" style="margin-top: 5px">견적 등록하기</a>
		    			</c:when>
		    			</c:choose>
		    		</div>
		    	</div>
		    	<div class="clear-both"></div>
		    </form>

	   	</div>
	    <div class="container margin-bottom-60">
			<c:if test="${sessionScope.memberVO==null}">
				<div class="alert alert-info" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		    		견적 등록을 위해선 <a href="${root}/member/login?rurl=/tender/lists?categoryNo2=${param.categoryNo2}">로그인</a> 필요합니다.
				</div>
			</c:if>
	    	<c:if test="${tenderNo!=null && tenderNo!=0}">
				<div class="alert alert-success" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  진행중인 견적이 있습니다. <a href="${root}/tender/lists?categoryNo2=${param.categoryNo2}&tenderNo=${tenderNo}" >해당 견적으로 이동</a>
				</div>
		    </c:if>


	    	<h2>견적 리스트</h2>

			<table class="table table-hover table-bordered table-striped" id="tender_list">
			<caption>Total <span class="total"></span></caption>
			<thead>
			    <tr>
			        <th style="width: 50px;">번호</th>
			        <th style="width: 70px;">작성자</th>
			        <th style="width: 70px;">입찰수</th>
			        <th class="hidden-sm">요청사항</th>
			        <th style="width: 90px;">수리품목</th>
			        <th style="width: 90px;">모델</th>
			        <th style="width: 90px;">희망날짜</th>
			    </tr>
			</thead>
			<tbody>
			</tbody>
			</table>

			<div class="row text-center">
		    	<ul class="pagination">
		        </ul>
	        </div>
	    </div>



	   <div id="areaModal" class="modal fade" role="dialog"  aria-labelledby="myLargeModalLabel">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">지역변경</h4>
		      </div>
		      <div class="modal-body">

				</div>
			</div>
			</div>
	    </div>

<script type="text/html" id="tmpl_tender_list">
<tr>
<td>\${tenderNo}</td>
<td>\${id}</td>
<td>\${replyCount}</td>
<td class="hidden-sm"><a href="#">\${content.substring(0,60)}..</a></td>
<td>\${categoryNo3Name}</td>
<td>\${categoryNo4Name}</td>
<td>\${dateConv(wantDate)}
	{{if status == 'S'}}
	<button type="button" class="btn-u btn-u-xs btn-u-default">입찰 완료</button>
	{{else status == 'T'}}
		{{if isTender > 0}}
			<button type="button" class="btn-u btn-u-xs btn-u-red">입찰 됨(\${isTender})</button>
		{{else}}
			<button type="button" class="btn-u btn-u-xs">입찰 중</button>
		{{/if}}
	{{/if}}
</td>
</tr>
<tr>
	<td colspan="7" class="hidden-md hidden-lg"><a href="#">\${content.substring(0,60)}..</a></td>
</tr>
</script>
<script type="text/html" id="tmpl_tender_empty">
<tr>
    <td colspan="6" class="text-center">견적이 없습니다.</td>
</tr>
</script>
<script type="text/html" id="tmpl_selected_area_list">
<small>\${name} <button type="button" class="btn-u btn-u-xs btn-u-default remove"><i class="fa fa-times"></i></button><input type="hidden" name="areaCode" value="\${area}"> </small>
</script>

<script type="text/javascript">

	var buyTypeMap = {1:'할부',2:'리스',3:'현금',4:'장기렌탈'};
	function buyTypeConv( data ) {
		return buyTypeMap[data];
	}

	var tbody = $('#tender_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		api: '${root}/api/tender/',
		total : $('#list .total'),
		template: {list:$('#tmpl_tender_list'), empty: $('#tmpl_tender_empty')},
		paging: $('#list .pagination'),
		onRender : function( $tar, datas ) {
			$tar.find('a, button').on('click', function(){
				var datas = $(this).closest('tr').data('data');
				var $content = mode('view', {tenderNo:datas.tenderNo, memberNo:datas.memberNo, status:datas.status});

				showView( datas.tenderNo );


				$content.formData( datas );
				if( !datas.fileNo )  $content.find('img').hide();
				else {
					$content.find('img').attr('src', "${root}/api/file/download?fileNo="+datas.fileNo).show();
					$content.find('a').attr('href', "${root}/api/file/download?fileNo="+datas.fileNo);
				}
				return false;
			});
		},
		onLoaded : function( datas ) {
		}
	});
	board.setFilter( $('#tender_list_form').formData() );
	board.load();

	$('#tender_list_form').on('submit', function(){
		board.setFilter( $('#tender_list_form').formData() );
		board.load();
		return false;
	});

	$('#list .datepicker').datepicker({
	    dateFormat: 'yy-mm-dd',
	    prevText: '<i class="fa fa-caret-left"></i>',
	    nextText: '<i class="fa fa-caret-right"></i>',
	    onSelect: function( selectedDate )
	    {
	        $('#finish').datepicker('option', 'minDate', selectedDate);
	    },
	    minDate:0,
	    maxDate:"+3m"
	});

	function getAreas() {
		var areas = [];
		$('[name="areaCode"]').each(function(){
			var name = $(this).parent().text();
			areas.push({area:this.value, name:name});
		});
		return areas;
	}
	function setAreas( datas ) {
		var $tar = $('#selected_areas');
		$tar.empty();
		for( var i=0,len=datas.length;i<len;i++ ) {
			$tar.append( $('#tmpl_selected_area_list').tmpl(datas[i]) );
		}
		$('#list button.remove').on('click', function(){
			$(this).parent().remove();
		});
	}

	$('#list button.remove').on('click', function(){
		$(this).parent().remove();
	});

</script>
	</div>




	<!----------------------------------------------------------------------------
								View
	 ---------------------------------------------------------------------------->
	<div class="content-body view" style="display: none;" id="view">

	    <div class="container margin-bottom-60">
			<h2>견적 내역</h2>

			<%----------------------------------------------------------------
									신차구매
			----------------------------------------------------------------%>
			<c:if test="${param.categoryNo2 == 3}">
			<table class="table table-hover table-bordered">
			<tbody>
			    <tr>
			        <th style="width:90px">번호</th>
			        <td data-name="tenderNo"></td>
			        <th style="width:70px">등록일</th>
			        <td data-name="regDate" data-formatter="dateConv"></td>
				</tr>
			    <tr>
			        <th>브랜드</th>
			        <td data-name="categoryNo3Name"></td>
			        <th>모델</th>
			        <td data-name="categoryNo4Name"></td>
				</tr>
			    <tr>
			        <th>등급</th>
			        <td data-name="grade"></td>
			        <th>지역</th>
			        <td><span data-name="bigAreaName"></span> - <span data-name="smallAreaName"></span></td>
				</tr>
			    <tr>
			        <th>구입방법</th>
			        <td data-name="buyType" data-formatter="buyTypeConv"></td>
			        <th>구매시기</th>
			        <td data-name="wantDate" data-formatter="dateConv"></td>
				</tr>
			    <tr>
			        <th>알아본 조건</th>
			        <td data-name="title" colspan="3"></td>
				</tr>
			    <tr>
			        <th>요청사항</th>
			        <td data-name="content" data-formatter="brConv" colspan="3"></td>
				</tr>
			</tbody>
			</table>
			</c:if>
			<%----------------------------------------------------------------
									수리견적
			----------------------------------------------------------------%>
			<c:if test="${param.categoryNo2 == 4}">
			<table class="table table-hover table-bordered">
			<tbody>
			    <tr>
			        <th style="width:90px">번호</th>
			        <td data-name="tenderNo"></td>
			        <th style="width:60px">등록일</th>
			        <td data-name="regDate" data-formatter="dateConv"></td>
				</tr>
			    <tr>
			        <th>수리 품목</th>
			        <td data-name="categoryNo3Name"></td>
			        <th>모델</th>
			        <td data-name="categoryNo4Name"></td>
				</tr>
			    <tr>
			        <th>브랜드</th>
			        <td data-name="grade"></td>
			        <th>지역</th>
			        <td><span data-name="bigAreaName"></span> - <span data-name="smallAreaName"></span></td>
				</tr>
			    <tr>
			        <th>알아본 조건</th>
			        <td data-name="title" colspan="3"></td>
				</tr>
			    <tr>
			        <th>요청사항</th>
			        <td data-name="content" data-formatter="brConv" colspan="3"></td>
				</tr>
			    <tr>
			        <th>수리부위</th>
			        <td class="imgs" colspan="3"></td>
				</tr>
			</tbody>
			</table>
			</c:if>


	    	<div class="text-center row margin-top-20">
	    		<button type="button" class="btn-u list">리스트로</button>
	    	</div>
		</div>

	    <div class="container margin-bottom-60">

	    	<h2>입찰 리스트</h2>
			<table class="table table-hover table-bordered table-striped" id="tender_reply_list">
			<caption>Total <span class="total"></span></caption>
			<thead>
			    <tr>
			        <th style="width: 50px;">번호</th>
			        <th style="width: 70px;">입찰자</th>
			        <th class="hidden-sm">입찰 제목</th>
			        <th style="width: 70px;">이미지</th>
			        <th style="width: 90px;">작성일</th>
			    </tr>
			</thead>
			<tbody>
			</tbody>
			</table>

			<div class="row text-center">
		    	<ul class="pagination">
		        </ul>
	        </div>

			<c:if test="${sessionScope.memberVO != null && sessionScope.memberVO.type >= 2}">
	    	<h2>입찰하기</h2>
			<form class="reg-page sky-form" method="POST" action="doRegMember" id="tender_form">
				<input type="hidden" name="tenderNo">

				<label>입찰 제목 <span class="color-red">*</span></label>
			    <div class="row">
			    	<div class="col-sm-10">
			        	<input type="text" class="form-control margin-bottom-10" name="contents" id="contents" placeholder="구매자에게 제시할 조건이나 옵션을 기술해 주세요">
		        	</div>
			  	</div>
				<label>사진 등록 <span class="color-red">*</span></label>
			    <div class="row">
			    	<div class="col-sm-6">
	                    <label class="form-control" id="fileLabel" style="height:auto">
							<span>사진 업로드 </span>
	                    	<input type="file" name="file" style="display: none">
							<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
	                   	</label>

			        	<!-- <input type="file" class="form-control margin-bottom-10" name="picture" id="picture">  -->
		        	</div>
			    	<div class="col-sm-6">
			    		차량의 사진이나 인테리어 사진을 올려주세요.
			    	</div>
			  	</div>
			  	<div class="row">
				    <div class="col-sm-12 text-center">
						<button type="submit" class="btn-u" id="btn_do_tender">추가 입찰하기</button>
					</div>
			  	</div>
			</form>
			</c:if>

	    </div>
<script type="text/html" id="tmpl_tender_reply_list">
<tr>
    <td>\${tenderSeq}</td>
    <td>\${id}</td>
    <td class="hidden-sm">\${contents}</td>
    <td>{{if fileNo}}<a href="${root}/api/file/download?fileNo=\${fileNo}" class="fancybox" title="상세이미지"><img src="${root}/api/file/download?fileNo=\${fileNo}" width="50" height="50"></a>{{/if}}</td>
    <td>\${diffDateConv(regDate)}
		{{if canBid()}}<button type="button" class="btn-u btn-u-xs ">낙찰 시키기</button>{{/if}}
	</td>
</tr>
<tr class='hidden-md hidden-lg'>
	<td colspan="4">\${contents}</td>
</tr>
</script>
<script type="text/html" id="tmpl_tender_reply_empty">
<tr>
    <td colspan="5" class="text-center">입찰글이 없습니다.</td>
</tr>
</script>
<script type="text/javascript">
	var userMemberNo = "${sessionScope.memberVO.memberNo}";
	var userType = "${sessionScope.memberVO.type}";
	var logBoard = null;
	$(function(){

		$('#view button.list').on('click', function(){
			mode('list');
		});
		var logTbody = $('#tender_reply_list tbody');
		logBoard = logTbody.board({
			page: 1,
			len: 10,
			api: '${root}/api/tenderReply/',
			total : $('#view .total'),
			template: {list:$('#tmpl_tender_reply_list'), empty:$('#tmpl_tender_reply_empty')},
			paging: $('#view .pagination'),
			onRender : function( $tar, datas ) {

			},
			onLoaded : function( datas ) {
				if( datas.data && datas.data.myTotal > 0 ) {
					$('#tender_form').show();
					$('#btn_do_tender').text('추가 입찰하기');
				} else {
					$('#tender_form').show();
					$('#btn_do_tender').text('신규 입찰하기');
				}
			}
		});

		$('#tender_form').on('submit', function(){

			var datas = $(this).formData();
			datas.where = JSON.stringify( {tenderNo:datas.tenderNo} );
			$.ajax({
				url : '${root}/api/tenderReply/add',
				data: datas,
				dataType:'json',
				type:'POST',
				success:function( datas ) {
					if( datas.status == 'ok' ) {

						callFileUpload(datas.data.tenderNo, datas.data.tenderSeq, function(){
							// 페이지 갱신
							$('#tender_form').formData({}, true);
							logBoard.load(1);
							toastr.success('입찰을 진행하였습니다.');
							cancelFileUpload();

						});
					}
				}
			});
			return false;
		});


		/*****************************************
					파일 업로드 처리
		*****************************************/
		var prevFile = null;

		$('input[name=file]').fileupload({
	        url: '${root}/api/file/upload/',
	        autoUpload : false,
	        imageMaxWidth: 300,
	        imageMaxHeight: 200,

	    }).on('fileuploadadd', function(e, data){

	        var acceptFileTypes = /^image\/(gif|jpe?g|png)$/i;
	        if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
	            toastr.error('이미지 파일만 올릴 수 있습니다.');
	            return false;
	        }
	        if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 1000000) {
	            toastr.error('1메가 이하의 이미지를 올려주세요.');
	            return false;
	        }

	        if( prevFile ) prevFile.abort();

	        $('.remove').removeClass('hide').off('click').on('click', function(){
	        	cancelFileUpload();
	        });

	        prevFile = data;
	        $('#fileLabel span').text(' '+data.originalFiles[0].name);

	        var reader = new FileReader();
	        // Closure to capture the file information.
	        reader.onload = (function(e) {

				var img = $('<img>', {src:e.target.result});
				$('#fileLabel span').prepend(img);
				if( img.width() > img.height() ) img.attr('width', 100);
				else img.attr('height', 50);
			});
	        reader.readAsDataURL(data.originalFiles[0]);

	    });
		function cancelFileUpload() {
	        if( prevFile ) prevFile.abort();
	        prevFile = null;
        	$('#fileLabel span').text('사진 업로드');
        	$('.remove').addClass('hide');
		}

		function callFileUpload( num, num2, callback ) {
			if( prevFile ) {
				prevFile.formData = {boardType:'tender_reply', boardNo:num, boardNo2:num2};
				prevFile.submit().complete(callback);
			} else {
				callback();
			}
		}

	});

	function showView( tenderNo ) {
		$.ajax({
			url :'${root}/api/tender/view',
			data: { where:JSON.stringify({tenderNo:tenderNo}) },
			dataType: 'json',
			success:function(datas){

				var $content = mode('view', {tenderNo:tenderNo, memberNo:datas.memberNo, status:datas.status});
				$content.formData( datas );

				if( !datas.fileNo1 && !datas.fileNo2 && !datas.fileNo3 && !datas.fileNo4 && !datas.fileNo5 )  $content.find('img').hide();
				else {
					$('.imgs').empty();
					for( var i=1;i<=5;i++ ) {
						if( datas['fileNo'+i] ) {
							$('.imgs').append( $('#tmpl_img').tmpl({fileNo:datas['fileNo'+i]}) );
						}
					}
				}

			}
		});
	}

</script>

	</div>

<script type="text/html" id="tmpl_img">
<div class="col-sm-2 col-xs-4">
	<img src="${root}/api/file/download/\${fileNo}" class="img-responsive">
</div>
</script>
<script type="text/javascript">

	var lastViewData = {};
	function mode( modeName, param ) {

		lastViewData = param;
		$('.content-body').hide();
		if( modeName == 'view' ) {
			logBoard.setFilter({tenderNo:param.tenderNo});
			logBoard.load();
		}
		$('body').scrollTop(0);
		return $('.content-body.'+modeName).show();
	}

	// 낙찰 가능 여부
	function canBid(  ) {
		if( lastViewData.status != 'T' ) return false;
		return lastViewData.memberNo == userMemberNo;
	}

	// 입찰 가능 여부
	function canTender( memberNo, status ) {
		if( userType != 2 ) return false;
		if( status != 'T' ) return false;
		if( lastViewData.memberNo == userMemberNo ) return false;
		return true;
	}

	// 로딩후
	var tenderNo = $.getParam('tenderNo');
	if( tenderNo ) {

		showView(tenderNo);
	}

	</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>