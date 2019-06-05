<div class="f_topics">
	<h3 class="f_titleL"><a href="arch_topic_more.do?archId=${arch.archId!}" class="fr">更多</a>相关话题</h3>
	<div class="commentCont clearfix">
		<dl>
			<dt><img src="${Config.snsUrl!}/data/upload/avatar/<#if loginUser??>${loginUser.userId!}</#if>.jpg" width="50" height="50" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/50x50.jpg'" /></dt>
			<dd class="replyBox">
            	<div class="textArea clearfix">
               		<span class="textAreaArrow"></span>
                	<span class="textAreaT"></span>
                	<div class="textAreaC" contenteditable="true">#在这输入话题标题#</div>
                	<span class="textAreaB"></span>
                	<span class="tipsWrap"></span>
            	</div>
            	<p class="textAreaTips mt10">
            		<a href="javascript:;" class="fr publishBtn">发表</a>
            		<span id="txt">还可以输入</span><span id="textNum">300</span>个字
            	</p>
        	</dd>
		</dl>
	</div>
	<div class="f_topicL">
		<ul class="articleLine topicLine">
			<#if appTopicList??>
				<#list appTopicList as a>
					<li>
						<span class="fr f_nameW"><a href="his.do?userId=${a.userId!}"><#if (4 < a.userName!?length)>${Util.getCountedWords(a.userName!?html, 3)}<#else>${a.userName!}</#if></a></span><b class="fr">${Util.getDateByLong(a.createDate!, "yyyy-MM-dd")}</b>
						<a href="${Config.snsUrl!}/common/topic/queryTopicDetail.do?topicId=${a.topicId!}" target="_blank">${a.title!}</a>
					</li>
				</#list>
			</#if>
		</ul>
	</div>
</div>