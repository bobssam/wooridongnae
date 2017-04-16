<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

   <div class="modal-content">
     <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       <h4 class="modal-title" id="myModalLabel">결제 내역</h4>
     </div>
     <div class="modal-body">
         
         <div class="context list">
	         <table class="table table-hover table-bordered table-striped" id="memo_list">
	         <caption>total <span class="total"></span></caption>
	         <thead>
	         <tr>
	         	<th width="50">번호</th>
	         	<th width="150">결제번호</th>
	         	<th width="90">가격</th>
	         	<th width="90">결제수단</th>
	         	<th>결제일</th>
	         </tr>
	         </thead>
	         <tbody>
	         </tbody>
	         </table>
	         <div class="text-center">
	         	<div class="pagination"></div>
	         </div>
         </div>
         
         
	</div>
     <div class="modal-footer">
       <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>	
</div>
<script type="text/javascript">

var paymentType = {"C":"카드", "P":"핸드폰"};
$(function(){
	
	var selectedData = null;
	function mode( modeName ) {
		$('#paymentModal .context').hide();
		return $('#paymentModal .context.'+modeName).show();
	}
	
	$('#paymentModal').on('show.bs.modal', function (e) {
		// 두번째 팝업 요청시 리스트를 재로딩시킴
		board.load(1);
	});
	
	var tbody = $('#memo_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		api: '${root}/api/payment/',
		total : $('#paymentModal .total'),
		template: {list:$('#tmpl_payment_list'), empty:$('#tmpl_payment_empty')},
		paging: $('#paymentModal .pagination'),
		onRender : function( $tar, datas ) {
		}
	});
	board.load();
});

</script>
<script type="text/html" id="tmpl_payment_list">
<tr>
	<td>\${paymentNo}</td>
  	<td>\${orderNo}</td>
  	<td>\${price}</td>
  	<td>\${paymentType[orderType]}</td>
  	<td>\${timeConv(paymentDate)}</td>
</tr>
</script>
<script type="text/html" id="tmpl_payment_empty">
<tr>
	<td colspan="5" class="text-center">결제 내역이 없습니다.</td>
</tr>
</script>

</fmt:bundle>