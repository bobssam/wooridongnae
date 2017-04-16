<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="context">
<fmt:message key="root" var="root" />

<c:import url="../global/header.jsp" charEncoding="UTF-8">
	<c:param name="title" value="협력업체" />
</c:import>
<style type="text/css">

	.community img{
		margin-left: -1px;
		margin-bottom: -1px;
	}
	.headline .btn {
		float:right;
	}
	.cate {
		font-size: 11px;
		color: #666;
	}
	th {
		text-align: center;
	}
	thead tr {
		background: #f1f1f1;
	}
	.work_cate label {
		width: 150px;
	}

	.search_box {
		border: 2px solid #ccc;
		padding:10px;
	}
	hr {
		margin: 5px 0;
	}

	table.info th {
		text-align: left;
	}
	table.info td {
		font-size: 12px;
		padding: 2px 0;
		color: #666;
	}

	table.table .title{
		font-size: 16px;
		font-weight: bold;
	}
	table.table .info{
		float: right;
		color:#999;
		font-size: 11px;
	}
	table.table.reply .name{
		font-weight: bold;
	}
	textarea[name=content] {
		width:100%;
	}
	.table.reply button.delete{
		float:right;
	}

	.company_inner img {
		max-width: 100%;
		height: auto;
	}
	.work_cate label {
		font-size:11px;
		font-weight: normal;
	}
	.work_cate label input{
		vertical-align: bottom;
	}
	#work_pics {
		height: 180px;
		width: 100%;
		position: absolute;
		left: 0;
		top: 0;
	}
	#work_pics .work_pic_box {
		position: absolute;
		width: 100%;
	}
	#work_pics .work_pic_image {
		max-height:180px;
	}
	#work_pic_container {
		height: 180px;
		width: 100%;
		overflow: hidden;
		position: relative;
	}

	.da-arrows{
		-moz-user-select: none;
		-webkit-user-select: none;
	}
	.da-arrows span{
		background: transparent url(${root}/assets/img/arrows.png) no-repeat;
		position: absolute;
		top: 50%;
		height: 50px;
		width: 49px;
		margin-top: -25px;
		cursor: pointer;
		z-index: 10;
		opacity: 0.8;
		-webkit-transition: opacity 0.4s ease-in-out 0.2s;
		-moz-transition: opacity 0.4s ease-in-out 0.2s;
		-o-transition: opacity 0.4s ease-in-out 0.2s;
		-ms-transition: opacity 0.4s ease-in-out 0.2s;
		transition: opacity 0.4s ease-in-out 0.2s;
	}
	#work_pic_container:hover .da-arrows span{
		opacity: 0.8;
	}
	#work_pic_container:hover .da-arrows span:hover{
		opacity: 1;
	}
	.da-arrows span:after{
		content: '';
		position: absolute;
		width: 49px;
		height: 49px;
	}
	.da-arrows span.da-arrows-prev{
		left: 12px;
		background-position:0 -68px;
	}
	.da-arrows span.da-arrows-next{
		right: 15px;
		background-position:0 1px;
	}

	.btn_more {
		display: none;
	}


	@media (max-width: 768px) {
		#work_pics {
			height: auto;
			position: initial;
		}
		#work_pic_container {
			height: auto;
			width: auto;
		}
		#work_pics .work_pic_image {
			max-height: inherit;
		}
		#work_pics .work_pic_box {
			position: initial;
		}
		.da-arrows {
			display: none;
		}
		.btn_more {
			display: block;
		}
	}

