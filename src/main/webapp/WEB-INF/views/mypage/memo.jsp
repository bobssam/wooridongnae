<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

   <div class="modal-content">
     <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       <h4 class="modal-title" id="myModalLabel">쪽지함</h4>
     </div>
     <div class="modal-body">

         <div class="context list">
	         <table class="table table-hover table-bordered table-striped" id="memo_list">
	         <caption>total <span class="total"></span></caption>
	         <thead>
	         <tr>
	         	<th width="50">번호</th>
	         	<th width="90">보낸이</th>
	         	<th>메세지</th>
	         	<th width="140">보낸일</th>
	         </tr>
	         </thead>
	         <tbody>
	         </tbody>
	         </table>
	         <div class="text-center">
	         	<div class="pagination"></div>
	         </div>
         </div>

         <div class="context detail" style="display: none">
	         <table class="table table-hover table-bordered table-striped">
	         <tbody>
	         <tr>
	         	<th width="120">번호</th>
	         	<td data-name="noteNo"></td>
	         	<th width="120">보낸이</th>
	         	<td data-name="id"></td>
	         </tr>
	         <tr>
	         <tr>
	         	<th width="120">보낸일</th>
	         	<td colspan="3" data-name="regDate" data-formatter="timeConv"></td>
	         </tr>
	         <tr>
	         	<td colspan="4" data-name="content" data-formatter="brConv"></td>
	         </tr>
	         </tbody>
	         </table>
			<div class="text-center">
				<button class="btn btn-u del" type="button">삭제</button>
				<button class="btn btn-u reply" type="button">답장</button>
				<button class="btn btn-u list" type="button">리스트</button>
			</div>

         </div>


         <div class="context reply" style="display: none">
	         <table class="table table-hover table-bordered table-striped">
	         <tbody>
	         <tr>
	         	<th width="120">받는이</th>
	         	<td data-name="id"></td>
	         </tr>
	         <tr>
	         	<td colspan="2" data-name="content"><textarea name="content" class="form-control" rows="5"></textarea></td>
	         </tr>
	         </tbody>
	         </table>
			<div class="text-center">
				<button class="btn btn-u doReply" type="button">답장하기</button>
				<button class="btn btn-u detail" type="button">취소</button>
			</div>

         </div>

	</div>
     <div class="modal-footer">
       <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
</div>
<script type="text/javascript">


$(function(){

	var selectedData = null;
	function mode( modeName ) {
		$('#memoModal .context').hide();
		return $('#memoModal .context.'+modeName).show();
	}

	$('#memoModal').on('show.bs.modal', function (e) {
		// 두번째 팝업 요청시 리스트를 재로딩시킴
		board.load(1);
	});

	$('#memoModal button.list').on('click', function(){
		mode('list');
	});
	$('#memoModal button.detail').on('click', function(){
		mode('detail');
	});
	$('#memoModal button.del').on('click', function(){
		$.ajax({
			url : '${root}/mypage/user/memo/del',
			data: {where: JSON.stringify({noteNo:selectedData.noteNo})},
			dataType : 'JSON',
			success:function(datas) {
				if( datas.status == 'ok' ) {
					mode('list');
					board.load(1);
				} else {
					alert('메모 삭제 실패');
				}
			}
		});
	});

	$('#memoModal button.reply').on('click', function(){
		mode('reply').formData({id:selectedData.id}, true);
	});

	$('#memoModal button.doReply').on('click', function(){
		var content = $('#memoModal [name=content]').val();
		$.ajax({
			url : '${root}/mypage/user/memo/reply',
			data: {
				where: JSON.stringify({noteNo:selectedData.noteNo}),
				data: JSON.stringify({content:content})
			},
			dataType : 'JSON',
			success:function(datas) {
				if( datas.status == 'ok' ) {
					toastr.success('메세지를 전송하였습니다');
					mode('detail');
				} else {
					toastr.error('메세지 보내기에 실패하였습니다.', datas.reason);
				}
			}
		});
	});

	var tbody = $('#memo_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		api: '${root}/mypage/user/memo/',
		total : $('#memoModal .total'),
		template: $('#tmpl_memo_list'),
		paging: $('#memoModal .pagination'),
		onRender : function( $tar, datas ) {
			$tar.find('a').off('click').on('click', function(){

				selectedData = datas;

				$.ajax({
					url : '${root}/mypage/user/memo/read',
					data: {where: JSON.stringify({noteNo:datas.noteNo})},
					dataType : 'JSON',
					success:function(datas) {
						$tar.addClass('readed');
					}
				});

				mode('detail').formData(datas);
			});
		}
	});
	board.load();
});



</script>
<script type="text/html" id="tmpl_memo_list">
<tr {{if read==1}}class="readed"{{/if}}>
	<td>\${noteNo}</td>
	<td>\${id}</td>
	<td><a href="#">\${content.substring(0, 30)}...</a></td>
	<td>\${diffDateConv(regDate)}</td>
</tr>
</script>

</fmt:bundle>