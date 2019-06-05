<div class="countBox">
	<p class="cTit"><@compress single_line=true><@getNameByObj "" /></@compress></p>
	<span class="contNum"><@compress single_line=true><@getStatByObj stat!, "" /></@compress><em>份</em></span>
</div>
<div class="f_dataBox mt20 pt24 borderT">
	<h3 class="siderTit">科学成果统计</h3>
    <ul class="f_data">
    	<#include "SiteStatDetail.ftl" />
    </ul>
</div>