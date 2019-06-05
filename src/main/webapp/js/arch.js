$(".subBox").on("click",".issueSel",{list:".myOptions",selV:".selValue", fn:function(sel){
	var selected = $(sel).parents(".myOptions").siblings(".selValue").text();
	if(selected != "请选择课题"){
		$(".stageSel").show();
	}else{
		$(".stageSel").hide();
	};
}}, simSelect);

$(".subBox").on("click",".stageSel",{list:".myOptions",selV:".selValue"},simSelect);

// 选择获奖等级
$(".baseBox").on("click", ".selLevel", {list:".options", selV:".selValue", fn:function(sel) {
	$("#prizeLevelId").val($(sel).parents(".prizeLevel").val());
}}, simSelect);

// 选择教育类型
//$(".baseBox").on("click", ".selType", {list : ".options", selV : ".selValue", fn : function(sel) {
//	$("#eduTypeId").val($(sel).parents(".eduType").val());
//	var selected = $(sel).parents(".options").siblings(".selValue").text();
//	// 学段选择框
//	var $semester = $(sel).parents(".selType").siblings(".selSemester");
//	if ("幼儿教育" == selected) {
//		$semester.hide();
//	} else {
//		$semester.show();
//	}
//}}, simSelect);

// 选择学段
$(".baseBox").on("click", ".selSemester", {list:".options", selV:".selValue", fn : function(sel) {
	$("#gradeId").val($(sel).parents(".gradeId").val());
}}, simSelect);

// 选择学科
$(".baseBox").on("click",".selSub",{list:".options",selV:".selValue", fn : function(sel) {
	$("#subjectId").val($(sel).parents(".subjectId").val());
}}, simSelect);

// 下拉框滑鼠
$(".mySelect").live({
	mouseover:function(){
		$(this).addClass("mySelHover");
	}, mouseout:function(){
		$(this).removeClass("mySelHover");
	}
});

// 是否获奖、是否出版
$(".whether .imgRadio").live("click", function() {
	var $hid = $(this).parents(".whether").siblings(".hasBox");
	if ($(this).hasClass("hasbeen")) {
		$hid.show();
	} else {
		$hid.hide();
	}
});

// 鼠标放作者名上
$(".auName").live({
	mouseover:function() {
		$(this).find(".delItem").show();
	}, mouseout:function() {
		$(this).find(".delItem").hide();
	}
});

// 鼠标放标签上
$(".addTags .tagItem").live({
	mouseover:function() {
		$(this).addClass("onTag").find(".delItem").show();
	}, mouseout:function() {
		$(this).removeClass("onTag").find(".delItem").hide();
	}
});

// 添加作者弹层
$(".addAuthor").live("click",function(){
	popup($(".popFriends"),function(){
		scrollBarShow($(".scrollF"));
	});
});

// 删除作者
$(".delAu").live("click", function() {
	var This = this;
	var cur = parseInt($(This).siblings(".did").text());
	confirmTip("确定删除？", function() {
		if (0 < cur) {
			$.get("adel.do", {"archId" : cur}, function(data) {
				if ("success" != data) {
					$.messager.alert("提示：", data, "error", "");
					return false;
				}
			});
		}
		delItem($(This).parents(".auName"), succTip);
	});
});

// 删除关键词
$(".delTag").live("click", function() {
	var This = this;
	var cur = parseInt($(This).siblings(".did").text());
	confirmTip("确定删除？", function() {
		if (0 < cur) {
			$.get("kdel.do", {"archId" : cur}, function(data) {
				if ("success" != data) {
					$.messager.alert("提示：", data, "error", "");
					return false;
				}
			});
		}
		delItem($(This).parents(".tagItem"), succTip);
	});
});