</style>
<script type="text/javascript" src="${root}/assets/plugins/se/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="${root}/assets/plugins/fileupload/js/jquery.fileupload.js" charset="utf-8"></script>
<script type="text/javascript" src="${root}/assets/js/workpics.js"></script>


    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">협력업체</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li class="active"><a href="">협력업체</a></li>
            </ul>
        </div>
    </div>



    <div class="container content col-sm-offset-2">

		<!------------------------------------------
					리스트 페이지
		-------------------------------------------->
    	<div class="body list">

			<div class="search_box">
				<form id="search_form">
					<table>
					<tr>
						<td width="50">업종</td>
						<td class="work_cate"></td>
					</table>

					<hr style="clear: both;">
					<div>
						지역
						<select name="bigAreaNo"></select> 시(도)
						<select name="smallAreaNo"><option value="">전체</option></select> 구(군)
						<button type="submit">검색</button>
					</div>
				</form>
			</div>

			<c:if test="${sessionScope.memberVO != null && sessionScope.memberVO.categoryNo2 == 4}">
			<h3>내 업체 정보</h3>
			<div class="row">
				<table class="table table-hover table-striped" id="my_table_list">
				<thead>
				<tr>
					<th width="120">사진</th>
					<th>업체명</th>
					<th>업종</th>
					<th>소개</th>
					<th>회원혜택</th>
					<th>지역</th>
					<th>조회수</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
				</table>

			</div>
			</c:if>


			<h3>등록된 협력 업체</h3>
			<div class="row">
				<table class="table table-hover table-striped" id="table_list">
				<thead>
				<tr>
					<th width="120">사진</th>
					<th>업체명</th>
					<th>업종</th>
					<th>소개</th>
					<th>할인혜택</th>
					<th>지역</th>
					<th>조회수</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
				</table>

				<div class="row text-center">
			    	<ul class="pagination">
			        </ul>
		        </div>

			</div>
		</div>

		<!------------------------------------------
					상세 페이지
		-------------------------------------------->
    	<div class="body detail" style="display: none;">

			<div class="headline"><h2 data-name="title">..</h2></div>

			<div class="row">
				<div class="col-md-8"><img alt="" class="img-responsive center-block main"></div>
				<div class="col-md-4">
	            	<!-- Contacts -->
	                <div class="headline"><h3 data-name="corpName"></h3></div>
	                <table class="info">
	                	<tr>
	                		<th width="90">업종</th>
	                		<td data-name="categoryNo3Name" data-formatter="commaJoin"></td>
                		</tr>
	                	<tr>
	                		<th>취급품목</th>
	                		<td data-name="cropGoods"></td>
                		</tr>
	                	<tr>
	                		<th>대표자</th>
	                		<td data-name="represent"></td>
                		</tr>
	                	<tr>
	                		<th>주소</th>
	                		<td data-name="address"></td>
                		</tr>
	                	<tr>
	                		<th>전화번호</th>
	                		<td>
	                			<span class="glyphicon glyphicon-earphone"></span> <span data-name="phone"></span>
	                			<span aria-hidden="true" class="icon-screen-smartphone"></span> <span data-name="mobile"></span>
	                		</td>
                		</tr>
	                	<tr>
	                		<th>홈페이지</th>
	                		<td><a href="#" data-name="homepage"></a></td>
                		</tr>
	                	<tr>
	                		<th>회원혜택</th>
	                		<td><span data-name="dc"></span></td>
                		</tr>
	                </table>

	                <div class="headline"><h3>업무시간</h3></div>
	                <ul class="list-unstyled margin-bottom-30">
	                	<li data-name="openingHour"></li>
	                </ul>
	            </div>
			</div>

			<div class="headline"><h2>업체 소개</h2></div>
			<div class="tag-box tag-box-v3 form-page">
				<div class="row">
					<div class="col-md-12 company_inner" data-name="content"></div>
				</div>
			</div>

