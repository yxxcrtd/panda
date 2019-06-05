<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>科研成果详情</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" />
		<div class="outerWrap borderWrap bgWrap clearfix">
			<div class="mainLeft fl">
		        <div class="f_xiangqing">
		        	<h2>
		                <p class="fr praiseColl">
		                	<a href="javascript:;" class="collection <#if (op.collect!)>hasColl</#if>">收藏</a>
		                	<a href="javascript:;" class="praise <#if (op.praise!)>hasPra</#if>">赞</a>
		                </p>
		                ${arch.title!}   
		            </h2>
		            <div class="titXq"><b class="fr">上传时间：${Util.getDateByLong(arch.createTime!, "yyyy-MM-dd")}</b>作者
			            <#if (authorList??)>
			            	<#list authorList as a>
			            		<#if ("" != a.nameId!)>
			            			<em><a href="${Config.snsUrl!}/personhome/home/index.do?userId=${a.nameId!}" target="_blank">${a.name!}</a></em>
			            		<#else>
			            			<em>${a.name!}</em>
			            		</#if>
			            	</#list>
			            </#if>
		            </div>
		            <p>${arch.archExtra.summary!}</p>
		        </div>
	        	<#include "ArchivementDetailResult.ftl" />
				<#include "ArchivementDetailTopic.ftl" />
			</div>
			<div class="sider fr">
				<#include "ArchivementDetailRight.ftl" />
		    </div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script src="js/jquery.replyBox.js" type="text/javascript"></script> 
		<script src="js/common.js" type="text/javascript"></script> 
		<script type="text/javascript">
		<!--
		$(function() {
			/*$("#collect").on("click", function() {
				$.get("op_collect.do", {"archId" : ${arch.archId!}}, function(data) {
					if ("success" == data) {
						if ("收藏" == $("#collect").html()) {
							$("#collect").html("已收藏");
						} else {
							$("#collect").html("收藏");
						}
					} else {
						$.messager.alert("提示：", "操作失败！", "error", "");
					}
				});
			});*/
			// 收藏
			$(".collection").live("click", function() {
				var This = $(this);
				$.get("op_collect.do", {"archId" : ${arch.archId!}}, function(data) {
					if ("success" == data) {
						This.toggleClass("hasColl");
					} else {
						$.messager.alert("提示：", "操作失败！", "error", "");
					}
				});
			});
			// 赞
			$(".praise").live("click", function() {
				var This = $(this);
				$.get("op_praise.do", {"archId" : ${arch.archId!}}, function(data) {
					if ("success" == data) {
						This.toggleClass("hasPra");
					} else {
						$.messager.alert("提示：", "操作失败！", "error", "");
					}
				});
			});
			// 评论
			$(".textAreaC").replyBox({
				par: ".textArea",
				cur: "textAreaH",
				tLeft: "14px",
				tTop: "2px"
			});
			tabsShow(".f_tabTit a",".f_tabCont> div", "active");
			// 视频、图片鼠标滑过
			$(".maskBox").live({
				"mouseover":function() {
					$(this).find(".imgMask, .maskCont").show();
				},
				"mouseout":function() {
					$(this).find(".imgMask, .maskCont").hide();
				}
			});
			// 话题
			$(".publishBtn").live("click", function() {
				var topicText = $(".textArea .textAreaC").html();
				reg = /^#(\S|\s)+#(\S|\s)*$/;
				if (reg.test(topicText)) {
		    		var temp = topicText.substring(1);
		    		var index = temp.indexOf("#");
		    		var title = $.trim(temp.substring(0, index).replace(/&nbsp;/g, ""));
		    		var content = $.trim(temp.substring(index + 1));
		    		if("" == title) {
		    			$.messager.alert("提示：", "请填写话题标题", "warning", "");
		    			return false;
		    		} else if (30 < title.length) {
		    			$.messager.alert("提示：", "话题标题长度最大为30", "warning", "");
		    			return false;
		    		}
		    		if (500 < content.length) {
		    			$.messager.alert("提示：", "话题内容长度最大为500", "warning", "");
		    			return false;
		    		}
		    		// 提交
					$.get("arch_topic.do", { "archId" : ${arch.archId!}, "objTypeName" :  "${arch.title!}", "topic" : "#" + title + "#", "content" : content }, function(data) {
						if ("success" == data) {
							succTip("发布成功！");
							window.location.reload();
						} else {
							$.messager.alert("提示：", data, "error", "");
						}
					});
				} else {
					$.messager.alert("提示：", "格式有误，正确格式：#标题#话题内容", "warning", "");
					return false;
				}
			});
		});
		//-->
		</script>
	</body>
</html>
