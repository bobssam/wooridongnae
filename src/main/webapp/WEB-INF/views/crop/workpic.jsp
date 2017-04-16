<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="협력업체" />
</c:import>
<style type="text/css">

	.community img{
		margin-left: -1px;
		margin-bottom: -1px;
	}
	.headline .btn {
		float:right;
	}
	.cate {
		font-size: 11px;
		color: #666;
	}
	th {
		text-align: center;
	}
	thead tr {
		background: #f1f1f1;
	}
	.work_cate label {
		width: 150px;
	}

	.search_box {
		border: 2px solid #ccc;
		padding:10px;
	}
	hr {
		margin: 5px 0;
	}

	table.info th {
		text-align: left;
	}
	table.info td {
		font-size: 12px;
		padding: 2px 0;
		color: #666;
	}

	table.table .title{
		font-size: 16px;
		font-weight: bold;
	}
	table.table .info{
		float: right;
		color:#999;
		font-size: 11px;
	}
	table.table.reply .name{
		font-weight: bold;
	}
	textarea[name=content] {
		width:100%;
	}
	.table.reply button.delete{
		float:right;
	}

</style>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">협력업체</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li class="active"><a href="">협력업체</a></li>
            </ul>
        </div>
    </div>



    <div class="container content col-sm-offset-2">

		<!------------------------------------------
					리스트 페이지
		-------------------------------------------->
    	<div class="body list">

			<div class="search_box">
				<form id="search_form">
					<div class="work_cate">
						업종
					</div>
					<hr>
					<div>
						지역
						<select name="bigAreaNo"></select> 시(도)
						<select name="smallAreaNo"></select> 구(군)
						<button type="submit">검색</button>
					</div>
				</form>
			</div>

			<c:if test="${sessionScope.memberVO != null && sessionScope.memberVO.categoryNo2 == 4}">
			<h3>내 업체 정보</h3>
			<div class="row">
				<table class="table table-hover table-striped" id="my_table_list">
				<thead>
				<tr>
					<th width="120">사진</th>
					<th>업체명</th>
					<th>업종</th>
					<th>소개</th>
					<th>할인혜택</th>
					<th>지역</th>
					<th>조회수</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
				</table>

			</div>
			</c:if>


			<h3>등록된 협력 업체</h3>
			<div class="row">
				<table class="table table-hover table-striped" id="table_list">
				<thead>
				<tr>
					<th width="120">사진</th>
					<th>업체명</th>
					<th>업종</th>
					<th>소개</th>
					<th>할인혜택</th>
					<th>지역</th>
					<th>조회수</th>
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
		</div>

		<!------------------------------------------
					상세 페이지
		-------------------------------------------->
    	<div class="body detail" style="display: none;">

			<div class="headline"><h2 data-name="title">..</h2></div>

			<div class="row">
				<div class="col-md-8"><img alt="" class="img-responsive center-block"></div>
				<div class="col-md-4">
	            	<!-- Contacts -->
	                <div class="headline"><h3 data-name="corpName"></h3></div>
	                <table class="info">
	                	<tr>
	                		<th width="90">업종</th>
	                		<td data-name="categoryNo3Name" data-formatter="commaJoin"></td>
                		</tr>
	                	<tr>
	                		<th>취급품목</th>
	                		<td data-name="cropCate"></td>
                		</tr>
	                	<tr>
	                		<th>대표자</th>
	                		<td data-name="name"></td>
                		</tr>
	                	<tr>
	                		<th>주소</th>
	                		<td data-name="address"></td>
                		</tr>
	                	<tr>
	                		<th>전화번호</th>
	                		<td>
	                			<span class="glyphicon glyphicon-earphone"></span> <span data-name="phone"></span>
	                			<span aria-hidden="true" class="icon-screen-smartphone"></span> <span data-name="mobile"></span>
	                		</td>
                		</tr>
	                	<tr>
	                		<th>홈페이지</th>
	                		<td><a href="#" data-name="homepage"></a></td>
                		</tr>
	                	<tr>
	                		<th>회원혜택</th>
	                		<td><span data-name="dc"></span>% 할인</td>
                		</tr>
	                </table>

	            	<!-- Business Hours -->
	                <div class="headline"><h3>업무시간</h3></div>
	                <ul class="list-unstyled margin-bottom-30">
	                	<li data-name="openingHour"></li>
	                </ul>
	                <!-- End Business Hours -->
	                <!-- End Social -->
	            </div>
			</div>

			<div class="headline"><h2>업체 소개</h2></div>
			<div class="row">
				<div class="col-md-12" data-name="content"></div>
			</div>

			<div class="headline"><h2>할인/이벤트</h2><button class="btn btn-xs rounded btn-success" type="button"><span class="glyphicon glyphicon-plus"></span> 이벤트 추가</button></div>
			<div class="row">
				<div class="col-md-12">
				</div>
			</div>

			<div class="headline"><h2>작업 사진 (0)</h2>
				<button class="btn btn-xs rounded btn-success work_pics" type="button"><span class="glyphicon glyphicon-th-list"></span> 전체보기</button>
			</div>
			<div class="row" id="work_pics">
				<div class="col-md-3">
					<img alt="" class="img-responsive center-block  margin-bottom-10" src="/api/file/download/60">
				</div>
				<div class="col-md-3">
					<img alt="" class="img-responsive center-block  margin-bottom-10" src="/api/file/download/60">
				</div>
				<div class="col-md-3">
					<img alt="" class="img-responsive center-block  margin-bottom-10" src="/api/file/download/60">
				</div>
				<div class="col-md-3">
					<img alt="" class="img-responsive center-block margin-bottom-10" src="/api/file/download/60">
				</div>
			</div>


			<div class="text-center" style="margin-top: 20px;">
				<button class="btn btn-u list" type="button">리스트</button>
			</div>



			<table class="table reply table-hover table-bordered table-striped" id="table_reply" style="margin-top: 20px;">
				<thead>
					<tr>
						<th>의견쓰기</th>
					</tr>
				</thead>
				<tbody></tbody>
				<tfoot>
					<tr>
						<td>
							<form id="reply_form">
								<textarea name="content" class="form-control" placeholder="댓글 작성을 위해선 로그인을 해주세요."></textarea>
								<button type="submit">등록</button>
								<span id="reply_max">0/2000</span>
							</form>
						</td>
					</tr>
				</tfoot>
			</table>

		</div>

	</div>



