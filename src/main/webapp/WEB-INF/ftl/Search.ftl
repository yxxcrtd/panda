<form id="searchForm" action="arch_search.do">
	<input type="text" class="SearchInput fl" id="search_input" name="k" placeholder="输入<@compress single_line=true><@getNameByObj "${obj!}" /></@compress>名称或作者名" value="${k!}">
	<input type="button" class="SearchBtn fl" id="arch_search" value="查找" />
	<input type="hidden" name="obj" value="${obj!}" />
</form>
<script type="text/javascript">
<!--
$(function() {
	var searchNode = $("#search_input");
	$("#arch_search").on("click", function() {
		if ("" == $.trim(searchNode.val())) {
			$.messager.alert("提示：", "请输入查询关键字！", "warning", "");
			return false;
		}
		$("#searchForm").submit();
	});
});
//-->
</script>