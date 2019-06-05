//滑动门
function tabsShow(obj,target,curr){
    $(obj).click(function(){
		var index = $(this).index();
		$(this).addClass(curr).siblings().removeClass(curr);
		$(target).hide().eq(index).show();
	});
}
//找相同的
function findSame(n,arr){
	for(var i=0;i<arr.length;i++){
		if(n == arr[i]){
			return true;
		}
	}
}
//弹出层定位
function rule(target){
    bodyWidth = $("body").width(),
    bodyHeight = $("body").height(),
    screenWidth = $(window).width()/2,
    screenHeight = $(window).height()/2,
	screenHall  = $(window).height(),
    thisWidth = target.width()/2,
    thisHeight = target.height()/2,
    scrollHeight = $("body").scrollTop();
	if(bodyHeight<screenHall){
		bodyHeight = screenHall;
	}
}
//滚动条
function scrollBarShow(obj){
    obj.jscroll({ W:"7px"
        ,BgUrl:"url(images/scrollBar.gif)"
        ,Bg:"right 0 repeat-y"
        ,Bar:{Bd:{Out:"#989c9e",Hover:"#a3a8aa"}
        ,Bg:{Out:"-21px 0 repeat-y",Hover:"-28px 0 repeat-y",Focus:"-35px 0 repeat-y"}}
        ,Btn:{btn:true
            ,uBg:{Out:"0 0",Hover:"-7px 0",Focus:"-14px 0"}
            ,dBg:{Out:"0 -8px",Hover:"-7px -8px",Focus:"-14px -8px"}
        }
        ,Fn:function(){}
    });
}
//删除
function delItem(box,fn){
	box.remove();
	fn && fn();
}
//关闭弹层
function closePop(target){
	$("#layoutBg").remove();
    target.hide();
}
//删除弹出层
function removePop(target){
    $("#layoutBg").remove();
    target.remove();
}
/* 弹窗方法
 * obj 触发方法的元素
 * target 弹出层元素
 * close 关闭弹出层按钮
 * submt 提交按钮
 */
function popup(popObj,fn){
	var pObj = popObj;
	rule(pObj);
	$("body").append('<div id="layoutBg"></div>');
	$("#layoutBg").css({"height":bodyHeight});
	pObj.show().css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight});
	/*改变浏览器大小*/
	window.onresize = function(){
		rule(pObj);
		pObj.css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight});
		$("#layoutBg").css({"height":bodyHeight});
	};  
	$(".closePop,.cancelBtn").click(function(){
		closePop(pObj);
	});
	fn && fn();
}
/*
 * confirm 提示框2
 * 参数txt：提示文字
 */
function confirmTip(txt,callbak,argument){
    if(!txt) txt = "确定要进行此操作吗？";
    var dialog = '<div class="popLayer friendlyPop"><h3><a class="closePop" href="javascript:;"></a>提示信息</h3><div class="tipsCont"><div class="popupTips"><span class="warnTxt">'+txt+'</span></div></div><div class="popOpt"><a class="popBtn subBtn" href="javascript:;">确定</a> <a class="popBtn cancelBtn" href="javascript:;">取消</a></div></div>';
    $("body").append(dialog+'<div id="layoutBg"></div>');
	var popObj = $(".friendlyPop");
    rule(popObj);
    $("#layoutBg").css({"height":bodyHeight,"z-index":1100});
    popObj.show().css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight,"z-index":1110});
    window.onresize = function(){
        rule(popObj);
        popObj.css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight,"z-index":1110});
    };
    popObj.find(".closePop, .cancelBtn").die("click").live("click",function(){
        removePop(popObj);
    });
    popObj.find(".subBtn").die("click").live("click",function(){
        removePop(popObj);
        if(typeof callbak == "function"){
            callbak(argument);
        }
    });
}
/*
 * 错误提示
 */
function errorTip(txt){
    if(!txt) txt = "操作出现错误";
    var con = '<div class="popLayer errorPop"><h3><a href="javascript:;" class="closePop"></a>提示</h3><div class="errorPopMain"><p class="errorCont"><span class="warnTxt">'+txt+'</span></p></div><div class="popOpt"><a href="javascript:;" class="popBtn subBtn">确定</a></div></div>';
    $("body").append(con+'<div id="layoutBg"></div>');
	var $targetBox = $(".errorPop");
    rule($targetBox);
    $("#layoutBg").css({"width":bodyWidth,"height":bodyHeight});
    $targetBox.show().css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight});
    window.onresize = function(){
        rule($targetBox);
        $targetBox.css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight});
    };
    $targetBox.find(".closePop").live("click",function(){
        removePop($targetBox);
    });
    $targetBox.find(".subBtn").live("click",function(){
        removePop($targetBox);
    });
}
/*
 * 操作成功提示弹出层
 * 参数txt:提示文字
 */