// 填加作者
$("#addAuthor").live("click", function() {
	var iptV = $.trim($(this).prev(".txtStyle").val());
	var auHtml = '<span class="auName"><span class="showspan">' + iptV + '</span><a href="javascript:;" class="delItem delAu"></a><input type="hidden" name="authors" value="' + iptV + '" /><span class="did" style="display: none;">0</span></span>';
	var $auBox = $(this).parents(".iptBox").siblings(".auList");
	var auArr = [];
	if ("" == iptV) {
		$.messager.alert("提示：", "作者不能为空！", "warning", "");
		return false;
	} else {
		$auBox.find(".auName").each(function() {
			auArr.push($.trim($(this).find(".showspan").text()));
		});
		if (0 < $auBox.length) {
			//update nlf
			$auBox.append(auHtml);
			/*if (!findSame(iptV, auArr)) {
				$auBox.append(auHtml);
			} else {
				$.messager.alert("提示：", "作者已经存在！", "warning", "");
				return false;
			};*/
		};
	};
});

// 填加关键词
$("#addTag").live("click", function() {
	var iptV = $.trim($(this).prev(".txtStyle").val());
	var tagHtml = '<span class="tagItem"><i class="tagRad"></i><span class="showspan">' + iptV + '</span><a href="javascript:;" class="delItem delTag"></a><input type="hidden" name="keywords" value="' + iptV + '" /><span class="did" style="display: none;">0</span></span>';
	var $tagBox = $(this).parents(".iptBox").siblings(".addTags");
	var tagArr = [];
	if ("" == iptV) {
		$.messager.alert("提示：", "关键词不能为空！", "warning", "");
		return false;
	} else {
		$tagBox.find(".tagItem").each(function() {
			tagArr.push($.trim($(this).find(".showspan").text()));
		});
		if (0 < $tagBox.length) {
			if (!findSame(iptV, tagArr)) {
				$tagBox.append(tagHtml);
			} else {
				$.messager.alert("提示：", "关键词已经存在！", "warning", "");
				return false;
			}
		};
	};
});

// 添加作者
$(".popFriends .subBtn").live("click",function() {
	var $box = $(this).parents(".popLayer");
	var $nameBox = $box.find(".selectedF:not('.allF')");
	var aut = '';
	var auArr = [];
	var sameArr = [];
	//已有作者数组
	var hasArr = [];
	var $has = $(".auList .auName");
	$has.each(function() {
		hasArr.push($.trim($(this).find(".showspan").text())); 
	});
	$nameBox.each(function() {
		var name = $(this).siblings(".friendName").text();
		var nameId = $(this).siblings(".nameId").text();
		auArr.push($.trim(name));
		aut += '<span class="auName"><span class="showspan">'+name+'</span><a href="javascript:;" class="delItem delAu"></a><input type="hidden" value="' + name + ',' + nameId + '" name="authors"><span class="did" style="display: none;">0</span></span>';
	});
	//查找两个数组中是否有相同的
	for(var i=0; i<hasArr.length;i++){
		for(var j=0; j<auArr.length;j++){
			if(hasArr[i]==auArr[j]){
				sameArr.push(hasArr[i]);
			}
		}
	}
	closePop($box);
	if($nameBox.length > 0) {
		//不能添加相同的
		if(sameArr.length<=0){
			$(".auList").append(aut);
		}else{
			errorTip("不能重复添加作者!");
		}
	} else {
		errorTip("您没有选择作者!");
	};
});

// 查找用户
$("#SearchBtn").on("click", function() {
	var searchNode = $("#SearchInput");
	if ("" == $.trim(searchNode.val())) {
		$.messager.alert("提示：", "用户昵称不能为空！", "warning", function() {
			searchNode.attr("placeholder", "");
			searchNode.focus();
		});
		return false;
	} else {
		$.ajax({
			type : "POST",
			url : "arch_search_user.do",
			data : {
				"k" : $.trim(searchNode.val())
			},
			success : function(data) {
				$("div#showuser").empty();
				$("div#showuser").html(data);
				if ("" == $.trim($("div#showuser").text())) {
					$("#showuser").html("<center><br />没有找到用户！</center>");
				}
				// 添加作者弹层复选框
				$(".popfList").checkAll({
					allBtn: ".allF", //全选按钮元素
					boxBtn: ".selectF", //复选框元素
					isCheck: "selectedF" //选中状态样式
				});
			}
		});
	}
});
