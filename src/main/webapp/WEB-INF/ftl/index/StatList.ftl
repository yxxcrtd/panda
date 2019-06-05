<div class="gxList mt25">
	<div class="modTit clearfix">
		<h2>贡献达人</h2>
	</div>
	<div class="gxdrList mt15">
		<ul class="drBox clearfix">
			<#if statList??>
				<#list statList as s>
					<li>
						<a href="his.do?userId=${s.userId}"><img src="${Config.snsUrl!}/data/upload/avatar/${s.userId!}.jpg" width="80" height="80" onerror="javascript:this.src='images/demoimg/80x80.jpg'" /></a> 
						<div class="drInfo">
							<h4>
								<a href="his.do?userId=${s.userId}">
									<#if userList??>
										<#list userList as u>
											<#if (u.userId == s.userId)>
												<#if u??><#if (5 < u.trueName!?length)>${Util.getCountedWords(u.trueName!?html, 4)}<#else>${u.trueName!}</#if></#if>
											</#if>
										</#list>
									</#if>
								</a>
							</h4>
							<p>
								<span class="num">${s.sum!}</span>
								个科研成果
							</p>
						</div>
					</li>
				</#list>
			</#if>
		</ul>
	</div>
</div>