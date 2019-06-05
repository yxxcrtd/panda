<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>成果内容</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/index.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" />
		<div class="outerWrap borderWrap">
			<div class="subBox">
				<div class="modTit clearfix">
					<h2><#if (0 == arch.archId!)>上传<#else>编辑</#if><@compress single_line=true><@getNameByObj "${obj!}" /></@compress></h2>
				</div>
				<ul class="stepNav mt30 clearfix">
					<li class="baseLink"><a href="<#if (0 == arch.archId!)>javascript:;<#else>arch_edit.do?obj=${obj!}&archId=${arch.archId!}<#if upd??>&upd=true</#if></#if>">基本信息</a><i class="navArr"></i></li>
					<li class="currStep"><a href="<#if (0 == arch.archId!)>javascript:;<#else>upload2<#if (!appArticleList?? && !appResourceList?? && !appPhotoList?? && !appVideoList??)><#else>List</#if>.do?obj=${obj!}&arch.archId=${arch.archId!}</#if>">成果内容</a></li>
				</ul>
				<div class="selMod">
					<p class="sTit">请选择成果相对应的内容形式发布</p>
					<div class="sortList clearfix">
						<div class="innerList fl">
							<a href='${Config.snsUrl!}/oppTool/article/toAddAppArticle.do?groupId=0&appObjId=${arch.archId!}&appObjName=${arch.title!?url("utf-8")}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="artIcon"></span>发表文章</a>
							<a href='${Config.snsUrl!}/oppTool/photo/toAddPhoto.do?groupId=0&appObjId=${arch.archId!}&appObjName=${arch.title!?url("utf-8")}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="phoIcon"></span>图片</a>
							<a href='${Config.snsUrl!}/oppTool/resource/toAddAppResource.do?groupId=0&appObjId=${arch.archId!}&appObjName=${arch.title!?url("utf-8")}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="resouIcon"></span>资源</a>
							<a href='${Config.snsUrl!}/oppTool/video/toAddAppVideo.do?groupId=0&appObjId=${arch.archId!}&appObjName=${arch.title!?url("utf-8")}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="videoIcon"></span>视频</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
	</body>
</html>
