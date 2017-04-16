<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="어플다운로드" />
</c:import>
<link rel="stylesheet" href="${root}/assets/plugins/owl-carousel/owl-carousel/owl.carousel.css">



    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">제휴/광고문의</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">이용안내</a></li>
                <li class="active">제휴/광고문의</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

     <!--=== Content Part ===-->
    <div class="container content">
        <div class="row margin-bottom-30">
            <div class="col-md-9 mb-margin-bottom-30">
                <!-- Google Map -->
                <div id="map" class="map map-box map-box-space margin-bottom-40"></div>
                <!-- End Google Map -->

                <p>저희에게 문의를 위하는 내용을 보내주세요. 최대한 빠른 시일안에 연락을 드리겠습니다</p><br />

                <form action="${root}/api/partnerask/add" method="post" id="sky-form3" class="sky-form contact-style">
                    <fieldset class="no-padding">
                        <label>이름 <span class="color-red">*</span></label>
                        <div class="row sky-space-20">
                            <div class="col-md-7 col-md-offset-0">
                                <div>
                                    <input type="text" name="name" id="name" class="form-control">
                                </div>
                            </div>
                        </div>

                        <label>연락처 <span class="color-red">*</span></label>
                        <div class="row sky-space-20">
                            <div class="col-md-7 col-md-offset-0">
                                <div>
                                    <input type="text" name="mobile" id="mobile" class="form-control">
                                </div>
                            </div>
                        </div>

                        <label>Email <span class="color-red">*</span></label>
                        <div class="row sky-space-20">
                            <div class="col-md-7 col-md-offset-0">
                                <div>
                                    <input type="text" name="email" id="email" class="form-control">
                                </div>
                            </div>
                        </div>

                        <label>문의사항 <span class="color-red">*</span></label>
                        <div class="row sky-space-20">
                            <div class="col-md-11 col-md-offset-0">
                                <div>
                                    <textarea rows="8" name="comment" id="comment" class="form-control"></textarea>
                                </div>
                            </div>
                        </div>

                        <p><button type="submit" class="btn-u">문의하기</button></p>
                    </fieldset>

                    <div class="message">
                        <i class="rounded-x fa fa-check"></i>
                        <p>Your message was successfully sent!</p>
                    </div>
                </form>
            </div><!--/col-md-9-->

    		<div class="col-md-3">
            	<!-- Contacts -->
                <div class="headline"><h2>연락처</h2></div>
                <ul class="list-unstyled who margin-bottom-30">
                    <li><a href="#"><i class="fa fa-home"></i>충청남도 공주시 금학동길 35-23 5층 502호</a></li>
                    <li><a href="#"><i class="fa fa-envelope"></i>info@example.com</a></li>
                    <li><a href="#"><i class="fa fa-phone"></i>1333-1222</a></li>
                </ul>
                <!-- End Contacts -->

            	<!-- Business Hours -->
                <div class="headline"><h2>업무시간</h2></div>
                <ul class="list-unstyled margin-bottom-30">
                	<li><strong>월 - 금:</strong> 오전 10시 ~ 오후 8시</li>
                	<li><strong>토요일:</strong> 오전 11시 ~ 오후 3시</li>
                </ul>
                <!-- End Business Hours -->
                <!-- End Social -->
            </div><!--/col-md-3-->
        </div><!--/row-->

        <!-- Owl Clients v1 -->
        <div class="headline"><h2>파트너사 (결정필요)</h2></div>
        <div class="owl-clients-v1">
            <div class="item">
                <img src="${img}/assets/img/clients4/1.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/2.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/3.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/4.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/5.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/6.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/7.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/8.png" alt="">
            </div>
            <div class="item">
                <img src="${img}/assets/img/clients4/9.png" alt="">
            </div>
        </div>
        <!-- End Owl Clients v1 -->
    </div><!--/container-->
    <!--=== End Content Part ===-->

	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">등록 성공</h4>
				</div>
				<div class="modal-body">
					<p>제휴사 문의가 등록되었습니다.<br/>빠른 시일안에 연락 드리겠습니다.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>


</fmt:bundle>
<script type="text/javascript" src="${root}/assets/plugins/owl-carousel/owl-carousel/owl.carousel.js"></script>
<script type="text/javascript">
    $(function() {

    	var $form = $('form').eq(0);
    	$form.validate();
    	$form.on('submit', function(){

    		var valided = $form.valid();
    		if( !valided ) return false;

    		var formData = $form.formData();
    		$.ajax({
    			type : "POST",
    			url :  $form.attr('action'),
    			cache : false,
    			data : formData,
    			dataType : 'json',
    			success : function(datas){
    				if( datas.status == "ok" ){
    					$(".modal").modal();
    				} else {
    					toastr.error('제휴사 문의 등록 실패\n'+datas.reason);
    				}
    			},
    			error : function(){
    				toastr.error('제휴사 문의 등록 실패');
    			}
    		});
    		return false;

    	});

    	$(".modal").on('hide.bs.modal', function(){
    		$form.formData({}, true);
    	});

    	/*********************************************
    				파트너사 스크롤
    	*********************************************/
        $(".owl-clients-v1").owlCarousel({
            items : 7,
            autoPlay : 5000,
            itemsDesktop : [1000,5],
            itemsDesktopSmall : [900,4],
            itemsTablet: [600,3],
            itemsMobile : [300,2]
        });
	});
</script>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>