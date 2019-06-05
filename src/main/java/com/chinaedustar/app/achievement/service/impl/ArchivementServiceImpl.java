package com.chinaedustar.app.achievement.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Archivement;
import com.chinaedustar.app.achievement.domain.Author;
import com.chinaedustar.app.achievement.domain.Keyword;
import com.chinaedustar.app.achievement.domain.Operation;
import com.chinaedustar.app.achievement.domain.Stat;
import com.chinaedustar.app.achievement.service.ArchivementService;
import com.chinaedustar.app.achievement.service.BaseService;
import com.chinaedustar.common.vo.PageInfo;

@Service("ArchivementService")
public class ArchivementServiceImpl extends BaseService implements ArchivementService {
	
	@Override
	public Archivement findById(int id) throws ProException {
		try {
			return this.daoManager.getArchivementDao().findById(id);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - findById】根据ID查找对象发生异常！", e);
		}
	}
	
	@Override
	public Archivement findByIdAndUserId(int id, String userId) throws ProException {
		try {
			return this.daoManager.getArchivementDao().findByIdAndUserId(id, userId);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - findByIdAndUserId】根据ID和用户ID查找对象发生异常！", e);
		}
	}

	@Override
	public void save(Archivement arch) throws ProException {
    	try {
    		this.daoManager.getArchivementDao().save(arch);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - save】保存对象发生异常！", e);
		}
    }

	@Override
	public void del(Archivement arch) throws ProException {
    	try {
    		delRelation(arch.getArchId());
    		this.daoManager.getArchivementDao().del(arch);
            stat(String.valueOf(arch.getUserId()), arch.getCategory(), -1);
            stat("Site", arch.getCategory(), -1);
            List<Keyword> keywordList = this.daoManager.getKeywordDao().queryKeywordByArchId(arch.getArchId());
            for (Keyword k : keywordList) {
            	this.daoManager.getKeywordDao().del(k);
			}
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - del】删除对象发生异常！", e);
		}
    }

    @Override
    public int save(Archivement arch, Set<Author> authors, Set<Keyword> keywords) throws ProException {
    	boolean bool = false;
    	if (0 == arch.getArchId()) {
    		bool = true;
    	}
    	
    	try {
        	// 1，保存arch主表
    		this.daoManager.getArchivementDao().save(arch);
            
            // 2，保存作者
    		List<Author> authorList = this.daoManager.getAuthorDao().queryAuthorByArchId(arch.getArchId());
    		for (Author a : authorList) {
    			this.daoManager.getAuthorDao().del(a);
			}
            for (Author au : authors) {
            	au.setArchId(arch.getArchId());
            	this.daoManager.getAuthorDao().save(au);
            }
            
            // 3，保存关键字
            List<Keyword> keywordList = this.daoManager.getKeywordDao().queryKeywordByArchId(arch.getArchId());
            for (Keyword kw : keywordList) {
            	this.daoManager.getKeywordDao().del(kw);
            }
            for (Keyword k : keywords) {
            	k.setArchId(arch.getArchId());
            	this.daoManager.getKeywordDao().save(k);
            	List<Keyword> keyworNameList = this.daoManager.getKeywordDao().queryKeywordByName(k.getName());
            	if (null != keyworNameList) {
            		for (Keyword kn : keyworNameList) {
            			kn.setCount(kn.getCount() + 1);
            			this.daoManager.getKeywordDao().save(kn);
            		}
            	}
            }
            
            // 4，统计
            if (bool) {
            	stat(String.valueOf(arch.getUserId()), arch.getCategory(), 1);
            	stat("Site", arch.getCategory(), 1);
            }
            
            // 5，返回刚插入的arch主表ID
            return arch.getArchId();
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - save】保存对象发生异常！", e);
		}
    }
    
