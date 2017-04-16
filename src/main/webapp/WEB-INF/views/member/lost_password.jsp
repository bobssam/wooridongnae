<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">비밀번호 찾기</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">비밀번호 찾기</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->

    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page" method="POST" action="doFindPassword" data-toggle="validator" >
                    <div class="reg-header">
                        <h2>비밀번호 찾기</h2>
                        <p>
                        	가입시에 사용한 핸드폰 번호를 입력해주세요<br/>
                        	신규 비밀번호를 핸드폰으로 전송해 드립니다.<br/>

                        	가입하신 아이디도 생각이 나지 않으신다면 <a href="${root}/member/lost_id">여기</a>를 눌러 주세요
                        </p>
                    </div>

                    <div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">


	                    <label class="margin-top-20">아이디 <span class="color-red">*</span></label>
	                    <div class="row">
	                        <div class="col-sm-12 col-xs-12">
			                    <input type="text" class="form-control" name="id" id="id" required title="아이디를 적어주시기 바랍니다.">
		                    </div>
	                    </div>

	                    <label class="margin-top-20">전화번호 <span class="color-red">*</span></label>
	                    <div class="row">
							<div class="col-sm-4 col-xs-4">
								<select class="form-control" name="phoneNumber1">
									<option>010</option>
								</select>
							</div>
							<div class="col-sm-4 col-xs-4">
								<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4" name="phoneNumber2">
							</div>
							<div class="col-sm-4 col-xs-4">
								<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4" name="phoneNumber3">
							</div>
	                    </div>
                    </div>
                    <div class="text-right col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
						<button class="btn-u btn submit" type="submit" disabled="disabled">비밀번호 찾기</button>
                    </div>
                    <div style="clear:both"></div>

                </form>
            </div>
        </div>
    </div><!--/container-->


<script type="text/javascript">
$(function(){

	var checkedId = null;
	var $form = $('form.reg-page');

	$form.on('submit', function(){

		var formData = $form.formData();
		formData.phoneNo = formData.phoneNumber1+formData.phoneNumber2+formData.phoneNumber3;

		$.ajax({
			type : "POST",
			url :  $form.attr('action'),
			cache : false,
			data : formData,
			success : function(datas){
				if( datas.status == "ok" ){
					location.href = '${root}/member/find_password';
				} else {
					toastr.error('비밀번호 찾기 실패<br/>'+datas.reason);
				}
			},
			error : function(){
				toastr.error('비밀번호 찾기 실패');
			}
		});
		return false;
	});
	$form.validate();

	$form.find('input').on('keyup', toggleSubmit);

	function toggleSubmit() {
		if( checkedCanSubmit() ) $form.find('button.submit').removeAttr('disabled');
		else $form.find('button.submit').attr('disabled', 'disabled');
	}

	function checkedCanSubmit() {

		if( !$form.find('[name=id]').val() ) {
			return false;
		}

		if( !$form.find('[name=phoneNumber2]').val() ) {
			return false;
		}
		if( !$form.find('[name=phoneNumber3]').val() ) {
			return false;
		}

		return true;
	}

});
</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>