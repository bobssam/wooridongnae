<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%


/*
	categoryNo2 = 3  > 신차구매

	브랜드, 모델
	구입방법
	구매시기

	categoryNo2 = 4  > 자동차 수리
	수리 부위 사진 (5장까지)



*/

%>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

<script src="${root}/assets/plugins/fileupload/js/jquery.iframe-transport.js"></script>
<script src="${root}/assets/plugins/fileupload/js/jquery.fileupload.js"></script>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">자동차 비교 견적</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">견적</a></li>
                <li class="active">자동차 견적 작성</li>
            </ul>
        </div>
    </div>

    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page sky-form" method="POST" action="doMake">
                	<input type="hidden" name="categoryNo2" value="${param.categoryNo2}">

                    <div class="reg-header">
                        <h2>${categoryName} 비교 견적 등록하기</h2>
                    </div>

					<%----------------------------------------------------------------
											신차구매
					----------------------------------------------------------------%>
					<c:if test="${param.categoryNo2 == 3}">

                    <label>브랜드 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
                        	<select name="categoryNo3" class="form-control margin-bottom-10" required title="브랜드를 선택해 주시기 바랍니다."></select>
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>모델선택 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
                        	<select name="categoryNo4" class="form-control margin-bottom-10" required title="모델을 선택해 주시기 바랍니다."><option value="">선택해주세요</option></select>
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>등급 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control margin-bottom-10" name="grade" id="grade" placeholder="ex) 520D, 색상) 화이트" required title="등급을 작성해 주시기 바랍니다.">
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>지역 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control margin-bottom-10" name="areaName" readonly="readonly" required title="지역을 선택해 주시기 바랍니다.">
		                    <input type="hidden" name="areaCode" id="areaCode" readonly="readonly">
	                    </div>
                        <div class="col-sm-3 col-xs-4">
                        	<a href="${root}/area/select?selectOne=true" type="button" class="btn-u" data-toggle="modal" data-target="#areaModal" data-backdrop="static">검색</a>
	                    </div>
                    </div>

                    <label>구입 방법 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
                        	<select name="buyType" class="form-control margin-bottom-10" required title="구입방법을 선택해 주시기 바랍니다.">
                        		<option value="">선택해주세요</option>
                        		<option value="1">할부</option>
                        		<option value="2">리스</option>
                        		<option value="3">현금</option>
                        		<option value="4">장기렌탈</option>
                        	</select>
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>구입 시기 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">

	                        <label class="input">
								<i class="icon-append fa fa-calendar"></i>
                            	<input type="text" name="wantDate" id="wantDate" maxlength="10" placeholder="희망 구입시기를 선택해주세요" required title="구입시기를 선택해 주시기 바랍니다.">
    						</label>
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>


					<label>알이본 조건 <span class="color-red">*</span></label>
                    <textarea class="form-control" name="title" required title="알아본 조건을 입력해 주시기 바랍니다."></textarea>
					<div class="note margin-bottom-10">ex) 500만원 할인, 블랙박스 썬팅</div>

                    <label>요청사항 <span class="color-red">*</span></label>
                    <textarea class="form-control" name="content" required title="요청사항을 입력해 주시기 바랍니다."></textarea>
					<div class="note margin-bottom-10">구매하고자 하는 차량에 대한 상세 내역이나, 희망하는 내역을 적어주세요</div>
                   	</c:if>
					<%----------------------------------------------------------------
											수리견적
					----------------------------------------------------------------%>
					<c:if test="${param.categoryNo2 == 4}">

					<input type="hidden" name="buyType" value="0" />

                    <label>수리 품목 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
                        	<select name="categoryNo3" class="form-control margin-bottom-10" required title="수리 품목을 선택해 주시기 바랍니다."></select>
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>모델선택 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
                        	<select name="categoryNo4" class="form-control margin-bottom-10" required title="모델을 선택해 주시기 바랍니다."><option value="">선택해주세요</option></select>
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>브랜드 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control margin-bottom-10" name="grade" id="grade" placeholder="ex) 520D, 색상) 화이트" required title="브랜드를 입력해 주시기 바랍니다.">
        		        </div>
                        <div class="col-sm-3 col-xs-4">
						</div>
   	                </div>

                    <label>지역 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control margin-bottom-10" name="areaName" readonly="readonly" required title="지역을 선택해 주시기 바랍니다.">
		                    <input type="hidden" name="areaCode" id="areaCode" readonly="readonly">
	                    </div>
                        <div class="col-sm-3 col-xs-4">
                        	<a href="${root}/area/select?selectOne=true" type="button" class="btn-u" data-toggle="modal" data-target="#areaModal" data-backdrop="static">검색</a>
	                    </div>
                    </div>


					<label>알이본 조건 <span class="color-red">*</span></label>
                    <textarea class="form-control" name="title" required title="알이본 조건을 입력해 주시기 바랍니다."></textarea>
					<div class="note margin-bottom-10">ex) 수리비용</div>

                    <label>요청사항 <span class="color-red">*</span></label>
                    <textarea class="form-control margin-bottom-10" name="content" required title="요청사항을 입력해 주시기 바랍니다."></textarea><br/>

                    <label>수리부위</label>
                    <label class="form-control" id="fileLabel1" style="height:auto">
						<span>1번 사진 업로드 </span>
						<input type="hidden" name="file_no1" value="0">
                    	<input type="file" name="file" style="display: none">
						<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
                   	</label>
                    <label class="form-control" id="fileLabel2" style="height:auto">
						<span>2번 사진 업로드 </span>
						<input type="hidden" name="file_no2" value="0">
                    	<input type="file" name="file" style="display: none">
						<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
                   	</label>
                    <label class="form-control" id="fileLabel3" style="height:auto">
						<span>3번 사진 업로드 </span>
						<input type="hidden" name="file_no3" value="0">
                    	<input type="file" name="file" style="display: none">
						<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
                   	</label>
                    <label class="form-control" id="fileLabel4" style="height:auto">
						<span>4번 사진 업로드 </span>
						<input type="hidden" name="file_no4" value="0">
                    	<input type="file" name="file" style="display: none">
						<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
                   	</label>
                    <label class="form-control" id="fileLabel5" style="height:auto">
						<span>5번 사진 업로드 </span>
						<input type="hidden" name="file_no5" value="0">
                    	<input type="file" name="file" style="display: none">
						<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
                   	</label>

                   	</c:if>




                    <hr>
                    <div class="row text-right">
						<button class="btn-u btn submit" type="submit">견적 넣기</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

   <div id="areaModal" class="modal fade" role="dialog"  aria-labelledby="myLargeModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel" data-toggle="modal" data-target="#areaModal" data-backdrop="static">지역변경</h4>
	      </div>
	      <div class="modal-body">

			</div>
		</div>
		</div>
    </div>



