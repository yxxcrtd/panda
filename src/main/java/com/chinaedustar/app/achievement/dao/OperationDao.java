package com.chinaedustar.app.achievement.dao;

import com.chinaedustar.app.achievement.domain.Operation;

public interface OperationDao {

	void save(Operation operation);
	
	void del(Operation operation);
	
	Operation queryOperationByArchIdAndUserId(int archId, String userId);
	
	Operation queryByArchId(int archId);
	
}
