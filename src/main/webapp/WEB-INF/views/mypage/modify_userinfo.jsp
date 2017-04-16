<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <form class="reg-page" method="POST" action="${root}/member/doModifyMember" data-toggle="validator" >
                    <div class="reg-header">
                        <h2>일반 회원 정보 수정</h2>
                    </div>

                    <label class="margin-top-20">성함 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control " maxlength="10" name="name" id="name" disabled="disabled" value="${sessionScope.memberVO.name}">
		                    <input type="hidden" class="form-control margin-bottom-20" name="type" id="type" value="1">
		                    <input type="hidden" class="form-control " name="phoneNumber" id="phoneNumber" value="000">
	                    </div>
                    </div>

                    <label class="margin-top-20">아이디 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control" name="id" id="id" disabled="disabled" value="${sessionScope.memberVO.id}">
	                    </div>
                    </div>

					<label class="margin-top-20">기존 비밀번호 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="password" class="form-control" name="prev_pw" id="prev_pw">
	                    </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label  class="margin-top-20">비밀번호 <span class="color-red">*</span></label>
                            <input type="password" class="form-control" name="pw" id="pw" minlength="6" required title="비밀번호는 6자 이상 적어주세요" >
                        </div>
                        <div class="col-sm-6">
                            <label  class="margin-top-20">비밀번호 확인 <span class="color-red">*</span></label>
                            <input type="password" class="form-control" name="pw2" id="pw2" equalTo="#pw" title="패스워드가 같지 않습니다." required>
                        </div>
                    </div>

					<hr>
                    <div class="row text-right">
						<button class="btn-u btn submit" type="submit" disabled="disabled">수정하기</button>
                    </div>

                </form>
            </div>
        </div>
    </div>



<script type="text/javascript">
$(function(){

	var checkedId = null;
	var $form = $('form.reg-page');

	$form.on('submit', function(){

		var formData = $form.formData();

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

	$form.find('input').on('keyup', toggleSubmit);

	function toggleSubmit() {
		if( checkedCanSubmit() ) $form.find('button.submit').removeAttr('disabled');
		else $form.find('button.submit').attr('disabled', 'disabled');
	}

	function checkedCanSubmit() {

		if( !$form.valid() ) {
			return false;
		}

		return true;
	}

});
</script>

<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>