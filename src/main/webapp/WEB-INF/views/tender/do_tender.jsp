<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <!--=== Breadcrumbs ===-->
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
    <!--=== End Breadcrumbs ===-->

     <!--=== Content Part ===-->
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <form class="reg-page" method="POST" action="doRegMember">
                    <div class="reg-header">
                        <h2>딜러 회원 등록</h2>
                        <p>계정이 이미 있으시다면 <a href="/member/login" class="color-green">로그인</a> 을 눌러 로그인하세요.</p>
                    </div>

                    <label>성함 <span class="color-red">*</span></label>
                    <div class="row">
                        <div class="col-sm-9 col-xs-8">
		                    <input type="text" class="form-control margin-bottom-20" name="name" id="name">
		                    <input type="hidden" class="form-control margin-bottom-20" name="type" id="type" value="2">
	                    </div>
                        <div class="col-sm-3 col-xs-4">
							<button class="btn-u" data-toggle="modal" data-target="#phoneModal" data-backdrop="static"  type="button">휴대폰 인증</button>
	                    </div>
                    </div>

                    <label>아이디 <span class="color-red">*</span></label>
                    <input type="text" class="form-control margin-bottom-20" name="id" id="id">

                    <label>사업자명 <span class="color-red">*</span></label>
                    <input type="text" class="form-control margin-bottom-20" name="corpName" id="corpName">

                    <label>회사 로고 및 회사 CI</label>
                    <input type="file" class="form-control margin-bottom-20">


                    <div class="row">
                        <div class="col-sm-6">
                            <label>비밀번호 <span class="color-red">*</span></label>
                            <input type="password" class="form-control margin-bottom-20" name="pw" id="pw">
                        </div>
                        <div class="col-sm-6">
                            <label>비밀번호 확인 <span class="color-red">*</span></label>
                            <input type="password" class="form-control margin-bottom-20" name="pw2" id="pw2">
                        </div>
                    </div>

                    <hr>

                    <div class="row checkbox">
						<label>
						    <input type="checkbox"> 약관 동의
						    <a href="/infos/terms" class="color-green">읽기</a>
						</label>
					</div>
                    <div class="row checkbox">
						<label>
						    <input type="checkbox"> 개인 정보 취급 동의
						    <a href="/infos/privacy" class="color-green">읽기</a>
						</label>
                    </div>
                    <div class="row text-right">
						<button class="btn-u btn" disabled="disabled" type="submit">가입하기</button>
                    </div>

                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->


<!-- Modal -->
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
					<select class="form-control">
						<option>010</option>
					</select>
				</div>
				<div class="col-sm-3 col-xs-3">
					<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4">
				</div>
				<div class="col-sm-3 col-xs-3">
					<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4">
				</div>
				<div class="col-sm-3 col-xs-3 text-right">
					<button class="btn-u form-control" type="button">전송</button>
				</div>
			</div>

			<div style="display: none">
				<label>인증번호 <span class="color-red">*</span></label>
				<div class="row">
					<div class="col-sm-9 col-xs-9">
						<input type="text" class="margin-bottom-20 form-control" size="4" maxlength="4">
						남은 시간 30초
					</div>
					<div class="col-sm-3 col-xs-3 text-right">
						<button class="btn-u form-control" type="button">인증완료</button>
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


<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>