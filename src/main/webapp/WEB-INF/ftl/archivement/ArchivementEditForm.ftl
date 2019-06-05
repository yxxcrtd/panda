<form id="archForm">
	<div class="subBox">
		<div class="modTit clearfix">
			<h2><#if (0 == arch.archId!)>上传<#else>编辑</#if><@compress single_line=true><@getNameByObj "${obj!}" /></@compress></h2>
		</div>
		<div class="selTopic blueBox mt20 none">
			<p>您想在哪个课题下发表论文？</p>
			<div class="selItems mt10 clearfix">
				<div class="fl mySelect issueSel ">
					<i class="selArr"></i>
					<span class="selValue">请选择课题</span>
					<div class="myOptions">
						<div class="scrollList">
							<ul id="topicSelect">
								<#if topicList??>
								<#list topicList as topic>
									<li  id="${topic.id}"><a href="javascript:;">${topic.title}</a></li>
								</#list>
								</#if>
							</ul>
						</div>
					</div>
				</div>
				<div class="fl mySelect stageSel">
					<i class="selArr"></i>
					<span class="selValue">请选择阶段</span>
					<div class="myOptions">
						<div class="scrollList" id="stageSelect">
							<ul id="topicSelect">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="baseInfo">
			<ul class="stepNav mt30 clearfix">
				<li class="baseLink currStep"><a href="<#if (0 == arch.archId!)>javascript:;<#else>arch_edit.do?obj=${obj!}&archId=${arch.archId!}<#if upd??>&upd=true</#if></#if>">基本信息</a><i class="navArr"></i></li>
				<li><a href="<#if (0 == arch.archId!)>javascript:;<#else>upload2<#if (!appArticleList?? && !appResourceList?? && !appPhotoList?? && !appVideoList??)><#else>List</#if>.do?obj=${obj!}&arch.archId=${arch.archId!}</#if>">成果内容</a></li>
			</ul>
			<div class="baseBox">
				<ul class="clearfix">
					<#if ("courseware" != obj)>
						<li class="baseItem">
							<span class="itemTit">类型：</span>
							<div class="itemDetail radioItems">
								<#if dictTypeList??>
									<#list dictTypeList as d>
										<span class="radioItem radioType">
											<label class="imgRadio <#if (0 == arch.archId!)><#if (0 == d_index)>isChecked</#if><#else><#if (0 < arch.archId! && arch.type! == d.id!)>isChecked</#if></#if>" value="${d.id!}">${d.name!}</label>
										</span>
									</#list>
								</#if>
								<input type="hidden" id="typeId" name="arch.type" value="${arch.type!}" />
							</div>
						</li>
					</#if>
					<li class="baseItem">
						<span class="itemTit">名称：</span>
						<div class="itemDetail">
							<input type="text" name="arch.title" value="${arch.title!}" maxlength="32" class="txtStyle iptW583" placeholder="标题最长32个汉字" />
						</div>
					</li>
					<li class="baseItem">
						<span class="itemTit">简介：</span>
						<div class="itemDetail">
							<textarea class="txtStyle sumArea" name="archExtra.summary" maxlength="3000"><#if arch.archExtra??>${arch.archExtra.summary!}</#if></textarea>
						</div>
					</li>
					<#if ("thesis" == obj || "book" == obj || "other" == obj)>
						<li class="baseItem">
							<span class="itemTit"><#if ("book" == obj)>出版社名称：<#else>是否出版：</#if></span>
							<div class="itemDetail">
								<p class="fl radioItems whether radioPublish">
									<#if ("thesis" == obj || "other" == obj)>
										<span class="radioItem"><label class="imgRadio <#if (!arch.publish!)>isChecked</#if>" value="false">未出版</label></span>
										<span class="radioItem"><label class="imgRadio hasbeen <#if (arch.publish!)>isChecked</#if>" value="true">已出版</label></span>
									</#if>
									<input type="hidden" id="publishId" name="arch.publish" value="<#if ("book" == obj)>true<#else>${arch.publish?string('true', 'false')}</#if>" />
								</p>
								<div class="fl hasBox <#if ("book" == obj)>ml0<#else>none</#if>" <#if (arch.publish!)>style="display: block;"</#if>>
									<input type="text" name="archExtra.publishName" value="<#if arch.archExtra??>${arch.archExtra.publishName!}</#if>" class="txtStyle mIpt iptW180" placeholder="请输入刊物名称" maxlength="20" />
									<input type="text" name="archExtra.publishTime" value="<#if arch.archExtra??><#if arch.archExtra.publishTime??>${arch.archExtra.publishTime!?string('yyyy-MM-dd')}</#if></#if>" class="txtStyle mIpt Wdate" placeholder="出版时间" onClick="WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '${.now?string("yyyy-MM-dd")}'})" />
								</div>
							</div>
						</li>
					</#if>
					<#if ("patent" != obj)>
						<li class="baseItem">
							<span class="itemTit">是否获奖：</span>
							<div class="itemDetail">
								<p class="fl radioItems whether radioPrize">
									<span class="radioItem"><label class="imgRadio <#if (!arch.prize!)>isChecked</#if>" value="false">未获奖</label></span>
									<span class="radioItem"><label class="imgRadio hasbeen <#if (arch.prize!)>isChecked</#if>" value="true">已获奖</label></span>
									<input type="hidden" id="prizeId" name="arch.prize" value="${arch.prize?string('true', 'false')}" />
								</p>
								<div class="fl hasBox none" <#if (arch.prize)>style="display: block;"</#if>>
									<input type="text" name="archExtra.prizeItem" value="<#if arch.archExtra??>${arch.archExtra.prizeItem!}</#if>" class="txtStyle mIpt iptW180" placeholder="请输入刊物名称" maxlength="20" />
									<div class="simSelect selLevel">
										<i class="selArr"></i>
										<span class="selValue">
											<#if (0 == arch.archId || 0 = arch.archExtra.prizeLevel)>请选择获奖等级
											<#else>
												<#if dictPrizeLevelList??>
													<#list dictPrizeLevelList as d>
														<#if arch.archExtra??><#if (d.id = arch.archExtra.prizeLevel)>${d.name!}<#else></#if></#if>
													</#list>
												</#if>
											</#if>
										</span>
										<div class="options">
											<div class="scrollList">
												<ul>
													<#if dictPrizeLevelList??>
														<#list dictPrizeLevelList as d>
															<li value="${d.id!}" class="prizeLevel"><a href="javascript:;">${d.name!}</a></li>
														</#list>
													</#if>
												</ul>
											</div>
										</div>
										<input type="hidden" id="prizeLevelId" name="archExtra.prizeLevel" value="<#if arch.archExtra??>${arch.archExtra.prizeLevel!}</#if>" />
									</div>
									<input type="text" name="archExtra.prizeTime" <#if arch.archExtra??><#if arch.archExtra.prizeTime??>value="${arch.archExtra.prizeTime!?string('yyyy-MM-dd')}"</#if></#if> class="txtStyle mIpt Wdate" placeholder="获奖时间" onClick="WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '${.now?string("yyyy-MM-dd")}'})" />
								</div>
							</div>
						</li>
					</#if>
					<#if ("patent" == obj)>
						<li class="baseItem">
							<span class="itemTit">职务专利：</span>
							<div class="itemDetail radioItems radioServicePatent">
								<span class="radioItem"><label class="imgRadio <#if arch.archExtra??><#if (arch.archExtra.servicePatent)>isChecked</#if></#if>" value="true">是</label></span>
								<span class="radioItem"><label class="imgRadio <#if arch.archExtra??><#if (!arch.archExtra.servicePatent)>isChecked</#if></#if>" value="false">否</label></span>
								<input type="hidden" id="servicePatentId" name="archExtra.servicePatent" value="${archExtra.servicePatent!?string('true', 'false')}" />
							</div>
						</li>
						<li class="baseItem">
							<span class="itemTit">是否授权：</span>
							<div class="itemDetail radioItems radioAuthorize">
								<span class="radioItem"><label class="imgRadio <#if arch.archExtra??><#if (arch.archExtra.authorize)>isChecked</#if></#if>" value="true">是</label></span>
								<span class="radioItem"><label class="imgRadio <#if arch.archExtra??><#if (!arch.archExtra.authorize)>isChecked</#if></#if>" value="false">否</label></span>
								<input type="hidden" id="authorizeId" name="archExtra.authorize" value="${archExtra.authorize!?string('true', 'false')}" />
							</div>
						</li>
						<li class="baseItem">
							<span class="itemTit">申请号：</span>
							<div class="itemDetail">
								<input type="text" class="txtStyle mIpt iptW180" name="archExtra.applyNumber" value="<#if arch.archExtra??>${arch.archExtra.applyNumber!}</#if>" />
								<input type="text" class="txtStyle mIpt Wdate" name="archExtra.applyTime" value="<#if arch.archExtra??><#if arch.archExtra.applyTime??>${arch.archExtra.applyTime!?string('yyyy-MM-dd')}</#if></#if>" placeholder="请选择申请时间" onClick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate: '${.now?string("yyyy-MM-dd")}'})" />
							</div>
						</li>
						<li class="baseItem">
							<span class="itemTit">专利号：</span>
							<div class="itemDetail">
						        <input type="text" class="txtStyle mIpt iptW180" name="archExtra.number" value="<#if arch.archExtra??>${arch.archExtra.number!}</#if>" />
						        <input type="text" class="txtStyle mIpt Wdate" name="archExtra.obtainTime" value="<#if arch.archExtra??><#if arch.archExtra.obtainTime??>${arch.archExtra.obtainTime!?string('yyyy-MM-dd')}</#if></#if>" placeholder="请选择获得专利时间" onClick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate: '${.now?string("yyyy-MM-dd")}'})" />
							</div>
						</li>
						<li class="baseItem">
							<span class="itemTit">代理机构：</span>
							<div class="itemDetail">
								<input type="text" class="txtStyle iptW583" name="archExtra.agency" value="<#if arch.archExtra??>${arch.archExtra.agency!}</#if>" />
							</div>
						</li>
						<li class="baseItem">
							<span class="itemTit">代理人：</span>
							<div class="itemDetail">
								<input type="text" class="txtStyle iptW583" name="archExtra.agent" value="<#if arch.archExtra??>${arch.archExtra.agent!}</#if>" />
							</div>
						</li>
					</#if>
					<li class="baseItem">
						<span class="itemTit">作者：</span>
						<div class="itemDetail">
							<span class="fl iptBox">
								<input type="text" class="txtStyle" maxlength="12" placeholder="填写姓名或机构" />
								<a href="javascript:;" class="gofillBtn" id="addAuthor">确定</a>
							</span>
							<a href="javascript:;" class="fl addAuthor"></a>
							<div class="fl auList">
								<#if (0 == arch.archId!)>
									<span class="auName">
										<#if loginUser??>${loginUser.trueName!}<#else>没有登录的用户</#if>
										<input type="hidden" name="authors" value="<#if loginUser??>${loginUser.trueName!},${loginUser.userId!}<#else>没有登录的用户</#if>" />
									</span>
								<#else>
									<#if authorList??>
										<#list authorList as a>
											<span class="auName">
												<span class="showspan">${a.name!}</span>
												<#if (a.nameId! != loginUser.userId!)>
													<a class="delItem delAu" href="javascript:;"></a>
												</#if>
												<input type="hidden" name="authors" value="${a.name!}<#if ("" != a.nameId!)>,${loginUser.userId!}</#if>" />
												<span class="did" style="display: none;">${a.id!}</span>
											</span>
										</#list>
									</#if>
								</#if>
							</div>
						</div>
					</li>
					<li class="baseItem">
						<span class="itemTit">学段学科：</span>
						<div class="itemDetail">
							<div class="simSelect selSemester">
								<i class="selArr"></i>
								<span class="selValue">
									<#if (0 == arch.archId || 0 == arch.gradeId!)>选择学段
									<#else>
										<#if gradeList??>
											<#list gradeList as g>
												<#if (g.id == arch.gradeId!)>${g.name!}</#if>
											</#list>
										</#if>
									</#if>
								</span>
								<div class="options">
									<div class="scrollList">
										<ul>
											<#if gradeList??>
												<#list gradeList as g>
													<li value="${g.id!}" class="gradeId"><a href="javascript:;">${g.name!}</a></li>
												</#list>
											</#if>
										</ul>
									</div>
								</div>
								<input type="hidden" id="gradeId" name="arch.gradeId" value="${arch.gradeId!}" />
							</div>
							<div class="simSelect selSub">
								<i class="selArr"></i>
								<span class="selValue">
									<#if (0 == arch.archId || 0 == arch.subjectId!)>选择学科
									<#else>
										<#if subjectList??>
											<#list subjectList as s>
												<#if (s.id == arch.subjectId!)>${s.name!}</#if>
											</#list>
										</#if>
									</#if>
								</span>
								<div class="options">
									<div class="scrollList">
										<ul>
											<#if subjectList??>
												<#list subjectList as s>
													<li value="${s.id!}" class="subjectId"><a href="javascript:;" title="${s.name!?html}">${Util.getCountedWords(s.name!,6)}</a></li>
												</#list>
											</#if>
										</ul>
									</div>
								</div>
								<input type="hidden" id="subjectId" name="arch.subjectId" value="${arch.subjectId!}" />
							</div>
						</div>
					</li>
					<li class="baseItem">
						<span class="itemTit">关键词：</span>
						<div class="itemDetail">
							<span class="fl iptBox">
								<input type="text" class="txtStyle" maxlength="20" placeholder="填写关键词" />
								<a href="javascript:;" class="gofillBtn" id="addTag">确定</a>
							</span>
							<div class="fl addTags">
								<#if keywordList??>
									<#list keywordList as k>
										<span class="tagItem">
											<i class="tagRad"></i>
											<span class="showspan">${k.name!}</span>
											<a href="javascript:;" class="delItem delTag"></a>
											<input type="hidden" name="keywords" value="${k.name!}" />
											<span class="did" style="display: none;">${k.id!}</span>
										</span>
									</#list>
								</#if>
							</div>
						</div>
					</li>
				</ul>
				<div class="btBtns">
					<#if (upd)>
						<a href="javascript:;" class="blueBtn" id="arch_next">发布</a>
						<a href="javascript:history.go(-1);" class="cancelBtn">取消</a>
					<#else>
						<a href="javascript:;" class="blueBtn" id="arch_next">下一步</a>
					</#if>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="arch.category" value="${obj!}" />
	<input type="hidden" name="arch.archId" value="${arch.archId!}" />
	<input type="hidden" name="arch.userId" value="${loginUser.userId!}" />
	<input type="hidden" name="arch.createTime" value="${arch.createTime!}" />
	<input type="hidden" name="arch.viewCount" value="${arch.viewCount!}" />
	<input type="hidden" name="arch.collectCount" value="${arch.collectCount!}" />
	<input type="hidden" name="arch.praiseCount" value="${arch.praiseCount!}" />
	<input type="hidden" name="arch.hotCount" value="${arch.hotCount!}" />
	<input type="hidden" id="topicResearchId" name="arch.topicResearchId" value="${arch.topicResearchId!}" />
	<input type="hidden" id="TopicResearchName" name="arch.topicResearchName" value="${arch.topicResearchName!}" />
	<input type="hidden" id="TopicResearchStageId" name="arch.topicResearchStageId" value="${arch.topicResearchStageId!}" />
</form>