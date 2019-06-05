package com.chinaedustar.app.achievement.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.ProBaseAction;
import com.chinaedustar.app.achievement.common.constants.ConstDefine;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Stat;
import com.chinaedustar.common.utils.JsonUtils;
import com.chinaedustar.hessian.factory.HessianFactoryUtils;
import com.chinaedustar.honeybee.domain.SUserBaseInfo;

/**
 * 首页
 */
@SuppressWarnings("serial")
public class IndexAction extends ProBaseAction {

    public String index() throws ProException {
    	archList = serviceManager.getArchivementService().queryArchListByUserIdAndObj(loginUser.getUserId(), "");
    	for (String s : ConstDefine.CATEGORY_KEY) {
    		switch (s) {
			case "thesis": 
				thesisHotArchList = serviceManager.getArchivementService().queryArchHotList(s, 7);
				thesisNewArchList = serviceManager.getArchivementService().queryArchList(s, "createTime", 10);
				break;
			case "book": 
				bookHotArchList = serviceManager.getArchivementService().queryArchHotList(s, 7);
				bookNewArchList = serviceManager.getArchivementService().queryArchList(s, "createTime", 10);
				break;
			case "courseware": 
				coursewareHotArchList = serviceManager.getArchivementService().queryArchHotList(s, 7);
				coursewareNewArchList = serviceManager.getArchivementService().queryArchList(s, "createTime", 10);
				break;
			case "instruction": 
				instructionHotArchList = serviceManager.getArchivementService().queryArchHotList(s, 7);
				instructionNewArchList = serviceManager.getArchivementService().queryArchList(s, "createTime", 10);
				break;
			case "patent": 
				patentHotArchList = serviceManager.getArchivementService().queryArchHotList(s, 7);
				patentNewArchList = serviceManager.getArchivementService().queryArchList(s, "createTime", 10);
				break;
			case "other": 
				otherHotArchList = serviceManager.getArchivementService().queryArchHotList(s, 7);
				otherNewArchList = serviceManager.getArchivementService().queryArchList(s, "createTime", 10);
				break;
			default:
				break;
			}
    	}
    	allNewArchList = serviceManager.getArchivementService().queryArchList("", "createTime", 10);
    	statList = serviceManager.getStatService().queryStatList();
    	List<String> ls = new ArrayList<String>();
    	for (Stat s : statList) {
			ls.add(s.getUserId());
		}
    	userList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getUserService().queryUserList(JsonUtils.toJson(ls)), false, SUserBaseInfo.class);
    	stat = serviceManager.getStatService().queryStatByUserId("Site");
//    	List<Integer> l = new ArrayList<Integer>();
//    	List<Archivement> list = serviceManager.getArchivementService().queryArchList("", "viewCount", 10);
//    	for (Archivement a : list) {
//			l.add(a.getArchId());
//		}
//    	keywordList = serviceManager.getKeywordService().queryKeywordList(l);
    	keywordList = this.serviceManager.getKeywordService().queryKeywordOrderbyCount();
    	return SUCCESS;
    }
    
    public String his() throws ProException {
    	pager = new Pager();
    	pager.setPageNo(Integer.valueOf(null == p ? "1" : p));
    	archList = serviceManager.getArchivementService().queryArchPageListByUserIdAndObj(userId, obj, topicFlag, pager);
    	pager.setTotalCount(serviceManager.getArchivementService().queryArchCount(obj, 0, userId));
    	JSONObject json = (JSONObject) JSON.parse(HessianFactoryUtils.honeyBeeServiceFactory.getUserService().queryUserById(userId));
    	if ("1".equals(json.get("status"))) {
    		user = JsonUtils.parseObject(json.get("data").toString(), SUserBaseInfo.class);
    	}
    	stat = serviceManager.getStatService().queryStatByUserId(userId);
    	hotArchList = serviceManager.getArchivementService().queryArchList(null == obj ? "" : obj, "viewCount", 10);
    	return SUCCESS;
    }
    
    public String my() throws ProException {
    	if (validateObj()) {
    		return NOT_FIND;
    	}
    	pager = new Pager();
    	pager.setPageNo(Integer.valueOf(null == p ? "1" : p));
    	archList = this.serviceManager.getArchivementService().queryArchPageListByUserIdAndObj(loginUser.getUserId(), obj, topicFlag, pager);
    	pager.setTotalCount(this.serviceManager.getArchivementService().queryArchCount(obj, 0, loginUser.getUserId()));
    	if (!"courseware".equals(obj)) {
    		dictTypeList = this.serviceManager.getDictionaryService().getDictionaryList(obj);
    	}
    	return SUCCESS;
    }

}
