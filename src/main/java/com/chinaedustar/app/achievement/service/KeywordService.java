package com.chinaedustar.app.achievement.service;

import java.util.List;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Keyword;

public interface KeywordService {
	
	Keyword findById(int id) throws ProException;
	
	void del(Keyword keyword) throws ProException;
    
    List<Keyword> queryKeywordList(List<Integer> list) throws ProException;
    
    List<Keyword> queryKeywordByArchId(int archId) throws ProException;

    List<Keyword> queryKeywordOrderbyCount() throws ProException;
    
    List<Keyword> queryKeywordByName(String name) throws ProException;
    
    List<Keyword> queryKeywordPageList(String k, Pager pager) throws ProException;
    
}
