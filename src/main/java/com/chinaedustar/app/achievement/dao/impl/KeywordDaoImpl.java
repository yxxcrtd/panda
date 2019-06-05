package com.chinaedustar.app.achievement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.dao.KeywordDao;
import com.chinaedustar.app.achievement.domain.Keyword;
import com.chinaedustar.common.dao.BaseDaoHibernate;
import com.chinaedustar.common.vo.QueryRule;

@Component("keywordDao")
public class KeywordDaoImpl extends BaseDaoHibernate<Keyword, Integer> implements KeywordDao {
	
	public Keyword findById(int id) {
		return super.get(id);
	}
	
	public void save(Keyword keyword) {
		super.save(keyword);
	}
	
	public void del(Keyword keyword) {
		super.delete(keyword);
	}
	
//	public void delById(int id) {
//		super.deleteByPK(id);
//	}
	
	public List<Keyword> queryKeywordList(List<Integer> list) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("archId", list);
		return super.find(queryRule);
	}
	
	public List<Keyword> queryKeywordByArchId(int archId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("archId", archId);
		queryRule.addAscOrder("id");
		return super.find(queryRule);
	}
	
	public List<Keyword> queryKeywordOrderbyCount() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addDescOrder("count");
		return super.findList(queryRule, 0, 10);
	}
	
	public List<Keyword> queryKeywordByName(String name) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("name", name);
		queryRule.addAscOrder("id");
		return super.find(queryRule);
	}
	
	public List<Keyword> queryKeywordPageList(String k, Pager pager) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("name", k);
		queryRule.addDescOrder("count");
		return super.findList(queryRule, pager.getOffset(), pager.getPageSize());
	}
	
}
