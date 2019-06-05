<div id="showuser">
	<#if searchUserList??>
		<#list searchUserList as u>
			<li>
				<a href="${Config.snsUrl!}/personhome/home/index.do?userId=${u.userId!}" class="fl friendHeadPic" target="_blank"><img src="${Config.snsUrl!}/data/upload/avatar/${u.userId!}.jpg" width="30" height="30" onerror="javascript:this.src='images/demoimg/30x30.jpg'" /></a>
				<span class="fl friendName">${u.trueName!}</span>
				<span class="nameId" style="display: none;">${u.userId!}</span>
				<span class="fr selectF"></span>
			</li>
		</#list>
	</#if>
</div>