package com.chinaedustar.app.achievement.action;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinaedustar.app.achievement.common.MyComparator;
import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.ProBaseAction;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Archivement;
import com.chinaedustar.app.achievement.domain.ArchivementExtra;
import com.chinaedustar.app.achievement.domain.Author;
import com.chinaedustar.app.achievement.domain.Keyword;
import com.chinaedustar.app.achievement.domain.Operation;
import com.chinaedustar.common.utils.JsonUtils;
import com.chinaedustar.hessian.app.bean.Topic;
import com.chinaedustar.hessian.factory.HessianFactoryUtils;
import com.chinaedustar.honeybee.constants.AppConst;
import com.chinaedustar.honeybee.constants.ConstDefine;
import com.chinaedustar.honeybee.constants.HessianConst;
import com.chinaedustar.honeybee.domain.STopic;
import com.chinaedustar.honeybee.domain.SUserBaseInfo;
import com.chinaedustar.honeybee.vo.hessian.HessianResultVo;
import com.chinaedustar.honeybee.vo.user.PortalUserVo;

@SuppressWarnings("serial")
public class ArchivementAction extends ProBaseAction {
	
	public String uploadBaseInfo() throws ProException {
		return SUCCESS;
	}
    
	public String uploadResult() throws ProException {
    	if (validateObj()) {
    		return NOT_FIND;
    	}
		if (null == arch) {
			addActionError("请不要更改参数！");
			return NOT_FIND;
		} else {
			arch = serviceManager.getArchivementService().findById(arch.getArchId());
			if (null == arch || !obj.equals(arch.getCategory())) {
	    		addActionError("科研成果类型不匹配！");
	    		return NOT_FIND;
			}
		}
		returnUrl = getReturnListURL()+"?arch.archId="+arch.getArchId();
		sign = getSIGN(arch.getArchId(), returnUrl);
		showAppsList();
		return SUCCESS;
	}
	
	public String upload2Result() throws ProException {
		arch = serviceManager.getArchivementService().findById(Integer.valueOf(appObjId));
		if (null != arch) {
			obj = arch.getCategory();
			appObjCategoryIdName = getArchNameByObj(obj);
		}
		if (ConstDefine.CONTENT_TYPE_ARTICLE == Integer.valueOf(objType)) {
			objTypeName = "文章";
		} else if (ConstDefine.CONTENT_TYPE_PICTURE == Integer.valueOf(objType)) {
			objTypeName = "图片";
		} else if (ConstDefine.CONTENT_TYPE_RESOURCE == Integer.valueOf(objType)) {
			objTypeName = "资源";
		} else if (ConstDefine.CONTENT_TYPE_VIDEO == Integer.valueOf(objType)) {
			objTypeName = "视频";
		}
		if (!"true".equals(suc)) {
			addActionError(appObjCategoryIdName + "成果" + objTypeName + "发布失败，请联系您的系统管理员！");
			return NOT_FIND;
		}
		returnUrl = getReturnURL();
		sign = getSIGN(arch.getArchId(), returnUrl);
		logger.info(appObjCategoryIdName + "成果" + objTypeName + "发布成功！");
		return SUCCESS;
	}
	
	public String upload2ResultList() throws ProException {
		arch = serviceManager.getArchivementService().findById(arch.getArchId());
		if (null == arch || !obj.equals(arch.getCategory())) {
    		addActionError("科研成果类型不匹配！");
    		return NOT_FIND;
		}
		showAppsList();
		returnUrl = getReturnListURL()+"?arch.archId="+arch.getArchId();
		sign = getSIGN(arch.getArchId(), returnUrl);
		return SUCCESS;
	}
	
