<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="로그인" />
</c:import>

    <link rel="stylesheet" href="${root}/assets/plugins/cube-portfolio/cubeportfolio/css/cubeportfolio.min.css">
    <link rel="stylesheet" href="${root}/assets/plugins/cube-portfolio/cubeportfolio/custom/custom-cubeportfolio.css">

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


    <!--=== Cube-Portfdlio ===-->
    <div class="cube-portfolio container margin-bottom-60">
        <div class="content-xs">
            <div id="filters-container" class="cbp-l-filters-text content-xs">
                <div data-filter="*" class="cbp-filter-item-active cbp-filter-item"> All </div> |
                <div data-filter=".identity" class="cbp-filter-item"> Identity </div> |
                <div data-filter=".web-design" class="cbp-filter-item"> Web Design </div> |
                <div data-filter=".graphic" class="cbp-filter-item"> Graphic </div> |
                <div data-filter=".logos" class="cbp-filter-item"> Logo </div>
            </div><!--/end Filters Container-->
        </div>

        <div id="grid-container" class="cbp-l-grid-agency">
            <div class="cbp-item graphic">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img12.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img12.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 01</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item web-design logos">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img18.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img18.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 02</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item graphic logos">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img7.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img7.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 03</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item web-design graphic">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img4.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img4.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 04</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item identity web-design">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img3.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img3.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 05</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item identity web-design">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img6.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img6.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 06</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item identity web-design">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img6.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img6.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 06</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
            <div class="cbp-item identity web-design">
                <div class="cbp-caption margin-bottom-20">
                    <div class="cbp-caption-defaultWrap">
                        <img src="${root}/assets/img/main/img6.jpg" alt="">
                    </div>
                    <div class="cbp-caption-activeWrap">
                        <div class="cbp-l-caption-alignCenter">
                            <div class="cbp-l-caption-body">
                                <ul class="link-captions no-bottom-space">
                                    <li><a href="${root}/tender/shop"><i class="rounded-x fa fa-link"></i></a></li>
                                    <li><a href="${root}/assets/img/main/img6.jpg" class="cbp-lightbox" data-title="Design Object"><i class="rounded-x fa fa-search"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cbp-title-dark">
                    <div class="cbp-l-grid-agency-title">Design Object 06</div>
                    <div class="cbp-l-grid-agency-desc">Web Design</div>
                </div>
            </div>
        </div><!--/end Grid Container-->
    </div>
    <!--=== End Cube-Portfdlio ===-->

	<script type="text/javascript" src="${root}/assets/plugins/cube-portfolio/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
	<script type="text/javascript" src="${root}/assets/js/plugins/cube-portfolio/cube-portfolio-4.js"></script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>