<script type="text/html" id="tmpl_list">
<tr>
	<td rowspan="2"><img src="${root}/api/file/download/\${fileNo}" alt="" width="116" height="80"></td>
	<td>\${corpName}</td>
	<td class="cate">{{html categoryNo3Name.join('<br>')}}</td>
	<td><a href="#">\${title}</a></td>
	<td>\${dc}% 할인</td>
	<td>\${smallAreaName}</td>
	<td>\${hits}</td>
</tr>
<tr>
	<td><b>전화</b> \${phone}</td>
	<td><b>주소</b> \${bigAreaName} \${smallAreaName}</td>
	<td colspan="4"><b>url</b> <a href="\${homepage}">\${homepage}</a></td>
</tr>
</script>
<script type="text/html" id="empty_list">
<tr>
	<td colspan="7" class="text-center">조건에 맞는 업체가 없습니다.</td>
</tr>
</script>
<script type="text/html" id="tmpl_reply">
<tr>
	<td>
		{{if isMine == 1}}<button class="btn btn-xs delete" type="button" >삭제</button>{{/if}}
   		<span class="name">\${name}</span>
   		\${dateConv(regDate)}
   		<p>
   			\${content}
   		</p>
	</td>
</tr>
</script>
<script type="text/html" id="tmpl_work_pic">
<div class="col-md-3">
	<img alt="" class="img-responsive center-block  margin-bottom-10" src="${root}/api/file/download/\${fileNo}">
</div>
</script>

<script type="text/javascript">

function commaJoin( datas ) {
	if( !datas ) return '';
	return datas.join(",");
}

