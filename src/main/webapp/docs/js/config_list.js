/**
 *
 */

var $lastEditTr = null;
/*********************************
		컨피그 리스트 한줄에 대한 이벤트 처리
*********************************/
function addEventLineConf( $tr ) {
	/***********************
			리스트 상태
	***********************/
	// 컨피그 수정
	$tr.find('.edit_conf').on('click', function(){

		var datas = $tr.data('data');
		var userList = datas.notificateGroup.userList;
		// 팝업에 데이터 세팅
		$('#info_list').formData(datas);
		// 관제 그룹 유저 세팅
		$tarTbody = $('.noti_group_list .info_list tbody').empty();
		for( var i=0,len=userList.length;i<len;i++ ) {
			var $newEl2 = $('#tmp_user_list').mustache(userList[i]).data('data', userList[i]);
			addEventLineInfo( $newEl2 );
			$tarTbody.append( $newEl2 );
		}
		$('#message').val(datas.notificateGroup.message);

		// 수정 팝업을 띄워줌
		$('#info_list').dialog({
			title : datas.service +" 수정",
			width: 700,
			height: 520,
			modal: true,
			buttons: {
				"도움말": function() {
			    	$('#help_popup').dialog({
			    		title : '설정 요령',
						width: 900,
						height: 700,
						modal: true,
			    	});
			    	$('#help_popup').load( 'html/help.html' );
				},
		       	"저장": function() {

		       		// 업데이트용 데이터 제작
		       		var that = this;
		       		var newDatas =  $('.name_list,.info_list').formData();
		       		var notiUsers = [];
		       		var message = $('#message').val();

		       		// 관제 유저 정보 수집
		       		$('.info_list tr').each(function() {
		       			if( $(this).data('data') !== undefined )
		       				notiUsers.push( $(this).data('data') );
		       		});

		       		newDatas.notificateGroup = {
		       			method : newDatas.noti_type,
		       			userList : notiUsers,
		       			message : message
		       		};

		       		var sendData = {
		       			service : newDatas.service,
		       			key : newDatas.key,
		       			configData : JSON.stringify(newDatas)
		       		};

		    		// 데이터 갱신 요청
		    		$.ajax({
		    			url : '/api/config/update',
		    			data : sendData,
		    			dataType : 'json',
		    			success : function( data ) {
		    				if(data.result == 'ok' ) {
		    					$tr.data('data', data);

		    					var $newEl = $("#tmp_config_list").mustache(newDatas).data('data',newDatas);
		    					addEventLineConf( $newEl );
		    					$tr.replaceWith( $newEl );
		    					// 관제 그룹에 유저 추가
		    					toastr.success('저장되었습니다.');
		    			    	$(that).dialog("close");

		    				}else{
		    					toastr.error('저장에 실패 하였습니다.<br/>'+data.reason);
		    				}
		    			}
		    		});

		       	},
				"취소": function() {
			    	$(this).dialog("close");
				}
			}
		});

	});

	// 컨피그 삭제
	$tr.find('.del_conf').on('click', function(){
		if( confirm( '삭제 하시겠습니까?' ) ) {

			$.ajax({
				url : '/api/config/del',
				data : {service:$tr.data('data').service, key:$tr.data('data').key},
				dataType : 'json',
				success : function( data ) {
					if( data.result == 'ok' ) {
						$tr.remove();
    					toastr.success('삭제 되었습니다.');
					} else {
						toastr.error('삭제에 실패 하였습니다.<br/>'+data.reason);
					}
				}
			});

		}
	});

	/***********************
			신규 등록 확인
	***********************/
	// 글 신규 등록 및 수정(컨피그)
	$tr.find('.create_conf').on('click', function(){

		// 저장을 합시다.
		if($('#service').val() == "" ) {
			alert('서비스키를 입력하시오.');
			$('#service').focus();
			return false;
		}
		if( $('#key').val() == ""){
			alert('키를 입력하시오.');
			$('#key').focus();
			return false;
		}

		/* 데이터 샘플

			{"type":"log","retry":3,"waitTime":1,
				"notificateGroup":
					{"userList":[{"phoneNumber":"22","name":"222"}],"method":"sms"},"service":"kakao","key":"gw","comment":"1413"
			}
		*/
		var datas = $('.config_list').formData();
		datas.notificateGroup = {userList:[]};

		// ajax 통신을 하자
		$.ajax({
			url : '/api/config/insert',
			data : {service:datas.service, key:datas.key, configData:JSON.stringify(datas)},
			dataType : 'json',
			success : function(data) {
				if( data.result == 'ok' ) {
					// JQuery 체인을 이용
					$tr.data('data', datas);

					// 입력된 UI로 갱신
					var $output = $("#tmp_config_list").mustache(datas).data('data', datas);
					addEventLineConf($output);
					$tr.replaceWith($output);
					toastr.success('저장되었습니다.');
				} else {
					toastr.error('실패 하였습니다.<br/>'+data.reason);
				}
			}
		});
	});

	// 추가탭 삭제(컨피그)
	$tr.find('.cancel_conf').on('click', function(){
		$tr.remove();
	});
}

// 관제 추가
$('#config_list .add_conf').on('click', function(){

	var $newEl = $("#tmp_config_new").mustache({});
	addEventLineConf( $newEl );
	$('table.config_list tbody').append( $newEl );
	$lastEditTr = $newEl;
});


// 관제 유저 추가
$('#info_list .add_info').on('click', function(){

	var $newEl = $("#tmp_user_new").mustache({});
	addEventLineInfo( $newEl );
	$('table.info_list tbody').append( $newEl );

});

//테스트 메세지 발송
$('#info_list .test_send').on('click', function(){

	var datas = $('#info_list').formData();

	$.ajax({
		url : '/api/config/send',
		data : {service:datas.service, key:datas.key},
		dataType : 'json',
		success : function(data) {
			if( data.result == 'ok' ) {
				toastr.success('발송 되었습니다.');
			} else {
				toastr.error('발송 실패 하였습니다.<br/>'+data.reason);
			}
		}
	});

});



function addEventLineInfo( $tr ) {
	// 관제 수정
	$tr.find('.edit_info').on('click', function(){
		var datas = $tr.data('data');
		$('.info_list').formData( datas );

		var $newEl = $("#tmp_user_edit").mustache($tr.data('data'));
		addEventLineInfo( $newEl );
		console.log($tr);
		$tr.replaceWith( $newEl );

	});

	// 관제 삭제
	$tr.find('.del_info').on('click', function(){
		if( confirm( '삭제 하시겠습니까?' ) ) {
			$tr.remove();
		}
	});

	// 글 신규 등록 및 수정(관제)
	$tr.find('.create_info').on('click', function(){
		var datas = {};
		$tr.find('input').each(function(){
			datas[ $(this).attr('name') ] = $(this).val();
		});
		$tr.data('data', datas);

		var $output = $("#tmp_user_list").mustache(datas).data('data', datas);
		addEventLineInfo($output);
		$tr.replaceWith($output);

	});

	// 추가탭 삭제(관제)
	$tr.find('.cancel_info').on('click', function(){
		$tr.remove();
	});
}


function init() {
	// 최초 시작점
	// 컨피그 리스트를 가져온다.
	$.ajax({
		url : "/api/config/list",
		dataType: 'json',
		type : 'GET',
		success : function( datas ) {
			for( var key in datas ) {
				var $output = $("#tmp_config_list").mustache(datas[key]).data('data', datas[key]);
				addEventLineConf($output);
				$('table.config_list tbody').append($output);
			}
		}
	});
}

$(function(){
	init();
});
