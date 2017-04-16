<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">내정보 수정하기
            </h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">마이페이지</a></li>
                <li class="active">내정보 수정하기</li>
            </ul>
        </div>
    </div>


    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page" method="POST" action="${root}/member/doModifyMemberArea" data-toggle="validator" >
                    <div class="reg-header">
                        <h2>업종 및 지역선택</h2>
                        <p>
                        	희망하시는 업종과, 지역을 선택해주세요<br/>
                        	지역 선택은 복수 입니다.
                        </p>
                    </div>

					<label class="margin-top-20">업종 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-4 col-xs-4">
		                    <select name="categoryNo2" class="form-control" required title="업종을 선택해 주시기바랍니다."></select>
	                   </div>
                    </div>

					<label class="margin-top-20">상세 업종 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-12 col-xs-12">
                       		<div style="border: 1px solid #ccc; min-height: 34px; padding:5px;" id="check_cate4"></div>
	                   </div>
                    </div>
                    <p>상세 업종 최대 5개 까지 입니다.</p>


					<label class="margin-top-20">희망 지역 <span class="color-red">*</span>
						<a href="${root}/area/select" type="button" class="btn-u btn-u-xs" data-toggle="modal" data-target="#areaModal" data-backdrop="static">지역 변경</a>
					</label>
                    <div class="row">
                        <div class="col-sm-12 col-xs-12">
                       		<div style="border: 1px solid #ccc; min-height: 34px; padding:5px;" id="selected_areas"></div>
	                    </div>
                    </div>
                    <p>희망 지역은 최대 5지역까지 입니다.</p>

					<hr>
                    <div class="row text-right">
						<button class="btn-u btn submit" type="submit">수정하기</button>
                    </div>

                </form>
            </div>
        </div>
    </div>


	<div id="areaModal" class="modal fade" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">지역변경</h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>

	<script type="text/html" id="tmpl_selected_area_list">
		<small>\${name}<button type="button" class="btn-u btn-u-xs btn-u-default remove"><i class="fa fa-times"></i></button><input type="hidden" name="areaCode" value="\${area}"> </small>
	</script>
	<script type="text/html" id="tmpl_selected_cate4_list">
		<label><input type="checkbox" name="categoryNo3s" value="\${categoryNo}"> \${categoryName}</label>&nbsp;&nbsp;
	</script>

<script type="text/javascript">
$(function(){

	var checkedId = null;
	var $form = $('form.reg-page');

	$form.on('submit', function(){

		if( !$form.valid() ) {
			return false;
		}
		var formData = $form.formData();
		if( !formData.areaCode ) {
			toastr.error('희망 지역을 선택해주세요');
			return false;
		}
		if( !formData.categoryNo3s ) {
			toastr.error('상세 업종을 선택해주세요');
			return false;
		}

		$.ajax({
			type : "POST",
			url :  $form.attr('action'),
			cache : false,
			data : formData,
			success : function(datas){
				if( datas.status == "ok" ){
					location.href = '${root}/member/modifyed_info';
				} else {
					toastr.error('개인 정보 수정 실패<br/>'+datas.reason);
				}
			},
			error : function(){
				toastr.error('개인 정보 수정 실패');
			}
		});
		return false;
	});
	$form.validate();


	// 2차 카테고리 수정
	$form.find('[name=categoryNo2]').on('change', function( ){
		if( this.value != '' )
		loadSubCategory( this.value, function(datas){
			if( datas.status == 'ok' ) {
				var $tar = $form.find('#check_cate4');
				$tar.empty();

				for( var i=0,len=datas.list.length;i<len;i++ ) {
					$tar.append( $('#tmpl_selected_cate4_list').tmpl(datas.list[i]) );
				}

				$tar.find('input[name=categoryNo3s]').on('click', function(){
					if( $tar.find('input[name=categoryNo3s]:checked').length > 5 ) {
						toastr.error('상세 업종은 최대 5개 까지입니다.');
						return false;
					}
				});
			}
		});
	});


	function loadSubCategory(parentCategoryNo, callback) {
		$.ajax({
			url : '${root}/api/category/list',
			data : { where : JSON.stringify({parentCategoryNo:parentCategoryNo}), len:9999 },
			dataType : 'json',
			success : function( datas ) {
				callback( datas );
			}
		});

	}

	// 2차 카테고리 부름 (자동차 고정)
	loadSubCategory( 1, function(datas){
		if( datas.status == 'ok' ) {

			var $tar = $form.find('[name=categoryNo2]');
			var map = {};
			$tar.empty().options({'':'선택해주세요'});
			//$form.find('[name=categoryNo3]').empty();

			for( var i=0,len=datas.list.length;i<len;i++ ) {
				map[ datas.list[i].categoryNo ] = datas.list[i].categoryName;
			}
			$tar.options(map);
		}
	});

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
	if( datas.length > 5 ) {
		toastr.error('희망지역은 최대 5개 까지 입니다.');
		return false;
	}

	var $tar = $('#selected_areas');
	$tar.empty();
	for( var i=0,len=datas.length;i<len;i++ ) {
		$tar.append( $('#tmpl_selected_area_list').tmpl(datas[i]) );
	}
	$('#selected_areas button.remove').on('click', function(){
		$(this).parent().remove();
	});
	$('form select').eq(2).trigger('change');
}


</script>
</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>