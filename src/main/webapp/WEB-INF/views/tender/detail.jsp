<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <link rel="stylesheet" href="/assets/plugins/owl-carousel/owl-carousel/owl.carousel.css"> 

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">추천 업체</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">견적</a></li>
                <li class="active">추천 업체</li>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->


    <!--=== Content Part ===-->
    <div class="container content">
    	<div class="row portfolio-item margin-bottom-50">
            <!-- Carousel -->
            <div class="col-md-7">
                <div class="carousel slide carousel-v1" id="myCarousel">
                    <div class="carousel-inner">
                        <div class="item active">
                            <img alt="" src="/assets/img/main/img11.jpg">
                            <div class="carousel-caption">
                                <p>Facilisis odio, dapibus ac justo acilisis gestinas.</p>
                            </div>
                        </div>
                        <div class="item">
                            <img alt="" src="/assets/img/main/img12.jpg">
                            <div class="carousel-caption">
                                <p>Cras justo odio, dapibus ac facilisis into egestas.</p>
                            </div>
                            </div>
                        <div class="item">
                            <img alt="" src="/assets/img/main/img13.jpg">
                            <div class="carousel-caption">
                                <p>Justo cras odio apibus ac afilisis lingestas de.</p>
                            </div>
                        </div>
                    </div>

                    <div class="carousel-arrow">
                        <a data-slide="prev" href="#myCarousel" class="left carousel-control">
                            <i class="fa fa-angle-left"></i>
                        </a>
                        <a data-slide="next" href="#myCarousel" class="right carousel-control">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                </div>
            </div>
            <!-- End Carousel -->

            <!-- Content Info -->
            <div class="col-md-5">
            	<h2>울트라 캡숑 업체</h2>
                <p>태초에 우리에겐 악기를 연주하는 남자가 있었다. 시작은 리라 연주 하나 만으로 여신들의 마음을 사로잡았던 오르페우스가 있었고, 이후에는 영화 에서 자신의 연인인 벨라를 집으로 초대해 그녀 앞에서 피아노를 연주했던 뱀파이어 에드워드도 있었다. 특히 우리나라에서는 악기들 중에서도 ‘피아노’의 보급률이 높은 편이고, 어렸을 때 남과 여를 가리지 않고 한 번쯤은 배워보았을 것이다. 예로 아무리 피아노를 못 치는 사람도 ‘고양이 춤’이나 ‘엘리제를 위하여’, ‘젓가락 행진곡’ 정도는 들어는 보았고, 혹은 칠 줄 안다. 이런 이유로 남자들도 바이올린이나 첼로 같은 비교적 접하기 어려운 악기 보다는 저마다 이벤트용, 프로포즈 용으로 피아노 곡을 한 두 번씩은 연습해 본 경험이 있을 것이고, 실제로 페이스북 페이지 중 ‘피아노 치는 남자들’이라는 페이지는 좋아요 수가 36만이 넘는다</p>
                <ul class="list-unstyled">
                	<li><i class="fa fa-user color-green"></i> 김대표</li>
                	<li><i class="fa fa-calendar color-green"></i> 2013창립</li>
                	<li><i class="fa fa-tags color-green"></i> 실내 인테리어, 외벽</li>
                </ul>
            </div>
            <!-- End Content Info -->
        </div><!--/row-->

        <div class="tag-box tag-box-v2">
            <p>울트라 갭숑 입니다.</p>
        </div>

        <div class="margin-bottom-20 clearfix"></div>

        <!-- Recent Works -->
        <div class="owl-carousel-v1 owl-work-v1 margin-bottom-40">
            <div class="headline"><h2 class="pull-left">Recent Works</h2>
                <div class="owl-navigation">
                    <div class="customNavigation">
                        <a class="owl-btn prev-v2"><i class="fa fa-angle-left"></i></a>
                        <a class="owl-btn next-v2"><i class="fa fa-angle-right"></i></a>
                    </div>
                </div><!--/navigation-->
            </div>

            <div class="owl-recent-works-v1">
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img1.jpg" alt="">
                        </em>
                        <span>
                            <strong>Happy New Year</strong>
                            <i>Anim pariatur cliche reprehenderit</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img2.jpg" alt="">
                        </em>
                        <span>
                            <strong>Award Winning Agency</strong>
                            <i>Responsive Bootstrap Template</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img3.jpg" alt="">
                        </em>
                        <span>
                            <strong>Wolf Moon Officia</strong>
                            <i>Pariatur prehe cliche reprehrit</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img4.jpg" alt="">
                        </em>
                        <span>
                            <strong>Food Truck Quinoa Nesciunt</strong>
                            <i>Craft labore wes anderson cred</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img5.jpg" alt="">
                        </em>
                        <span>
                            <strong>Happy New Year</strong>
                            <i>Anim pariatur cliche reprehenderit</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img1.jpg" alt="">
                        </em>
                        <span>
                            <strong>Happy New Year</strong>
                            <i>Anim pariatur cliche reprehenderit</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img2.jpg" alt="">
                        </em>
                        <span>
                            <strong>Award Winning Agency</strong>
                            <i>Responsive Bootstrap Template</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img3.jpg" alt="">
                        </em>
                        <span>
                            <strong>Wolf Moon Officia</strong>
                            <i>Pariatur prehe cliche reprehrit</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img4.jpg" alt="">
                        </em>
                        <span>
                            <strong>Food Truck Quinoa Nesciunt</strong>
                            <i>Craft labore wes anderson cred</i>
                        </span>
                    </a>
                </div>
                <div class="item">
                    <a href="#">
                        <em class="overflow-hidden">
                            <img class="img-responsive" src="/assets/img/main/img5.jpg" alt="">
                        </em>
                        <span>
                            <strong>Happy New Year</strong>
                            <i>Anim pariatur cliche reprehenderit</i>
                        </span>
                    </a>
                </div>
            </div>
        </div>
        <!-- End Recent Works -->
    </div><!--/container-->
    <!--=== End Content Part ===-->

<script type="text/javascript" src="/assets/plugins/owl-carousel/owl-carousel/owl.carousel.js"></script>
<script type="text/javascript" src="/assets/js/plugins/owl-recent-works.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
    OwlRecentWorks.initOwlRecentWorksV1();
});
</script>

<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>