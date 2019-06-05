<#macro objHotList list obj>
	<#if (list?? && list?has_content)>
		<div class="modBox mt30">
			<#list list as l>
				<#include "../Obj.ftl" />
				<#if (0 == l_index)>
					<div class="modMain clearfix">
						<span class="fl modTheme <@compress single_line=true><@getThemeByObj "${obj!}" /></@compress>"><@compress single_line=true><@getNameByObj "${obj!}" /></@compress></span>
						<div class="cont">
							<h4><a href="arch_detail.do?arch.archId=${l.archId!}" target="_blank">${l.title!}</a></h4>
							<div class="relaInfo">
								<span>来源：XX课题</span>
								<span>时间：${Util.getDateByLong(l.createTime!, "MM-dd")}</span>
							</div>
							<p title="${l.archExtra.summary!}">${Util.getCountedWords(l.archExtra.summary!?html, 60)}</p>
						</div>
					</div>
				<#else>
					<#if (1 == l_index)><ul class="modList"></#if>
						<li	<#if (1 == l_index % 2)>class="oddItem"<#else></#if>><span class="artTime">${Util.getDateByLong(l.createTime!, "MM-dd")}</span><a href="arch_detail.do?arch.archId=${l.archId!}" target="_blank" <#if (20 < l.title!?length)>title="${l.title!}"</#if>>${Util.getCountedWords(l.title!?html, 19)}</a></li>
					<#if (6 == l_index)></ul></#if>
				</#if>
			</#list>
		</div>
	</#if>
</#macro>

<#macro objNewList list all>
	<#if (list?? && list?has_content)>
		<ul class="modList" <#if ("a" == all)>style="display:block;"</#if>>
			<#list list as l>
				<li <#if (0 == l_index % 2)>class="oddItem"<#else></#if>><span class="artTime">${Util.getDateByLong(l.createTime!, "MM-dd")}</span><a href="arch_detail.do?arch.archId=${l.archId!}" target="_blank" <#if (20 < l.title!?length)>title="${l.title!}"</#if>>${Util.getCountedWords(l.title!?html, 19)}</a></li>
			</#list>
		</ul>
	</#if>
</#macro>
