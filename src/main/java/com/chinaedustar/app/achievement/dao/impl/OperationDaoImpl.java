package com.chinaedustar.app.achievement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.dao.OperationDao;
import com.chinaedustar.app.achievement.domain.Operation;
import com.chinaedustar.common.dao.BaseDaoHibernate;

@Component("operationDao")
@SuppressWarnings("unchecked")
public class OperationDaoImpl extends BaseDaoHibernate<Operation, Integer> implements OperationDao {
	
	public void save(Operation operation) {
		super.save(operation);
	}
	
	public void del(Operation operation) {
		super.delete(operation);
	}
	
	public Operation queryOperationByArchIdAndUserId(int archId, String userId) {
		String hql = "FROM Operation WHERE archId = ? AND userId = ?";
		List<Operation> operationList = (List<Operation>) super.findByHql(hql, new Object[] { archId, userId });
		return 0 < operationList.size() ? operationList.get(0) : null;
	}
	
	public Operation queryByArchId(int archId) {
		String hql = "FROM Operation WHERE archId = ?";
		List<Operation> operationList = (List<Operation>) super.findByHql(hql, new Object[] { archId });
		return 0 < operationList.size() ? operationList.get(0) : null;
	}
	
}
