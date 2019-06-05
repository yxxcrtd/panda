<#include "../Obj.ftl" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>编辑成果-基本信息</title>
		<link type="text/css" rel="stylesheet" href="css/common.css">
		<link type="text/css" rel="stylesheet" href="css/scientific.css">
		<link type="text/css" rel="stylesheet" href="css/index.css">
		<#include "/WEB-INF/ftl/Header.ftl" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<#include "../SnsHeader.ftl" />
		<div class="outerWrap borderWrap clearfix">
			<div class="f_edit">
				<div class="modTit">
					<h2>编辑<@compress single_line=true><@getNameByObj "${obj!}" /></@compress></h2>
				</div>
				<div class="f_resultsTit clearfix">
					<#if (appArticleList?? || appResourceList?? || appPhotoList?? || appVideoList??)>
						<a href="upload2.do?obj=${obj!}&arch.archId=${arch.archId!}" class="greenBtn btnW145 fr"><span>上传成果内容</span></a>
					</#if>
					<ul class="stepNav clearfix">
						<li class="baseLink"><a href="<#if (0 == arch.archId!)>javascript:;<#else>arch_edit.do?obj=${obj!}&archId=${arch.archId!}<#if upd??>&upd=true</#if></#if>">基本信息</a><i class="navArr"></i></li>
						<li class="currStep"><a href="<#if (0 == arch.archId!)>javascript:;<#else>upload2List.do?obj=${obj!}&arch.archId=${arch.archId!}</#if>">成果内容</a></li>
					</ul>
				</div>
				
				<#if (appArticleList?? || appResourceList?? || appPhotoList?? || appVideoList??)>
					<div class="f_allResults">
						<#if appArticleList??>
							<div class="f_article">
								<h4>文章</h4>
							</div>
							<div class="f_caseBox">
								<ul class="articleLine">
									<#list appArticleList as a>
										<li>
											<a href="${Config.snsUrl!}/oppTool/article/queryAppArticleDetail.do?appArticleId=${a.appArticle.appArticleId!}&appCode=ARCHIVE&appObjId=${a.appArticle.appObjId!}" target="_blank">${a.article.title!}</a>
											<b class="f_time">${Util.getDateByLong(a.appArticle.createTime!, "yyyy-MM-dd")}</b>
											<span class="opts">
												<a href="${Config.snsUrl!}/personcenter/article/toModifyArticle.do?articleId=${a.article.articleId!}" class="edit" target="_blank">编辑</a>
												<a href="javascript:;" class="delete">删除</a>
												<span class="did" style="display: none;">${a.appArticle.appArticleId!}</span>
												<span class="type" style="display: none;">article</span>
											</span>
										</li>
									</#list>
								</ul>
							</div>
						</#if>
						<#if appResourceList??>
							<div class="f_article">
								<h4>资源</h4>
							</div>
							<div class="f_caseBox f_resIcon">
								<ul class="articleLine changeBg">
									<#list appResourceList as a>
										<li>
											<span class="resIcon ${Util.iconCss(a.resource.href!)}"></span>
											<a href="${Config.snsUrl!}/oppTool/resource/queryAppResourceDetail.do?appResourceId=${a.appResource.appResourceId!}&appCode=ARCHIVE&appObjId=${a.appResource.appObjId!}" target="_blank">${a.resource.title!}</a>
											<span class="opts">
												<a href="${Config.snsUrl!}/personcenter/resource/toModifyResource.do?resourceId=${a.resource.resourceId!}" class="edit" target="_blank">编辑</a>
												<a href="javascript:;" class="delete">删除</a>
												<span class="did" style="display: none;">${a.appResource.appResourceId!}</span>
												<span class="type" style="display: none;">resource</span>
											</span>
											<b class="f_time">${Util.getDateByLong(a.appResource.createTime!, "yyyy-MM-dd")}</b>
										</li>
									</#list>
								</ul>
							</div>
						</#if>
						<#if appPhotoList??>
							<div class="f_article">
								<h4>图片</h4>
							</div>
							<div class="f_ImgBox">
								<ul class="imgUl clearfix z_imgBox">
									<#list appPhotoList as a>
										<li>
											<div class="maskBox">
												<a href="${Config.snsUrl!}/oppTool/photo/queryAppPhotoDetail.do?appPhotoId=${a.appPhoto.appPhotoId!}&appCode=ARCHIVE&appObjId=${a.appPhoto.appObjId!}" target="_blank">
													<img src="${Config.snsUrl!}/data/upload/photo/${loginUser.userId!}/s_${a.sphoto.photoUrl!}" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/170x127.jpg'" width="170" height="127">
												</a>
												<div class="z_imgTitle">
													<span class="z_imgTitleText" id="${a.sphoto.photoId!}">${a.sphoto.summary!''}</span>
												</div>
					                            <p class="z_imgOpts"><a href="javascript:;" class="trash">删除</a></p>
												<span class="did" style="display: none;">${a.appPhoto.appPhotoId!}</span>
												<span class="type" style="display: none;">photo</span>
											</div>
										</li>
									</#list>
								</ul>
							</div>
						</#if>
						<#if appVideoList??>
							<div class="f_article">
								<h4>视频</h4>
							</div>
							<div class="f_videoBox">
								<ul class="imgUl clearfix">
									<#list appVideoList as a>
										<li>
											<div class="maskBox">
												<a href="${Config.snsUrl!}/oppTool/video/queryAppVideoDetail.do?appVideoId=${a.appVideo.appVideoId!}&appCode=ARCHIVE&appObjId=${a.appVideo.appObjId!}" target="_blank">
													<img src="${Config.snsUrl!}/data/upload/video/${loginUser.userId!}/${a.video.flvThumbnailHref!}" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/170x127.jpg'" width="170" height="127">
												</a>
												<p class="imgMask"></p>
												<a href="${Config.snsUrl!}/personcenter/video/queryVideoDetail.do?videoId=${a.video.videoId!}" target="_blank" class="maskCont playBtn"></a>
												<p class="opts">
													<a href="${Config.snsUrl!}/personcenter/video/toModifyVideo.do?videoId=${a.video.videoId!}" class="edit tipsPop" target="_blank"><i>编辑</i></a>
													<a href="javascript:;" class="delete tipsPop"><i>删除</i></a>
													<span class="did" style="display: none;">${a.appVideo.appVideoId!}</span>
													<span class="type" style="display: none;">video</span>
												</p>
											</div>
					                        <p class="imgDes">
					                        	<a href="${Config.snsUrl!}/personcenter/video/queryVideoDetail.do?videoId=${a.video.videoId!}" target="_blank" <#if (18 < a.video.title?length)>title="${a.video.title!}"</#if>>
					                        		 ${Util.getCountedWords(a.video.title!?html, 10)}
					                        	</a>
					                        </p>
										</li>
									</#list>
								</ul>
							</div>
						</#if>
					</div>
				<#else>
					<div class="selMod">
						<p class="sTit">请选择成果相对应的内容形式发布</p>
						<div class="sortList clearfix">
							<div class="innerList fl">
								<a href='${Config.snsUrl!}/oppTool/article/toAddAppArticle.do?groupId=0&appObjId=${arch.archId!}&appObjName=${obj!}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="artIcon"></span>发表文章</a>
								<a href='${Config.snsUrl!}/oppTool/photo/toAddPhoto.do?groupId=0&appObjId=${arch.archId!}&appObjName=${obj!}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="phoIcon"></span>图片</a>
								<a href='${Config.snsUrl!}/oppTool/resource/toAddAppResource.do?groupId=0&appObjId=${arch.archId!}&appObjName=${obj!}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="resouIcon"></span>资源</a>
								<a href='${Config.snsUrl!}/oppTool/video/toAddAppVideo.do?groupId=0&appObjId=${arch.archId!}&appObjName=${obj!}&appCode=ARCHIVE&sign=${sign!}&returnUrl=${returnUrl!}' target="_blank"><span class="videoIcon"></span>视频</a>
							</div>
						</div>
					</div>
				</#if>
			</div>
		</div>		
		<#include "../Footer.ftl" />
		<script type="text/javascript" src="js/common.js"></script>
		<script language="javascript" type="text/javascript">
		$(function() {
			$(".maskBox").live({"mouseover":function() {
					$(this).find(".imgMask, .maskCont, .opts").show();
				}, "mouseout":function() {
					$(this).find(".imgMask, .maskCont, .opts").hide();
				}
			});
			$(".f_ImgBox li").live({"mouseover": function() {
					$(this).addClass("currBox");
				}, "mouseout": function(){
					$(this).removeClass("currBox");
				}
			});
			$(".articleLine li").live({"mouseover":function() {
					$(this).addClass("trHover")
					$(this).find(".opts").show();
				}, "mouseout":function(){
					$(this).removeClass("trHover")
					$(this).find(".opts").hide();
				}
			});
			$(".delete, .trash").live("click", function() {
				var This = this;
				var cur = parseInt($(This).parents("li").find(".did").text());
				var t = $(This).parents("li").find(".type").text();
				confirmTip("确定删除吗？", function() {
					$.get("delResult.do", {"appId" : cur, "objType" : t }, function(data) {
						if ("success" == data) {
							delItem($(This).parents("li"), succTip);
						} else {
							errorTip(data);
						}
					});
				});
			});
		});
		$('.z_imgTitleText').click(function() {
			beginEdit($(this), $(this).attr("id"));
		});
		var beginEdit = function(obj, id) {
			var value = obj.val();
			var p = obj.parent();
			var input = $('<input type="text" value="'+obj.html()+'"/>').appendTo(p);
			$('<a title="保存" class="z_imgsave"></a>').appendTo(p).bind('click',function(){
				var v = input.val();
				$.get("arch_photo_summary.do", { "archId" : ${arch.archId!}, "topic" : v, "appContentId" : id }, function(data) {
					if ("success" == data) {
						succTip("图片描述修改成功！");
					} else {
						$.messager.alert("提示：", data, "error", "");
					}
				});
				obj.html(v);
				p.find('input').remove();
				p.find('a').remove();
			});
			$('<a title="取消" class="z_imgcancel"></a>').appendTo(p).bind('click',function(){
				p.find('input').remove();
				p.find('a').remove();
			});
		}
		</script>
	</body>
</html>