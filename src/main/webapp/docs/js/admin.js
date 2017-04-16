/******************************
 * 				네비게이션 세팅					  *
 ******************************/

$(function(){
	
	var $context = $('.body .context');
	var $menu = $('#menu');
	var $tabs = $('.body>.tabs');
	var BF = {
		$context : $context,
		$menu : $menu,
		$tabs : $tabs,
		toast: toastr,
		makeApi: makeApi 
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
			var data = localStorage.getItem('__api__tabs__');
			try{
				childs = JSON.parse(data);
				
				for( var i=0,len=childs.length;i<len;i++ )
					for( var key in childs[i] ) {
						Tabs.loadTab( i, childs[i][key].name, childs[i][key].url, null, true );
					}
					
			} catch(e){
				childs = [{}];
			}
			
		},
		save : function() {
			
			localStorage.setItem('__api__tabs__', JSON.stringify( Tabs.childs ));
			
		},
		close : function( number, id ) {
			$tabs.eq(number).find('.tab.'+id).remove();
			$context.find('#'+id+'.tab_body').trigger('unload');
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
			
			$tabs.find('.tab a').off('click').on('click', function(){
				var index = $('.body>.tabs').index($(this).closest('.tabs'));
				$('.body>.tabs').eq(index).find('.tab').removeClass('selected');
				$(this).parent().addClass('selected');
				
				var id = $(this).attr('href');
				
				$context.eq(index).find('>.tab_body').hide();
				$context.eq(index).find('>'+id+'.tab_body').show().trigger('show');
				
			});
			$tabs.find('.tab button.close').off('click').on('click', function(){
				var number = $('.body>.tabs').index($(this).closest('.tabs'));
				
				var id = $(this).parent().find('a').attr('href').substring(1);
				Tabs.close(number, id);
			});
			
		},
		loadTab : function( number, name, _url, callback, isFirst ) {
			
			var arg = _url.split('.');
			var id = name;
			var $newEl = $('<div>', {'class':'tab_body', 'id':id});
			var url = "html/"+arg.join("/")+".html";
			
			if( Tabs.childs[number] && Tabs.childs[number][id] ) {
				$tabs.eq(number).find('.tab.'+id).find('a').trigger('click');
				return;
			}
			
			var $tab = $('#tmpl_tab').tmpl({id:id, name:name});
			$tab.addClass(id);
			
			//$newEl.hide();
			$newEl.load( url,  function(){
				
				if( Tabs.childs[number] === undefined ) Tabs.childs[number] = {}; 
				Tabs.childs[number][id] = {name:name, url:_url};
				Tabs.save();
				$context.eq(number).append($newEl, {data:{name:name}, $body:$newEl, BF:BF});
				$tab.find('a').trigger('click');
				$tab.data('id', name);

				// 초기 복구시 신텍스가 안먹을때를 대비
				var $this = $(this);
				if( isFirst ) {
					$this.on('show', function(){
						if( $this.data('_show_') ) return;
						$this.data('_show_', true);
						makeApi( $this );
					});
					setTimeout(function(){
						if( $this.is(':visible') ) {
							$this.data('_show_', true);
							makeApi( $this );
						}
					},100);
				} else {
					makeApi( $this );
				}
				
				
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
	
	
	
	var components = {};
	
	BF.component = function( name, param ) {
		
		var $newEl = $('<div>', {'class':'component'});
		var comp = new Component($newEl, param);
		$newEl.data('comp', comp);
		
		if( components[name] !== undefined ) $newEl.html(components[name]);
		else {

			$.ajax({
				url: 'html/component/'+name+".html",
				dataType:'text',
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
			console.log('append')
			$el.append( $tar, {data:param, $body:$tar, BF:BF} )
			console.log('append end')
			return this;
		}
		
		this.get = get;
		this.set = set;
		this.clear = clear;
		this.reset = reset;
		this.child = child;
		this.append = append;
	}
	
	function Tab() {
		
	}
	
	function makeApi( $body ) {
		
		$body.data('editors', []);
		$body.on('unload', function(){
			var editors = $body.data('editors');
			for( var i=0;i<editors.length;i++ ) {
				editors[i].destroy();
			}
		});
				
		$body.find('pre').each(function(){
			
			var $newEl = $('<button>', {type:'button', 'class':'copy'}).text('copy');
			$(this).prev().append($newEl);
			$(this).css('height', $(this).height());
			
			$newEl.attr('data-clipboard-text', $(this).text())
			var client = new ZeroClipboard( $newEl[0] );

			client.on( "ready", function( readyEvent ) {

			  client.on( "aftercopy", function( event ) {
				  
			    toastr.info("클립보드로 카피되었습니다");
			  });
			});
			
		    var editor = ace.edit(this);
		    editor.getSession().setMode("ace/mode/javascript");
		    editor.setReadOnly(true);  // false to make it editable
		    
		    var editors = $body.data('editors');
		    editors.push(editor);
		    $body.data('editors', editors);
		    
		    $newEl.data('editor', editor);
		});
	}
	

	window.loadPage = Tabs.loadTab;
	
	
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

    if( window.Handlebars )
    	Handlebars.registerHelper('comma', function(data) {
   	  return makeComma(data);
   	});

});

var Loading = {
	show : function( ) {
		$("#loadingDiv").fadeIn();
	}, 
	hide : function( ) {
		$("#loadingDiv").fadeOut();
	}
};


function adapteData( datas, adepter ) {
	var len = datas.length;
	for( var i=0;i<len;i++ ) {
		datas[i] = adepter(datas[i]);
	}
}

function getAdminContextDir() {
	
	var loca = location.href+'';
	if( loca.indexOf('COOPNC-STATIC') > 0 ) return '/COOPNC-STATIC';
	else return '';
}


function makeChartData( data, labelName, options )  {
	
	var labels = [];
	var datasets = [];
	var convMap = options.convertMap;
	
	for( var key in data[0] )  {
		
		if( key == labelName ) {
			for( var i=0,len=data.length;i<len;i++ )
				labels.push( data[i][key]  );
			continue;
		}
		
		var name = convMap[key].chartName;
		var datas = [];
		
		if( !name ) continue;
		
		for( var i=0,len=data.length;i<len;i++ )
			datas.push( data[i][key] );
		
		datasets.push( {
			name : name,
			data : datas
		});
		
	}
	
	return {
	    chart: {
	        type: 'area'
	    },
	    title: {
	        //text: 'Fruit Consumption'
	    },
	    xAxis: {
	        categories: labels,
            tickmarkPlacement: 'on'
	    },
	    yAxis: {
	        title: {
	           // text: 'Fruit eaten'
	        }
	    },
      plotOptions: {
            area: {
                stacking: 'normal'
            }
        },
	    series: datasets
	} ;
	
}

function makeComma( val ) {
	
	var val = val + "";
	val = val.replace(/,/g, '');
	var len = val.length;
	var rstr = "";
	
	
	for( var i=len-1,count=0;i>=0;i--,count++ ) {
		if( count%3 == 0 && count != 0 ) rstr = ","+rstr;
		rstr = val.charAt(i)+rstr;
	}
	
	return rstr;
	
}


/*************************************************************
 * 
 * 				JQuery plugin 
 * 
*************************************************************/
(function( $ ) {
  $.fn.formData = function( datas, clear ) {
	  
	  if( datas !== undefined ) {
		  
		  var options = {}; 
		  $(this).find('select,input,textarea').each(function(){
			  var name = $(this).attr('name');
			  if( datas[name] !== undefined ) {
				  
				  if( this.tagName == 'INPUT' ) {
					  var type = $(this).attr('type').toLowerCase();
					  if( type == 'checkbox' || type == 'radio' ) {
						  this.checked = $(this).attr('value') == ''+datas[name];
					  } 
					  else $(this).val( datas[name] ); 
				  } else {
					  $(this).val( datas[name] ); 
				  }
				  
			  } else {
				  // 값 초기화
				  if( clear ) {
					  if( this.tagName == 'INPUT' ) {
						  var type = $(this).attr('type').toLowerCase();
						  if( type == 'checkbox' || type == 'radio' ) this.checked = false;
						  else $(this).val('');
					  }
					  else $(this).val('');
				  }
			  }
			  $(this).data('orvalue', datas[name]);
			  
		  }).trigger('change');
		  return options;
		  
	  } else {
		  
		  var options = {}; 
		  $(this).find('select,input,textarea').each(function(){
			  
			  var name = $(this).attr('name');
			  if( name ) {
				  
				  if( $(this).attr('disabled') ) return;
				  
				  if( this.tagName == 'INPUT' ) {
					  var type = $(this).attr('type').toLowerCase();
					  if( type == 'checkbox' || type == 'radio' ) {
						  if( !this.checked ) return;
					  } 
					  options[name] = $(this).val();
					  
				  } else 
					  options[name] = $(this).val(); 
			  }
		  });
		  return options;		  
		  
	  }
  };
  
  $.fn.loadDialog = function( opt , callback ) {
	  var $this = $(this);
	  if( opt.cache === false ||  !$this.data('loaded') ) {
		  $this.load( 'html/popup/'+opt.popup+'.html', function() {
			  $this.data('loaded', true);
			  //$this.find('button[type=button]').button();
			  $this.dialog(opt);
			  if(callback)
				  callback();
		  });
	  } else {
		  $this.dialog(opt);
		  if(callback)
			  callback();
	  }
  };
  
  
  $.fn.onlyNew = function( isNew ) {
	  $(this).find('input,textarea').each(function( ){
		  
		  if( $(this).attr('onlynew') ) 
			  $(this).attr('readonly', 'readonly');
		  else $(this).removeAttr('readonly');
		  
		  return this;
	  })
  }
  
  $.fn.options = function( datas ) {
	  for( var key in datas ) {
		  $(this).append( $('<option>', {text:datas[key], value:key}) );
	  }
	  
  }
  $.fn.disabled = function() {
	  $(this).attr('disabled', 'disabled');
  }
  $.fn.enabled = function() {
	  $(this).removeAttr('disabled');
  }
  
  $.fn.paging = function( opt ) {
	  
	  if( this.length == 0 ) return;
	  var $this = $(this);
	  function init() {
		  
		  if( !$this.data('opt') ) {
			  $this.data('opt', {page:1, pagelen:5, total:0, click:function(){}  });
		  }
		  var orOpt = $this.data('opt'); 
		  
		  for( var key in opt ) {
			  orOpt[key] = opt[key];
		  }
		  $this.data('opt', orOpt);
		  
		  var nextPage = orOpt.page+1;
		  var nextStepPage = orOpt.page+orOpt.pagelen;
		  var prevPage = orOpt.page-1;
		  var prevStepPage = orOpt.page-orOpt.pagelen;
		  var totalPage = Math.ceil( orOpt.total/orOpt.pagelen );
		  
		  var start = orOpt.page-orOpt.pagelen;
		  var end = orOpt.page+orOpt.pagelen;

		  if( end > totalPage ) end = totalPage;
		  if( start < 1 ) start = 1;

		  
		  if( nextStepPage > totalPage ) nextStepPage = totalPage;
		  if( prevStepPage < 1 ) prevStepPage = 1;
		  
		  $this.empty();
		  if( start > 1 ) {
			  $this.append( $('<button>', {type:'button'}).text('«').data('page',prevStepPage) );
			  $this.append( $('<button>', {type:'button'}).text('‹').data('page', prevPage) );
		  }
		  
		  for( var i=start;i<=end;i++ ) {
			  var $el = $('<button>', {type:'button'}).text(i).data('page', i);
			  if( i == orOpt.page ) $el.addClass('submit');
			  $this.append( $el );
		  }
		  
		  if( end < totalPage ) {
			  $this.append( $('<button>', {type:'button'}).text('›').data('page', nextPage) );
			  $this.append( $('<button>', {type:'button'}).text('»').data('page', nextStepPage) );
		  }
		  
		  $this.find('button').button().on('click', function(){
			  if( orOpt.page != $(this).data('page') )
				  orOpt.click($(this).data('page'));
		  });		  
	  }

	  init();

  };
  


  $.fn.board = function( option ) {

	  var $tar = $(this);

	  if( !$tar.data('opt') ) {
		  $tar.data('opt', {
			  url : '',
			  onRender : function( $el, datas ) {
				  
			  },
			  param: {},
			  loading: false,
			  page: 1,
			  pageSize: 10,
			  paging : null,
			  template: null,
			  type: 'POST'
		  });
	  }
	  var opt = $tar.data('opt'); 
	  
	  for( var key in option ) {
		  opt[key] = option[key];
	  }
	  $tar.data('opt', opt);
	  
	  
	  
	  
	  function load( page ){
			if( page === undefined ) page = 1;
			var params = {curPage:page, pageSize: opt.pageSize};
			if( typeof(opt.param) == 'function' ) {
				$.extend(params, opt.param());
			}
			else $.extend(params, opt.param);
			
			
			var ajaxParams = {
					url : opt.url,
					data: params,
					dataType: 'json',
					type: opt.type,
					success: function( datas )  {
						$tar.empty();
						for( var i=0,len=datas.list.length;i<len;i++ ) {
							var $newEl = opt.template.mustache( datas.list[i] ).data('data', datas.list[i]);
							$newEl.update = function(datas){
								var $newEl = opt.template.mustache( datas );
								this.data('data',datas);
								this.empty().append( $newEl.find('>') );
								opt.onRender(this, datas );
							};
							$newEl.data('update', $newEl.update);
							
							opt.onRender( $newEl, datas.list[i] );
							$tar.append( $newEl );
						}
						
						$('.paging').paging({
							page : page,
							pagelen : opt.pageSize,
							total : datas.total,
							click : function( page ) {
								load( page );
							}
					});
				}
			};
			
			if( opt.loading )
				$.ajaxLoading(ajaxParams);
			else $.ajax(ajaxParams);
	  }
	  load(1);

  };
  
  $.fn.checkChanged = function( opt ) {
	  
	 $(this).on('change', function(){
		 
		 var orvalue =  $(this).data('orvalue');
		 var hasChange = false;
		 if( orvalue == null ) orvalue = '';
		 //if( $(this).attr('name') === undefined ) return;
		 
		 if( this.tagName == 'INPUT' ) {
			 
			 var type = $(this).attr('type').toLowerCase();
			 if( type == 'checkbox' || type == 'radio' ) {
				 if( this.checked && orvalue != $(this).val() ) hasChange = true; 
			 } 
			 else if( $(this).val() != orvalue ) hasChange = true;
			 
		 } else {
			 if( $(this).val() != orvalue ) hasChange = true; 
		 }
		 
		 if( hasChange ) $(this).css({'border': 'red 1px solid'});
		 else $(this).css('border', '');
	 });
	 
  };

  $.fn.hasChanged = function() {
			  
	  var hasChanged = false;
	  $(this).each(function(){
		  if( hasChanged ) return;
		  
		 var orvalue =  $(this).data('orvalue');
		 var hasChange = false;
		 if( orvalue == null ) orvalue = '';
		 if( $(this).attr('name') === undefined ) return;
		 
		 if( this.tagName == 'INPUT' ) {
			 
			 var type = $(this).attr('type').toLowerCase();
			 if( type == 'checkbox' || type == 'radio' ) {
				 if( this.checked && orvalue != $(this).val() ) hasChanged = true; 
			 }
			 else if( $(this).val() != orvalue ) hasChanged = true;

		 } else {
			 if( $(this).val() != orvalue ) hasChanged = true; 
		 }
		 
			 
	  });
	  return hasChanged;
  };

  
 
  $.fn.onlyNumber = function( opt ) {
	  $(this).data('ordata', $(this).val());
	  $(this).off('keyup,keydown')
	  	.on('keyup', function(event){
	  		
			var nowVal = $(this).val();
	  		var ordata = $(this).data('ordata');
	  		
			if( ordata != nowVal && ordata.replace(/,/g,'') != nowVal.replace(/,/g,'') ) {
				//saveSelection(this);
				$(this).data('ordata',  makeComma(nowVal));
				$(this).toPrice();
				$(this).getOnlyNumeric();
				$(this).trigger('change');
				//restoreSelection(this);
			}
	  	})
	  	.on('keydown', function(event){
	  		
	  		// home , end
	  		// 방향키 삭제키 허락
			var allowKeyCode = [27, 8,46,35,36,37,39];
			var len=allowKeyCode.length;
			
			for( var i=0;i<len;i++ ){ 
				if( allowKeyCode[i] == event.keyCode ) 
					return;
			}
			
			// 숫자키 허락
			if( event.keyCode >= 48 && event.keyCode <= 57 && !event.shiftKey ) ;
			if( event.keyCode >= 96 && event.keyCode <= 105 && !event.shiftKey ) ;
			else {
				console.log( event.keyCode )
			   event.preventDefault(); 
			   return; 
			}
	  	})
  }

  $.fn.fixIEDatePicker = function( opt ) {
	  
	  $.extend(opt, 
		  {
		        fixFocusIE: false,    
		        onSelect: function(dateText, inst) {
		              this.fixFocusIE = true;
		              $(this).change().focus();
		        },
		        onClose: function(dateText, inst) {
		              this.fixFocusIE = true;
		              this.focus();
		        },
		        beforeShow: function(input, inst) {
		              var result = isIE ? !this.fixFocusIE : true;
		              this.fixFocusIE = false;
		              return result;
		        }
		});
	  
  	$(this).datepicker(opt).click(function(){$(this).focus()});
  
  }
  
  $.ajaxLoading = function( opt ) {
	  Loading.show();

	  var backComplete = opt.complete;
	  function newComplete() {
		  Loading.hide();
		  if( backComplete )
			  backComplete();
	  }
	  opt.complete = newComplete;
	  
	  $.ajax(opt);
  }
  
  $.fn.ajaxXML = function( opt ) {
	  
	  var dataType =  isIE ? "text" : "xml";
	  var success = opt.success;
	  
	  opt.dataType = dataType;
	  opt.success = function( data ) {
		  
		  var xml;
          if (isIE) 
          {
              xml = new ActiveXObject("Microsoft.XMLDOM");
              xml.async = false;
              xml.loadXML(data);
          } 
          else 
          {
             xml = data;
          }
          success(xml);
	  };
	  $.ajax(opt);
	  
  }
  
  
  $.alert = function (message, title) {
	  $("<div></div>").dialog( {
	    buttons: { "Ok": function () { $(this).dialog("close"); } },
	    close: function (event, ui) { $(this).remove(); },
	    resizable: false,
	    title: title,
	    modal: true
	  }).html(message);
	}


  // datepicker 한글화
  $.datepicker.regional['ko'] = {
          closeText: '닫기',
          prevText: '이전달',
          nextText: '다음달',
          currentText: '오늘',
          monthNames: ['1월','2월','3월','4월','5월','6월',
          '7월','8월','9월','10월','11월','12월'],
          monthNamesShort: ['1월','2월','3월','4월','5월','6월',
          '7월','8월','9월','10월','11월','12월'],
          dayNames: ['일','월','화','수','목','금','토'],
          dayNamesShort: ['일','월','화','수','목','금','토'],
          dayNamesMin: ['일','월','화','수','목','금','토'],
          //buttonImage: "/images/kr/create/btn_calendar.gif",
          buttonImageOnly: false,
         // showOn :"both",
          weekHeader: 'Wk',
          dateFormat: 'yymmdd',
          firstDay: 0,
          isRTL: false,
          duration:200,
          //showAnim:'show',
          showMonthAfterYear: false,
          showButtonPanel: true,
          showOtherMonths: true,
          selectOtherMonths: true          

         // yearSuffix: '년'
             };
    $.datepicker.setDefaults($.datepicker.regional['ko']);
  
    
    
    
    
    $.widget( "custom.combobox", {
        _create: function() {
          this.wrapper = $( "<span>" )
            .addClass( "custom-combobox" )
            .insertAfter( this.element );
   
          this.element.hide();
          this._createAutocomplete();
          this._createShowAllButton();
        },
   
        _createAutocomplete: function() {
          var selected = this.element.children( ":selected" ),
            value = selected.val() ? selected.text() : "";
   
          this.input = $( "<input>" )
            .appendTo( this.wrapper )
            .val( value )
            .attr( "title", "" )
            .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
            .autocomplete({
              delay: 0,
              minLength: 0,
              source: $.proxy( this, "_source" )
            })
            .tooltip({
              tooltipClass: "ui-state-highlight"
            });
   
          this._on( this.input, {
            autocompleteselect: function( event, ui ) {
              ui.item.option.selected = true;
              this._trigger( "select", event, {
                item: ui.item.option
              });
            },
   
            autocompletechange: "_removeIfInvalid"
          });
        },
   
        _createShowAllButton: function() {
          var input = this.input,
            wasOpen = false;
   
          $( "<a>" )
            .attr( "tabIndex", -1 )
            .attr( "title", "Show All Items" )
            .tooltip()
            .appendTo( this.wrapper )
            .button({
              icons: {
                primary: "ui-icon-triangle-1-s"
              },
              text: false
            })
            .removeClass( "ui-corner-all" )
            .addClass( "custom-combobox-toggle ui-corner-right" )
            .mousedown(function() {
              wasOpen = input.autocomplete( "widget" ).is( ":visible" );
            })
            .click(function() {
              input.focus();
   
              // Close if already visible
              if ( wasOpen ) {
                return;
              }
   
              // Pass empty string as value to search for, displaying all results
              input.autocomplete( "search", "" );
            });
        },
   
        _source: function( request, response ) {
          var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
          response( this.element.children( "option" ).map(function() {
            var text = $( this ).text();
            if ( this.value && ( !request.term || matcher.test(text) ) )
              return {
                label: text,
                value: text,
                option: this
              };
          }) );
        },
   
        _removeIfInvalid: function( event, ui ) {
   
          // Selected an item, nothing to do
          if ( ui.item ) {
            return;
          }
   
          // Search for a match (case-insensitive)
          var value = this.input.val(),
            valueLowerCase = value.toLowerCase(),
            valid = false;
          this.element.children( "option" ).each(function() {
            if ( $( this ).text().toLowerCase() === valueLowerCase ) {
              this.selected = valid = true;
              return false;
            }
          });
   
          // Found a match, nothing to do
          if ( valid ) {
            return;
          }
   
          // Remove invalid value
          this.input
            .val( "" )
            .attr( "title", value + " didn't match any item" )
            .tooltip( "open" );
          this.element.val( "" );
          this._delay(function() {
            this.input.tooltip( "close" ).attr( "title", "" );
          }, 2500 );
          this.input.autocomplete( "instance" ).term = "";
        },
   
        _destroy: function() {
          this.wrapper.remove();
          this.element.show();
        }
      });
    
    
})( jQuery );


$.comma = function( data ) {
	var str = "";
	var instr =  data +"";
	
	for( var i=instr.length;i>=0;i-=3) {
		if( str != "" ) str =","+str; 
		str = instr.substring( i-3 , i )+str;
	}
	
	return str;
}

$.xmlToObject = function($xml, childName){
	
    var products = $($xml).find(childName);
    var len = products.length;
    var datas = [];
    
    for( var i=0;i<len;++i ){
    	var product =  products.eq(i);
    	var data = {};
    	product.find('>').each(function(){
    		if( this.firstChild ) {
    			var key = this.tagName;
    			var value = this.firstChild.nodeValue;
    			data[key] = value;
    		}
    	});
    	datas.push(data);
    }
    return datas;
};


var isIE = (function(){
	  
	var date = new Date('2014-12-21 12:12:12');
	if( date+'' == 'Invalid Date' ) return true;
	return false;
     
}());


function getBF() {
	return $('.body>.context>.tab_body:last').data('data');
}









