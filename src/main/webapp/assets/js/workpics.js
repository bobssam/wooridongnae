/**
 *
 */


function commaJoin( datas ) {
	if( !datas ) return '';
	return datas.join(",");
}

$(function(){

	var selectedCrop = null;
	var selectedWorkPic = null;
	$('.work_cate').checkboxApi({
		api: root+'/api/category/',
		value: 'categoryName',
		key: 'categoryNo',
		name : 'categoryNo3',
		async: false,
		filter : {'parentCategoryNo': 4},
	}, function( datas ){
	});

	$('[name=categoryNo3]').setMaxCheck(3);

	function loadBigArea() {

		$('[name=bigAreaNo]').empty().optionsApi({
			api: root+'/api/bigarea/',
			value: 'bigAreaName',
			key: 'bigAreaNo',
			async: false,
		}, function( datas ){

		});
	}
	function loadSmallArea( bigAreaNo ) {

		$('[name=smallAreaNo]').empty().optionsApi({
			api: root+'/api/smallarea/',
			value: 'smallAreaName',
			key: 'smallAreaNo',
			async: false,
			filter: {bigAreaNo:bigAreaNo}
		}, function( datas ){

		});
	}

	loadBigArea();
	$('[name=bigAreaNo]').on('change', function(){
		loadSmallArea($(this).val());
	});


	$('#search_form').on('submit', function(){
		var datas = $(this).formData();
		board.setFilter(datas);
		board.load();
		return false;
	});

	/****************************************
			모드 컨트롤
	****************************************/
	var firstSetting = true;
	var firstSettingWorkpic = true;
	var writeFormEditorIdx = 0;
	var write2FormEditorIdx = 0;
	var oEditors = [];
	var lastMode = 'list';
	var prevMode = 'list';

	function mode( modeName ) {
		$('.container .body').hide();
		$('.container .body.'+modeName).show();

		if( modeName == 'modify' && firstSetting ) {
			firstSetting = false;
			writeFormEditorIdx = oEditors.length;
			// 스마트 에디터 세팅
			var editor = nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: $('#write_form [name=content]')[0],
				sSkinURI: root+"/assets/plugins/se/SmartEditor2Skin.html",
				htParams : {
					bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					fOnBeforeUnload : function(){
						alert("완료!");
					}
				}, //boolean
				fCreator: "createSEditor2"
			});
		}

		if( modeName == 'modify_workpic' && firstSettingWorkpic ) {
			firstSettingWorkpic = false;
			write2FormEditorIdx = oEditors.length;
			// 스마트 에디터 세팅
			var editor = nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: $('#write_form2 [name=content]')[0],
				sSkinURI: root+"/assets/plugins/se/SmartEditor2Skin.html",
				htParams : {
					bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					fOnBeforeUnload : function(){
						alert("완료!");
					}
				}, //boolean
				fCreator: "createSEditor2"
			});
		}

		prevMode = lastMode;
		lastMode = modeName;
		$('body').scrollTop(0);

	}


	$('.list').on('click', function(){
		mode('list');
		resetWorkPic();
	});

	/********************************************************
						협력 업체 리스트
	********************************************************/
	var isFirst = true;
	var tbody = $('#table_list tbody');
	var board = tbody.board({
		page: 1,
		len: 10,
		api: root+'/api/crop/',
		total : $('#total'),
		template: {list: $('#tmpl_list'), empty: $('#empty_list')},
		paging: $('.pagination'),
		onRender : function( $tar, datas ) {

			$tar.find('a').off('click').on('click', function(){
				showDeatail(datas);
				return false;
			});
		}
	});
	board.load();

	function showDeatail( datas ) {
		$.ajax({
			url : root+'/api/crop/view',
			data: {memberNo: datas.memberNo },
			success : function(datas) {

				// 상세 페이지 설정
				if( datas.status == "ok" ) {
					selectedCrop = datas.data;
					mode('detail');
					var $tar = $('.container .body.detail');
					$('.container .body.detail').formData( datas.data );
					$('.container .body.detail textarea').val('');
					$('.container .body.detail img.main').attr('src', root+'/api/file/download/'+selectedCrop.fileNo);


					$('.add_work_pics').off().on('click', function(){
						showModifyWorkpic(selectedCrop, true);
					});

					replyBoard.setFilter({cropNo:selectedCrop.memberNo});
					replyBoard.load();

					workPicsBoard.setFilter({cropNo:selectedCrop.memberNo});
					resetWorkPic();
					workPicsBoard.load(workPicPage);

					// 수정 버튼 활성화
					$tar.find('button.modify').hide();
					$tar.find('button.add_work_pics').hide();
					if( selectedCrop.isMine == 1 ) {
						$tar.find('button.modify').show();
						$tar.find('button.add_work_pics').show();
					}


					$tar.find('button.modify').off('click').on('click', function(){
						mode('modify');
						showModify( selectedCrop );
					});

					$tar.find('button.cancel').off('click').on('click', function(){
						mode('detail');
					});

				}
			}
		});
	}


	/****************************************
				수정 세팅
	****************************************/
	function showModify( datas ) {
		var $tar = $('.container .body.modify');
		$tar.formData( datas );
		if( oEditors[writeFormEditorIdx] )
			oEditors[writeFormEditorIdx].exec("LOAD_CONTENTS_FIELD", [datas.content]);

		if( datas.fileNo ) {
			fileModeChange('uploaded');
			$('img.preview').attr('src', root+'/api/file/download/'+datas.fileNo);
		}

		$tar.find('button.cancel').off('click').on('click', function(){
			mode('detail');
		});

		$('#write_form').off('submit').on('submit', function(){
			oEditors[writeFormEditorIdx].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			var formData = $(this).formData();
			formData.where = JSON.stringify({memberNo:datas.memberNo});

			$.ajax({
				url : root+'/api/crop/modify',
				data: formData,
				type:'POST',
				dataType: 'json',
				success : function(res) {
					if( res.status == 'ok' ) {
						toastr.success('업체 정보 갱신 성공');

						// view 갱신
						// list 갱신 필요
						showDeatail( {memberNo:datas.memberNo} );
						board.reload();

					} else {
						toastr.error('업체 정보 갱신 실패\n'+ res.reason );
					}
				},
				fail : function(){
					toastr.error('업체 정보 갱신 실패');
				}
			});

			return false;
		});
	}

	var prevFile = null;
	$('input[name=file]').fileupload({
        url: root+'/api/file/upload/',
        autoUpload : true,
        imageMaxWidth: 300,
        imageMaxHeight: 200,

    }).on('fileuploadadd', function(e, data){

        var acceptFileTypes = /^image\/(gif|jpe?g|png)$/i;
        if( !data.originalFiles[0]['type'] || !acceptFileTypes.test(data.originalFiles[0]['type'])) {
            toastr.error('이미지 파일만 올릴 수 있습니다.');
            return false;
        }
        if(data.originalFiles[0]['size'] && data.originalFiles[0]['size'] > 1000000) {
            toastr.error('1메가 이하의 이미지를 올려주세요.');
            return false;
        }

    }).on('fileuploaddone', function(e, data){
    	if( data.result.status == 'ok' ) {
    		var fileNo = data.result.data.fileNo;
    		$('[name=fileNo]').val(fileNo);
    		fileModeChange('uploaded');
    		$('img.preview').attr('src', root+'/api/file/download/'+fileNo);
    	}
    });

	function fileModeChange( mode ) {
		$('.file').hide();
		$('.file.'+mode).show();
	}
	fileModeChange('upload');

	$('.del_img').on('click', function(){
		$('[name=fileNo]').val(0);
		fileModeChange('upload');
	});


	/****************************************
				댓글 세팅
	****************************************/
	$('#reply_form textarea').getByteLength( {
		target: $('#reply_max'),
		max: 2000
	});

	$('#reply_form').on('submit', function(){
		var datas =  $(this).formData();
		datas.cropNo = selectedCrop.memberNo;

		$.ajax({
			url: root+'/api/cropreply/add',
			data: datas,
			dataType: 'json',
			method: 'POST',
			success: function(data){

				if( data.status == 'ok' ) {

					replyBoard.reload();
					toastr.success("댓글이 등록되었습니다.");
					$('#reply_form').formData({}, true);
				} else {

				}
			},
			error: function(data) {
				if( data.status == 403 )
					toastr.error("로그인 이후에 이용해 주시기 바랍니다.");
			}
		})

		return false;
	});


	var $tbodyReply = $('#table_reply tbody');
	var replyBoard = $tbodyReply.board({
		page: 1,
		len: 10,
		api: root+'/api/cropreply/',
		template: $('#tmpl_reply'),
		onRender : function( $tar, datas ) {
			$tar.find('button.delete').on('click', function(){

				if( confirm('정말로 삭제 하시겠습니까?') ) {
					$.ajax({
						url : root+'/api/cropreply/delete',
						data: {replyNo: datas.replyNo},
						success : function(data) {

							if( data.status == 'ok' ) {
								toastr.success("댓글이 삭제 되었습니다.");
								$tar.remove();
							} else {
								toastr.error("댓글 삭제에 실패 하였습니다", data.reason);
							}
						}
					});
				}

			});
		}
	});

	/****************************************
				작업 사진 세팅
	****************************************/
	// temp에 담았다가 모두 렌더링 되면, 내용을 꺼내서 4개 짜리 상자에 담고 #work_pics에 넣는다.
	var $workPics = $('#work_pics');
	var workPicPage = 1;
	var workPicLoaded = 1;
	var workPicsBoard = $('#work_pics_temp').board({
		page: 1,
		len: 4,
		api: root+'/api/cropworkpic/',
		template: $('#tmpl_work_pic'),
		onRender : function( $tar, datas ) {
			$tar.find('img').on('click', function(){
				showWorkPic( {cropWorkPicNo:datas.cropWorkPicNo} );
			});
		},
		onRendered : function( datas ) {
			var $box = $('<div>', {'class':'work_pic_box'}).css('left', ((workPicPage-1)*100)+'%');
			$box.append( $('#work_pics_temp').find('.col-md-3') );
			$workPics.append($box);

			$('.work_pics_count').text( datas.total );
			updateNextPrevButton( datas.total );

			$('.da-arrows-next, .more').off().on('click', function(){
				workPicPage ++;
				if( workPicLoaded < workPicPage ) {
					workPicLoaded = workPicPage;
					workPicsBoard.load(workPicPage, true);
				}
				$('#work_pics').animate({'left': ((workPicPage-1)*-100)+'%'}, 600);
				updateNextPrevButton( datas.total );
			});
			$('.da-arrows-prev').off().on('click', function(){
				workPicPage --;
				$('#work_pics').animate({'left': ((workPicPage-1)*-100)+'%'}, 600);
				updateNextPrevButton( datas.total );
			});
		}
	});

	function resetWorkPic() {
		workPicPage = 1;
		workPicLoaded = 1;
		$workPics.empty();
		$workPics.css('left', 0);
	}

	function updateNextPrevButton( total ) {
		var hasNext = false;
		var hasPrev = false;
		var pageTotal = Math.ceil(total/4);
		if( workPicPage < pageTotal ) hasNext = true;
		if( workPicPage > 1 ) hasPrev = true;

		if( hasNext ) $('.da-arrows-next, .more').show();
		else $('.da-arrows-next, .more').hide();

		if( hasPrev ) $('.da-arrows-prev').show();
		else $('.da-arrows-prev').hide();
	}

	/****************************************
				작업 사진 댓글
	****************************************/
	var $tbodyReply2 = $('#table_reply2 tbody');
	var replyBoard2 = $tbodyReply2.board({
		page: 1,
		len: 10,
		api: root+'/api/workpicreply/',
		template: $('#tmpl_reply'),
		onRender : function( $tar, datas ) {
			$tar.find('button.delete').on('click', function(){

				if( confirm('정말로 삭제 하시겠습니까?') ) {
					$.ajax({
						url : root+'/api/workpicreply/delete',
						data: {replyNo: datas.replyNo},
						success : function(data) {

							if( data.status == 'ok' ) {
								toastr.success("댓글이 삭제 되었습니다.");
								$tar.remove();
							} else {
								toastr.error("댓글 삭제에 실패 하였습니다", data.reason);
							}
						}
					});
				}

			});
		}
	});

	$('#reply_form2 textarea').getByteLength( {
		target: $('#reply_max2'),
		max: 2000
	});

	$('#reply_form2').on('submit', function(){
		var datas =  $(this).formData();
		datas.cropNo = selectedCrop.memberNo;
		datas.cropWorkPicNo = selectedWorkPic.cropWorkPicNo;

		$.ajax({
			url: root+'/api/workpicreply/add',
			data: datas,
			dataType: 'json',
			method: 'POST',
			success: function(data){

				if( data.status == 'ok' ) {

					replyBoard2.reload();
					toastr.success("댓글이 등록되었습니다.");
					$('#reply_form2').formData({}, true);
				} else {

				}
			},
			error: function(data) {
				if( data.status == 403 )
					toastr.error("로그인 이후에 이용해 주시기 바랍니다.");
			}
		})

		return false;
	});

	/****************************************
				작업 사진 상세
	****************************************/
	function showWorkPic( where ) {
		$.ajax({
			url : root+'/api/cropworkpic/view',
			data: where,
			success : function(data) {

				if( data.status == 'ok' ) {

					selectedWorkPic = data.data;

					mode('workpic');
					var $tar = $('.container .body.workpic');
					$tar.formData( selectedCrop );
					$tar.find('textarea').val('');
					$tar.find('img.main').attr('src', root+'/api/file/download/'+selectedCrop.fileNo);
					$tar.find('.company_inner').html(data.data.content);

					// 수정 버튼 활성화
					$tar.find('button.modify').hide();
					if( selectedCrop.isMine == 1 ) {
						$tar.find('button.modify').show();
					}

					$tar.find('button.modify').off('click').on('click', function(){
						//mode('modify_workpic');
						showModifyWorkpic( data.data );
						//showModify( selectedCrop );
					});

					$tar.find('button.detail').off('click').on('click', function(){
						mode('detail');
						// 작업 사진 다시 로딩
						//workPicsBoard.load();
					});

					// 댓글 로딩
					replyBoard2.setFilter(where);
					replyBoard2.load();

				}
			}
		});
	}

	/****************************************
			작업 사진 상세 수정
	****************************************/
	function showModifyWorkpic( data, isNew ) {
		mode('modify_workpic');
		var $tar = $('.container .body.modify_workpic');
		$tar.formData( selectedCrop );
		$tar.formData( data );
		if( isNew )
			$tar.formData( {content:''} );
		$tar.find('img.main').attr('src', root+'/api/file/download/'+selectedCrop.fileNo);
		//$tar.find('.company_inner').html(data.content);

		$tar.find('button.cancel').off('click').on('click', function(){
			if( isNew ) mode('detail');
			else mode('workpic');
		});

		if( !isNew && oEditors[write2FormEditorIdx] )
			oEditors[write2FormEditorIdx].exec("LOAD_CONTENTS_FIELD", [data.content]);

		$('#write_form2 .modifyok').text( isNew ? '등록':'수정완료' );

		$('#write_form2').off('submit').on('submit', function(){
			oEditors[write2FormEditorIdx].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			var formData = $(this).formData();
			formData.cropNo = data.memberNo;
			formData.where = JSON.stringify({cropWorkPicNo:data.cropWorkPicNo, cropNo:data.memberNo});

			$.ajax({
				url : root+'/api/cropworkpic/'+(isNew?'add':'modify'),
				data: formData,
				type:'POST',
				dataType: 'json',
				success : function(res) {
					if( res.status == 'ok' ) {
						toastr.success('작업 사진 갱신 성공');
						// TOOD workpic만 갱신하여 주면 됨
						if( isNew ) {
							mode('detail');
						}
						else showWorkPic({cropWorkPicNo:data.cropWorkPicNo});

						resetWorkPic();
						workPicsBoard.load();

					} else {
						toastr.error('작업 사진 갱신 실패\n'+ res.reason );
					}
				},
				fail : function(){
					toastr.error('작업 사진 갱신 실패');
				}
			});

			return false;
		});
	}


	var cropNo = $.getHashParam('cropNo');
	if( cropNo ) {
		showDeatail( {memberNo:cropNo} );
	}



});
