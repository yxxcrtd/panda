package com.chinaedustar.app.achievement.service;

import java.util.List;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Stat;

public interface StatService {
	
	void save(Stat stat) throws ProException;
	
	Stat queryStatByUserId(String userId) throws ProException;
    
    List<Stat> queryStatList() throws ProException;
    
}
