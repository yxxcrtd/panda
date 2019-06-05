<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>话题列表</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
	</head>

	<body>
		<#include "../SnsHeader.ftl" />
		
		<!-- 正文 start -->
		<div class="outerWrap borderWrap clearfix">
			<div class="container">
				<div class="manageTit clearfix">
		            <a href="arch_detail.do?arch.archId=${archId!}" class="fl backTo"></a>
					<h2 class="fl">${arch.title!}</h2>
				</div>
				<div class="topicBox">
		            <h3>话题</h3>
		            <p class="topicStat">当前科研成果共发起了<span id="totalCount">${totalCount!0}</span>个话题</p>
		            <ul class="topicCont">
		            	<#if appTopicList??>
		            		<#list appTopicList as a>
				                <li>
				                	<#if (a.userId = loginUser.userId)><a href="javascript:;" class="delete tipsPop"><i>删除</i></a></#if>
				                	<span><#if (4 < a.userName!?length)>${Util.getCountedWords(a.userName!?html, 3)}<#else>${a.userName!}</#if></span>
				                	<span class="did" style="display: none;">${a.topicId!}</span>
				                	<a href="${Config.snsUrl!}/common/topic/queryTopicDetail.do?topicId=${a.topicId!}">${a.title!}</a>
				                </li>
		            		</#list>
		            	</#if>
		            </ul>
		    		<!-- 分页 strar-->
		            <div class="w_pagination">
		                <#include "../Pager.ftl" />
		            </div>
		            <!-- 分页 end-->
		        </div>
			</div>
		</div>
		<div class="btShadow"></div>
		<!-- 正文 end -->
		<#include "../Footer.ftl" />
		<script>
		function changeLi(box,curr) {
			$(box).find("li").live({
				"mouseover":function(){
					$(this).addClass(curr);
				},
				"mouseout":function(){
					$(this).removeClass(curr);
				}
			});
		}
		$(function() {
			changeLi(".topicCont","liHover");
			$(".delete").live("click", function() {
				var This = this;
				var cur = parseInt($(This).siblings(".did").text());
				var totalCountNode = $("#totalCount");
				confirmTip("确定删除吗？", function() {
					$.get("arch_topic_del.do", {"appContentId" : cur}, function(data) {
						if ("success" == data) {
							delItem($(This).parents("li"), succTip);
							totalCountNode.html(totalCountNode.text() - 1);
						} else {
							errorTip(data);
						}
					});
				});
			});
		});	
		</script>
		<script type="text/javascript" src="js/common.js"></script>
	</body>
</html>
