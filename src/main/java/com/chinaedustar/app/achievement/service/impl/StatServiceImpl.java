package com.chinaedustar.app.achievement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Stat;
import com.chinaedustar.app.achievement.service.BaseService;
import com.chinaedustar.app.achievement.service.StatService;

@Service("StatService")
public class StatServiceImpl extends BaseService implements StatService {

	public void save(Stat stat) throws ProException {
		try {
			this.daoManager.getStatDao().save(stat);
		} catch (Exception e) {
        	throw new ProException("【StatServiceImpl - save】保存对象发生异常！", e);
		}
	}
    
	public Stat queryStatByUserId(String userId) throws ProException {
		try {
			return this.daoManager.getStatDao().queryStatByUserId(userId);
		} catch (Exception e) {
        	throw new ProException("【StatServiceImpl - queryStatByUserId】根据用户ID查找统计表中是否有对象记录发生异常！", e);
		}
	}
	
	public List<Stat> queryStatList() throws ProException {
		try {
			return (List<Stat>) this.daoManager.getStatDao().queryStatList();
		} catch (Exception e) {
        	throw new ProException("【StatServiceImpl - queryStatList】查首页的6个贡献达人发生异常！", e);
		}
	}
	
}