$(function(){

	var selectedCrop = null;
	$('.work_cate').checkboxApi({
		api: '/api/category/',
		value: 'categoryName',
		key: 'categoryNo',
		name : 'categoryNo3',
		async: false,
		filter : {'parentCategoryNo': 4},
	}, function( datas ){
	});

	$('[name=categoryNo3]').setMaxCheck(3);

	function loadBigArea() {

		$('[name=bigAreaNo]').empty().optionsApi({
			api: '/api/bigarea/',
			value: 'bigAreaName',
			key: 'bigAreaNo',
			async: false,
		}, function( datas ){

		});
	}
	function loadSmallArea( bigAreaNo ) {

		$('[name=smallAreaNo]').empty().optionsApi({
			api: '/api/smallarea/',
			value: 'smallAreaName',
			key: 'smallAreaNo',
			async: false,
			filter: {bigAreaNo:bigAreaNo}
		}, function( datas ){

		});
	}

	loadBigArea();
	$('[name=bigAreaNo]').on('change', function(){
		loadSmallArea($(this).val());
	});


	$('#search_form').on('submit', function(){
		var datas = $(this).formData();
		board.setFilter(datas);
		board.load();
		return false;
	});

	/****************************************
			모드 컨트롤
	****************************************/
	var firstSetting = true;
	var oEditors = [];
	var lastMode = 'list';
	var prevMode = 'list';
	function mode( modeName ) {
		$('.container .body').hide();
		$('.container .body.'+modeName).show();

		if( modeName == 'write' && firstSetting ) {
			firstSetting = false;
			// 스마트 에디터 세팅
			var editor = nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: $('#write_form [name=content]')[0],
				sSkinURI: "${root}/assets/plugins/se/SmartEditor2Skin.html",
				htParams : {
					bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					fOnBeforeUnload : function(){
						alert("완료!");
					}
				}, //boolean
				fCreator: "createSEditor2"
			});
		}
		prevMode = lastMode;
		lastMode = modeName;
	}


	$('.list').on('click', function(){
		mode('list');
	});

	/********************************************************
						협력 업체 리스트
	********************************************************/
	var isFirst = true;
	var tbody = $('#table_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		api: '${root}/api/crop/',
		total : $('#total'),
		template: {list: $('#tmpl_list'), empty: $('#empty_list')},
		paging: $('.pagination'),
		onRender : function( $tar, datas ) {

			$tar.find('a').off('click').on('click', function(){
				showDeatail(datas);
			});
		}
	});
	board.load();

	function showDeatail( datas ) {
		$.ajax({
			url : '${root}/api/crop/view',
			data: {memberNo: datas.memberNo },
			success : function(datas) {

				if( datas.status == "ok" ) {
					selectedCrop = datas.data;
					mode('detail');
					$('.container .body.detail').formData( datas.data );
					$('.container .body.detail textarea').val('');
					$('.container .body.detail img').attr('src', '${root}/api/file/download/'+selectedCrop.fileNo);

					replyBoard.setFilter({cropNo:selectedCrop.memberNo});
					replyBoard.load();

					workPicsBoard.setFilter({cropNo:selectedCrop.memberNo});
					workPicsBoard.load();

					$('.work_pics').off('click').on('click', function(){
						location.href = '${root}/crop/workpic?cropNo='+selectedCrop.memberNo;
					});
				}
			}
		});
	}
	/****************************************
				댓글 세팅
	****************************************/
	$('#reply_form textarea').getByteLength( {
		target: $('#reply_max'),
		max: 2000
	});

	$('#reply_form').on('submit', function(){
		var datas =  $(this).formData();
		datas.cropNo = selectedCrop.memberNo;

		$.ajax({
			url: '${root}/api/cropreply/add',
			data: datas,
			dataType: 'json',
			method: 'POST',
			success: function(data){

				if( data.status == 'ok' ) {

					replyBoard.reload();
					toastr.success("댓글이 등록되었습니다.");
					$('#reply_form').formData({}, true);
				} else {

				}
			},
			error: function(data) {
				if( data.status == 403 )
					toastr.error("로그인 이후에 이용해 주시기 바랍니다.");
			}
		})

		return false;
	});


	var $tbodyReply = $('#table_reply tbody');
	var replyBoard = $tbodyReply.board({
		page: 1,
		len: 10,
		api: '${root}/api/cropreply/',
		template: $('#tmpl_reply'),
		onRender : function( $tar, datas ) {
			$tar.find('button.delete').on('click', function(){

				if( confirm('정말로 삭제 하시겠습니까?') ) {
					$.ajax({
						url : '${root}/api/cropreply/delete',
						data: {replyNo: datas.replyNo},
						success : function(data) {

							if( data.status == 'ok' ) {
								toastr.success("댓글이 삭제 되었습니다.");
								$tar.remove();
							} else {
								toastr.error("댓글 삭제에 실패 하였습니다", data.reason);
							}
						}
					});
				}

			});
		}
	});

	/****************************************
				작업 사진 세팅
	****************************************/
	var $workPics = $('#work_pics');
	var workPicsBoard = $workPics.board({
		page: 1,
		len: 10,
		api: '${root}/api/cropworkpic/',
		template: $('#tmpl_work_pic'),
		onRender : function( $tar, datas ) {
			$tar.find('button.delete').on('click', function(){

				if( confirm('정말로 삭제 하시겠습니까?') ) {
					$.ajax({
						url : '${root}/api/cropreply/delete',
						data: {replyNo: datas.replyNo},
						success : function(data) {

							if( data.status == 'ok' ) {
								toastr.success("댓글이 삭제 되었습니다.");
								$tar.remove();
							} else {
								toastr.error("댓글 삭제에 실패 하였습니다", data.reason);
							}
						}
					});
				}

			});
		}
	});

	var cropNo = $.getParam('cropNo');
	if( cropNo ) {
		showDeatail( {memberNo:cropNo} );
	}


});


</script>


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>