package com.chinaedustar.app.achievement.dao;

import java.util.List;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.domain.Keyword;

public interface KeywordDao {
	
	Keyword findById(int id);

	void save(Keyword keyword);
	
	void del(Keyword keyword);
	
//	void delById(int id);

	List<Keyword> queryKeywordList(List<Integer> list);
	
	List<Keyword> queryKeywordByArchId(int archId);
	
	List<Keyword> queryKeywordOrderbyCount();
	
	List<Keyword> queryKeywordByName(String name);
	
	List<Keyword> queryKeywordPageList(String k, Pager pager);

}
