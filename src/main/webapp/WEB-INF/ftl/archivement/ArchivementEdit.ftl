<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title><#if (0 == arch.archId!)>上传<#else>编辑</#if><@compress single_line=true><@getNameByObj "${obj!}" /></@compress></title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/index.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" />
		<div class="outerWrap borderWrap">
			<#include "ArchivementEditForm.ftl" />
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<#include "ArchivementEditAddAuthor.ftl" />
		<script type="text/javascript" src="js/jscroll.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script src="My97DatePicker/WdatePicker.js"></script>
		<!--[if IE 6]>
		<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
		<script type="text/javascript">
			DD_belatedPNG.fix('.pngfix,.pngfix:hover');
		</script>
		<![endif]-->
		<script type="text/javascript" src="js/arch.js"></script>
		<script type="text/javascript">
		<!--
		
		function setStage(value){
			$("#TopicResearchStageId").val(value);
		}
		
		function getStage(value){
			$.get("query_stage.do",{"topicId":value},function(data){
				$("#stageSelect li").remove();
				if(1==data.result){
					var array=data.data;
					for(var i=0;i<array.length;i++){
						$("#stageSelect").append("<li><a href='javascript:;' onclick='setStage("+array[i].id+");'>"+array[i].name+"</a></li>");
					}
				}else{
					alert(data.data);
				}
			});
		}
		$(function() {
			//课题选择是否显示
			var topicFlag = ${topicFlag};
			if(topicFlag==1){
				$(".selTopic").first().removeClass("none");
				$("#topicSelect").find("li").bind("click",function(){
					$("#topicResearchId").val($(this).attr("id"));
					$("#TopicResearchName").val($(this).text());
					getStage($(this).attr("id"));
				});
				
			}else if(topicFlag==3){
				$(".selTopic").first().removeClass("none");
				$(".issueSel").first().addClass("none");
				$(".selTopic p:first").text("您想在'"+${TopicResearchName!"课题名称为空"}+"'课题的那个阶段发表论文？")
				$("#topicResearchId").val(${topicResearchId!});
				$("#TopicResearchName").val(${TopicResearchName!});
				getStage(${topicResearchId});
			}
			
			var titleNode = $("input[name='arch.title']");
			var summaryNode = $("textarea[name='archExtra.summary']");
			$("#arch_next").bind("click", function() {
				if ("" == $.trim(titleNode.val())) {
					$.messager.alert("提示：", "名称不能为空！", "warning", function() {
						titleNode.attr("placeholder", "");
						titleNode.focus();
					});
					return false;
				}
				if ("" == $.trim(summaryNode.val())) {
					$.messager.alert("提示：", "简介不能为空！", "warning", function() {
						summaryNode.attr("placeholder", "");
						summaryNode.focus();
					});
					return false;
				}
				// span下class为radioType的imgRadio点击时候，让隐藏域的值=当前点击的label属性为value的值
				$("#typeId").val($(".radioType .isChecked").attr("value"));
				// 为著作或课件的时候不用获取了
				<#if ("thesis" == obj! || "other" == obj!)>
					$("#publishId").val($(".radioPublish .isChecked").attr("value"));
				</#if>
				$("#prizeId").val($(".radioPrize .isChecked").attr("value"));
				<#if ("patent" == obj!)>
					$("#servicePatentId").val($(".radioServicePatent .isChecked").attr("value"));
					$("#authorizeId").val($(".radioAuthorize .isChecked").attr("value"));
				</#if>
				$.ajax({
					type: "POST",
					url: "arch_save.do",
					data: $("#archForm").serialize(),
					success: function(data) {
						if ("a" == data) {
						} else if ("unknown" == data) {
							errorTip("发生未知错误，请稍后重试！");
						} else {
							$.messager.alert("", "<@compress single_line=true><@getNameByObj "${obj!}" /></@compress><#if (0 == arch.archId!)>上传<#else><#if (upd)>发布<#else>修改</#if></#if>成功！", "ok", function() {window.location.href="upload2<#if (!appArticleList?? && !appResourceList?? && !appPhotoList?? && !appVideoList??)><#else>List</#if>.do?obj=${obj!}&arch.archId=" + data});
						}
					}
				});
			});
		});
		//-->
		</script>
	</body>
</html>
