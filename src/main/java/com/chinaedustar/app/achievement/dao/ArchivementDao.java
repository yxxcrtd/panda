package com.chinaedustar.app.achievement.dao;

import java.util.List;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.domain.Archivement;
import com.chinaedustar.common.vo.PageInfo;

public interface ArchivementDao {

	Archivement findById(int archId);
	
	Archivement findByIdAndUserId(int id, String userId);
	
	void save(Archivement arch);
	
	void del(Archivement arch);
	
	PageInfo<Archivement> queryArchPageList(String keyword, String obj, int pageNo, int pageSize);
	
	PageInfo<Archivement> queryArchCategoryPageList(String category, int type, boolean prize, boolean publish, int pageNo, int pageSize);
	
	List<Archivement> queryArchListByUserIdAndObj(String userId, String obj);
	
	List<Archivement> queryArchPageListByUserIdAndObj(String userId, String obj, int topicFlag, Pager pager);

	int queryArchCount(String obj, int type, String userId);
	
	List<Archivement> queryArchList(String obj, String orderby, int count);
	
	List<Archivement> queryArchHotList(String obj, int count);
	
	List<Archivement> queryArchListByIds(List<Integer> list);
	
}
