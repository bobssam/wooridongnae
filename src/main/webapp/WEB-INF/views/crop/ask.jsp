<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>
<script type="text/javascript" src="${root}/assets/plugins/se/js/HuskyEZCreator.js" charset="utf-8"></script>


<style type="text/css">
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
	.content {
		padding-top: 0;
	}
	textarea[name=content] {
		width:100%;
	}
	.table.reply button.delete{
		float:right;
	}
	h2 {
		margin-top: 15px;
	}
	
</style>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">커뮤니티</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li>커뮤니티</li>
                <li class="active">${pageName}</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->

<!--

    <div class="container content col-sm-offset-2">
		
		<div class="row">
			<div class="col-md-3">
				유머게시판
			</div>
			<div class="col-md-3">
				신차소식
			</div>
			<div class="col-md-3">
				차량 구입 노하우
			</div>
			<div class="col-md-3">
				출고 체크리스트
			</div>
			<div class="col-md-3">
				자동차 정비상식
			</div>
			<div class="col-md-3">
				차량시승기
			</div>
		</div>
	</div>
-->

    <div class="container ">
    
    	<h2>${pageName}</h2>
    
    	<div class="content list">
	    	<form id="search_form">
				<div class="row margin-bottom-10">
					<div class="col-sm-9">
						<!-- Total <span id="total"></span>  -->
					</div>
					<div class="col-sm-3 text-right">
						<button class="btn btn-u write" type="button">글쓰기</button>
						<!-- <div class="input-group">
							<input type="text" class="form-control" name="keyword">
			                <span class="input-group-btn"><button class="btn btn-u" type="submit">검색</button></span>
						</div>
						 -->
					</div>
				</div>
			</form>
	
			<table class="table table-hover table-bordered table-striped" id="table_list">
			<thead>
			    <tr>
			        <th style="width: 50px;">번호</th>
			        <th>제목</th>
			        <th style="width: 100px;" class="hidden-sm">작성자</th>
			        <th style="width: 70px;">읽은수</th>
			        <th style="width: 90px;">등록일</th>
			    </tr>
			</thead>
			<tbody></tbody>
			</table>
	
			<div class="row text-center">
		    	<ul class="pagination">
		        </ul>
	        </div>
        </div>
    	<div class="content detail" style="display: none;">
			<table class="table table-hover table-bordered table-striped">
			<tbody>
			    <tr>
			    	<td>
			    		<span class="title" data-name="title"></span> 
			    		<div class="info">
				    		조회 <span data-name="hits"></span> |
				    		<span data-name="regDate" data-formatter="dateConv"></span>
			    		</div>
			    	</td>
			    </tr>
	   		    <tr>
			        <td>
			        	글쓴이 : <span data-name="name"></span>
			        </td>
			    </tr>
	   		    <tr>
			        <td data-name="content" data-formatter="brConv"></td>
			    </tr>
			</tbody>
			</table>
			
			<div class="text-center">
				<button class="btn btn-u list" type="button">리스트</button>
				<button class="btn btn-u edit" type="button">수정</button>
				<button class="btn btn-u del" type="button">삭제</button>
			</div>
			
			<table class="table reply table-hover table-bordered table-striped" id="table_reply" style="margin-top:20px;">
			<thead>
				<tr>
					<th>댓글</th>
				</tr>	
			</thead>
			<tbody>
	    	</tbody>
	    	<tfoot>
	    	<tr>
	    		<td>
	    			<form id="reply_form" >
		    			<textarea name="content" class="form-control" placeholder="댓글 작성을 위해선 로그인을 해주세요."></textarea>
	    				<button type="submit">등록</button>
	    				<span id="reply_max">0/2000</span>
	   				</form>
	    		</td>
	    	</tr>
	    	</tfoot>
	    	</table>
		</div>
    
	    <div class="content write" style="display: none;">
	    	<form id="write_form">
				<table class="table table-hover table-bordered table-striped">
				<tbody>
				    <tr>
				    	<th>프로필</th>
				    	<td>
				    		bobssam
				    	</td>
				    </tr>
		   		    <tr>
		   		       	<th>카테고리</th>
				        <td>
				        	<select name="category">
				        	</select>
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>제목</th>
				        <td>
				        	<input type="text" name="title" size="40"> <span id="title_max"></span> (최대 한글 25자)
				        </td>
				    </tr>
		   		    <tr>
				        <td colspan="2">
				        	<textarea name="content"></textarea>
				        </td>
				    </tr>
				    
				</tbody>
				</table>
				<div class="text-center">
					<button type="submit">확인</button>
					<button type="button" class="cancel">취소</button>
				</div>
			</form>
		</div>
    </div>

<script type="text/html" id="tmpl_list">                    
<tr>
    <td>\${boardNo}</td>
    <td class="td-width">
		<a href="${root}/infos/notice/view?boardNo=\${boardNo}" data-toggle="modal" data-target="#phoneModal" >\${title} [\${replyCount}]</a>
	</td>
    <td class="hidden-sm">\${memberNo}</td>
    <td>\${hits}</td>
    <td>\${dateConv(regDate)}</td>
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
<script type="text/javascript">

