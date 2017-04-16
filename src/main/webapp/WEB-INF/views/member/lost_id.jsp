<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">아이디 찾기</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">아이디 찾기</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <!--=== Content Part ===-->
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page" method="POST" action="find_id" data-toggle="validator" >
                    <div class="reg-header">
                        <h2>아이디 찾기</h2>
                        <p>
                        	가입시에 사용한 핸드폰 번호를 입력해주세요<br/>
                        	가입하신 id의 일부를 보여 드립니다.
                        </p>
                    </div>
                    
                    <div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
                    
                    
	                    <label class="margin-top-20">전화번호 <span class="color-red">*</span></label>
	                    <div class="row">
							<div class="col-sm-4 col-xs-4">
								<select class="form-control" name="phoneNumber1" required>
									<option>010</option>
								</select>
							</div>
							<div class="col-sm-4 col-xs-4">
								<input type="text" name="phoneNumber2" class="margin-bottom-20 form-control" size="4" maxlength="4" required title="핸드폰 중간자리">
							</div>
							<div class="col-sm-4 col-xs-4">
								<input type="text" name="phoneNumber3" class="margin-bottom-20 form-control" size="4" maxlength="4" required title="핸드폰 마지막자리">
							</div>
	                    </div>
                    </div>
                    <div class="text-right col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
						<button class="btn-u btn submit" type="submit" disabled="disabled">인증하기</button>
                    </div>
                    <div style="clear:both"></div>

                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->



<script type="text/javascript">
$(function(){
	
	var $form = $('form.reg-page');
	$form.validate();
	$form.find('input').on('keyup', toggleSubmit);
	
	function toggleSubmit() {
		if( checkedCanSubmit() ) $form.find('button.submit').removeAttr('disabled');
		else $form.find('button.submit').attr('disabled', 'disabled');
	}
	
	function checkedCanSubmit() {
		
		var valided = $form.valid();
		if( !valided ) return false;
		
		return true;
	}
	
});
</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>