<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />
<style type="text/css">
.reg-page {
	padding: 20px !important;
}
.row.checkbox {
	margin-left: 10px;
}
.margin-top-10{
	margin-top: 10px;
}

@media (max-width: 768px){

	#phoneModal .col-xs-3 {
		padding-right: 5px;
		padding-left: 5px;

	}
	#phoneModal select {
		padding-right: 5px;
		padding-left: 5px;

	}
}
</style>

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">딜러 회원가입</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">회원</a></li>
                <li class="active">딜러 회원가입</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->

    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page" method="POST" action="doRegMember" enctype="multipart/form-data">
                    <div class="reg-header">
                        <h2>딜러 회원 등록</h2>
                        <p>계정이 이미 있으시다면 <a href="${root}/member/login" class="color-green">로그인</a> 을 눌러 로그인하세요.</p>
                    </div>

                    <label class="margin-top-10">성함 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-7">
		                    <input type="text" class="form-control" name="name" id="name"  required title="성함을 적어주시기 바랍니다.">
		                    <input type="hidden" class="form-control" name="type" id="type" value="2">
		                    <input type="hidden" class="form-control " name="phoneNo" id="phoneNo" value="">
	                    </div>
                        <div class="col-sm-3 col-xs-5">
							<button class="btn-u form-control" data-toggle="modal" data-target="#phoneModal" data-backdrop="static"  type="button" id="btn_mobile_check">휴대폰 인증</button>
	                    </div>
                    </div>

                    <label class="margin-top-10">아이디 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-7">
		                    <input type="text" class="form-control" name="id" id="id" required title="아이디를 적어주시기 바랍니다.">
	                    </div>
                        <div class="col-sm-3 col-xs-5">
							<button class="btn-u form-control" type="button" id="id_check">중복체크</button>
	                    </div>
                    </div>

					<div>
    	                <label class="margin-top-10">사업자명 <span class="color-red">*</span></label>
	                    <input type="text" class="form-control" name="corpName" id="corpName" required title="회사이름을 적어주시기 바랍니다.">
                    </div>

                    <label class="margin-top-10">전화번호</label>
					<div class="row">
						<div class="col-sm-3 col-xs-4">
							<select class="form-control" name="tel1" required="required" required title="회사 전호 번호를 적어주세요" >
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
							<input type="text" class="margin-bottom-20 form-control" name="tel2" size="4" minlength="3" maxlength="4" required title="회사 전호 번호를 적어주세요" >
						</div>
						<div class="col-sm-3 col-xs-4">
							<input type="text" class="margin-bottom-20 form-control" name="tel3" size="4" minlength="3" maxlength="4" required title="회사 전호 번호를 적어주세요" >
						</div>
					</div>

					<div>
	                    <label class="margin-top-10">회사 로고 및 회사 CI</label>
    	                <input type="file" name="file" id="attachFile" class="form-control">
   	                </div>


                    <div class="row">
                        <div class="col-sm-6">
                            <label class="margin-top-10">비밀번호 <span class="color-red">*</span></label>
                            <input type="password" class="form-control" name="pw" id="pw" minlength="6" required title="비밀번호는 6자 이상 적어주세요" >
                        </div>
                        <div class="col-sm-6">
                            <label class="margin-top-10">비밀번호 확인 <span class="color-red">*</span></label>
                            <input type="password" class="form-control" name="pw2" id="pw2" equalTo="#pw" title="패스워드가 같지 않습니다." required>
                        </div>
                    </div>

                    <hr>

                    <div class="row checkbox">
						<label>
						    <input type="checkbox" id="agree_terms"> 약관 동의
						    <a href="${root}/infos/terms" class="color-green">읽기</a>
						</label>
					</div>
                    <div class="row checkbox">
						<label>
						    <input type="checkbox" id="agree_privacy"> 개인 정보 취급 동의
						    <a href="${root}/infos/privacy" class="color-green">읽기</a>
						</label>
                    </div>
                    <div class="row text-right">
						<button class="btn-u btn submit" type="submit">가입하기</button>
                    </div>

                </form>
            </div>
        </div>
    </div>


<div class="modal fade" id="phoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">가입 인증</h4>
      </div>
      <div class="modal-body">

      	<form >
			<div class="reg-header">
				<h2>휴대폰 인증 (무료)</h2>
				<p>
					휴대폰 번호를 입력후 확인을 클릭하세요<br/>동일번호로 1개의 아이디를 가입 할 수 있습니다
				</p>
			</div>

			<label>전화번호 <span class="color-red">*</span></label>
			<div class="row">
				<div class="col-sm-3 col-xs-3">
					<select class="form-control" id="phoneNum1">
						<option>010</option>
					</select>
				</div>
				<div class="col-sm-3 col-xs-3">
					<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4" id="phoneNum2">
				</div>
				<div class="col-sm-3 col-xs-3">
					<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4" id="phoneNum3">
				</div>
				<div class="col-sm-3 col-xs-3 text-right">
					<button class="btn-u form-control" type="button" id="smsSend">전송</button>
				</div>
			</div>

			<div style="display: none" id="certification">
				<label>인증번호 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-9">
						<input type="text" class="margin-bottom-20 form-control" size="6" maxlength="6" id="inputKey">
						남은 시간 <span id="sec">180</span>초
					</div>
					<div class="col-sm-3 col-xs-3 text-right">
						<button class="btn-u form-control" type="button" id="certificationSubmit">인증완료</button>
					</div>
				</div>
			</div>

			<div style="display: none" id="certification_fail">
				<label>인증실패 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-9">
						인증 시간 이내에 입력하지 못했습니다.
					</div>
					<div class="col-sm-3 col-xs-3 text-right">
						<button class="btn-u form-control" type="button" id="retry">재시도</button>
					</div>
				</div>
			</div>
		</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>



