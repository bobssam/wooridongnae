<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />





    <div class="footer-v1">
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

	                    <!-- Address -->
	                    <div class="map-img md-margin-bottom-40">
	                        <div class="headline"><h2>Contact Us</h2></div>
	                        <address class="md-margin-bottom-10">
	                    	    혀누앤션  : 정대명 <br/>
								주소 : 충청남도 공주시 금학동길 35-23 5층 502호<br/>
								사업자등록번호 : 286-29-00056 <a href="#">사업자정보확인</a><br/>
								문의전화 : 1333-1222 / 02-1234-4351 / 팩스 : 02-3131-12121

	                        </address>
	                    </div><!--/col-md-3-->
	                    <!-- End Address -->
                    </div>
                </div>
            </div>
        </div><!--/footer-->

        <div class="copyright">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <p>
                            2015 &copy; All Rights Reserved.
                            <a href="${root}/infos/partner">제휴/광고문의</a> |
                           <a href="${root}/infos/privacy">개인정보 취급</a> | <a href="${root}/infos/terms">이용약관</a>
                        </p>
                    </div>

                    <!-- Social Links -->
                    <div class="col-md-6 text-right hidden-xs">
						<a href="/"><img id="logo-footer" class="footer-logo" src="${root}/assets/img/logo2-default.png" alt=""></a>
                    </div>
                    <!-- End Social Links -->
                </div>
            </div>
        </div><!--/copyright-->
    </div>
    <!--=== End Footer Version 1 ===-->
</div><!--/wrapper-->

<c:choose>
	<c:when test="${param.title eq '메인'}">

<!-- JS Global Compulsory -->
<script type="text/javascript" src="${root}/assets/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- JS Implementing Plugins -->
<script type="text/javascript" src="${root}/assets/plugins/back-to-top.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/smoothScroll.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/parallax-slider/js/modernizr.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/parallax-slider/js/jquery.cslider.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/owl-carousel/owl-carousel/owl.carousel.js"></script>
<!-- JS Customization -->
<script type="text/javascript" src="${root}/assets/js/custom.js"></script>
<!-- JS Page Level -->
<script type="text/javascript" src="${root}/assets/js/app.js"></script>
<script type="text/javascript" src="${root}/assets/js/plugins/owl-carousel.js"></script>
<script type="text/javascript" src="${root}/assets/js/plugins/parallax-slider.js"></script>


<script type="text/javascript">
    jQuery(document).ready(function() {
      	App.init();
        OwlCarousel.initOwlCarousel();
        ParallaxSlider.initParallaxSlider();
    });
</script>


    </c:when>
    <c:otherwise>

<!-- JS Global Compulsory -->
<script type="text/javascript" src="${root}/assets/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- JS Implementing Plugins -->
<script type="text/javascript" src="${root}/assets/plugins/back-to-top.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/smoothScroll.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
<!-- JS Customization -->
<script type="text/javascript" src="${root}/assets/js/custom.js"></script>
<!-- JS Page Level -->
<script type="text/javascript" src="${root}/assets/js/app.js"></script>
<script type="text/javascript" src="${root}/assets/plugins/jquery-ui/jquery-ui.js"></script>

<script type="text/javascript" src="${root}/assets/js/admin.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
    	jQuery(".fancybox").fancybox({
            groupAttr: 'data-rel',
            prevEffect: 'fade',
            nextEffect: 'fade',
            openEffect  : 'elastic',
            closeEffect  : 'fade',
            type : "image",
            closeBtn: true,
            helpers: {
                title: {
                        type: 'float'
                    }
                }
            });
    });
</script>

	</c:otherwise>
</c:choose>




<!--[if lt IE 9]>
    <script src="assets/plugins/respond.js"></script>
    <script src="assets/plugins/html5shiv.js"></script>
    <script src="assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->

</body>
</html>
</fmt:bundle>