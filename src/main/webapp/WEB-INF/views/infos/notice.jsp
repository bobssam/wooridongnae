<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="공지사항" />
</c:import>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />


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

    <div class="container content list">
    
    	<form id="search_form">
			<div class="row margin-bottom-10">
				<div class="col-sm-9">
					Total <span id="total"></span>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<input type="text" class="form-control" name="keyword">
		                <span class="input-group-btn"><button class="btn btn-u" type="submit">검색</button></span>
					</div>
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
    </div><!--/container-->
    <div class="container content detail" style="display: none;">
		<table class="table table-hover table-bordered table-striped">
		<tbody>
		    <tr>
		        <th>번호</th>
		        <td data-name="boardNo"></td>
		        <td>등록일</td>
		        <td data-name="regDate" data-formatter="dateConv"></td>
		        <td>읽은수</td>
		        <td data-name="hits"></td>
		    </tr>
		    <tr>
		        <th>제목</th>
		        <td colspan="5" data-name="title"></td>
		    </tr>
   		    <tr>
		        <th>내용</th>
		        <td colspan="5" data-name="answer"></td>
		    </tr>
		</tbody>
		</table>
		<div class="text-center">
			<button class="btn btn-u list" type="button">리스트</button>
		</div>
    
    </div>


<script type="text/html" id="tmpl_list">                    
<tr>
    <td>\${boardNo}</td>
    <td class="td-width">
		{{if isNotice=='Y'}}<b>{{/if}}<a href="${root}/infos/notice/view?boardNo=\${boardNo}" data-toggle="modal" data-target="#phoneModal" >\${title}</a>{{if isNotice=='Y'}}</b>{{/if}}
	</td>
    <td class="hidden-sm">\${memberNo}</td>
    <td>\${hits}</td>
    <td>\${dateConv(regDate)}</td>
</tr>
</script>
<script type="text/javascript">

$(function(){
	
	$('#search_form').on('submit', function(){
		board.setFilter({title: $(this).find('[name=keyword]').val() });
		board.load();
		return false;
	});
	
	$('.btn.list').on('click', function(){
		mode('list');		
	});
	
	function mode( modeName ) {
		$('.container.content').hide();
		$('.container.content.'+modeName).show();		
	}

	var isFirst = true;
	var tbody = $('#table_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		api: '${root}/api/notice/',
		total : $('#total'),
		template: $('#tmpl_list'),
		paging: $('.pagination'),
		onRender : function( $tar, datas ) {
			
			$tar.off('click').on('click', function(){
				
				$.ajax({
					url : '${root}/api/notice/view',
					data: {boardNo: datas.boardNo },
					success : function(datas) {
						
						$tar.data('update').apply($tar, [datas]);
						
						mode('detail');
						$('.container.content.detail').formData( datas );
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
	board.load();
	
});


</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>