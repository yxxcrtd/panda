<#if obj??>
	<#if ("文章" == objTypeName)><#assign url = "oppTool/article/toAddAppArticle.do" />
	<#elseif ("图片" == objTypeName)><#assign url = "oppTool/photo/toAddPhoto.do" />
	<#elseif ("资源" == objTypeName)><#assign url = "oppTool/resource/toAddAppResource.do" />
	<#elseif ("视频" == objTypeName)><#assign url = "oppTool/video/toAddAppVideo.do" />
	<#else><#assign url = "oppTool/article/toAddAppArticle.do" />
	</#if>
</#if>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>上传成果信息结果</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/index.css">
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>

	<body>
		<#include "../SnsHeader.ftl" />
		
		<#-- 正文 start-->
		<div class="outerWrap borderWrap">
			<div class="succBox">
				<p class="txtInfo">
					<span class="succIcon"></span>
					<span class="succTxt">
						${appObjCategoryIdName!}成果${objTypeName!}发布已成功<br />
						<#--<em><span id="s">3</span> 秒钟以后返回上一级页面</em>-->
						<em><a href="upload2List.do?obj=${obj!}&arch.archId=${appObjId!}">返回成果内容列表</a></em>
					</span>
				</p>
				<div class="optBtn mt25">
					<a class="grayBtn" href="upload2.do?obj=${obj!}&arch.archId=${appObjId!}">选择其他形式发布</a>
					<a class="grayBtn" href="${Config.snsUrl!}/${url!}?groupId=0&appObjId=${appObjId!}&appObjName=${obj!}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}">继续发布${objTypeName!}</a>
				</div>
			</div>
		</div>
		<div class="btShadow pngfix"></div>
		<#-- 正文 end-->
		
		<#include "../Footer.ftl" />
		
		<script type="text/javascript" src="js/common.js"></script>
		<script language="javascript" type="text/javascript">
		<!--
		var i = 3;
		var intervalid;
		intervalid = setInterval("fun()", 1000);
		function fun() {
			if (0 == i) {
				window.location.href = "upload2List.do?obj=${obj!}&arch.archId=${appObjId!}";
				clearInterval(intervalid);
			}
			document.getElementById("s").innerHTML = i;
			i--;
		}
		//-->
		</script>
	</body>
</html>
