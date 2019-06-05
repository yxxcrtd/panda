package com.chinaedustar.app.achievement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Keyword;
import com.chinaedustar.app.achievement.service.BaseService;
import com.chinaedustar.app.achievement.service.KeywordService;

@Service("KeywordService")
public class KeywordServiceImpl extends BaseService implements KeywordService {
	
	public Keyword findById(int id) throws ProException {
		try {
			return this.daoManager.getKeywordDao().findById(id);
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - findById 发生异常！ 】", e);
		}
	}
	
	public void del(Keyword keyword) throws ProException {
		try {
			this.daoManager.getKeywordDao().del(keyword);
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - del 发生异常！ 】", e);
		}
	}

	public List<Keyword> queryKeywordList(List<Integer> list) throws ProException {
		try {
			return (List<Keyword>) this.daoManager.getKeywordDao().queryKeywordList(list);
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - queryKeywordList 发生异常！ 】", e);
		}
	}
	
	public List<Keyword> queryKeywordByArchId(int archId) throws ProException {
		try {
			return this.daoManager.getKeywordDao().queryKeywordByArchId(archId);
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - queryKeywordByArchId 发生异常！ 】", e);
		}
	}
	
	public List<Keyword> queryKeywordOrderbyCount() throws ProException {
		try {
			return this.daoManager.getKeywordDao().queryKeywordOrderbyCount();
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - queryKeywordOrderbyCount 发生异常！ 】", e);
		}
	}
	
	public List<Keyword> queryKeywordByName(String name) throws ProException {
		try {
			return this.daoManager.getKeywordDao().queryKeywordByName(name);
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - queryKeywordByName 发生异常！ 】", e);
		}
	}
	
	public List<Keyword> queryKeywordPageList(String k, Pager pager) throws ProException {
		try {
			return this.daoManager.getKeywordDao().queryKeywordPageList(k, pager);
		} catch (Exception e) {
			throw new ProException("【KeywordServiceImpl - queryKeywordPageList 发生异常！ 】", e);
		}
	}

}