<!--
			<div class="headline"><h2>할인/이벤트</h2><button class="btn btn-xs rounded btn-success" type="button"><span class="glyphicon glyphicon-plus"></span> 이벤트 추가</button></div>
			<div class="row">
				<div class="col-md-12">
				</div>
			</div>
 -->
			<div class="headline"><h2>작업 사진 (<span class="work_pics_count">0</span>)</h2>
				<button class="btn btn-xs rounded btn-success add_work_pics" type="button"><span class="glyphicon glyphicon-th-plus"></span> 등록하기</button>
				<!-- <button class="btn btn-xs rounded btn-success work_pics" type="button"><span class="glyphicon glyphicon-th-list"></span> 전체보기</button>  -->
			</div>
			<!-- 작업 사진 리스트 -->
			<div class="row" id="work_pic_container">
				<div id="work_pics">
				</div>
				<div id="work_pics_temp">
				</div>
				<div class="da-arrows">
                	<span class="da-arrows-prev"></span>
                	<span class="da-arrows-next"></span>
            	</div>
            	<div class="text-center btn_more">
					<button class="btn btn-u more" type="button">작업사진 더보기</button>
            	</div>
			</div>


			<div class="text-center" style="margin-top: 20px;">
				<button class="btn btn-u modify" type="button">수정</button>
				<button class="btn btn-u list" type="button">리스트</button>
			</div>



			<table class="table reply table-hover table-bordered table-striped" id="table_reply" style="margin-top: 20px;">
				<thead>
					<tr>
						<th>의견쓰기</th>
					</tr>
				</thead>
				<tbody></tbody>
				<tfoot>
					<tr>
						<td>
							<form id="reply_form">
								<textarea name="content" class="form-control" placeholder="댓글 작성을 위해선 로그인을 해주세요."></textarea>
								<button type="submit">등록</button>
								<span id="reply_max">0/2000</span>
							</form>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>


		<!------------------------------------------
					수정 페이지
		-------------------------------------------->
    	<div class="body modify" style="display: none;">

			<form id="write_form">
				<input type="hidden" name="memberNo" value="0">
				<input type="hidden" name="boardType" value="crop">

				<table class="table table-hover table-bordered table-striped">
				<tbody>
		   		    <tr>
		   		       	<th>업체 소개 문구</th>
				        <td colspan="3">
				        	<input type="text" name="title" size="80" maxlength="50"> <span id="title_max">0/50</span> (최대 한글 25자)
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>대표자</th>
				        <td>
				        	<input type="text" name="represent" size="20" maxlength="20" >
				        </td>
		   		       	<th>회원혜택</th>
				        <td>
				        	<input type="text" name="dc" size="20" maxlength="20" >
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>주소</th>
				        <td colspan="3">
				        	<input type="text" name="address" size="40"> <span id="address_max">0/100</span> (최대 한글 50자)
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>전화번호</th>
				        <td>
				        	<input type="text" name="phone" size="15" maxlength="15">
				        </td>
		   		       	<th>핸드폰</th>
				        <td>
				        	<input type="text" name="mobile" size="15" maxlength="15">
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>영업시간</th>
				        <td>
				        	<input type="text" name="openingHour" size="20" maxlength="20">
				        </td>
		   		       	<th>취급 품목</th>
				        <td>
				        	<input type="text" name="cropGoods" size="50" maxlength="50">
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>홈페이지</th>
				        <td colspan="3">
				        	<input type="text" name="homepage" size="50" maxlength="50">
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th>대표이미지</th>
				        <td colspan="3">
				        	<input type="hidden" name="fileNo" value="0">

							<p class="guide">가로 600px 세로 500px 이하의 이미지를 올려주세요 </p>

							<div class="file upload">
								<input type="file" name="file">
							</div>
							<div class="file uploaded"> <button type="button" class="del_img">이미지삭제</button><br/>
								<img class="img-responsive center-block preview">
							</div>
				        </td>
				    </tr>
		   		    <tr>
		   		       	<th colspan="4">업체 소개글</th>
				    </tr>
		   		    <tr>
				        <td colspan="4">
				        	<textarea name="content" style="display: none;" rows="40"></textarea>
				        </td>
				    </tr>

				</tbody>
				</table>
				<div class="text-center" style="margin-top: 20px;">
					<button class="btn btn-u modifyok" type="submit">수정완료</button>
					<button class="btn btn-u cancel" type="button">취소</button>
				</div>
			</form>
		</div>



		<!------------------------------------------
					작업사진 페이지
		-------------------------------------------->
    	<div class="body workpic" style="display: none;">

			<div class="headline"><h2 data-name="title">..</h2></div>

			<div class="row">
				<div class="col-md-8"><img alt="" class="img-responsive center-block main"></div>
				<div class="col-md-4">
	            	<!-- Contacts -->
	                <div class="headline"><h3 data-name="corpName"></h3></div>
	                <table class="info">
	                	<tr>
	                		<th width="90">업종</th>
	                		<td data-name="categoryNo3Name" data-formatter="commaJoin"></td>
                		</tr>
	                	<tr>
	                		<th>취급품목</th>
	                		<td data-name="cropGoods"></td>
                		</tr>
	                	<tr>
	                		<th>대표자</th>
	                		<td data-name="represent"></td>
                		</tr>
	                	<tr>
	                		<th>주소</th>
	                		<td data-name="address"></td>
                		</tr>
	                	<tr>
	                		<th>전화번호</th>
	                		<td>
	                			<span class="glyphicon glyphicon-earphone"></span> <span data-name="phone"></span>
	                			<span aria-hidden="true" class="icon-screen-smartphone"></span> <span data-name="mobile"></span>
	                		</td>
                		</tr>
	                	<tr>
	                		<th>홈페이지</th>
	                		<td><a href="#" data-name="homepage"></a></td>
                		</tr>
	                	<tr>
	                		<th>회원혜택</th>
	                		<td><span data-name="dc"></span></td>
                		</tr>
	                </table>

	                <div class="headline"><h3>업무시간</h3></div>
	                <ul class="list-unstyled margin-bottom-30">
	                	<li data-name="openingHour"></li>
	                </ul>
	            </div>
			</div>

			<div class="headline"><h2>작업 사진</h2></div>
			<div class="tag-box tag-box-v3 form-page">
				<div class="row">
					<div class="col-md-12 company_inner" data-name="content"></div>
				</div>
			</div>

			<div class="text-center" style="margin-top: 20px;">
				<button class="btn btn-u modify" type="button">수정</button>
				<button class="btn btn-u detail" type="button">돌아가기</button>
			</div>

			<table class="table reply table-hover table-bordered table-striped" id="table_reply2" style="margin-top: 20px;">
				<thead>
					<tr>
						<th>의견쓰기</th>
					</tr>
				</thead>
				<tbody></tbody>
				<tfoot>
					<tr>
						<td>
							<form id="reply_form2">
								<textarea name="content" class="form-control" placeholder="댓글 작성을 위해선 로그인을 해주세요."></textarea>
								<button type="submit">등록</button>
								<span id="reply_max2">0/2000</span>
							</form>
						</td>
					</tr>
				</tfoot>
			</table>

    	</div>

		<!------------------------------------------
					작업 사진 수정 페이지
		-------------------------------------------->
    	<div class="body modify_workpic" style="display: none;">

    		<div class="headline"><h2 data-name="title">..</h2></div>

			<div class="row">
				<div class="col-md-8"><img alt="" class="img-responsive center-block main"></div>
				<div class="col-md-4">
	            	<!-- Contacts -->
	                <div class="headline"><h3 data-name="corpName"></h3></div>
	                <table class="info">
	                	<tr>
	                		<th width="90">업종</th>
	                		<td data-name="categoryNo3Name" data-formatter="commaJoin"></td>
                		</tr>
	                	<tr>
	                		<th>취급품목</th>
	                		<td data-name="cropGoods"></td>
                		</tr>
	                	<tr>
	                		<th>대표자</th>
	                		<td data-name="represent"></td>
                		</tr>
	                	<tr>
	                		<th>주소</th>
	                		<td data-name="address"></td>
                		</tr>
	                	<tr>
	                		<th>전화번호</th>
	                		<td>
	                			<span class="glyphicon glyphicon-earphone"></span> <span data-name="phone"></span>
	                			<span aria-hidden="true" class="icon-screen-smartphone"></span> <span data-name="mobile"></span>
	                		</td>
                		</tr>
	                	<tr>
	                		<th>홈페이지</th>
	                		<td><a href="#" data-name="homepage"></a></td>
                		</tr>
	                	<tr>
	                		<th>회원혜택</th>
	                		<td><span data-name="dc"></span></td>
                		</tr>
	                </table>

	                <div class="headline"><h3>업무시간</h3></div>
	                <ul class="list-unstyled margin-bottom-30">
	                	<li data-name="openingHour"></li>
	                </ul>
	            </div>
			</div>
			<form id="write_form2">
				<input type="hidden" name="memberNo" value="0">
				<input type="hidden" name="boardType" value="crop">

				<div class="headline"><h2>작업 사진</h2></div>
				<div class="tag-box tag-box-v3 form-page">
					<div class="row">
						<div class="col-md-12 company_inner">
				        	<textarea name="content" rows="40"></textarea>
						</div>
					</div>
				</div>


				<div class="text-center" style="margin-top: 20px;">
					<button class="btn btn-u modifyok" type="submit">수정완료</button>
					<button class="btn btn-u cancel" type="button">취소</button>
				</div>
			</form>
		</div>

	</div>



