<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>他的科研成果</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" /> 
		<#include "../Obj.ftl" />
		<div class="outerWrap borderWrap bgWrap clearfix">
			<div class="mainLeft fl">
				<div class="f_resultsNav clearfix">
				    <a class="fl backTo" href="index.do" title="返回首页"></a>
					<ul class="f_tabNav clearfix fl">
				        <li <#if ("" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}">全部</a></li>
				        <li <#if ("thesis" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}&obj=thesis"><@getNameByObj "thesis" /></a></li>
				        <li <#if ("book" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}&obj=book"><@getNameByObj "book" /></a></li>
				        <li <#if ("courseware" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}&obj=courseware"><@getNameByObj "courseware" /></a></li>
				        <li <#if ("instruction" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}&obj=instruction"><@getNameByObj "instruction" /></a></li>
				        <li <#if ("patent" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}&obj=patent"><@getNameByObj "patent" /></a></li>
				        <li <#if ("other" == obj!)>class="active"</#if>><a href="his.do?userId=${userId!}&obj=other"><@getNameByObj "other" /></a></li>
				    </ul> 
				</div>          
				<ul class="f_tixt">
					<#if archList??>
						<#list archList as a>
							<li>
								<h6><i>${a.viewCount!0}次浏览</i><b>【<@compress single_line=true><@getNameByObj "${a.category!}" /></@compress>】</b><a href="arch_detail.do?arch.archId=${a.archId!}">${a.title!}</a><#if (a.prize)><span class="f_icon f_iconY">奖</span></#if><#if (a.publish)><span class="f_icon f_iconG">版</span></#if></h6>
								<p <#if (100 < a.archExtra.summary!?length)>title="${a.archExtra.summary!}"</#if>>${Util.getCountedWords(a.archExtra.summary!?html, 100)}</p>
								<p>
								<#if (a.topicResearchId > 0)>
									<span class="fr">来自:<a href="${Config.getResearchUrl()}/show.do?id=${a.topicResearchId}">${a.topicResearchName!}</a></span>
								</#if>
									<span class="f_author">作者：${a.author!}</span>
									<span>${Util.getDateByLong(a.createTime!, "yyyy-MM-dd HH:mm:ss")}</span>
								</p>
							</li>
						</#list>
					</#if>
				</ul>
				<div class="w_pagination">
					<#include "../Pager.ftl" />
				</div>
			</div>
			<div class="sider fr">
		    	<div class="pCont clearfix mt20">
		            <span class="fl hImg"><a href="his.do?userId=<#if user??>${user.userId!}</#if>"><img src="${Config.snsUrl!}/data/upload/avatar/<#if user??>${user.userId!}</#if>.jpg" width="80" height="80" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/default1.gif'" /></a></span>
		            <p class="fl pName"><#if user??><#if (6 < user.trueName!?length)>${Util.getCountedWords(user.trueName!?html, 5)}<#else>${user.trueName!}</#if></#if><a href="${Config.snsUrl!}/personhome/home/index.do?userId=<#if user??>${user.userId!}</#if>" class=" pCenterLink" target="_blank">个人中心</a></p>   
		        </div>
		        <div class="f_dataBox">
		            <ul class="f_data">
		                <#include "SiteStatDetail.ftl" />
		            </ul>
		        </div>
		        <div class="hisResearch borderT">
		            <h3 class="siderTit">热门<@compress single_line=true><@getNameByObj "${obj!''}" /></@compress></h3>
		            <ul class="txtList">
		            	<#if hotArchList??>
		            		<#list hotArchList as a>
		                		<li><a href="arch_detail.do?arch.archId=${a.archId!}" target="_blank" <#if (18 < a.title!?length)>title="${a.title!}"</#if>>${Util.getCountedWords(a.title!?html, 17)}</a></li>
		            		</#list>
		            	</#if>
		            </ul>
		        </div>
		    </div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
		<!--
		$(function() {
			// 鼠标滑过列表
			$(".f_tixt li").live({"mouseover":function() {
					$(this).addClass("tixBg");
				}, "mouseout":function(){
					$(this).removeClass("tixBg");
				}
			});
			// 标题切换
			$(".f_tabNav li").live("click",function() {
				$(this).addClass("active").siblings().removeClass("active");
			});
		});
		//-->
		</script>
	</body>
</html>
