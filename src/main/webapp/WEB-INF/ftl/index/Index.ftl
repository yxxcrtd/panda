<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>科研成果首页</title>
		<link type="text/css" rel="stylesheet" href="${base}/css/common.css">
		<link type="text/css" rel="stylesheet" href="${base}/css/index.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	
	<body>
		<#include "../SnsHeader.ftl" />
		<#include "ObjMacro.ftl" />
		<div class="outerWrap borderWrap bgWrap clearfix">
			<div class="topNav clearfix">
				<ul class="navList fl">
					<li class="homeNav"><span>科研成果</span></li>
					<li><a href="category.do?obj=thesis"><@getNameByObj "thesis" /></a></li>
					<li><a href="category.do?obj=book"><@getNameByObj "book" /></a></li>
					<li><a href="category.do?obj=courseware"><@getNameByObj "courseware" /></a></li>
					<li><a href="category.do?obj=instruction"><@getNameByObj "instruction" /></a></li>
					<li><a href="category.do?obj=patent"><@getNameByObj "patent" /></a></li>
					<li class="lastNav"><a href="category.do?obj=other"><@getNameByObj "other" /></a></li>
				</ul>
				<div class="searchBox fr">
					<#include "../Search.ftl" />
				</div>
			</div>
			<div class="fl mainLeft">
				<div class="hotResearch mt15">
					<div class="modTit clearfix"><h2>最热科研成果</h2></div>
					<@objHotList thesisHotArchList!, "thesis" />
					<@objHotList bookHotArchList!, "book" />
					<@objHotList coursewareHotArchList!, "courseware" />
					<@objHotList instructionHotArchList!, "instruction" />
					<@objHotList patentHotArchList!, "patent" />
					<@objHotList otherHotArchList!, "other" />
				</div>
				<div class="newResearch mt25">
					<div class="modTit clearfix"><h2>最新科研成果</h2></div>
					<div class="newReTabNav">
						<a class="curr" href="javascript:;"><i class="leftR"></i><span>全部</span><i class="rightR"></i></a>
						<a href="javascript:;"><i class="leftR"></i><span><@getNameByObj "thesis" /></span><i class="rightR"></i></a>
						<a href="javascript:;"><i class="leftR"></i><span><@getNameByObj "book" /></span><i class="rightR"></i></a>
						<a href="javascript:;"><i class="leftR"></i><span><@getNameByObj "courseware" /></span><i class="rightR"></i></a>
						<a href="javascript:;"><i class="leftR"></i><span><@getNameByObj "instruction" /></span><i class="rightR"></i></a>
						<a href="javascript:;"><i class="leftR"></i><span><@getNameByObj "patent" /></span><i class="rightR"></i></a>
						<a href="javascript:;"><i class="leftR"></i><span><@getNameByObj "other" /></span><i class="rightR"></i></a>
					</div>
					<div class="newReTab mt20">
						<@objNewList allNewArchList!, "a" />
						<@objNewList thesisNewArchList!, "" />
						<@objNewList bookNewArchList!, "" />
						<@objNewList coursewareNewArchList!, "" />
						<@objNewList instructionNewArchList!, "" />
						<@objNewList patentNewArchList!, "" />
						<@objNewList otherNewArchList!, "" />
					</div>
				</div>
				<#include "StatList.ftl" />
			</div>
			<div class="fr sider">
				<#include "SiteStat.ftl" />
				<#include "Profile.ftl" />
				<div class="sbtnBox mt10">
		        	<a href="upload.do" class="greenBtn btnW245"><span>上传科研成果</span></a>
		        </div>
		        <#include "Tag.ftl" />
			</div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
		<!--[if IE 6]>
		<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
		<script type="text/javascript">
			DD_belatedPNG.fix('.pngfix,.pngfix:hover');
		</script>
		<![endif]-->
		<script type="text/javascript">
		<!--
		$(function() {
			$(".newReTabNav a").live("click", function() {
				$(this).addClass("curr").siblings().removeClass("curr");
			});
			$(".newReTabNav a").live("click", function() {
				var index = $(this).index();
				$(this).addClass("curr").siblings().removeClass("curr");
				$(".newReTab").find(".modList").hide().eq(index).show();
			});
		});
		//-->
		</script>
	</body>
</html>
