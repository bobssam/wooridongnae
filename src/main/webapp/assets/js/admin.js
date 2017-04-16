var apiRoot = '';
if( location.host.indexOf("localhost") >= 0 ) apiRoot = '';
if( location.host.indexOf(":8080") >= 0 ) apiRoot = '';

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
			  $(this).find('select,input,textarea,[data-name]').each(function(){
				  var name = $(this).attr('name');
				  var data = $(this).attr('data-name');
				  var formatter = window[$(this).attr('data-formatter')] || function(data){return data};

				  if( data && datas[data] !== undefined  ) {

					  $(this).html(formatter(datas[data]));

				  }
				  else if( datas[name] !== undefined ) {


					  if( this.tagName == 'INPUT' ) {
						  var type = $(this).attr('type').toLowerCase();
						  if( type == 'checkbox' || type == 'radio' ) {
							  this.checked = $(this).attr('value') == ''+datas[name];
						  }
						  else $(this).val( formatter(datas[name]) );
					  } else {
						  $(this).val( formatter(datas[name]) );
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
						  if( options[name] !== undefined ) {
							  if( options[name].push ) options[name].push( $(this).val() );
							  else options[name] = [options[name], $(this).val()];
						  }
						  else options[name] = $(this).val();

					  } else {
						  if( options[name] !== undefined ) {
							  if( options[name].push ) options[name].push( $(this).val() );
							  else options[name] = [options[name], $(this).val()];
						  }
						  else options[name] = $(this).val();
					  }
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
  $.fn.checkboxs = function( name, datas ) {
	  for( var key in datas ) {
		  var $newEl = $('<label>');
		  $newEl.append( $('<input>', {type:'checkbox', value:key, name:name}) )
		  	.append( ' '+datas[key] );
		  $(this).append( $newEl );
	  }
  }
  $.fn.optionsApi = function( param, callback ) {
	 var $this = this;
	$.ajax({
		url: apiRoot+ param.api+'/list',
		async: param.async,
		data: {where: JSON.stringify(param.filter) },
		success: function( datas ) {
			if( datas.status == 'ok' ) {
				var map = {};
				$this.options({'':'전체'});
				for( var i=0,len=datas.list.length;i<len;i++ ) {
					map[ datas.list[i][param.key] ] = datas.list[i][param.value];
				}
				$this.options(map);
				if( callback )
					callback(map);
			}
		}
	});
  }

  $.fn.checkboxApi = function( param, callback ) {
	 var $this = this;
	 $.ajax({
		url: apiRoot+ param.api+'/list',
		data: {where: JSON.stringify(param.filter) },
		async: param.async,
		success: function( datas ) {
			if( datas.status == 'ok' ) {
				var map = {};
				for( var i=0,len=datas.list.length;i<len;i++ ) {
					map[ datas.list[i][param.key] ] = datas.list[i][param.value];
				}
				$this.checkboxs(param.name, map);
				if( callback )
					callback(map);
				}
			}
		});
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
			  $this.data('opt', {page:1, pagelen:2, total:0, click:function(){}  });
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
			  $this.append( $('<button>', {type:'button', 'class':'page first'}).data('page',prevStepPage));
			  $this.append( $('<button>', {type:'button', 'class':'page prev'}).data('page', prevPage));
		  }

		  for( var i=start;i<=end;i++ ) {
			  var $el = $('<button>', {type:'button', 'class':'page number'}).text(i).data('page', i);
			  if( i == orOpt.page ) $el.addClass('selected');
			  $this.append( $el );
		  }

		  if( end < totalPage ) {
			  $this.append( $('<button>', {type:'button', 'class':'page next'}).data('page', nextPage) );
			  $this.append( $('<button>', {type:'button', 'class':'page last'}).data('page', nextStepPage) );
		  }

		  $this.find('button').on('click', function(){
			  if( orOpt.page != $(this).data('page') )
				  orOpt.click($(this).data('page'));
		  });
	  }

	  init();

  };

  $.fn.makeCheckAll = function( option ) {
	  var thisClass = this;
	  thisClass.find('thead :checkbox').on('click', function(){
			var checked = this.checked;
			var name = $(this).attr('name');

			thisClass.find('tbody input[name='+name+'][type=checkbox]').each(function() {
				this.checked = checked;
			});
	  });
  };

  $.fn.board = function( option ) {

	  var $tar = $(this);
	  var opt = $.extend({
		  api : '',
		  onRender : function( $el, datas ) {
		  },
		  onLoaded : function( datas ) {
		  },
		  onRendered : function( datas ) {
		  },
		  filter: {},
		  param: {},
		  loading: false,
		  page: 1,
		  len: 10,
		  paging : null,
		  template: null,
		  type: 'POST',
		  total : null

	  }, option);
	  if( !opt.template.list ) opt.template = {list:opt.template};

	  function reload(){
		  load( opt.page );
	  }

	  function setFilter( fitler ) {
		  opt.filter = fitler;
	  }

	  function load( page, isAppend ){
		  if( page === undefined ) opt.page = 1;
		  else opt.page = page;
		  var params = {page:opt.page, len: opt.len};
		  if( opt.filter ) params.where = JSON.stringify( opt.filter );

		  var ajaxParams =
		  {
			url :  apiRoot+ opt.api+'list', //opt.api+'list',
			data: params,
			dataType: 'json',
			type: opt.type,
			success: function( datas )
			{
				opt.onLoaded(datas);
				if( isAppend !== true ) $tar.empty();
				total = datas.total;
				if( opt.total ) opt.total.text(total);
				if( total == 0  ) {
					if( opt.template.empty )
						$tar.append( opt.template.empty.tmpl() );
				}

				for( var i=0,len=datas.list.length;i<len;i++ ) {

					datas.list[i].index = i+1;

					var $newEl = opt.template.list.tmpl( datas.list[i] ).data('data', datas.list[i]);
					$newEl.update = function(datas){
						var $newEl = opt.template.list.tmpl( datas );
						this.data('data',datas);
						this.empty().append( $newEl.find('>') );
							opt.onRender(this, datas );
					};
					$newEl.data('update', $newEl.update);
					if( i%2==1) $newEl.addClass('ord');

					opt.onRender( $newEl, datas.list[i] );
					$tar.append( $newEl );
				}

				opt.onRendered(datas);
				makePages();
			}
		  };

		  if( opt.loading )
			  $.ajaxLoading(ajaxParams);
		  else $.ajax(ajaxParams);

	  }


		function makePages() {
			opt.pageLen = Math.ceil(total / opt.len);
			if( !opt.paging ) return;
			var $pageEl = opt.paging.empty();

			var $totalEl = $('<span><a>', {text:'total '+total}).css({'float':'left', 'marginLeft':10});
			var $firstEl = $('<li><a>').find('a').text('«').on('click', function(){ goPage(1); }).end();
			var $prevEl = $('<li><a>').find('a').text('<').on('click', function(){ goPage(opt.page-1); }).end();
			var $nextEl = $('<li><a>').find('a').text('>').on('click', function(){ goPage(opt.page+1); }).end();
			var $lastEl = $('<li><a>').find('a').text('»').on('click', function(){ goPage(opt.pageLen); }).end();

			function goPage( p ) {
				opt.page = p;
				if( opt.page >= opt.pageLen ) opt.page = opt.pageLen;
				if( opt.page < 1 ) opt.page = 1;
				load( opt.page );
			}


			$pageEl.append($totalEl);
			$pageEl.append($firstEl);
			$pageEl.append($prevEl);


			for( var i=0;i<opt.pageLen;i++ ) {
				var $pEl = $('<li><a>').find('a').text(i+1).end();
				if( i+1 == opt.page ) $pEl.addClass('active');
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

	  function remove( where, callback ) {
		  console.log("remove");
			$.ajax({
				type:'POST',
				url:apiRoot+opt.api+'delete',
				data: {where: JSON.stringify(where)},
				dataType:'json',
				success:function( res ) {

					if( res.status == 'ok' ) {
						if( callback ) {
							callback(true, where);
						}
					} else {
						if( callback ) {
							callback(false);
						}
					}
				}
			});
	  }

	  function modify( where, datas, callback ) {
		  datas.where = JSON.stringify(where);
			$.ajax({
				type:'POST',
				url:apiRoot+opt.api+'modify',
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

	  function add( datas, callback ) {
			$.ajax({
				type:'POST',
				url:apiRoot+opt.api+'add',
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



	  //load(1);
	  var thisClass = {
		  load:load,
		  reload: reload,
		  remove: remove,
		  modify: modify,
		  add: add,
		  setFilter: setFilter
	  };

	  return thisClass;

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
			else if( event.keyCode >= 96 && event.keyCode <= 105 && !event.shiftKey ) ;
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

  $.fn.setMaxCheck = function( max ) {
	  var thisClass = this;
	  this.on('change', function(){

		  var len = 0;
		  thisClass.each(function(){
			  if(this.checked) len++;
		  });
		  if( len > max ) {
			  this.checked = false;
			  toastr.error('최대 '+max+'개 까지 선택이 가능합니다');
		  }
	  });
  }

  $.fn.getByteLength = function( opt ) {
	  this.on('input', function(){
		  var $this = $(this);
		  var len = $.getByteLength($this.val());
		  if( opt.max < len ) $this.val( $.byteSubstring($this.val(), 0, opt.max) );
		  opt.target.text( len +"/"+opt.max );
	  }).trigger('input');
  };


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

    $.ajaxSetup({
    	dataTYpe:'json'
    });

    $.getParam = function(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };

    $.getHashParam = function(sParam) {
        var sPageURL = decodeURIComponent(window.location.hash.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };



})( jQuery );

$.getByteLength = function( str ) {
	for( var b=0,i=0,c='';c=str.charCodeAt(i++);b+=c>>11?2:c>>7?2:1 );
	return b;
}

$.byteSubstring = function( str, start, end ) {
	var sp = -1;
	var ep = -1;
	for( var b=0,i=0,c='';c=str.charCodeAt(i++);b+=c>>11?2:c>>7?2:1 ) {

		if( sp == -1 && start <= b ) sp = i-1;
		if( ep == -1 && end <= b ) ep = i-1;

	}
	return str.substring( sp, ep );
}


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

function dateConv(UNIX_timestamp){
	  var a = new Date(UNIX_timestamp );

	  function zeroFill( val ) {
		  if( val < 10 )  return '0'+val;
		  return val;
	  }

	  var year = a.getFullYear();
	  var month = a.getMonth()+1;
	  var date = a.getDate();
	  var hour = a.getHours();
	  var min = a.getMinutes();
	  var sec = a.getSeconds();

	  return zeroFill(year) + '-' + zeroFill(month) + '-' + zeroFill(date);
	}

function timeConv(UNIX_timestamp){
  var a = new Date(UNIX_timestamp );

  function zeroFill( val ) {
	  if( val < 10 )  return '0'+val;
	  return val;
  }

  var year = a.getFullYear();
  var month = a.getMonth()+1;
  var date = a.getDate();
  var hour = a.getHours();
  var min = a.getMinutes();
  var sec = a.getSeconds();

  return zeroFill(year) + '-' + zeroFill(month) + '-' + zeroFill(date)
  	+ ' ' + zeroFill(hour) + ':' + zeroFill(min) + ':' + zeroFill(sec);
}

function diffDateConv(UNIX_timestamp) {
	var a = new Date(UNIX_timestamp );
	var diff = new Date().getTime() - a.getTime();

	var sec = Math.floor(diff/1000)%60;
	var min = Math.floor(diff/1000/60)%60;
	var hour = Math.floor(diff/1000/60/60)%24;
	var day = Math.floor(diff/1000/60/60/24);

	if( day > 0 ) return timeConv(UNIX_timestamp);
	if( hour > 0 ) return hour+"시간"+min+"분전";
	if( min > 0 ) return min+"분전";
	if( sec > 0 ) return sec+"초전";
	return "방금전";
}

function brConv( data ) {

	return data.replace(/\n/g, '<br>');

}


var isIE = (function(){

	var date = new Date('2014-12-21 12:12:12');
	if( date+'' == 'Invalid Date' ) return true;
	return false;

}());


var Member = {
	show: function( datas ) {
		return $.loadDialog({
			title: '유저정보',
			popup: 'user/list',
			data:datas,
			width:'auto',
		});
	}
}

function doLogout(url) {
	$.ajax({
		url: url,
		success: function( datas ) {
			if( datas.status == 'ok' ) {
				location.reload();
			}
		}
	});
}
function scrollToTop() {
	verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
	element = $('body');
	offset = element.offset();
	offsetTop = offset.top;
	$('html, body').animate({scrollTop: offsetTop}, 500, 'linear');
}