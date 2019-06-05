package com.chinaedustar.app.achievement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.dao.AuthorDao;
import com.chinaedustar.app.achievement.domain.Author;
import com.chinaedustar.common.dao.BaseDaoHibernate;
import com.chinaedustar.common.vo.QueryRule;

@Component("authorDao")
public class AuthorDaoImpl extends BaseDaoHibernate<Author, Integer> implements AuthorDao {
	
	public Author findById(int id) {
		return super.get(id);
	}
	
	public void save(Author author) {
		super.save(author);
	}
	
	public void del(Author author) {
		super.delete(author);
	}
	
	public List<Author> queryAuthorByArchId(int archId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("archId", archId);
		queryRule.addAscOrder("id");
		return super.find(queryRule);
	}
	
	public List<Author> queryAuthorByIds(List<Integer> list) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("archId", list);
		queryRule.addAscOrder("id");
		return super.find(queryRule);
	}
	
}
