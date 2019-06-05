<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>关键词</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" />
		<#include "../Obj.ftl" />
		<div class="outerWrap borderWrap clearfix">
		    <div class="f_searchTit">
				<p class="seTixt fl">包含关键词：”<i>${k!}</i>“的科研成果列表<span>共<#if keywordList??>${keywordList.size()!}</#if>条科研成果</span></p>
		    </div>
		    <div class="f_searchBox">
		        <ul class="f_searchCont">
					<#if keywordList??>
						<#list keywordList as k>
				            <li class="clearfix">
				            	<#if (archList??)>
				            		<#list archList as a>
				            			<#if (a.archId! == k.archId!)>
							            	<span class="fr">${Util.getDateByLong(a.createTime!, "yyyy-MM-dd")}</span>
							            	<span class="fr"><a href="his.do?userId=${a.userId!}">${Util.getFirstString(a.author!, ",")}</a></span>
							            	<b>【<@compress single_line=true><@getNameByObj "${a.category!}" /></@compress>】</b><a href="arch_detail.do?arch.archId=${a.archId!}" target="_blank">${a.title!}</a>
							            </#if>
				            		</#list>
				            	</#if>
					        </li>
						</#list>
					</#if>
		        </ul> 
				<div class="w_pagination">
					<#include "../Pager.ftl" />
				</div>
		    </div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script type="text/javascript">
		<!--
		$(function() {
			// 列表隔行换色
			$(".f_searchCont li:even").addClass("oddBg");
		});
		//-->
		</script>
	</body>
</html>
