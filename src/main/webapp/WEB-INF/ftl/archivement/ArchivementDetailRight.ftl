<dl class="f_thesisCont" <#if (0 < arch.archExtra.publishName!?length || 0 < arch.archExtra.prizeItem!?length || arch.keyword??)><#else>style="border-bottom: none;"</#if>>
	<dt class="clearfix">
		<img src="${Config.snsUrl!}/data/upload/avatar/<#if user??>${user.userId!}</#if>.jpg" width="50" height="50" onerror="javascript:this.src='images/demoimg/80x80.jpg'" />
		<p>
			<span class="colorB"><#if user??><#if (4 < user.trueName!?length)>${Util.getCountedWords(user.trueName!?html, 3)}<#else>${user.trueName!}</#if></#if></span>&nbsp;的<@compress single_line=true><@getNameByObj arch.category! /></@compress>成果<br />
			<span class="colorB">
				<#if dictTypeList??>
					<#list dictTypeList as d>
						<#if (arch.type! = d.id!)>${d.name!}</#if>
					</#list>
				</#if>
			</span>
		</p>
	</dt>
	<#if (arch.archExtra.publishName?? && 0 < arch.archExtra.publishName?length)>
		<dd><span class="colorH">出版：</span><span class="colorB jianju">${arch.archExtra.publishName!}</span></dd>
	</#if>
	<#if (arch.archExtra.prizeItem?? && 0 < arch.archExtra.prizeItem?length)>
		<dd><span class="colorH">获奖：</span><span class="colorB jianju"><#if (arch.prize)>${arch.archExtra.prizeItem!}/<#if dictPrizeLevelList??><#list dictPrizeLevelList as p><#if (p.id = arch.archExtra.prizeLevel!)>${p.name!}</#if></#list></#if></#if></span></dd>
	</#if>
	<#if arch.keyword??>
		<dd class="clearfix">
			<span class="colorH fl">关键词：</span>
			<p class="fl jianju">
				<#list arch.keyword.split(",") as k>
					<a class="tagItem" href="key_show.do?k=${k!?html}" target="_blank"><i class="tagRad"></i><span>${k!}</span></a>
				</#list>
			</p>
		</dd>
	</#if>
</dl>
<div class="f_thesisList">
	<h3 class="siderTit"><a href="his.do?userId=<#if user??>${user.userId!}</#if>&obj=${arch.category!}" class="fr">更多</a>作者其他<#if ("other" != arch.category)><@compress single_line=true><@getNameByObj arch.category! /></@compress></#if>成果</h3>
	<ul class="f_thesis">
		<#if archList??>
			<#list archList as a>
				<li><em class="fr">${Util.getDateByLong(a.createTime!, "MM-dd")}</em><a href="arch_detail.do?arch.archId=${a.archId!}" target="_blank" <#if (15 < a.title!?length)>title="${a.title!}"</#if>>${Util.getCountedWords(a.title!?html, 14)}</a></li>
			</#list>
		</#if>
	</ul>
</div>