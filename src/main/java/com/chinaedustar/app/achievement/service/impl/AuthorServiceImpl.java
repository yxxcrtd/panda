package com.chinaedustar.app.achievement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Author;
import com.chinaedustar.app.achievement.service.AuthorService;
import com.chinaedustar.app.achievement.service.BaseService;

@Service("AuthorService")
public class AuthorServiceImpl extends BaseService implements AuthorService {

	public Author findById(int id) throws ProException {
		try {
			return this.daoManager.getAuthorDao().findById(id);
		} catch (Exception e) {
        	throw new ProException("【AuthorServiceImpl - findById】发生异常！", e);
		}
	}

	public void save(Author author) throws ProException {
		try {
			this.daoManager.getAuthorDao().save(author);
		} catch (Exception e) {
        	throw new ProException("【AuthorServiceImpl - save】保存对象发生异常！", e);
		}
	}

	public void del(Author author) throws ProException {
		try {
			this.daoManager.getAuthorDao().del(author);
		} catch (Exception e) {
        	throw new ProException("【AuthorServiceImpl - del】删除对象发生异常！", e);
		}
	}
	
	public List<Author> queryAuthorByArchId(int archId) throws ProException {
		try {
			return this.daoManager.getAuthorDao().queryAuthorByArchId(archId);
		} catch (Exception e) {
        	throw new ProException("【AuthorServiceImpl - queryAuthorByArchId】查询作者列表发生异常！", e);
		}
	}
	
	public List<Author> queryAuthorByIds(List<Integer> list) throws ProException {
		try {
			return this.daoManager.getAuthorDao().queryAuthorByIds(list);
		} catch (Exception e) {
        	throw new ProException("【AuthorServiceImpl - queryAuthorByIds】查询作者列表发生异常！", e);
		}
	
		
	}
	
}
