package com.chinaedustar.app.achievement.service.impl;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Operation;
import com.chinaedustar.app.achievement.service.BaseService;
import com.chinaedustar.app.achievement.service.OperationService;

@Service("OperationService")
public class OperationServiceImpl extends BaseService implements OperationService {

	public void save(Operation operation) throws ProException {
		try {
			this.daoManager.getOperationDao().save(operation);
		} catch (Exception e) {
        	throw new ProException("【OperationServiceImpl - save】保存对象发生异常！", e);
		}
	}

	public void del(Operation operation) throws ProException {
		try {
			this.daoManager.getOperationDao().del(operation);
		} catch (Exception e) {
        	throw new ProException("【OperationServiceImpl - del】删除对象发生异常！", e);
		}
	}
    
	public Operation queryOperationByArchIdAndUserId(int archId, String userId) throws ProException {
		try {
			return this.daoManager.getOperationDao().queryOperationByArchIdAndUserId(archId, userId);
		} catch (Exception e) {
        	throw new ProException("【OperationServiceImpl - queryOperationByArchIdAndUserId】发生异常！", e);
		}
	}
	
}
