package com.chinaedustar.app.achievement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.dao.StatDao;
import com.chinaedustar.app.achievement.domain.Stat;
import com.chinaedustar.common.dao.BaseDaoHibernate;
import com.chinaedustar.common.vo.QueryRule;

@Component("statDao")
public class StatDaoImpl extends BaseDaoHibernate<Stat, Integer> implements StatDao {
	
	public void save(Stat stat) {
		super.save(stat);
	}
	
	@SuppressWarnings("unchecked")
	public Stat queryStatByUserId(String userId) {
		String hql = "FROM Stat WHERE userId = ?";
		List<Stat> statList = (List<Stat>) super.findByHql(hql, new Object[] { userId });
		return 0 < statList.size() ? statList.get(0) : null;
	}
    
	public List<Stat> queryStatList() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addNotEqual("userId", "Site");
		queryRule.addDescOrder("sum");
		return super.findList(queryRule, 0, 6);
	}
	
}
