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
            <h1 class="pull-left">QNA</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">QNA</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <div class="container content list">
    
		<div class="row margin-bottom-10">
			<div class="col-sm-9">
				Total <span id="total"></span>
			</div>
			<div class="col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control">
	                <span class="input-group-btn"><button class="btn btn-u" type="button">검색</button></span>
				</div>
			</div>
		</div>

		<table class="table table-hover table-bordered table-striped" id="table_list">
		<thead>
		    <tr>
		        <th style="width: 50px;">번호</th>
		        <th class="hidden-sm">제목</th>
		        <th style="width: 70px;">답변여부</th>
		        <th style="width: 150px;">등록일</th>
		    </tr>
		</thead>
		<tbody>
		<c:if test="${sessionScope.memberVO == null}">
			<tr>
			    <td colspan="4" class="text-center">QNA 이용을 위해선 <a href="${root}/member/login">로그인</a>이 필요합니다.</td>
			</tr>
		</c:if>
		</tbody>
		</table>

		<div class="text-right">
			<button type="button" class="btn btn-u write">글등록</button>
		</div>

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
		        <td data-name="regDate" data-formatter="timeConv"></td>
		        <td>답변일</td>
		        <td data-name="hits"></td>
		    </tr>
		    <tr>
		        <th>제목</th>
		        <td colspan="5" data-name="title"></td>
		    </tr>
   		    <tr>
		        <th>상세 질문</th>
		        <td colspan="5" data-name="content" data-formatter="brConv"></td>
		    </tr>
   		    <tr>
		        <th>답변</th>
		        <td colspan="5" data-name="answer"></td>
		    </tr>
		</tbody>
		</table>
		<div class="text-center">
			<button class="btn btn-u list" type="button">리스트</button>
		</div>
    
    </div>
    
    <div class="container content write" style="display: none;">
    <form id="qna_write_form">
		<table class="table table-hover table-bordered table-striped">
		<tbody>
		    <tr>
		        <th width="120">질문 제목</th>
		        <td><input type="text" name="title" class="form-control"></td>
		    </tr>
   		    <tr>
		        <th>상세 질문</th>
		        <td><textarea name="content" rows="10" class="form-control"></textarea></td>
		    </tr>
		</tbody>
		</table>
		<div class="text-center">
			<button class="btn btn-u submit" type="submit">등록</button>
			<button class="btn btn-u list" type="button">리스트</button>
		</div>
    </form>
    </div>


<script type="text/html" id="tmpl_list">                    
<tr>
    <td>\${boardNo}</td>
    <td class="td-width"><a href="#" >\${title}</a></td>
    <td>{{if answer=='Y'}}답변함{{else}}답변중{{/if}}</td>
    <td>\${timeConv(regDate)}</td>
</tr>
</script>
<script type="text/html" id="tmpl_empty">                    
<tr>
    <td colspan="4">등록된 QNA가 없습니다.</td>
</tr>
</script>
<script type="text/javascript">

$(function(){
	function mode( modeName ) {
		$('.container.content').hide();
		$('.container.content.'+modeName).show();		
	}
	
	$('#qna_write_form').on('submit', function(){
		$.ajax({
			url : '${root}/api/qna/add',
			data: $('#qna_write_form').formData(),
			success : function(datas) {
				if( datas.status == 'ok' ) {
					mode('list');
					board.load();
					toastr.success('QNA 등록이 성공 하였습니다.');
				} else {
					toastr.error('QNA 등록 실패', datas.reason);
				}
			}
		});
		
		return false;
	});
	
	$('.btn.list').on('click', function(){
		mode('list');		
	});
	$('.btn.write').on('click', function(){
		mode('write');
	});

	var tbody = $('#table_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		total : $('#total'),
		api: '${root}/api/qna/',
		template: {list:$('#tmpl_list'), empty: $('#tmpl_empty')},
		paging: $('.pagination'),
		onRender : function( $tar, datas ) {
			$tar.find('a').on('click', function(){
				
				$.ajax({
					url : '${root}/api/qna/view',
					data: {boardNo: datas.boardNo },
					success : function(datas) {
						mode('detail');
						$('.container.content.detail').formData( datas );
					}
				});
				return false;
			});
		}
	});

	<c:if test="${sessionScope.memberVO != null}">
	board.load();
	</c:if>
});

</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>