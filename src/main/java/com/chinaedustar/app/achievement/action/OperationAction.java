package com.chinaedustar.app.achievement.action;

import com.chinaedustar.app.achievement.common.ProBaseAction;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Operation;

/**
 * 收藏和点赞
 */
@SuppressWarnings("serial")
public class OperationAction extends ProBaseAction {

	// 收藏
    public String collect() throws ProException {
    	op = this.serviceManager.getOperationService().queryOperationByArchIdAndUserId(archId, loginUser.getUserId());
    	if (null == op) {
    		op = new Operation();
    		op.setCollect(true);
    		op.setUserId(loginUser.getUserId());
    		collectCount(archId, 1);
    	} else {
    		if (op.getCollect()) {
    			op.setCollect(false);
    			// 为false减1
    			collectCount(archId, -1);
    			// 都为假的话，删除记录
    			if (!op.getPraise()) {
    				this.serviceManager.getOperationService().del(op);
    		    	renderText("success");
    		    	return NONE;
    			}
    		} else {
    			op.setCollect(true);
    			// 为true加1
    			collectCount(archId, 1);
    		}
    	}
    	op.setArchId(archId);
    	this.serviceManager.getOperationService().save(op);
    	renderText("success");
    	return NONE;
    }
    
    // 点赞
    public String praise() throws ProException {
    	op = this.serviceManager.getOperationService().queryOperationByArchIdAndUserId(archId, loginUser.getUserId());
    	if (null == op) {
    		op = new Operation();
    		op.setPraise(true);
    		op.setUserId(loginUser.getUserId());
    		praiseCount(archId, 1);
    	} else {
    		if (op.getPraise()) {
    			op.setPraise(false);
        		praiseCount(archId, -1);
    			// 都为假的话，删除记录
    			if (!op.getCollect()) {
    				this.serviceManager.getOperationService().del(op);
    		    	renderText("success");
    		    	return NONE;
    			}
    		} else {
    			op.setPraise(true);
        		praiseCount(archId, 1);
    		}
    	}
    	op.setArchId(archId);
    	this.serviceManager.getOperationService().save(op);
    	renderText("success");
    	return NONE;
    }
    
    private void collectCount(int archId, int value) throws ProException {
		arch = this.serviceManager.getArchivementService().findById(archId);
		if (null != arch) {
			arch.setCollectCount(arch.getCollectCount() + value);
			this.serviceManager.getArchivementService().save(arch);
		}
    }
    
    private void praiseCount(int archId, int value) throws ProException {
		arch = this.serviceManager.getArchivementService().findById(archId);
		if (null != arch) {
			arch.setPraiseCount(arch.getPraiseCount() + value);
			this.serviceManager.getArchivementService().save(arch);
		}
    }

}