<script type="text/javascript">
function getAreas() {
	var areas = [];
	return areas;
}
function setAreas( datas ) {
	if( datas.length > 1 ) {
		toastr.error("희망지역은 한개만 선택 할 수 있습니다.");
		return false;
	}

	$('[name=areaName]').val( datas[0].name );
	$('[name=areaCode]').val( datas[0].area );

	$('[name=areaCode]').trigger('keyup');

}

$(function() {

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
			폼 처리
	*****************************************/

	var checkedId = null;
	var $form = $('form.reg-page');

	$form.on('submit', function(){

		var valided = $form.valid();
		if( !valided ) return false;

		var formData = $form.formData();
		if( formData.wantDate )
			formData.wantDate = new Date(formData.wantDate);

		$.ajax({
			type : "POST",
			url :  $form.attr('action'),
			cache : false,
			data : formData,
			dataType : 'json',
			success : function(datas){
				if( datas.status == "ok" ){
					// 파일 업로드 처리
					callFileUpload(datas.data.tenderNo, function(){
						location.href = '${root}/tender/lists?categoryNo2=${param.categoryNo2}';
					});

				} else {
					toastr.error('견적서가 등록 실패\n'+datas.reason);
				}
			},
			error : function(){
				toastr.error('견적서가 등록 실패');
			}
		});
		return false;

	});
	$form.validate();


	function checkedCanSubmit() {

		var title = $form.find('[name=title]').val();
		if( title == '' ) return false;

		var areaCode = $form.find('[name=areaCode]').val();
		if( areaCode == '' ) return false;

		var wantDate = $form.find('[name=wantDate]').val();
		if( wantDate == '' ) return false;


		var valided = $form.valid();
		if( !valided ) return false;


		return true;
	}
	/*****************************************
			카테고리 처리
	*****************************************/

	var cateNo = ${param.categoryNo2};
	function loadSubCategory(parentCategoryNo, callback) {
		$.ajax({
			url : '${root}/api/category/list',
			data : { where : JSON.stringify({parentCategoryNo:parentCategoryNo}), len:999 },
			dataType : 'json',
			success : function( datas ) {

				callback( datas );
			}
		});
	}

	function makeOptionMap( datas ) {
		var map = {};
		for( var i=0,len=datas.list.length;i<len;i++ ) {
			map[ datas.list[i].categoryNo ] = datas.list[i].categoryName;
		}
		return map;
	}

	loadSubCategory(cateNo, function(datas){
		$('[name=categoryNo3]').options({'':'선택해주세요'});
		$('[name=categoryNo3]').options(makeOptionMap(datas));
	});



	$('[name=categoryNo3]').on('change', function(){
		loadSubCategory($(this).val(), function(datas){
			$('[name=categoryNo4]').empty();
			$('[name=categoryNo4]').options({'':'선택해주세요'});
			$('[name=categoryNo4]').options(makeOptionMap(datas));
		});
	});


	/*****************************************
				파일 업로드 처리
	*****************************************/
	$('input[name=file]').fileupload({
        url: '${root}/api/file/upload/',
        autoUpload : false,
        imageMaxWidth: 300,
        imageMaxHeight: 200,

    }).on('fileuploadadd', function(e, data){

    	var $this = $(this);
    	var prevFile = $this.data('prev');
        var acceptFileTypes = /^image\/(gif|jpe?g|png)$/i;
        if(!data.originalFiles[0]['type'] || !acceptFileTypes.test(data.originalFiles[0]['type'])) {
            toastr.error('이미지 파일만 올릴 수 있습니다.');
            return false;
        }
        if(data.originalFiles[0]['size'] && data.originalFiles[0]['size'] > 1000000) {
            toastr.error('1메가 이하의 이미지를 올려주세요.');
            return false;
        }

        if( prevFile ) prevFile.abort();

        $('.remove').removeClass('hide').off('click').on('click', function(){
        	$this.parent().find('span').text('사진 업로드');
        	$(this).addClass('hide');
        	data.abort();
        	prevFile = null;
        });

        prevFile = data;
        $this.data('prev', prevFile);
        $this.parent().find('span').text(' '+data.originalFiles[0].name);

        var reader = new FileReader();
        // Closure to capture the file information.
        reader.onload = (function(e) {

			var img = $('<img>', {src:e.target.result});
			$this.parent().find('span').prepend(img);
			if( img.width() > img.height() ) img.attr('width', 100);
			else img.attr('height', 50);
		});
        reader.readAsDataURL(data.originalFiles[0]);

    });

	function callFileUpload( num, callback ) {

		var hasUpload = false;
		var uploadCount = 0;
		$('input[name=file]').each(function(i){
			var prevFile = $(this).data('prev');
			if( prevFile ) {
				prevFile.formData = {boardType:'tender', boardNo:num, fileCount:i};
				prevFile.submit().complete(function() {
					// 모든 파일 업로드 성공까지 기다림
					uploadCount --;
					if( uploadCount == 0 ) callback();
				});
				uploadCount ++;
				hasUpload = true;
			}
		});

		// 업로드할것이 없다면 바로 진행
		if( !hasUpload ) {
			callback();
		}
	}

});
</script>
</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>