$(function(){
	
	/****************************************
	
		버튼 세팅

	****************************************/
	$('#reply_form textarea').getByteLength( {
		target: $('#reply_max'),
		max: 2000
	});
	
	$('#reply_form').on('submit', function(){
		var datas =  $(this).formData();
		datas.boardNo = nowBoardData.boardNo;
		
		$.ajax({
			url: '${root}/api/boardreply/add',
			data: datas,
			dataType: 'json',
			method: 'POST',
			success: function(data){
				
				if( data.status == 'ok' ) {
					
					replyBoard.reload();
					//toastr.options.positionClass = "toast-bottom-center";
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
	
	$('#search_form').on('submit', function(){
		board.setFilter({title: $(this).find('[name=keyword]').val() });
		board.load();
		return false;
	});
	$('.btn.list').on('click', function(){
		mode('list');		
	});
	$('.btn.edit').on('click', function(){
		mode('write');
		$('#write_form').formData(nowBoardData);
		oEditors[0].exec("LOAD_CONTENTS_FIELD", [nowBoardData.content]);
	});
	$('.btn.write').on('click', function(){
		<c:if test="${sessionScope.memberVO == null}">
		toastr.error('로그인 이후에 글쓰기를 이용해 주시기 바립니다.');
		return;
		</c:if>
		mode('write');
		$('#write_form select[name=category]').val(${pageId});
	});
	$('#write_form .cancel').on('click', function(){
		mode(prevMode);
	});	
	$('.btn.del').on('click', function(){
		if( confirm('정말로 삭제 하시겠습니까?') ) {
			$.ajax({
				url : '${root}/api/board/delete',
				data: {boardNo: nowBoardData.boardNo},
				success : function(data) {
					
					if( data.status == 'ok' ) {
						toastr.success("게시글이 삭제 되었습니다.");
						mode('list');
					} else {
						toastr.error("게시글이 삭제에 실패 하였습니다");
					}					
				}
			});
		}		
	});

	/****************************************
	
		모드 컨트롤

	****************************************/	
	var firstSetting = true;
	var oEditors = [];
	var lastMode = 'list';
	var prevMode = 'list';
	function mode( modeName ) {
		$('.container .content').hide();
		$('.container .content.'+modeName).show();
		
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

	
	/****************************************
	
				게시판 세팅
	
	****************************************/
	var nowBoardData = null;
	var isFirst = true;
	var $tbody = $('#table_list tbody');
	var $tbodyReply = $('#table_reply tbody');
	var board = $tbody.board({
		page: 1,
		len: 10,
		api: '${root}/api/board/',
		total : $('#total'),
		template: $('#tmpl_list'),
		paging: $('.pagination'),
		onRender : function( $tar, datas ) {
			
			
			$tar.off('click').on('click', function(){
				
				// 게시물 상세 보기
				nowBoardData = datas;
				$.ajax({
					url : '${root}/api/board/view',
					data: {boardNo: datas.boardNo },
					success : function(datas) {
						
						nowBoardData = datas;
						$tar.data('update').apply($tar, [datas]);
						mode('detail');
						$('#reply_form textarea').val('').trigger('input');
						$('.container .content.detail table').eq(0).formData( datas );
						
						// 본인의 글이거나 관리자면 노출
						if( datas.isMine )
							$('.btn.edit,.btn.del').show();
						else
							$('.btn.edit,.btn.del').hide();
						
						replyBoard.setFilter({boardNo:datas.boardNo});
						replyBoard.load();
					}
				});
			});
			
			if( isFirst ) {
				var boardNo = $.getParam('boardNo');
				if( boardNo != null ) {
					if( datas.boardNo == boardNo ) {
						$tar.trigger('click');
					}  
				} 
				setTimeout( function(){
					isFirst = false;
				});
			}			
		}
	});
	board.setFilter({category:${pageId}});
	board.load();
	
	/****************************************
	
		글쓰기 세팅

	****************************************/
	$('#write_form input[name=title]').getByteLength( {
		target: $('#title_max'),
		max: 50
	});
	
	// 카테고리 세팅
	$.ajax({
		url: '${root}/community/category',
		dataType: 'json',
		method: 'POST',
		success: function(data){
			$('select[name=category]').options(data);
				
		}
	});
	
	$('#write_form').on('submit', function(){
		oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
		var datas =  $(this).formData();

		$.ajax({
			url: '${root}/api/board/add',
			data: datas,
			dataType: 'json',
			method: 'POST',
			success: function(data){
				
				if( data.status == 'ok' ) {
					
					board.reload();
					//toastr.options.positionClass = "toast-bottom-center";
					toastr.success("게시글이 등록되었습니다.");
					mode('list');
					$('#write_form').formData({}, true);
				} else {
					toastr.success("게시글이 등록에 실패 하였습니다");
				}
			},
			error: function(data) {
				if( data.status == 403 ) 
					toastr.error("로그인 이후에 이용해 주시기 바랍니다.");
			}
			
		})
		
		return false;
	});

	
	/****************************************
	
		댓글 세팅

	****************************************/
	var replyBoard = $tbodyReply.board({
		page: 1,
		len: 10,
		api: '${root}/api/boardreply/',
		//total : $('#total'),
		template: $('#tmpl_reply'),
		onRender : function( $tar, datas ) {
			$tar.find('button.delete').on('click', function(){
				
				if( confirm('정말로 삭제 하시겠습니까?') ) {
					$.ajax({
						url : '${root}/api/boardreply/delete',
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
});


</script>


</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>