    private void delRelation(int archId) throws ProException {
    	List<Author> authorList = this.daoManager.getAuthorDao().queryAuthorByArchId(archId);
    	for (Author author : authorList) {
			this.daoManager.getAuthorDao().del(author);
		}
    	List<Keyword> keywordList = this.daoManager.getKeywordDao().queryKeywordByArchId(archId);
    	for (Keyword keyword : keywordList) {
			this.daoManager.getKeywordDao().del(keyword);
		}
    	Operation operation = this.daoManager.getOperationDao().queryByArchId(archId);
    	if (null != operation) {
    		this.daoManager.getOperationDao().del(operation);
    	}
    }
    
    private void stat(String userId, String obj, int value) throws ProException {
    	Stat stat = this.daoManager.getStatDao().queryStatByUserId(userId);
        if (null == stat) {
        	stat = new Stat();
        	stat.setUserId(userId);
        	if ("thesis".equals(obj)) {
        		stat.setThesis(value);
        	} else if ("book".equals(obj)) {
        		stat.setBook(value);
        	} else if ("courseware".equals(obj)) {
        		stat.setCourseware(value);
        	} else if ("instruction".equals(obj)) {
        		stat.setInstruction(value);
        	} else if ("patent".equals(obj)) {
        		stat.setPatent(value);
        	} else if ("other".equals(obj)) {
        		stat.setOther(value);
        	}
        } else {
        	int before;
        	if ("thesis".equals(obj)) {
        		before = stat.getThesis();
        		stat.setThesis(before + value);
        	} else if ("book".equals(obj)) {
        		before = stat.getBook();
        		stat.setBook(before + value);
        	} else if ("courseware".equals(obj)) {
        		before = stat.getCourseware();
        		stat.setCourseware(before + value);
        	} else if ("instruction".equals(obj)) {
        		before = stat.getInstruction();
        		stat.setInstruction(before + value);
        	} else if ("patent".equals(obj)) {
        		before = stat.getPatent();
        		stat.setPatent(before + value);
        	} else if ("other".equals(obj)) {
        		before = stat.getOther();
        		stat.setOther(before + value);
        	}
        }
        stat.setSum(stat.getThesis() + stat.getBook() + stat.getCourseware() + stat.getInstruction() + stat.getPatent() + stat.getOther());
        this.daoManager.getStatDao().save(stat);
    }
    
    public PageInfo<Archivement> queryArchPageList(String keyword, String obj, int pageNo, int pageSize) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchPageList(keyword, obj, pageNo, pageSize);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryArchPageList】", e);
		}
	}
    
    public PageInfo<Archivement> queryArchCategoryPageList(String category, int type, boolean prize, boolean publish, int pageNo, int pageSize) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchCategoryPageList(category, type, prize, publish, pageNo, pageSize);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryArchCategoryPageList】", e);
		}
	}

	@Override
	public List<Archivement> queryArchListByUserIdAndObj(String userId, String obj) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchListByUserIdAndObj(userId, obj);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryArchList】", e);
		}
	}

	@Override
	public List<Archivement> queryArchPageListByUserIdAndObj(String userId, String obj, int topicFlag, Pager pager) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchPageListByUserIdAndObj(userId, obj, topicFlag, pager);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryArchList】", e);
		}
	}
	
	@Override
	public int queryArchCount(String obj, int type, String userId) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchCount(obj, type, userId);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryArchCount】", e);
		}
	}
	
	@Override
	public List<Archivement> queryArchList(String obj, String orderby, int count) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchList(obj, orderby, count);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryNewArchList】", e);
		}
	}
	
	@Override
	public List<Archivement> queryArchHotList(String obj, int count) throws ProException {
    	try {
			return this.daoManager.getArchivementDao().queryArchHotList(obj, count);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryNewArchList】", e);
		}
	}
	
	@Override
	public List<Archivement> queryArchListByIds(List<Integer> list) throws ProException {
    	try {
			return (List<Archivement>) this.daoManager.getArchivementDao().queryArchListByIds(list);
		} catch (Exception e) {
        	throw new ProException("【ArchivementServiceImpl - queryArchListByIds】", e);
		}
	}
	
}