<script type="text/html" id="tmpl_list">
<tr>
	<td rowspan="2"><img src="${root}/api/file/download/\${fileNo}" alt="" width="116" height="80"></td>
	<td>\${corpName}</td>
	<td class="cate text-center">{{html categoryNo3Name.join('<br>')}}</td>
	<td><a href="#">\${title}</a></td>
	<td class="text-center">\${dc}</td>
	<td class="text-center">\${smallAreaName}</td>
	<td class="text-center">\${hits}</td>
</tr>
<tr>
	<td><b>전화</b> \${phone}</td>
	<td><b>주소</b> \${bigAreaName} \${smallAreaName}</td>
	<td colspan="4"><b>url</b> <a href="\${homepage}">\${homepage}</a></td>
</tr>
</script>
<script type="text/html" id="empty_list">
<tr>
	<td colspan="7" class="text-center">조건에 맞는 업체가 없습니다.</td>
</tr>
</script>
<script type="text/html" id="tmpl_reply">
<tr>
	<td>
		{{if isMine == 1}}<button class="btn btn-xs delete" type="button" >삭제</button>{{/if}}
   		<span class="name">\${name}</span>
   		\${dateConv(regDate)}
   		<p>
   			\${content}
   		</p>
	</td>
</tr>
</script>
<script type="text/html" id="tmpl_work_pic">
<div class="col-md-3">
	<img alt="" class="img-responsive center-block  margin-bottom-10 work_pic_image" src="${root}/api/file/download/\${fileNo}">
</div>
</script>

</fmt:bundle>
<c:import url="../global/footer.jsp" charEncoding="UTF-8"></c:import>