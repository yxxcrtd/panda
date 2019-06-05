<#if pager??>
	<#assign pageCount = pager.totalPageCount><#-- 分页总数(调用Pager中的getTotalPageCount()方法) -->
	<#if (pageCount > 1)><#-- 当"分页总数"大于1才显示分页 -->
		<#assign offset = 5><#-- 定义前偏移值 -->
		<#assign current = pager.pageNo><#-- 当前分页号 -->
		<#assign maxStart = (pageCount - offset * 2)>
		<#if (maxStart < 1)>
			<#assign maxStart = 1>
		</#if>
		<#assign minEnd = (offset * 2 + 1)>
		<#assign start = (current - offset)>
		<#assign end = (current + offset)>
		<#if (start < 1)>
			<#assign start = 1>
			<#assign end = minEnd>
		</#if>
		<#if (end > pageCount)>
			<#assign end = pageCount>
			<#assign start = maxStart>
		</#if>
		<#if (current > 1)>
			<a href="javascript:go(${current - 1});" class="w_pre">&lt;</a>
		</#if>
		<#if (start > 1)>
			<a href="javascript:go(1);">1</a>
		</#if>
		<#if (start > 2)>
			<span>...</span>
		</#if>
		<#list start..end - 1 as i>
			<#if (i == current)>
				<a href="javascript:go(${i});" class="w_currPage">${i}</a>
			<#else>
				<a href="javascript:go(${i});">${i}</a>
			</#if>
		</#list>
		<#if (start < maxStart)>
			<span>...</span>
		</#if>
		<#if current == pageCount>
			<a href="javascript:go(${current});" class="w_currPage">${current}</a>
		<#else>
			<a href="javascript:go(${pageCount});">${pageCount}</a>
			<a href="javascript:go(${current + 1});" class="w_next">&gt;</a>
		</#if>

		<script type="text/javascript">
		<!--
		function go(i) {
			window.location.href = "${pager.pageUrl}".replace("{userId}", "${userId!}").replace("{obj}", "${obj!}").replace("{t}", "${t!}").replace("{prize}", "${prize!?c}").replace("{publish}", "${publish!?c}").replace("{archId}", "${archId!}").replace("{p}", i).replace("{k}", "${k!}");
		}
		//-->
		</script>
	</#if>
</#if>