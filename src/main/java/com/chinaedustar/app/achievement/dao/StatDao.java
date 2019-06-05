package com.chinaedustar.app.achievement.dao;

import java.util.List;

import com.chinaedustar.app.achievement.domain.Stat;

public interface StatDao {

	void save(Stat stat);
	
	Stat queryStatByUserId(String userId);
	
	List<Stat> queryStatList();

}
