package com.chinaedustar.app.achievement.service;

import java.util.List;
import java.util.Set;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Archivement;
import com.chinaedustar.app.achievement.domain.Author;
import com.chinaedustar.app.achievement.domain.Keyword;
import com.chinaedustar.common.vo.PageInfo;

public interface ArchivementService {
	
	Archivement findById(int id) throws ProException;
	
	Archivement findByIdAndUserId(int id, String userId) throws ProException;
	
	void save(Archivement arch) throws ProException;
	
	void del(Archivement arch) throws ProException;
    
    int save(Archivement arch, Set<Author> authors, Set<Keyword> keywords) throws ProException;
    
    PageInfo<Archivement> queryArchPageList(String keyword, String obj, int pageNo, int pageSize) throws ProException;
    
    PageInfo<Archivement> queryArchCategoryPageList(String category, int type, boolean prize, boolean publish, int pageNo, int pageSize) throws ProException;
    
    List<Archivement> queryArchListByUserIdAndObj(String userId, String obj) throws ProException;
    
    List<Archivement> queryArchPageListByUserIdAndObj(String userId, String obj, int topicFlag, Pager pager) throws ProException;

    int queryArchCount(String obj, int type, String userId) throws ProException;
    
    List<Archivement> queryArchList(String obj, String orderby, int count) throws ProException;
    
    List<Archivement> queryArchHotList(String obj, int count) throws ProException;
    
    List<Archivement> queryArchListByIds(List<Integer> list) throws ProException;
    
}
