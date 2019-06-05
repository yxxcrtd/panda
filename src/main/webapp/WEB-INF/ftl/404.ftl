<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
	  <title>错误信息提示</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/index.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>

	<body>
		<#include "SnsHeader.ftl" />
		
		<div class="outerWrap borderWrap clearfix">
			<div class="errorContPage">
				<div>
					<em class="endIcon"></em>
					<span class="oTipsTxt">亲~非常抱歉，<@s.actionerror cssClass="actionError" /></span>
				</div>
				<div class="mt15"><a href="javascript:history.go(-1);" class="bigBlueBtn">返 回</a></div>
			</div> 
		</div>
		
		<#include "Footer.ftl" />
	</body>
</html>