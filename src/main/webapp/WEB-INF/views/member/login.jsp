<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">개인정보취급안내</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">개인정보취급안내</li>
            </ul>
        </div>
    </div>

    <div class="container content">
    	<div class="row">
            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                <form class="reg-page">
                	<input type="hidden" name="rurl" value="${param.rurl}">
                    <div class="reg-header">
                        <h2>유저 로그인</h2>
                        <p>
                        	아직 계정이 없으세요?<br/>간편한 절차로 <a href="${root}/member/reg">회원가입</a>을 하세요.
                        </p>
                    </div>

                    <div class="input-group margin-bottom-20">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" placeholder="Id" class="form-control" name="id" id="id">
                    </div>
                    <div class="input-group margin-bottom-20">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" placeholder="Password" class="form-control" name="pw" id="pw">
                    </div>

                    <div class="row">
                        <div class="col-md-6 col-xs-6 checkbox">
                            <label><input type="checkbox" id="id_remember"> 아이디 기억</label>
                        </div>
                        <div class="col-md-6 col-xs-6">
                            <button class="btn-u pull-right" type="submit">로그인</button>
                        </div>
                    </div>

                    <hr>

                    <h4>비밀번호를 잊어 버리셨나요 ?</h4>
                    <p>걱정하지 마세요, <a class="color-green" href="${root}/member/lost_password">여기를</a> 눌러서 비밀번호를 초기화 하세요.</p>
                </form>
            </div>
        </div>
    </div>

	<script type="text/javascript">
		$('form.reg-page').on('submit', doLogin);

		var idRemember = $.cookie('idRemember');
		if( idRemember ) {
			$('#id_remember')[0].checked = true;
			$('#id').val(idRemember);
		}

		function doLogin() {
			var rurl = '${param.rurl}';
			var id = $("#id").val();
			var pw = $("#pw").val();
			if (id == "") {
				toastr.error("ID를 입력해주세요");
				$("#id").focus();
				return;
			}
			if (pw == "") {
				toastr.error("패스워드를 입력해주세요");
				$("#pw").focus();
				return;
			}

			$.ajax({
		        type:"POST",
		        url:"doLogin",
		        data:{id:id, pw:pw},
		        success:function(data){
		        	if(data.status=="ok") {

		        		if( $('#id_remember')[0].checked ) {
		        			$.cookie('idRemember', $('#id').val());
		        		}

		        		if(window.SubaBridge)
		        			window.SubaBridge.regId(data.data);

		        		if( data.reason == 'needWantArea' ) {
		        			location.href='${root}/member/want_area';
		        			return;
		        		}

		        		if( rurl != '' ) location.href=rurl;
		        		else location.href="${root}/";
		        	} else {
		        		toastr.error('로그인 실패', data.reason)
		        	}
		        },
		        error:function(e){
		        	toastr.error(e.responseText);
		        }
		    });
			return false;
		}
	</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>