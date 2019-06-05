<#if obj??>
	<#if ("thesis" == obj)><#assign title = "论文", theme = "blueTheme" />
	<#elseif ("book" == obj)><#assign title = "著作", theme = "greenTheme" />
	<#elseif ("courseware" == obj)><#assign title = "课件", theme = "lBlueTheme" />
	<#elseif ("instruction" == obj)><#assign title = "教学设计", theme = "ppTheme" />
	<#elseif ("patent" == obj)><#assign title = "专利", theme = "lGreenTheme" />
	<#elseif ("other" == obj)><#assign title = "其他成果", theme = "dGreenTheme" />
	<#else>
	</#if>
</#if>

<#macro getNameByObj obj>
	<#if obj??>
		<#if ("thesis" == obj)>论文
		<#elseif ("book" == obj)>著作
		<#elseif ("courseware" == obj)>课件
		<#elseif ("instruction" == obj)>教学设计
		<#elseif ("patent" == obj)>专利
		<#elseif ("other" == obj)>其他成果
		<#else>科研成果
		</#if>
	</#if>
</#macro>

<#macro getStatByObj stat obj>
	<#if obj??>
		<#if ("thesis" == obj)>${stat.thesis!0}
		<#elseif ("book" == obj)>${stat.book!0}
		<#elseif ("courseware" == obj)>${stat.courseware!0}
		<#elseif ("instruction" == obj)>${stat.instruction!0}
		<#elseif ("patent" == obj)>${stat.patent!0}
		<#elseif ("other" == obj)>${stat.other!0}
		<#else>${stat.sum!0}
		</#if>
	</#if>
</#macro>

<#macro getThemeByObj obj>
	<#if obj??>
		<#if ("thesis" == obj)>blueTheme
		<#elseif ("book" == obj)>greenTheme
		<#elseif ("courseware" == obj)>lBlueTheme
		<#elseif ("instruction" == obj)>ppTheme
		<#elseif ("patent" == obj)>lGreenTheme
		<#elseif ("other" == obj)>dGreenTheme
		<#else>blueTheme
		</#if>
	</#if>
</#macro>
