<div class="f_reTit">
    <p class="f_tabTit fr">
	    <#if (appArticleList?? || appResourceList?? || appPhotoList?? || appVideoList??)><a href="javascript:;" class="active">全部</a></#if>
	    <#if appArticleList??><a href="javascript:;">文章</a></#if>
	    <#if appResourceList??><a href="javascript:;">资源</a></#if>
	    <#if appPhotoList??><a href="javascript:;">图片</a></#if>
	    <#if appVideoList??><a href="javascript:;">视频</a></#if>
    </p>
	<h3 class="f_titleL"><#if (appArticleList?? || appResourceList?? || appPhotoList?? || appVideoList??)>成果内容</#if></h3>
</div>
<div class="f_tabCont">
    <div class="f_allDetail">
		<#if appArticleList??>
	        <div class="f_article">
	            <h4>文章</h4>
	        </div>
	        <div class="f_caseBox">
	            <ul class="articleLine">
					<#list appArticleList as a>
						<li>
							<b class="fr">${Util.getDateByLong(a.appArticle.createTime!, "yyyy-MM-dd")}</b>
							<a href="${Config.snsUrl!}/oppTool/article/queryAppArticleDetail.do?appArticleId=${a.appArticle.appArticleId!}&appCode=ARCHIVE&appObjId=${a.appArticle.appObjId!}" target="_blank">${a.article.title!}</a>
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
	            <ul class="articleLine">
					<#list appResourceList as a>
                        <li>
                        	<b class="fr">${Util.getDateByLong(a.appResource.createTime!, "yyyy-MM-dd")}</b>
                        	<span class="resIcon ${Util.iconCss(a.resource.href!)}"></span>
							<a href="${Config.snsUrl!}/oppTool/resource/queryAppResourceDetail.do?appResourceId=${a.appResource.appResourceId!}&appCode=ARCHIVE&appObjId=${a.appResource.appObjId!}" target="_blank">${a.resource.title!}</a>
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
	            <ul class="imgUl clearfix">
					<#list appPhotoList as a>
	                    <li> 
	                        <div class="maskBox">
								<a href="${Config.snsUrl!}/oppTool/photo/queryAppPhotoDetail.do?appPhotoId=${a.appPhoto.appPhotoId!}&appCode=ARCHIVE&appObjId=${a.appPhoto.appObjId!}" target="_blank">
									<img src="${Config.snsUrl!}/data/upload/photo/${loginUser.userId!}/s_${a.sphoto.photoUrl!}" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/151x113.jpg'" width="151" height="113" />
								</a>
								<#if ("" != a.sphoto.summary)>
									<p class="imgMask"></p>
									<p class="maskCont imgTxt">
										<a href="${Config.snsUrl!}/oppTool/photo/queryAppPhotoDetail.do?appPhotoId=${a.appPhoto.appPhotoId!}&appCode=ARCHIVE&appObjId=${a.appPhoto.appObjId!}" target="_blank">
											${a.sphoto.summary!}
										</a>
									</p>
								</#if>
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
	                            	<img src="${Config.snsUrl!}/data/upload/video/${loginUser.userId!}/${a.video.flvThumbnailHref!}" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/151x113.jpg'" width="151" height="113">
	                            </a> 
	                            <p class="imgMask"></p>
	                            <a href="${Config.snsUrl!}/personcenter/video/queryVideoDetail.do?videoId=${a.video.videoId!}" target="_blank" class="maskCont playBtn"></a>
	                        </div>
	                        <p class="imgDes">
	                        	<a href="${Config.snsUrl!}/personcenter/video/queryVideoDetail.do?videoId=${a.video.videoId!}" target="_blank" <#if (18 < a.video.title?length)>title="${a.video.title!}"</#if>>
	                        		${Util.getCountedWords(a.video.title!?html, 9)}
	                        	</a>
	                        </p>
	                    </li>
					</#list>
	            </ul>
	        </div>
		</#if>
    </div>
	<#if appArticleList??>
	    <div class="f_allDetail none">
	        <div class="f_article">
	            <h4>文章</h4>
	        </div>
	        <div class="f_caseBox">
	            <ul class="articleLine">
					<#list appArticleList as a>
						<li>
							<b class="fr">${Util.getDateByLong(a.appArticle.createTime!, "yyyy-MM-dd")}</b>
							<a href="${Config.snsUrl!}/oppTool/article/queryAppArticleDetail.do?appArticleId=${a.appArticle.appArticleId!}&appCode=ARCHIVE&appObjId=${a.appArticle.appObjId!}" target="_blank">${a.article.title!}</a>
						</li>
					</#list>
	            </ul>
	        </div>    
	    </div>
	</#if>
	<#if appResourceList??>
	    <div class="f_allDetail none">
	        <div class="f_article">
	            <h4>资源</h4>
	        </div>
	        <div class="f_caseBox f_resIcon">
	            <ul class="articleLine">
					<#list appResourceList as a>
	                    <li>
	                    	<b class="fr">${Util.getDateByLong(a.appResource.createTime!, "yyyy-MM-dd")}</b>
	                    	<span class="resIcon ${Util.iconCss(a.resource.href!)}"></span>
							<a href="${Config.snsUrl!}/oppTool/resource/queryAppResourceDetail.do?appResourceId=${a.appResource.appResourceId!}&appCode=ARCHIVE&appObjId=${a.appResource.appObjId!}" target="_blank">${a.resource.title!}</a>
	                    </li>
					</#list>
	            </ul>
	        </div>
	    </div>
	</#if>
	<#if appPhotoList??>
	    <div class="f_allDetail none">
	        <div class="f_article">
	            <h4>图片</h4>  
	        </div>
	        <div class="f_ImgBox">
	            <ul class="imgUl clearfix">
					<#list appPhotoList as a>
	                    <li> 
	                        <div class="maskBox">
								<a href="${Config.snsUrl!}/oppTool/photo/queryAppPhotoDetail.do?appPhotoId=${a.appPhoto.appPhotoId!}&appCode=ACHIEVEMENT&appObjId=${a.appPhoto.appObjId!}" target="_blank">
									<img src="${Config.snsUrl!}/data/upload/photo/${loginUser.userId!}/s_${a.sphoto.photoUrl!}" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/151x113.jpg'" width="151" height="113" />
								</a>
								<#if ("" != a.sphoto.summary)>
									<p class="imgMask"></p>
									<p class="maskCont imgTxt">
										<a href="${Config.snsUrl!}/oppTool/photo/queryAppPhotoDetail.do?appPhotoId=${a.appPhoto.appPhotoId!}&appCode=ARCHIVE&appObjId=${a.appPhoto.appObjId!}" target="_blank">
											${a.sphoto.summary!}
										</a>
									</p>
								</#if>
	                        </div>
	                    </li>
					</#list>
	            </ul>
	        </div>
	    </div>
	</#if>
    <#if appVideoList??>
	    <div class="f_allDetail none">
	        <div class="f_article">
	            <h4>视频</h4>
	        </div>
	        <div class="f_videoBox">
	            <ul class="imgUl clearfix">
					<#list appVideoList as a>
                        <li> 
                            <div class="maskBox">
                                <a href="${Config.snsUrl!}/oppTool/video/queryAppVideoDetail.do?appVideoId=${a.appVideo.appVideoId!}&appCode=ARCHIVE&appObjId=${a.appVideo.appObjId!}" target="_blank">
                                	<img src="${Config.snsUrl!}/data/upload/video/${loginUser.userId!}/${a.video.flvThumbnailHref!}" onerror="javascript:this.src='${Config.snsUrl!}/images/demoimg/151x113.jpg'" width="151" height="113">
                                </a>
                                <p class="imgMask"></p>
                                <a href="${Config.snsUrl!}/personcenter/video/queryVideoDetail.do?videoId=${a.video.videoId!}" target="_blank" class="maskCont playBtn"></a>
                            </div>
                            <p class="imgDes">
                            	<a href="${Config.snsUrl!}/personcenter/video/queryVideoDetail.do?videoId=${a.video.videoId!}" target="_blank" <#if (18 < a.video.title?length)>title="${a.video.title!}"</#if>>
                            		${Util.getCountedWords(a.video.title!?html, 9)}
                            	</a>
                            </p>
                        </li>
					</#list>
	            </ul>
	        </div>
		</div>
	</#if>
</div>