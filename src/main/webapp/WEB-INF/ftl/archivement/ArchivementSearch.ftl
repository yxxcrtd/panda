<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>搜索结果</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	
	<body>
		<#include "../SnsHeader.ftl" />
		<#include "../Obj.ftl" />
		
		<!-- 正文 start-->
		<div class="outerWrap borderWrap clearfix">
		    <div class="f_searchTit">
				<p class="seTixt fl">包含”<i>${k!}</i>“的科研成果列表<span>共<#if archPageList??>${archPageList.totalCount!}</#if>条科研成果</span></p>
				<div class="searchBox fr">
					<#include "../Search.ftl" />
				</div>
		    </div>  
		    <div class="f_searchBox">
		        <ul class="f_searchCont">
					<#if archPageList??>
						<#if archPageList.data??>
							<#list archPageList.data as a>
					            <li class="clearfix">
					            	<span class="fr">${Util.getDateByLong(a.createTime!, "yyyy-MM-dd")}</span>
					            	<span class="fr"><a href="his.do?userId=${a.userId!}">${Util.getFirstString(a.author!, ",")}</a></span>
					            	<b>【<@compress single_line=true><@getNameByObj "${a.category!}" /></@compress>】</b><a href="arch_detail.do?arch.archId=${a.archId!}" target="_blank">${a.title!}</a>
					            </li>
								<#--if (4 == a_index % 5)>
									<li class="clearfix"></li>
								</#if-->
							</#list>
						</#if>
					</#if>
		        </ul> 
		         <!-- 分页 start-->
				<div class="w_pagination">
					<#include "../Pager.ftl" />
				</div>
		        <!-- 分页 end-->   
		    </div>
		</div>
		<div class="btShadow pngfix"></div>
		<!-- 正文 end-->
		<#include "../Footer.ftl" />
		<script type="text/javascript">
		<!--
		$(function() {
			// 视频、图片鼠标滑过
			$(".maskBox").live({
				"mouseover":function(){
					$(this).find(".imgMask, .maskCont, .f_tabTip").show();
				},
				"mouseout":function(){
					$(this).find(".imgMask, .maskCont, .f_tabTip").hide();
				}
			});
			// 鼠标滑过列表
			$(".f_searchCont li").live({
				"mouseover":function(){
					$(this).addClass("liOn");
				},
				"mouseout":function(){
					$(this).removeClass("liOn");
				}
			});
			//文章列表隔行换色
			$(".f_searchCont li:even").addClass("oddBg");
		});
		//-->
		</script>
	</body>
</html>