	public String delResult() throws ProException {
		map.put("userId", loginUser.getUserId());
		map.put("appCode", AppConst.APP_CODE_ARCHIVE);
		if (null == objType && "".equals(objType) && null == appId && "".equals(appId)) {
			renderText("无效的请求！");
		}
		JSONObject obj = null;
		switch (objType) {
		case "article":
			map.put("appArticleId", appId);
			obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getArticleService().isArticleAuthor(JsonUtils.toJson(map)));
			if ("1".equals(obj.get("status"))) {
				map.put("appArticleIds", new String[] { appId });
				obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getArticleService().delAppArticles(JsonUtils.toJson(map)));
				if ("0".equals(obj.get("status"))) {
					renderText(String.valueOf(obj.get("message")));
				}
			} else {
				renderText("不能删除别人的文章！");
			}
			break;
		case "resource":
			map.put("appResourceId", appId);
			obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getResourceService().isResourceAuthor(JsonUtils.toJson(map)));
			if ("1".equals(obj.get("status"))) {
				map.put("appResourceIds", new String[] { appId });
				obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getResourceService().delAppResources(JsonUtils.toJson(map)));
				if ("0".equals(obj.get("status"))) {
					renderText(String.valueOf(obj.get("message")));
				}
			} else {
				renderText("不能删除别人的资源！");
			}
			break;
		case "photo":
			map.put("appPhotoId", appId);
			obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getPhotoService().isPhotoAuthor(JsonUtils.toJson(map)));
			if ("1".equals(obj.get("status"))) {
				map.put("appPhotoIds", new String[] { appId });
				obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getPhotoService().delAppPhotos(JsonUtils.toJson(map)));
				if ("0".equals(obj.get("status"))) {
					renderText(String.valueOf(obj.get("message")));
				}
			} else {
				renderText("不能删除别人的图片！");
			}
			break;
		case "video":
			map.put("appVideoId", appId);
			obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getVideoService().isVideoAuthor(JsonUtils.toJson(map)));
			if ("1".equals(obj.get("status"))) {
				map.put("appVideoIds", new String[] { appId });
				obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getVideoService().delAppVideos(JsonUtils.toJson(map)));
				if ("0".equals(obj.get("status"))) {
					renderText(String.valueOf(obj.get("message")));
				}
			} else {
				renderText("不能删除别人的视频！");
			}
			break;
		default:
			break;
		}
		renderText(SUCCESS);
		return NONE;
	}
	
	public String summary() throws ProException {
		
		map.put("summary", topic);
		map.put("photoId", appContentId);
		HessianResultVo resultVo = JsonUtils.parseObject(HessianFactoryUtils.honeyBeeServiceFactory.getPhotoService().editPhotoSummary(JsonUtils.toJson(map)),HessianResultVo.class);
		if(resultVo.getStatus().equals(HessianConst.STATUS_SUCCESS)){
			renderText("success");
		}else{
			renderText("图片描述修改失败！");
		}
		
		return NONE;
	}
	
    public String edit() throws ProException {
    	//判断是否这两个有值，有则说明是从外部调用过来的
    	if(0!=topicResearchId&&null!=topicResearchName&&!"".equals(topicResearchName)){
			topicFlag=3;
		}
    	if (validateObj()) {
    		return NOT_FIND;
    	}
    	arch = this.serviceManager.getArchivementService().findById(archId);
    	if (null == arch) {
    		arch = new Archivement();
    		arch.setCreateTime(new Date().getTime());
    		arch.setArchivementExtra(new ArchivementExtra());;
    		arch.archExtra.setServicePatent(true);
    		arch.archExtra.setAuthorize(true);
    	} else {
    		if (!obj.equals(arch.getCategory())) {
        		addActionError("科研成果类型不匹配！");
        		return NOT_FIND;
    		}
    		if (!loginUser.getUserId().equals(arch.getUserId())) {
        		addActionError("请不要修改别人的科研成果！");
        		return NOT_FIND;
    		}
    		showAppsList();
    	}
    	if (null == archExtra) {
    		archExtra = new ArchivementExtra();
    	}
    	dictTypeList = serviceManager.getDictionaryService().getDictionaryList(obj);
    	dictPrizeLevelList = serviceManager.getDictionaryService().getDictionaryList("prizeLevel");
    	authorList = this.serviceManager.getAuthorService().queryAuthorByArchId(archId);
    	keywordList = this.serviceManager.getKeywordService().queryKeywordByArchId(archId);
    	
    	HessianResultVo resultVo = JsonUtils.parseObject(HessianFactoryUtils.appServiceFactory.getResearchHessianService().queryTopicResearchOfUser(loginUser.getUserId()),HessianResultVo.class);
    	
    	if(resultVo.getStatus().equals(HessianConst.STATUS_SUCCESS)){
    		
    		topicList = JsonUtils.parseList(resultVo.getData(), Topic.class);
		}
    	return "edit_success";
    }
    
    public String queryStage() throws ProException {
    	HessianResultVo resultVo = JsonUtils.parseObject(HessianFactoryUtils.appServiceFactory.getResearchHessianService().queryStageOfTopicResearch(String.valueOf(topicId)),HessianResultVo.class);
    	if(resultVo.getStatus().equals(HessianConst.STATUS_SUCCESS)){
    		JSONArray array = JSONArray.parseArray(resultVo.getData());
    		this.rendJson(EXE_SUCCESS, array);
		}else{
			this.rendJson(EXE_FAIL, "没有查询到课题阶段！");
		}
    	return NONE;
    }
    
    public String save() throws ProException {
    	if (null != arch) {
			Set<Author> aus = new TreeSet<Author>(new MyComparator<Author>());
			Set<Keyword> keys = new TreeSet<Keyword>(new MyComparator<Keyword>());
    		if (0 == arch.getArchId()) {
    			arch.setCreateTime(System.currentTimeMillis());
    		}
			String authorString = "";
			for (int i = 0; authors.length > i; i++) {
				Author author = new Author();
				if (authors[i].contains(",")) {
					author.setName(authors[i].substring(0, authors[i].indexOf(",")));
					authorString += authors[i].substring(0, authors[i].indexOf(",")) + ",";
					author.setNameId(authors[i].substring(authors[i].indexOf(",") + 1, authors[i].length()));
					try{
    					String userId = author.getNameId();
    					String json = HessianFactoryUtils.honeyBeeServiceFactory.getUserService().queryUserById(userId);
    					HessianResultVo vo = JsonUtils.parseObject(json, HessianResultVo.class);
    					if(vo.getStatus().equals("1")){
    					    SUserBaseInfo user = JsonUtils.parseObject(vo.getData().toString(), SUserBaseInfo.class);
    					    if(user != null){
    					        author.setUnitId(user.getUnitId());
    					        author.setUnitPath(user.getUnitPathInfo());
    					    }
    					}
					}
					catch(Exception e){					    
					    this.logger.error("查询用户出现错误", e);
					}
					
				} else {
					author.setName(authors[i]);
					authorString += authors[i] + ",";
				}
				aus.add(author);
			}
    		arch.setAuthor(authorString.substring(0, authorString.length() - 1));
    		if (null != keywords && 0 < keywords.length) {
    			String keywordString = "";
        		for (int i = 0; keywords.length > i; i++) {
        			Keyword keyword = new Keyword();
        			keyword.setName(keywords[i]);
        			keys.add(keyword);
        			keywordString += keywords[i] + ",";
        		}
        		arch.setKeyword(keywordString.substring(0, keywordString.length() - 1));
    		}
    		int i = this.serviceManager.getArchivementService().save(arch, aus, keys);
    		archExtra.setArchId(i);
    		this.serviceManager.getArchivementExtraService().save(archExtra);
    		showAppsList();
    		renderText(String.valueOf(i));
    	}
		return NONE;
    }
    
    public String del() throws ProException {
		arch = this.serviceManager.getArchivementService().findById(archId);
		if (null != arch) {
			this.serviceManager.getArchivementService().del(arch);
			renderText("success");
		} else {
			renderText("科研成果不存在或已被删除！");
		}
    	return NONE;
    }
    
    public String detail() throws ProException {
    	arch = this.serviceManager.getArchivementService().findById(arch.getArchId());
    	if (null != arch) {
    		showAppsList();
    		appTopicList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getTopicService().queryAppTopics(JsonUtils.toJson(map)), false, STopic.class);
    		op = this.serviceManager.getOperationService().queryOperationByArchIdAndUserId(arch.getArchId(), loginUser.getUserId());
    		if (null == op) {
    			op = new Operation();
    			op.setCollect(false);
    			op.setPraise(false);
    		}
    		if(!loginUser.getUserId().equals(arch.getUserId())){
    			int beforViewCount = arch.getViewCount();
        		arch.setViewCount(beforViewCount + 1);
        		arch.setHotCount(arch.getViewCount() + arch.getCollectCount() + arch.getPraiseCount());
        		this.serviceManager.getArchivementService().save(arch);
    		}
    		
    		JSONObject obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getUserService().queryUserById(arch.getUserId()));
        	if ("1".equals(obj.get("status"))) {
        		user = JsonUtils.parseObject(obj.get("data").toString(), SUserBaseInfo.class);
        	}
        	pager = new Pager();
        	pager.setPageNo(0);
        	pager.setPageSize(10);
        	archList = serviceManager.getArchivementService().queryArchPageListByUserIdAndObj(arch.getUserId(), arch.getCategory(), topicFlag, pager);
        	authorList = this.serviceManager.getAuthorService().queryAuthorByArchId(arch.getArchId());
        	dictPrizeLevelList = serviceManager.getDictionaryService().getDictionaryList("prizeLevel");
        	dictTypeList = serviceManager.getDictionaryService().getDictionaryList(arch.getCategory());
    	} else {
    		addActionError("科研成果不存在！");
    		return NOT_FIND;
    	}
    	return SUCCESS;
    }
    
    public String topic() throws ProException {
    	map.put("appCode", AppConst.APP_CODE_ARCHIVE);
    	map.put("appObjId", archId);
    	map.put("appObjName", objTypeName);
		map.put("title", topic);
		map.put("content", content);
		map.put("userId", loginUser.getUserId());
		JSONObject obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getTopicService().addAppTopics(JsonUtils.toJson(map)));
		if ("1".equals(obj.get("status"))) {
			renderText("success");
		} else {
			renderText("话题发布失败！");
		}
    	return NONE;
    }
    
    public String topic_more() throws ProException {
    	arch = this.serviceManager.getArchivementService().findById(archId);
    	if (null != arch) {
        	pager = new Pager();
        	pager.setPageNo(Integer.valueOf(null == p ? "1" : p));
    		map.put("appCode", AppConst.APP_CODE_ARCHIVE);
    		map.put("appObjId", archId);
    		map.put("pageNo", null == p ? "1" : p);
    		map.put("pageSize", pager.getPageSize());
    		JSONObject obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getTopicService().queryAppTopicPageInfo(JsonUtils.toJson(map)));
    		if ("1".equals(obj.get("status"))) {
    			obj = (JSONObject) JSON.parse(obj.get("data").toString());
    			totalCount = obj.get("totalCount").toString();
    			if (null != obj.get("data") && !"".equals(obj.get("data"))) {
    				appTopicList = JsonUtils.parseList(obj.get("data").toString(), STopic.class);
    			}
    		}
    		pager.setTotalCount(Integer.valueOf(totalCount));
		} else {
			addActionError("科研成果不存在！");
			return NOT_FIND;
		}
    	return SUCCESS;
    }
    
    public String topic_del() throws ProException {
    	map.put("appTopicIds", new String[] { appContentId });
    	map.put("userId", loginUser.getUserId());
    	JSONObject obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getTopicService().isTopicAuthor(JsonUtils.toJson(map)));
    	if ("1".equals(obj.get("status"))) {
    		obj = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getTopicService().delAppTopics(JsonUtils.toJson(map)));
    		if ("1".equals(obj.get("status"))) {
    			renderText("success");
    		} else {
    			renderText(obj.get("message").toString());
    		}
    	} else {
			renderText("不能删除别人的话题！");
		}
		return NONE;
    }
    
    public String search() throws ProException {
    	k = k.trim();
    	pager = new Pager();
    	pager.setPageNo(Integer.valueOf(null == p ? "1" : p));
    	archPageList = this.serviceManager.getArchivementService().queryArchPageList(k, obj, Integer.valueOf(null == p ? "1" : p), pageSize);
    	pager.setTotalCount(archPageList.getTotalCount());
    	return SUCCESS;
    }
    
    public String search_user() throws ProException {
    	map.put("keyword", k);
    	searchUserList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getUserService().queryUsers(JsonUtils.toJson(map)), false, PortalUserVo.class);
    	return SUCCESS;
    }
   
}