<script type="text/javascript">
$(function(){
	var phone = "";
	var checkedId = null;
	var $form = $('form.reg-page');
	var remainSec = -1;
	var secCheckTimer = null;

	$form.on('submit', function(){


		if( checkedId != $form.find('[name=id]').val() ) {
			toastr.error("아이디 중복체크를 해주시기 바랍니다.");
			return false;
		}
		if( !$form.valid() ) return false;
		var phoneNo = $form.find('[name=phoneNo]').val();
		if( phoneNo == '' ) { toastr.error("휴대폰 인증을 받아주시기 바랍니다."); return false; }
		if( !$('#agree_terms')[0].checked ) {  toastr.error("서비스 이용을 위해 약관 동의를 확인해 주세요."); return false; }
		if( !$('#agree_privacy')[0].checked ) { toastr.error("서비스 이용을 위해 개인정보 취급 동의를 확인해 주세요."); return false; }


		var formData = $form.formData();
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
					location.href = '${root}/member/reg_success';
				} else {
					toastr.error('회원 가입 실패',datas.reason);
				}
			},
			error : function(){
				toastr.error('회원 가입 실패');
			}
		});

	});
	$form.validate();

	//$form.find('input').on('keyup', toggleSubmit);
	//$form.find('#agree_terms,#agree_privacy').on('change', toggleSubmit);

	function secTimer() {
		remainSec --;
		//var sec = parseInt($('#sec').text());
		if (remainSec > 0) {
			$('#sec').text(remainSec);
		}
		else {
			$('#sec').text('0');
			clearInterval(secCheckTimer);

			$('#inputKey').attr('readonly','readonly');
			$('#certificationSubmit').disabled();
			$('#certification_fail').show();
		}
	}

	// 아이디 체크
	$('#id_check').on('click', function(){
		var id =  $form.find('[name=id]').val();
		if( id == '' ) {
			toastr.error('아이디를 입력해주세요');
			return;
		}

		var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
        if( !idReg.test( id ) ) {
        	toastr.error("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
            return;
        }

		$.ajax({
			type : "POST",
			url :  '${root}/member/duplicateCheckId',
			cache : false,
			data : {id:id},
			success : function(datas){
				if( datas.status == "ok" ){
					checkedId = id;
					toastr.info('ID 중복 체크 성공','사용이 가능한 ID 입니다.');
				} else {
					toastr.error('ID 중복 체크 실패',datas.reason);
				}

				//toggleSubmit();
			},
			error : function(){
				toastr.error('ID 중복 체크 실패');
				//toggleSubmit();
			}
		});
	});

	$('#smsSend').on('click', function() {

		if( remainSec > 0 ) return;

		var phone1 = $('#phoneNum1').val();
		var phone2 = $('#phoneNum2').val();
		var phone3 = $('#phoneNum3').val();
		if ((phone1+phone2+phone3).length < 9) {
			toastr.error('휴대폰번호를 정확하게 입력해주세요','');
			return false;
		}
		phone = phone1 + "-" + phone2 + "-" + phone3;
		$.ajax({
			type : "POST",
			url :  '${root}/api/certification/keyGeneration',
			cache : false,
			data : {phone:phone},
			success : function(datas){
				if( datas == "success" ){
					remainSec = 180;	// 3분
					toastr.info('인증번호 발송','휴대폰으로 인증번호를 발송하였습니다.');
					$('#certification').css('display', 'block');

					$('#smsSend').disabled();
					$('#phoneNum1').attr('readonly','readonly');
					$('#phoneNum2').attr('readonly','readonly');
					$('#phoneNum3').attr('readonly','readonly');

					secCheckTimer = setInterval(secTimer, 1000);
				} else {
					toastr.error('발송 실패','발송에 실패하였습니다.');
				}
			},
			error : function(){
				toastr.error('발송 실패');
			}
		});

	});

	$('#certificationSubmit').on('click', function() {
		var inputKey = $('#inputKey').val();
		if (inputKey.length != 6) {
			toastr.error('입력오류','정확한 번호를 입력해주세요');
			return false;
		}
		$.ajax({
			type : "POST",
			url :  '${root}/api/certification/certification',
			cache : false,
			data : {phone:phone, inputKey:inputKey},
			success : function(datas){
				if( datas == "success" ){
					toastr.info('인증번호 완료','휴대폰 인증이 완료되었습니다.');
					$form.find('[name=phoneNo]').val(phone);
					$('#phoneModal').modal('hide');
					$('#btn_mobile_check').disabled();
				} else {
					toastr.error('인증 실패','인증에 실패하였습니다.');
				}
			},
			error : function(){
				toastr.error('인증 실패');
			}
		});
	});

	$('#retry').on('click', function(){
		$('#smsSend').enabled();
		$('#phoneNum1').removeAttr('readonly');
		$('#phoneNum2').removeAttr('readonly');
		$('#phoneNum3').removeAttr('readonly');
		$('#inputKey').removeAttr('readonly');
		$('#certificationSubmit').enabled();
		$('#certification').hide();
		$('#certification_fail').hide();

	});

	function toggleSubmit() {
		if( checkedCanSubmit() ) $form.find('button.submit').removeAttr('disabled');
		else $form.find('button.submit').attr('disabled', 'disabled');
	}

	//
	function checkedCanSubmit() {

		if( checkedId != $form.find('[name=id]').val() ) {
			return false;
		}

		var phoneNo = $form.find('[name=phoneNo]');
		if( phoneNo == '' ) return false;

		var valided = $form.valid();
		if( !valided ) return false;

		if( !$('#agree_terms')[0].checked ) return false;
		if( !$('#agree_privacy')[0].checked ) return false;

		return true;
	}

});
</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>