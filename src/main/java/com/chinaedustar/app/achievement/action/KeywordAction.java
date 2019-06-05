package com.chinaedustar.app.achievement.action;

import java.util.ArrayList;
import java.util.List;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.ProBaseAction;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Keyword;

@SuppressWarnings("serial")
public class KeywordAction extends ProBaseAction {

    public String del() throws ProException {
    	Keyword keyword = this.serviceManager.getKeywordService().findById(archId);
    	if (null != keyword) {
    		this.serviceManager.getKeywordService().del(keyword);
    		renderText("success");
    	} else {
			renderText("关键词不存在或已被删除！");
		}
    	return NONE;
    }

    public String show() throws ProException {
    	k = k.trim();
    	pager = new Pager();
    	pager.setPageNo(Integer.valueOf(null == p ? "1" : p));
    	keywordList = this.serviceManager.getKeywordService().queryKeywordPageList(k, pager);
    	pager.setTotalCount(this.serviceManager.getKeywordService().queryKeywordByName(k).size());
    	List<Integer> l = new ArrayList<Integer>();
    	for (Keyword k : keywordList) {
    		l.add(k.getArchId());
    	}
    	archList = this.serviceManager.getArchivementService().queryArchListByIds(l);
    	return SUCCESS;
    }
    
}
