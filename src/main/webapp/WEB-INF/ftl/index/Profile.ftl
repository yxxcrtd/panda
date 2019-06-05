<div class="pCont clearfix borderT pt30">
    <span class="fl hImg"><a href="his.do?userId=<#if loginUser??>${loginUser.userId!}"></#if><img src="${Config.snsUrl!}/data/upload/avatar/<#if loginUser??>${loginUser.userId!}</#if>.jpg" width="80" height="80" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/default1.gif'" /></a></span>
    <p class="fl pName"><#if loginUser??><#if (6 < loginUser.trueName!?length)>${Util.getCountedWords(loginUser.trueName!?html, 5)}<#else>${loginUser.trueName!}</#if></#if><a href="${Config.snsUrl!}/personhome/home/index.do" class=" pCenterLink">个人中心</a></p>   
</div>
<div class="myResearch mt25">
	<h3 class="siderTit"><a class="fr" href="my.do?obj=thesis">更多</a>我的科研成果</h3>
	<ul class="txtList">
		<#if archList??>
			<#list archList as arch>
				<li><a href="arch_detail.do?arch.archId=${arch.archId!}" <#if (18 < arch.title?length)>title="${arch.title!}"</#if>>${Util.getCountedWords(arch.title!?html, 18)}</a></li>
			</#list>
		</#if>
    </ul>
</div>