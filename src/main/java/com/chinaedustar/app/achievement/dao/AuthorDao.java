package com.chinaedustar.app.achievement.dao;

import java.util.List;

import com.chinaedustar.app.achievement.domain.Author;

public interface AuthorDao {
	
	Author findById(int id);
	
	void save(Author author);
	
	void del(Author author);
	
	List<Author> queryAuthorByArchId(int archId);
	
	List<Author> queryAuthorByIds(List<Integer> list);

}
