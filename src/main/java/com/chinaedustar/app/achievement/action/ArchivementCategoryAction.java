package com.chinaedustar.app.achievement.action;

import java.util.ArrayList;
import java.util.List;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.ProBaseAction;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Archivement;

@SuppressWarnings("serial")
public class ArchivementCategoryAction extends ProBaseAction {
	
	public String category() throws ProException {
    	if (validateObj()) {
    		return NOT_FIND;
    	}
		dictTypeList = serviceManager.getDictionaryService().getDictionaryList(obj);
		stat = serviceManager.getStatService().queryStatByUserId("Site");
		pager = new Pager();
		pager.setPageNo(Integer.valueOf(null == p ? "1" : p));
		archPageList = this.serviceManager.getArchivementService().queryArchCategoryPageList(obj, t, prize, publish, Integer.valueOf(p), pageSize);
		pager.setTotalCount(this.serviceManager.getArchivementService().queryArchCount(obj, t, ""));
		// 没有数据不能 for 循环
		if(null != archPageList  && null != archPageList.getData() && 0 < archPageList.getData().size()){
    		List<Integer> l = new ArrayList<Integer>();
    		for (Archivement a : archPageList.getData()) {
    			l.add(a.getArchId());
    		}
    		authorList = this.serviceManager.getAuthorService().queryAuthorByIds(l);
		}
		return SUCCESS;
	}
   
}
