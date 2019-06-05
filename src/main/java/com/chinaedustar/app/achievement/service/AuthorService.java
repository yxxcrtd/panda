package com.chinaedustar.app.achievement.service;

import java.util.List;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Author;

public interface AuthorService {
	
	Author findById(int id) throws ProException;
	
	void save(Author author) throws ProException;
	
	void del(Author author) throws ProException;
	
	List<Author> queryAuthorByArchId(int archId) throws ProException;
    
	List<Author> queryAuthorByIds(List<Integer> list) throws ProException;
}
