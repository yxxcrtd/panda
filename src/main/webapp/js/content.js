
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
    //赞
	$("a.o2").click(function(){
        $(".operateCont").toggle(100).children(".praise").show();
		var o2Tips = $("a.o2").text();
		if(o2Tips == "赞"){
			$(this).text("已赞").addClass("hover");
		}else{
			$(this).text("赞").removeClass("hover");
		}
		if($(".praise").is(":visible")){
			$(".operateCont ul").hide();
		}
	});
	$(".praise").click(function(){
		$(".operateCont ul").toggle(100);
	});
	//收藏
	$("a.o3").click(function(){
		var o3Tips = $("a.o3").children("b").text();
		$(this).toggleClass("hover");
		if(o3Tips == "收藏"){
			$("span.operateTips").show().children("span").text("收藏成功！").animate({"display":"block"}, 1000,function(){
				$("span.operateTips").hide();
			});
			$(this).children("b").text("取消收藏");
		}else{
			$("span.operateTips").show().children("span").text("取消成功！").animate({"display":"block"}, 1000,function(){
				$("span.operateTips").hide();
			});
			$(this).children("b").text("收藏");
		}
	});
    
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
	 
	//评论
	$(".textAreaC").replyBox({
		par: ".textArea",
		cur: "textAreaH",
		tLeft: "14px",
		tTop: "2px"
	});
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
	$(".textArea .textAreaC").live("keyup",function(){
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
		
	$('.publishBtn').click(function(){
		succTip("发表成rrrr功");
	});
});