function succTip(txt){
    if(!txt) txt = "操作成功！";
    var succ = '<div class="successTips"><a href="javascript:;" class="sTipsClose"></a><span class="sTipsText"><span class="sTipsIcon"></span><span class="sTipsT">'+txt+'</span></span></div>';
    $("body").append(succ);
	var $succPop = $(".successTips");
    rule($succPop);
    $succPop.show().css({"left":screenWidth-thisWidth,"top":screenHeight-thisHeight});
    setTimeout(function(){
        $succPop.remove();
    }, 1500);
	$succPop.find(".sTipsClose").live("click",function(){
        removePop($succPop);
    });
}


//模拟下拉选择
function simSelect(e){
	var $list = $(this).find(e.data.list);
	var $select = $(this).find(e.data.selV);
	var callbak = e.data.fn;
	$(e.data.list).not($list).hide().parent().css("z-index",1);  //隐藏其它下拉
	$list.toggle();
	$list.parent().css("z-index",10);
	scrollBarShow($list.children());
	e.stopPropagation();
	$list.click(function(e){
    	e.stopPropagation();
    });
	$(document).click(function(e){
        e.stopPropagation();
        $list.hide();
    });
	//选中赋值
	$list.off().on("click","a",function(event){
        event.stopPropagation();
        var newValue;
        newValue = $(this).text();
        $list.hide();
        $select.text(newValue);
		callbak && callbak(this);
    });
}


/*
 * checkAll：checkBox全选反选插件
 * checkAll使用方法：$(".kListBox").checkAll();
 * $(".kListBox"): 包含全选和单个复选框的父层元素
 */
(function($){
    $.fn.checkAll = function(options){
        var defaults = {
            allBtn: ".checkAll", //全选按钮元素
            boxBtn: ".checkBox", //复选框元素
            isCheck: "isChecked", //选中状态样式
			num: ".selectedNum"     //选中个数
        };
        var options = $.extend(defaults,options);
        return this.each(function(){
            var _this = $(this),//指向当前调用此方法的对象
               _allBtn = _this.find(options.allBtn),//this对象下的选择按钮
               _boxBtn = _this.find(options.boxBtn).not(options.allBtn),//this对象下的选择框
               _isCheck = options.isCheck;
			   _num = _this.find(options.num);
            //判断全选按钮的状态，如果处于选中状态，box全部选中，否则不选中
            _allBtn.unbind("click").bind("click",function(){
                _allBtn.toggleClass(_isCheck);
                if($(this).hasClass(_isCheck)){
                    _boxBtn.addClass(_isCheck);
					_num.text(_boxBtn.length);
                }else{
                    _boxBtn.removeClass(_isCheck);
					_num.text(0);
                }
            });
            //判断box的状态，如果有box处于没选择的状态，则全选按钮不选中
            function allChk(){
                var i=0,len = _boxBtn.length;
                _boxBtn.each(function(){
                    if($(this).hasClass(_isCheck)){
                        ++i;
                    }
                });
                if(i==len && len != 0){
                    _allBtn.addClass(_isCheck);
                }else{
                    _allBtn.removeClass(_isCheck);
                }
				_num.text(i);
            }
            allChk();
            _boxBtn.click(function(){
                $(this).toggleClass(_isCheck);
                //判断checkBox是否有未选中的元素,len为box所有元素的长度，i为选中元素的长度
                allChk();
            });
        });
    }
})(jQuery);


//点击回复按钮弹出回复框
var z_replyPrevID = null;
function replay(id,url){
	html  = '<dl id="r_'+id+'">';
	html += '<dt><img src="'+url+'" width="50" height="50" /></dt>';
	html += '<dd>';
	html += '<div class="replyArea clearfix">';
	html += '<span class="replyAreaArrow"></span>';
	html += '<span class="replyAreaT"></span>';
	html += '<textarea class="replyAreaC"></textarea>';
    html += '<span class="replyAreaB"></span>';
    html += '<span class="tipsWrap">我要回复</span>';
	html += '</div>';
	html += '<p class="textAreaTips mt10"><a href="javascript:;" class="fr publishBtn">发表</a>还可以输入<span id="replyTextNum">300</span>个字</p>';
	html += '</dd>';
	html += '<span class="replyArrow"></span>';
	html += '</dl>';
    var nowId = "#r"+id;
	$(nowId).after(html);
    replyTips();
    if(z_replyPrevID){
        $("#r_"+z_replyPrevID).remove();
    }
    z_replyPrevID = id;
}
$(function(){
    //回复框输入字数
	var len = 0;
	function gbLen(obj,target){
		obj.keyup(function(){
			var str = obj.text();
			var reg = /[^\u4e00-\u9fa5]/;
			if(reg.test(str)){
				len +=2;
			}else{
				len++;
			}
			len2 = parseInt(300-len);
			target.text(len2);
		});
	};
	//评论字数限制
	$(".textArea .textAreaC").live("keydown",function(){
		var length = $.trim($(this).text()).length;
		if(300-length < 0){
			$(".publishBtn").addClass("disPublish");
			$('.publishBtn').attr('title',"评论内容不能超过300个字符.");
			$("#txt").text("已超出");
			$("#textNum").html(length-300);
		}else{
			$(".publishBtn").removeClass("disPublish");
			$('.publishBtn').removeAttr('title');
			$("#txt").text("还可以输入");
			$("#textNum").html(300-length);
		}
	});
		
});

