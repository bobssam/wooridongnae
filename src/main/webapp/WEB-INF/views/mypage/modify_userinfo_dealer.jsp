<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

<script src="${root}/assets/plugins/fileupload/js/jquery.iframe-transport.js"></script>
<script src="${root}/assets/plugins/fileupload/js/jquery.fileupload.js"></script>
<style type="text/css">
.margin-top-10{
	margin-top: 10px;
}

</style>

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
    </div><!--/breadcrumbs-->


    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page" method="POST" action="${root}/member/doModifyMember" data-toggle="validator" >
                    <div class="reg-header">
                        <h2>딜러 회원 정보 수정</h2>
                    </div>

                    <label class="margin-top-10">성함 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control " maxlength="10" name="name" id="name" disabled="disabled" value="${sessionScope.memberVO.name}">
		                    <input type="hidden" class="form-control margin-bottom-20" name="type" id="type" value="1">
		                    <input type="hidden" class="form-control " name="phoneNumber" id="phoneNumber" value="000">
	                    </div>
                    </div>

                    <label class="margin-top-10">아이디 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control" name="id" id="id" disabled="disabled" value="${sessionScope.memberVO.id}">
	                    </div>
                    </div>

					<div>
    	                <label class="margin-top-10">사업자명 <span class="color-red">*</span></label>
	                    <input type="text" class="form-control" name="corpName" id="corpName" required title="회사이름을 적어주시기 바랍니다." value="${sessionScope.memberVO.corpName}">
                    </div>


                    <label class="margin-top-10">전화번호</label>
					<div class="row">
						<div class="col-sm-3 col-xs-4">
							<select class="form-control" name="tel1" required="required" title="회사 전호 번호를 적어주세요">
								<OPTION value="">없음</OPTION>
								<OPTION value=02>02</OPTION>
								<OPTION value=031>031</OPTION>
								<OPTION value=032>032</OPTION>
								<OPTION value=033>033</OPTION>
								<OPTION value=041>041</OPTION>
								<OPTION value=042>042</OPTION>
								<OPTION value=043>043</OPTION>
								<OPTION value=044>044</OPTION>
								<OPTION value=0303>0303</OPTION>
								<OPTION value=0502>0502</OPTION>
								<OPTION value=0503>0503</OPTION>
								<OPTION value=0504>0504</OPTION>
								<OPTION value=0505>0505</OPTION>
								<OPTION value=0506>0506</OPTION>
								<OPTION value=051>051</OPTION>
								<OPTION value=052>052</OPTION>
								<OPTION value=053>053</OPTION>
								<OPTION value=054>054</OPTION>
								<OPTION value=055>055</OPTION>
								<OPTION value=061>061</OPTION>
								<OPTION value=062>062</OPTION>
								<OPTION value=063>063</OPTION>
								<OPTION value=064>064</OPTION>
								<OPTION value=070>070</OPTION>
							</select>
						</div>
						<div class="col-sm-3 col-xs-4">
							<input type="text" class="margin-bottom-20 form-control" name="tel2" size="4" minlength="3" maxlength="4" required title="회사 전호 번호를 적어주세요">
						</div>
						<div class="col-sm-3 col-xs-4">
							<input type="text" class="margin-bottom-20 form-control" name="tel3" size="4" minlength="3" maxlength="4" required title="회사 전호 번호를 적어주세요" >
						</div>
					</div>
					<script type="text/javascript">
						var tel = "${sessionScope.memberVO.tel}";
						var telNos = tel.split("-");
						if( telNos.length == 3 ) {
							$('select[name=tel1]').val(telNos[0]);
							$('input[name=tel2]').val(telNos[1]);
							$('input[name=tel3]').val(telNos[2]);
						}

					</script>

					<div>
    	                <label class="margin-top-10">푸시 수신여부 <span class="color-red">*</span></label><br/>
    	                <div style="border: 1px solid #CCC; padding: 5px; ">
    	                	<label>수신함 <input type="radio" name="pushYn" value="Y" <c:if test="${sessionScope.memberVO.pushYn == 'Y'}">checked="checked"</c:if>></label>
    	                	<label>수신안함 <input type="radio" name="pushYn" value="N" <c:if test="${sessionScope.memberVO.pushYn == 'N'}">checked="checked"</c:if>></label>
    	                </div>
                    </div>

					<div>
	                    <label class="margin-top-10">회사 로고 및 회사 CI</label>
	                    <c:if test="${sessionScope.memberVO.fileNo>0}">
	                    <a href="${root}/api/file/download?fileNo=${sessionScope.memberVO.fileNo}" rel="gallery" class="fancybox img-hover-v1" title="회사 로그">
	                    <span><img src="${root}/api/file/download?fileNo=${sessionScope.memberVO.fileNo}" class="img-responsive center-block"></span></a>
	                    </c:if>
	                    <label class="form-control" id="fileLabel" style="height:auto">
							<span>사진 업로드 </span>
	                    	<input type="file" name="file" style="display: none">
							<button type="button" class="btn-u btn-u-xs btn-u-default remove pull-right hide"><i class="fa fa-times"></i></button>
	                   	</label>
    	                <!-- <input type="file" class="form-control"> -->
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
                       		<div style="border: 1px solid #ccc; min-height: 34px; padding:5px;" id="selected_areas">

								<c:forEach var="area" items="${wantArea}" >
		    					<small>${area.bigAreaName}>${area.smallAreaName}<button type="button" class="btn-u btn-u-xs btn-u-default remove"><i class="fa fa-times"></i></button><input type="hidden" name="areaCode" value="${area.areaCode}"> </small>
		    					</c:forEach>

                       		</div>
	                    </div>
                    </div>
                    <p>희망 지역은 최대 5지역까지 입니다.</p>

					<label class="margin-top-10">비밀번호 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="password" class="form-control" name="prev_pw" id="prev_pw" required minlength="6" title="비밀번호는 6자 이상 적어주세요">
	                    </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label  class="margin-top-10">신규 비밀번호 </label>
                            <input type="password" class="form-control" name="pw" id="pw" minlength="6" title="비밀번호는 6자 이상 적어주세요" >
                        </div>
                        <div class="col-sm-6">
                            <label  class="margin-top-10">신규 비밀번호 확인 </label>
                            <input type="password" class="form-control" name="pw2" id="pw2" equalTo="#pw" title="패스워드가 같지 않습니다." >
                        </div>
                    </div>
                    <p>
                    	비밀번호 변경을 원하시는 경우만 신규 비밀번호를 넣어주세요
                    </p>

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
		<label><input type="checkbox" name="categoryNo3s" value="\${categoryNo}" {{if checked}}checked="checked"{{/if}}> \${categoryName}</label>&nbsp;&nbsp;
	</script>



