/**
 *
 */

/******************************
 * 				네비게이션 세팅					  *
 ******************************/

$(function(){

	/********************************************
	 * 				initialize
	 ********************************************/
	var $context = $('.body .context');
	var $menu = $('#menu');
	var $tabs = $('.body>.tabs');
	var BF = {
		$context : $context,
		$menu : $menu,
		$tabs : $tabs,
		toast: toastr
	}

	var Tabs = {
		childs : [{}],
		init : function() {
			Tabs.addEvent();
			$tabs.eq(0).find('.tab a').eq(0).trigger('click');
		},
		closeAll : function() {
			for( var i=0,len=Tabs.childs.length;i<len;i++ )
				for( var key in Tabs.childs[i] ) {
					Tabs.childs[i][key];
				}
		},
		load : function() {
			var data = localStorage.getItem('__tabs__');
			try{
				childs = JSON.parse(data);

				for( var i=0,len=childs.length;i<len;i++ )
					for( var key in childs[i] ) {
						Tabs.loadTab( i, childs[i][key].name, childs[i][key].url );
					}

			} catch(e){
				childs = [{}];
			}
		},
		save : function() {
			localStorage.setItem('__tabs__', JSON.stringify( Tabs.childs ));
		},
		close : function( number, id ) {
			$tabs.eq(number).find('.tab.'+id).remove();
			$context.find('#'+id+'.tab_body').remove();
			delete Tabs.childs[number][id];
			Tabs.save();

			// 선택된 UI가 없다면
			if( $tabs.eq(number).find('.tab.selected').length == 0 ) {
				$tabs.eq(number).find('.tab:last').find('a').trigger('click');
			}
		},
		addEvent : function() {

			$tabs.sortable({
				delay: 150,
				connectWith: ".body>.tabs",
				receive: function(event, ui) {
					var toIndex = $('.body>.tabs').index(event.target);
					var fromIndex = $('.body>.tabs').index( ui.sender );
					var id = ui.item.data('id');
					id = id.replace(/ /g, '_');

					// 데이터 이관
					// 이미 같은 아이디가 있으면 실패
					if( Tabs.childs[toIndex] && Tabs.childs[toIndex][id] ) {
						$(ui.sender).sortable("cancel");
						toastr.info('같은 종류의 페이지가 이미 있습니다');
						return;
					}
					if( Tabs.childs[toIndex] === undefined ) Tabs.childs[toIndex] = {};
					Tabs.childs[toIndex][id] = Tabs.childs[fromIndex][id];
					delete Tabs.childs[fromIndex][id];
					// UI 이관
					$('.body>.context').eq(toIndex).append( $('#'+id) );
					// 클릭하게 해야함
					ui.item.find('a').trigger('click');
					Tabs.save();
				}
			});

			$tabs.find('.tab a').off('click').on('click', function(e){

				if( e.which == 2 ) {
					// 휠클릭
					$(this).parent().find('.close').trigger('click');
					e.preventDefault();
					return;
				}

				var index = $('.body>.tabs').index($(this).closest('.tabs'));
				$('.body>.tabs').eq(index).find('.tab').removeClass('selected');
				$(this).parent().addClass('selected');

				var id = $(this).attr('href');
				id = id.replace(/ /g, '_');

				$context.eq(index).find('>.tab_body').hide();
				$context.eq(index).find('>'+id+'.tab_body').show();

			});
			$tabs.find('.tab button.close').off('click').on('click', function(){
				var number = $('.body>.tabs').index($(this).closest('.tabs'));

				var id = $(this).parent().find('a').attr('href').substring(1);
				Tabs.close(number, id);
			});

		},
		loadTab : function( number, name, _url, callback ) {

			var datas = {};
			if( _url.indexOf('?') > 0 ) {
				var addParam = _url.substring(_url.indexOf('?')+1);
				var params = addParam.split('&');

				for( var i=0,len=params.length;i<len;i++ ) {
					var d =  params[i].split('=');
					datas[ d[0] ] = d[1];
				}
				_url =  _url.substring(0, _url.indexOf('?'));
			}
			var arg = _url.split('.');
			var id = name;
			id = id.replace(/ /g, '_');
			var $newEl = $('<div>', {'class':'tab_body', 'id':id});
			var url = "html/"+arg.join("/")+".html";

			if( Tabs.childs[number] && Tabs.childs[number][id] ) {
				$tabs.eq(number).find('.tab.'+id).find('a').trigger('click');
				return;
			}

			var $tab = $('#tmpl_tab').tmpl({id:id, name:name});
			$tab.addClass(id);
			datas.name = name;

			$newEl.hide();
			$newEl.load( url,  function(){

				if( Tabs.childs[number] === undefined ) Tabs.childs[number] = {};
				Tabs.childs[number][id] = {name:name, url:_url};
				Tabs.save();
				$context.eq(number).append($newEl, {data:datas, $body:$newEl, BF:BF});
				$tab.find('a').trigger('click');
				$tab.data('id', name);

				if( callback )
					callback();
			});

			$tabs.eq(number).append($tab);
			Tabs.addEvent();

		}
	};
	BF.Tabs = Tabs;
	Tabs.load();
	Tabs.init();












	/********************************************
	 * 				Component
	 ********************************************/
	var components = {};
	BF.component = function( name, param ) {

		var type = param.type || '';
		var compClass = Component;
		var $newEl = $('<div>', {'class':'component'});

		if( type == 'list' ) compClass = ComponentList;

		var comp = new compClass($newEl, param);
		$newEl.data('comp', comp);

		if( components[name] !== undefined ) $newEl.html(components[name]);
		else {

			$.ajax({
				url: 'html/component/'+name+".html",
				dataType:'text',
				type:'GET',
				async: false,
				success: function(data){
					components[name] = data;
				}
			})
			$newEl.html(components[name]);
			//$newEl.trigger('load');


		}

		return comp;
	}



	/********************************************
	 * 				Component
	 ********************************************/
	function Component( $tar, param ) {

		var childs = [];

		function get( key ) {
			var data = $tar.formData();
			if( key !== undefined ) {
				return data[key];
			}
			return data;
		}

		function set() {

			if( arguments.length == 2 ) {
				var data = {};
				data[ arguments[0] ] = arguments[1];
				$tar.formData( data );
				return;
			}
			if( arguments.length == 1 ) {

				$tar.formData( arguments[0] );
				return;
			}
		}

		function clear() {
			$tar.formData( {}, true );
		}

		function reset() {

		}

		function child( index ) {
			if( index !== undefined ) return childs[index];
			return childs;
		}

		function append( $el ) {
			$el.append( $tar, {data:param, $body:$tar, BF:BF, comp:this} )
			return this;
		}

		function init() {
			this.init = init;
			this.get = get;
			this.set = set;
			this.clear = clear;
			this.reset = reset;
			this.child = child;
			this.append = append;
		}
		init.apply( this );
	}

	/********************************************
	 * 				ComponentList
	 ********************************************/
	function ComponentList( $tar, param ) {
		var comp = new Component( $tar, param );
		comp.init.apply( this );

		var $listTmpl = null;
		var $emptyTmpl = null;
		var $tbody = param.$tar || $tar.find('tbody');
		var page = 1;
		var len = param.len || 10;
		var total = 0;
		var prevLoadData = {};
		var url = '';
		var listcallback = null;
		var thisClass = this;

		function load(data) {

			data.page = page;
			data.len = len;
			prevLoadData = data;

			$.ajax({
				url:url+'list',
				data: data,
				dataType:'json',
				success:function( datas ) {

					if( datas.status == 'ok' ) {

						total = datas.total;

						makePages();
						$tbody.empty();
						for( var i=0;i<datas.list.length;i++ ) {
							var $newEl = makeNewRow(datas.list[i], i);
							$tbody.append($newEl);
						}

						if( total == 0 ) {
							$tbody.append( $('#empty_tmpl').tmpl({}) );
						}
					}
				}
			});
		}

		function makePages() {
			var pageLen = Math.ceil(total / len);
			var $pageEl = $tar.find('.pageing').empty();

			var $firstEl = $('<button>', {type:'button', 'class':'page first'}).on('click', function(){ goPage(1); });
			var $prevEl = $('<button>', {type:'button', 'class':'page prev'}).on('click', function(){ goPage(page-1); });
			var $nextEl = $('<button>', {type:'button', 'class':'page next'}).on('click', function(){ goPage(page+1); });
			var $lastEl = $('<button>', {type:'button', 'class':'page last'}).on('click', function(){ goPage(pageLen); });

			function goPage( p ) {
				page = p;
				if( page >= pageLen ) page = pageLen;
				if( page < 1 ) page = 1;
				load( prevLoadData );
			}

			$pageEl.append($firstEl);
			$pageEl.append($prevEl);

			for( var i=0;i<pageLen;i++ ) {
				var $pEl = $('<button>', {type:'button', 'class':'page number',text:i+1});
				if( i+1 == page ) $pEl.addClass('selected');
				$pEl.data('page', i+1);
				$pEl.on('click', function(){
					goPage( $(this).data('page') );
				});
				$pageEl.append($pEl);
			}

			$pageEl.append($nextEl);
			$pageEl.append($lastEl);

			thisClass.goPage = goPage;
		}

		function setListTmpl( $tmpl ){
			$listTmpl = $tmpl;
		}
		function setTarget( $tar ){
			$tbody = $tar;
		}
		function setListCallback( callback ) {
			listcallback = callback;
		}
		function goPage() {

		}
		function reload() {
			this.goPage(page);
		}
		function setUrl( _url ) {
			url = _url;
		}
		function add( datas, callback ) {
			$.ajax({
				type:'POST',
				url:url+'add',
				data: datas,
				dataType:'json',
				success:function( res ) {

					if( res.status == 'ok' ) {
						if( callback ) {
							callback(true, datas);
						}
					} else {
						if( callback ) {
							callback(false);
						}
					}
				}
			});
		}
		function modify( datas, callback ) {
			$.ajax({
				type:'POST',
				url:url+'modify',
				data: datas,
				dataType:'json',
				traditional: true,
				success:function( res ) {

					if( res.status == 'ok' ) {
						if( callback ) {
							callback(true, datas);
						}
					} else {
						if( callback ) {
							callback(false);
						}
					}
				}
			});
		}
		function remove( datas, callback ) {
			$.ajax({
				type:'POST',
				url:url+'delete',
				data: datas,
				dataType:'json',
				success:function( res ) {

					if( res.status == 'ok' ) {
						if( callback ) {
							callback(true, datas);
						}
					} else {
						if( callback ) {
							callback(false);
						}
					}
				}
			});
		}

		function makeNewRow( datas, i ) {
			//datas['codeCategoryNm'] = comp.cates[datas['codeCategorySeq']];

			var $newEl = $listTmpl.tmpl( datas );
			if( i%2==1 ) $newEl.addClass('ord');
			$newEl.data('data', datas);
			if( listcallback ) listcallback( $newEl );

			return $newEl;
		}

		this.reload = reload;
		this.add = add;
		this.remove = remove;
		this.modify = modify;
		this.load = load;

		this.setUrl = setUrl;
		this.goPage = goPage;
		this.setListTmpl = setListTmpl;
		this.setTarget = setTarget;
		this.setListCallback = setListCallback;
	}


	  // 팝업 컴포넌트
	  var dialogs = {};
	  $.loadDialog = function( opt, callback ) {

		  var $this = $('<div>');
		  opt.close = function() {
			  $this.trigger('unload');
			  $this.remove();
		  };
		  if( opt.modal === undefined ) opt.modal = true;

		  if( dialogs[opt.popup] ) {

			  $this.html(dialogs[ opt.popup ]);
			  $('body').append($this, {data:opt.data, $body:$this, BF:BF});
			  $this.dialog(opt);
			  if(callback)
				  callback();

		  } else {


			  $this.load( 'html/popup/'+opt.popup+'.html', function() {

				  dialogs[ opt.popup ] = $this.html();
				  $('body').append($this, {data:opt.data, $body:$this, BF:BF});
				  $this.dialog(opt);
				  if(callback)
					  callback();
			  });
		  }
		  return $this;
	  };







	/********************************************
	 * 				loading
	 ********************************************/
	if( $("#loadingDiv").length > 0 )
    $("#loadingDiv").fakeLoader({
        timeToHide:1200,
        bgColor:"rgba(0,0,0,0.3)",
        spinner:"spinner6"
    }).hide();

    $( document ).ajaxError(function( event, jqxhr, settings, thrownError ) {
    	if( jqxhr.status == 403 ) {
    		location.href = '/admin/login.html';
    	}
    	if( jqxhr.status == 402 ) {
    		toastr.error('기능에 대한 권한이 없습니다');
    	}
    });


	var number = 0;

	$('.body').on('click', function(){
		$('.body').removeClass('selected');
		$(this).addClass('selected');
		number = $('.body').index($(this));
	});

	$menu.find('button.logout').on('click', function(){
		$.ajax({
			url: apiRoot+ '/api/login/logout',
			dataType:'json',
			success:function(data) {
				if( data.status == "ok" ) {
					location.href = '/admin/login.html';
				}
			}
		});
	});

	$menu.find('li p').on('click', function(){


		$(this).parent().toggleClass('selected');
		var className = $(this).parent().attr('class');

		var paths = [stripSelected(className)];
		var $ptar = $(this).parent().parent();
		// 최대 부모수는 2개
		for( var i=0;i<2;i++ ){
			var $ptar = $ptar.closest('li');
			if( $ptar.length > 0 ) {
				var className = $ptar.attr('class');
				paths.push( stripSelected(className) );
				$ptar = $ptar.parent();
			} else {
				break;
			}
		}
		if( $(this).parent().find('li p').length > 0 ) return;
		Tabs.loadTab( number, $(this).text(), paths.reverse().join('.') );

	});

	function stripSelected( className ){
		return $.trim(className.replace('selected', ''));
	}



});

