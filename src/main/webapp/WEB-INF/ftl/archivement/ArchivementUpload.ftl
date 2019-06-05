<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>上传科研成果</title>
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
					<h2>上传科研成果</h2>
				</div>
				<dl class="selMod">
					<dt class="sTit">请选择成果相对应的内容形式发布</dt>
					<dd class="selList clr">
						<a href="arch_edit.do?obj=thesis<#if topicResearchId??&&topicResearchName??>&topicResearchId=${topicResearchId!}&topicResearchName=${topicResearchName!}</#if>"><span class="lwIcon"></span><@getNameByObj "thesis" /></a>
						<a href="arch_edit.do?obj=book<#if topicResearchId??&&topicResearchName??>&topicResearchId=${topicResearchId!}&topicResearchName=${topicResearchName!}</#if>"><span class="zzIcon"></span><@getNameByObj "book" /></a>
						<a href="arch_edit.do?obj=courseware<#if topicResearchId??&&topicResearchName??>&topicResearchId=${topicResearchId!}&topicResearchName=${topicResearchName!}</#if>"><span class="kjIcon"></span><@getNameByObj "courseware" /></a>
						<a href="arch_edit.do?obj=instruction<#if topicResearchId??&&topicResearchName??>&topicResearchId=${topicResearchId!}&topicResearchName=${topicResearchName!}</#if>"><span class="jxIcon"></span><@getNameByObj "instruction" /></a>
						<a href="arch_edit.do?obj=patent<#if topicResearchId??&&topicResearchName??>&topicResearchId=${topicResearchId!}&topicResearchName=${topicResearchName!}</#if>"><span class="zlIcon"></span><@getNameByObj "patent" /></a>
						<a href="arch_edit.do?obj=other<#if topicResearchId??&&topicResearchName??>&topicResearchId=${topicResearchId!}&topicResearchName=${topicResearchName!}</#if>"><span class="qtIcon"></span><@getNameByObj "other" /></a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
	</body>
</html>