package com.chinaedustar.app.achievement.service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Operation;

public interface OperationService {
	
	void save(Operation operation) throws ProException;
	
	void del(Operation operation) throws ProException;
	
	Operation queryOperationByArchIdAndUserId(int archId, String userId) throws ProException;
    
}