//底部固定
function reHeight(box){
	$(box).removeAttr("style");
	var sHeight = $(window).height();
	var cHeight = $(box).height()+82;
	var fHeight = $(".footer").outerHeight([true]);
	if((cHeight+fHeight) < sHeight){
		if($.browser.msie&&($.browser.version == "6.0")){
			$(box).css({"height":"auto !important","height":sHeight-fHeight-82});
		}else{
			$(box).css({"min-height":sHeight-fHeight-82});
		}
	}
}	
$(function(){
	reHeight("body > .outerWrap");
	//回到顶部
    $("body").append('<div class="returnTop"><a href="javascript:;" id="returnTop"></a></div>');
    $(window).scroll(function(){
        var scrolltop = $(window).scrollTop();
        if(scrolltop >200){
            $(".returnTop").fadeIn(300);
        }else{
            $(".returnTop").fadeOut(300);
        }
    });
    $("#returnTop").click(function(){
        $('html,body').animate({scrollTop:0},300);
    });	
	
	//单选
	$(".imgRadio").live("click",function(){
		$(this).parents(".radioItems").find(".imgRadio").removeClass("isChecked");
		$(this).addClass("isChecked");
	});
	//tips
	$(".tipsPop").live({
		"mouseover":function(){
			var txt = $(this).children("i").text();
			var tips = '<span class="tips">'+txt+'<b class="lRadius"></b><b class="rRadius"></b><b class="tipsArr"></b></span>';
			$(this).append(tips);
		},
		"mouseout":function(){
			$(this).find(".tips").remove();
		}
	});
});

/**
 * messager
 */
(function($){
	//显示窗口
	function show(win,time){
		win.w.show().find('.popOpt a:first').focus();
		fixPosition(win);
		$(window).resize(function(){
			fixPosition(win);
		});
		var timer = null;
		if(time>0){
			timer= setTimeout(function(){
				win.w.hide();
				win.bg.hide();
			}, time);
		}
	}
	//固定窗口位置
	function fixPosition(win){
		win.w.css({left:($(window).width()-win.w.width())/2,top:($(window).height()-win.w.height())/2});
		win.bg.css({"width":$("body").width(),"height":$("body").height()});
	}
	//隐藏窗口
	function hide(win){
		win.hide().remove();
	}
	//创建窗口
	function createDialog(title, content, buttons){
		var win=$('<div class="popLayer"></div>').appendTo('body');
		var layoutBg=$('<div id="layoutBg"></div>').appendTo('body')
		.css({"width":$("body").width(),"height":$("body").height()});//遮罩层
		var title=$('<h3>'+title+'</h3>').appendTo(win);
		$('<a href="javascript:;" class="closePop"></a>').bind('click',function(){hide(win);hide(layoutBg);})
		.appendTo(title);
		win.append('<div class="popMain"><p class="popCont">'+content+'</p></div>');
		if (buttons){
			var tb = $('<div class="popOpt"></div>').appendTo(win);	
			for(var b in buttons){
				$('<a class="popBtn subBtn" href="javascript:;"></a>')
				.text(b).bind('click',eval(buttons[b]))
				.appendTo(tb);
			}
		}
		return {w:win,bg:layoutBg};//包含窗口和遮罩层两部分
	}
	
	$.messager={
		alert:function(title,msg,icon,fn){
			title=(title&&title!='')?title:'提示';
			var iconClass='';
			switch(icon){
				case 'error':iconClass='popIcon errorTxt';
				break;
				case 'info':iconClass='popIcon infoTxt';
				break;
				case 'warning':iconClass='popIcon warnTxt';
				break;
				case 'ok':iconClass='popIcon okTxt';
				break;
			}
			var content = '<span class="'+iconClass+'">' + msg + '<span>';
			var buttons={};
			buttons[$.messager.defaults.ok]=function(){
				hide(win.w);
				hide(win.bg);
				if (fn){
					fn();
				}	
			}
			var win = createDialog(title,content,buttons);
			show(win);
		},
		confirm:function(title,msg,fn){
			title=(title&&title!='')?title:'确认';
			
		}
			
	};
	$.messager.defaults = {
		ok: '确定',
		cancel: '取消'
	};
})(jQuery);	