<script type="text/javascript">
$(function(){

	var checkedId = null;
	var $form = $('form.reg-page');

	$form.on('submit', function(){

		if( !$form.valid() ) return false;
		var formData = $form.formData();

		if( !formData.areaCode ) {
			toastr.error('희망 지역을 선택해주세요');
			return false;
		}
		if( !formData.categoryNo3s ) {
			toastr.error('상세 업종을 선택해주세요');
			return false;
		}


		formData.tel = formData.tel1 +'-'+formData.tel2 +'-'+formData.tel3;
		delete formData.tel1;
		delete formData.tel2;
		delete formData.tel3;

		$.ajax({
			type : "POST",
			url :  $form.attr('action'),
			cache : false,
			data : formData,
			success : function(datas){
				if( datas.status == "ok" ){
					callFileUpload( function(){
						location.href = '${root}/member/modifyed_info';
					});
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

	//$form.find('input').on('keyup', toggleSubmit);
	/*
	function toggleSubmit() {
		if( checkedCanSubmit() ) $form.find('button.submit').removeAttr('disabled');
		else $form.find('button.submit').attr('disabled', 'disabled');
	}
	*/

	function checkedCanSubmit() {

		if( !$form.valid() ) {
			return false;
		}

		return true;
	}

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
        	$('#fileLabel span').text('사진 업로드');
        	$(this).addClass('hide');
        	data.abort();
        	prevFile = null;
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

	function callFileUpload( callback ) {
		if( prevFile ) {
			prevFile.formData = {boardType:'dealer'};
			prevFile.submit().complete(callback);
		} else {
			callback();
		}
	}


	/*****************************************

	*****************************************/

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

	var category3s = {};
	<c:forEach items="${sessionScope.memberVO.categoryNo3}" var="category3">
	category3s[${category3}] = true;
	</c:forEach>


	loadSubCategory(  ${sessionScope.memberVO.categoryNo2}, function(datas){
		if( datas.status == 'ok' ) {
			var $tar = $form.find('#check_cate4');
			$tar.empty();

			for( var i=0,len=datas.list.length;i<len;i++ ) {
				datas.list[i].checked = category3s[ datas.list[i].categoryNo ];
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