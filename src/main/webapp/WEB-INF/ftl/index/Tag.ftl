<div class="hotTags mt20 pt24 borderT">
	<h3 class="siderTit">热门标签</h3>
	<div class="tagList">
		<#if keywordList??>
			<#list keywordList as k>
				<a class="tagItem" href="key_show.do?k=${k.name!?html}" target="_blank"><i class="tagRad"></i><span>${k.name!}</span></a>
			</#list>
		</#if>
	</div>
</div>