package com.chinaedustar.app.achievement.action;

import com.chinaedustar.app.achievement.common.ProBaseAction;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Author;

@SuppressWarnings("serial")
public class AuthorAction extends ProBaseAction {

    public String del() throws ProException {
    	Author author = this.serviceManager.getAuthorService().findById(archId);
    	if (null != author) {
    		this.serviceManager.getAuthorService().del(author);
    		renderText("success");
    	} else {
			renderText("作者不存在或已被删除！");
		}
    	return NONE;
    }

}
