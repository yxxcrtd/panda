<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的科研成果--非课题</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" /> 
		<div class="outerWrap borderWrap">
			<div class="subBox">
				<div class="topSort clearfix f_levelTit">
					<ul class="fl sortTab">
						<li <#if ("thesis" == obj!)>class="sortOn"</#if>><a href="my.do?obj=thesis">我的<@compress single_line=true><@getNameByObj "thesis" /></@compress></a><em class="topArr"></em></li>
						<li <#if ("book" == obj!)>class="sortOn"</#if>><a href="my.do?obj=book">我的<@compress single_line=true><@getNameByObj "book" /></@compress></a><em class="topArr"></em></li>
						<li <#if ("courseware" == obj!)>class="sortOn"</#if>><a href="my.do?obj=courseware">我的<@compress single_line=true><@getNameByObj "courseware" /></@compress></a><em class="topArr"></em></li>
						<li <#if ("instruction" == obj!)>class="sortOn"</#if>><a href="my.do?obj=instruction">我的<@compress single_line=true><@getNameByObj "instruction" /></@compress></a><em class="topArr"></em></li>
						<li <#if ("patent" == obj!)>class="sortOn"</#if>><a href="my.do?obj=patent">我的<@compress single_line=true><@getNameByObj "patent" /></@compress></a><em class="topArr"></em></li>
						<li <#if ("other" == obj!)>class="sortOn"</#if>><a href="my.do?obj=other">我的<@compress single_line=true><@getNameByObj "other" /></@compress></a><em class="topArr"></em></li>
					</ul>
					<div class="fl subSortBox">
						<a href="my.do?topicFlag=-1&obj=${obj!}" <#if topicFlag??&topicFlag==-1>class="currSub"</#if>>非课题成果</a>
						<a href="my.do?topicFlag=1&obj=${obj!}" <#if topicFlag??&topicFlag==1>class="currSub"</#if>>课题成果</a>
					</div>
				</div>
				<div class="f_subCont">
					<div class="f_subStat">
						<div class="f_sUpBtn fr">
							<a href="arch_edit.do?obj=${obj!}&topicFlag=${topicFlag!}" class="f_colorG">上传<@compress single_line=true><@getNameByObj obj! /></@compress></a>
							<a href="javascript:;" class="f_dete">删除</a>
						</div>
						<p>共有 <#if pager??><span id="totalCount">${pager.totalCount!0}</span></#if> 篇<@compress single_line=true><@getNameByObj obj! /></@compress></p>
					</div>
					<div class="tbCont">
						<table width="100%" border="0" class="changeBg tableCont">
							<colgroup>
								<col width="3%" />
								<col width="37%" />
								<col width="12%" />
								<col width="10%" />
								<col width="20%" />
								<col width="18%" />
							</colgroup>
						<thead>
							<tr class="f_tabborder">
								<th><label class="checkBox checkAll"></label></th>
								<th>名称 </th>
								<th>创建时间</th>
								<th>类型 </th>
								<th>状态 </th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<#if archList??>
								<#list archList as a>
									<tr>
										<td><label class="checkBox"></label></td>
										<td><p class="f_txt"><a href="arch_detail.do?arch.archId=${a.archId!}" target="_blank">${a.title!}</a></p></td>
										<td>${Util.getDateByLong(a.createTime!, "yyyy-MM-dd")}</td>
										<td>
											<#if ("courseware" != obj!)>
												<#if dictTypeList??>
													<#list dictTypeList as d>
														<#if (d.id = a.type)>${d.name!}</#if>
													</#list>
												</#if>
											<#else>无
											</#if>
										</td>
										<td>未审核==TODO==</td>
										<td><span class="editIcon"><a href="arch_edit.do?obj=${obj!}&archId=${a.archId!}&upd=true" class="blueEdit tipsPop"><i>编辑</i></a><a href="javascript:;" class="removeItem f_remove tipsPop"><i>删除</i></a><span class="did" style="display: none;">${a.archId!}</span></span></td>
									</tr>
								</#list>
							</#if>
						</tbody>
					</table>
		            </div>
		            <div class="w_pagination">
		            	<#include "../Pager.ftl" />
		            </div>
		        </div>
			</div>
		</div>
		<div class="btShadow pngfix"></div>
		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
		<!--[if IE 6]>
		<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
		<script type="text/javascript">
			DD_belatedPNG.fix('.pngfix,.pngfix:hover');
		</script>
		<![endif]-->
		<script type="text/javascript">
		<!--
		$(function() {
			// 鼠标滑过列表
			$(".changeBg tr").live({"mouseover" : function() {
					$(this).addClass("trHover");
					$(this).find(".editIcon").show();
				}, "mouseout" : function() {
					$(this).removeClass("trHover");
					$(this).find(".editIcon").hide();
				}
			});
			// 文章列表隔行换色
			$(".changeBg tr:even").addClass("trbg");
			$(".f_subCont").checkAll();
			// 删除
			$(".f_remove").live("click", function() {
				var This = this;
				var cur = parseInt($(This).siblings(".did").text());
				var totalCountNode = $("#totalCount");
				confirmTip("确定删除吗？", function() {
					$.get("arch_del.do", {"archId" : cur}, function(data) {
						if ("success" == data) {
							delItem($(This).parents("tr"), succTip);
							totalCountNode.html(totalCountNode.text() - 1);
						} else {
							errorTip(data);
						}
					});
				});
			});
			// 点击checkbox删除
			$(".tbCont .checkBox").live("click", function() {
				if ($(".tbCont").find(".isChecked").length > 0) {
					$(".f_dete").addClass("delAble");
				} else {
					$(".f_dete").removeClass("delAble");
				}
			});
			// 批量删除
			$(".delAble").live("click",function() {
				var $sel = $(this).parents(".f_subCont").find(".isChecked:not('.checkAll')");
				var totalCountNode = $("#totalCount");
				confirmTip("确定删除选中<@compress single_line=true><@getNameByObj obj! /></@compress>吗？", function() {
					$sel.each(function() {
						$.get("arch_del.do", {"archId" : $(this).parents("tr").find(".did").text()}, function(data) {
							if ("success" == data) {
								delItem($sel.parents("tr").remove(), succTip);
								totalCountNode.html(totalCountNode.text() - 1);
							} else {
								errorTip(data);
							}
						});
					});
					// 重新隔行换色
					$(".changeBg tr").removeClass("trbg");
					$(".changeBg tr:even").addClass("trbg");
				});
			});
		});
		//-->
		</script>
	</body>
</html>
