<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>科研成果二级</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>

	<body>
		<#include "../SnsHeader.ftl" />
		<#-- 正文 start-->
		<div class="outerWrap borderWrap bgWrap clearfix">
			<div class="mainLeft fl">
				<div class="topSort clearfix">
					<a href="index.do" class="fl backLink" title="返回首页"></a>
					<ul class="fl sortTab">
						<li <#if ("thesis" == obj)>class="sortOn"</#if>><a href="category.do?obj=thesis"><@getNameByObj "thesis" /></a><em class="topArr"></em></li>
						<li <#if ("book" == obj)>class="sortOn"</#if>><a href="category.do?obj=book"><@getNameByObj "book" /></a><em class="topArr"></em></li>
						<li <#if ("courseware" == obj)>class="sortOn"</#if>><a href="category.do?obj=courseware"><@getNameByObj "courseware" /></a></li>
						<li <#if ("instruction" == obj)>class="sortOn"</#if>><a href="category.do?obj=instruction"><@getNameByObj "instruction" /></a><em class="topArr"></em></li>
						<li <#if ("patent" == obj)>class="sortOn"</#if>><a href="category.do?obj=patent"><@getNameByObj "patent" /></a><em class="topArr"></em></li>
						<li <#if ("other" == obj)>class="sortOn"</#if>><a href="category.do?obj=other"><@getNameByObj "other" /></a><em class="topArr"></em></li>
					</ul>
					<#if ("courseware" != obj)>
						<div class="fl subSortBox">
							<#if dictTypeList??>
								<#list dictTypeList as d>
									<a href="category.do?obj=${obj!}&t=${d.id!}" <#if (t == d.id)>class="currSub"<#elseif (0 == t && 0 == d_index)>class="currSub"</#if>>${d.name!}</a>
								</#list>
							</#if>
						</div>
					</#if>
				</div>
				<p class="sequNav">
					<i>排序：</i>
					<a href="javascript:;" class="currS">课题研究==TODO==</a>
					<a href="category.do?obj=${obj!}&prize=true&t=${t!}">已获奖</a>
					<a href="category.do?obj=${obj!}&publish=true&t=${t!}">已出版</a>
					<a href="category.do?obj=${obj!}&t=${t!}">最新</a>
					<a href="category.do?obj=${obj!}&t=${t!}">最热</a>
				</p>
				<ul class="f_tixt">
        			<#if archPageList??>
        				<#if archPageList.data??>
	        				<#list archPageList.data as a>
								<li>
								    <h6><i>${a.viewCount!0}次浏览</i><a href="arch_detail.do?arch.archId=${a.archId!}">${a.title!}</a><#if (a.prize)><span class="f_icon f_iconY">奖</span></#if><#if (a.publish)><span class="f_icon f_iconG">版</span></#if></h6>
								    <p <#if (100 < a.archExtra.summary!?length)>title="${a.archExtra.summary!?html}"</#if>>${Util.getCountedWords(a.archExtra.summary!?html, 100)}</p>
								    <p>
								    	<span class="fr">来自:<a href="#">课题研究==TODO==</a></span>
								    	<span class="f_author">作者：<#if authorList??><#list authorList as au><#if (a.archId == au.archId)>
								    	<#if ("" != au.nameId!)><a href="his.do?userId=${au.nameId!}" target="_blank">${au.name!}</a>
								    	<#else>${au.name!}</#if></#if></#list></#if></span>
								    	<span>${Util.getDateByLong(a.createTime!, "yyyy-MM-dd HH:mm:ss")}</span>
								    </p>
								</li>  					
	        				</#list>
	        			</#if>
        			</#if>
				</ul>
				<div class="w_pagination"> 
					<#include "../Pager.ftl" />
				</div>
			</div>
			
			<div class="sider fr">
				<div class="searchBox mt20 clearfix">
					<#include "../Search.ftl" />
				</div>
				<div class="countBox">
					<p class="cTit">已拥有<@compress single_line=true><@getNameByObj obj! /></@compress></p>
					<span class="contNum"><@compress single_line=true><@getStatByObj stat!, obj! /></@compress><em>份</em></span>
				</div>
				<div class="sUpBtn mt20">
					<a href="arch_edit.do?obj=${obj!}" class="greenBtn"><span>上传<@compress single_line=true><@getNameByObj obj /></@compress></span></a>
				</div>
				<div class="f_newUp mt30">
					<h3 class="siderTit">最新上传</h3>
					<ul>
	        			<#if archPageList??>
	        				<#if archPageList.data??>
		        				<#list archPageList.data as a>
									<li class="f_upload">
										<a href="his.do?userId=${a.userId!}">${Util.getFirstString(a.author!, ",")}</a>
										<p <#if (16 < a.title!?length)>title="${a.title!}"</#if>>
											<a href="arch_detail.do?arch.archId=${a.archId!}">${Util.getCountedWords(a.title!?html, 16)}</a>
										</p>
										<b></b>
									</li>
									<#if (9 = a_index)><#break /></#if>
		        				</#list>
		        			</#if>
	        			</#if>
					</ul>
				</div>
			</div>
		</div>
		<div class="btShadow pngfix"></div>
		<#-- 正文 end-->

		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
		<!--
		$(function() {
			// 鼠标滑过列表
			$(".f_tixt li").live({"mouseover":function() {
					$(this).addClass("tixBg");
				}, "mouseout":function() {
					$(this).removeClass("tixBg");
				}
			});
			// 最新上传滑过样式
			$(".f_newUp .f_upload").live({"mouseover":function() {
					$(this).addClass("newUpload");
				}, "mouseout":function() {
					$(this).removeClass("newUpload");
				}
			});
			// 排序切换
			$(".sequNav a").live("click",function() {
				$(this).addClass("currS").siblings().removeClass("currS");
			});
		});
		//-->
		</script>
	</body>
</html>
