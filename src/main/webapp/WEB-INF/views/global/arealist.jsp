<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

   <div class="modal-content">
     <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       <h4 class="modal-title" id="myModalLabel">지역변경</h4>
     </div>
     <div class="modal-body">

   	<div class="tab-v1" id="bigarea_list">
			<ul class="nav nav-tabs">
			<c:set var="i" value="0"></c:set>
			<c:forEach items="${bigArea}" var="area">
				<li <c:if test="${i==0}">class="active"</c:if> title="${area.bigAreaNo}"><a href="#area-${area.bigAreaNo}" data-toggle="tab">${area.bigAreaName}</a></li>
				<c:set var="i" value="${i+1}"></c:set>
			</c:forEach>

			</ul>

			<div class="tab-content area">

			<c:set var="i" value="0"></c:set>
			<c:forEach items="${bigArea}" var="area">
				<div class="tab-pane fade <c:if test="${i==0}">active in</c:if>" id="area-${area.bigAreaNo}"></div>
				<c:set var="i" value="${i+1}"></c:set>
			</c:forEach>
			</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		        <button type="button" class="btn btn-primary" id="submit-area">지역 변경</button>
		      </div>

		</div>
	</div>
</div>
<script type="text/javascript">
var bigAreaName = {};
<c:forEach items="${bigArea}" var="area">
bigAreaName[ ${area.bigAreaNo} ] = "${area.bigAreaName}";
</c:forEach>

$('#areaModal').on('show.bs.modal', function (e) {
	// 구조상 첫번째가 아니라 두번째 부터 들어온다.
	// 두번째 들어올땐 첫번째 선택을 초기화 해야한다.
	// 기준은 areas 다
	$('#bigarea_list input[name=smallAreaCode]').each(function(){
		this.checked = hasArea(this.value);
	});
});

$('#submit-area').on('click', function(){
	if( setAreas( getSelectedArea() ) !== false )
		$('#areaModal').modal('hide');
});

var areaMap = {};
function hasArea( code ) {
	var areas = getAreas();
	for( var i=0,len=areas.length;i<len;i++ ) {
		if( areas[i].area == code ) return true;
	}
	return false;
}
function getSelectedArea() {
	// 로딩되지 않은 지역에 대한것은
	// areas 에서 꺼내야하고
	// 로딩된 지역은 지우고 체크 박스 상태를 보고 머지해야함
	var areas = getAreas();
	var selectedList = [];
	$('#bigarea_list input[name=smallAreaCode]:checked').each(function(){
		var name  = bigAreaName[ Math.floor(this.value/10000) ]+'>'+$(this).parent().text();
		selectedList.push( {area:this.value, name:name } );
	});
	for( var i=0,len=areas.length;i<len;i++ ) {
		// 로딩된 지역은 패스
		if( !areaMap[ Math.floor(areas[i].area/10000) ] ) {
			selectedList.push( areas[i] );
		}
	}
	return selectedList;
}

$('#bigarea_list li').on('click', function(){
	var selectOne = "${param.selectOne}";
	var index = $('#bigarea_list li').index(this);
	var areaNo = $(this).attr('title');
	if( areaMap[areaNo] ) return;

	$.ajax({
		url : '${root}/area/small/list',
		dataType:'json',
		data : {
			where : JSON.stringify({bigAreaNo:areaNo}),
			len: 9999
		},
		success : function(datas){
			areaMap[areaNo] = true;
			var $tar = $('#area-'+areaNo);
			for( var i =0,len=datas.length;i<len;i++ ) {
				var area = datas[i];
				var $newEl = selectOne == "true" ?  $('#tmpl_area_list2').tmpl(area) : $('#tmpl_area_list').tmpl(area);
				$tar.append($newEl);
			}
		}
	});
});
$('#bigarea_list li').eq(0).trigger('click');

</script>
<script type="text/html" id="tmpl_area_list">
<label><input type="checkbox" name="smallAreaCode" value="\${areaCode}" {{if hasArea(areaCode)}} checked="checked" {{/if}} > \${smallAreaName}</label>
</script>
<script type="text/html" id="tmpl_area_list2">
<label><input type="radio" name="smallAreaCode" value="\${areaCode}" {{if hasArea(areaCode)}} checked="checked" {{/if}} > \${smallAreaName}</label>
</script>



</fmt:bundle>