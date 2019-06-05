package com.chinaedustar.app.achievement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.common.Pager;
import com.chinaedustar.app.achievement.dao.ArchivementDao;
import com.chinaedustar.app.achievement.domain.Archivement;
import com.chinaedustar.common.dao.BaseDaoHibernate;
import com.chinaedustar.common.vo.PageInfo;
import com.chinaedustar.common.vo.QueryRule;

@Component("archivementDao")
public class ArchivementDaoImpl extends BaseDaoHibernate<Archivement, Integer> implements ArchivementDao {
	
	public Archivement findById(int id) {
		return super.get(id);
	}
	
	public Archivement findByIdAndUserId(int id, String userId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("archId", id);
		queryRule.addEqual("userId", userId);
		return super.findUnique(queryRule);
	}

    public void save(Archivement arch) {
    	super.save(arch);
    }

    public void del(Archivement arch) {
    	super.delete(arch);
    }
    
    public PageInfo<Archivement> queryArchPageList(String keyword, String obj, int pageNo, int pageSize) {
    	if (null == obj || "".equals(obj) || 0 > obj.length()) {
    		String hql = "FROM Archivement WHERE (title LIKE ? OR author LIKE ?) ORDER BY createTime DESC";
    		return super.findByHql(hql, pageNo, pageSize, new Object[] { "%" + keyword + "%", "%" + keyword + "%" });
    	} else {
    		String hql = "FROM Archivement WHERE (category = ?) AND (title LIKE ? OR author LIKE ?) ORDER BY createTime DESC";
    		return super.findByHql(hql, pageNo, pageSize, new Object[] { obj, "%" + keyword + "%", "%" + keyword + "%" });
    	}
    }
    
    public PageInfo<Archivement> queryArchCategoryPageList(String category, int type, boolean prize, boolean publish, int pageNo, int pageSize) {
    	QueryRule queryRule = QueryRule.getInstance();
    	queryRule.addEqual("category", category);
    	if (0 < type) {
        	queryRule.addEqual("type", type);
    	}
    	if (prize) {
    		queryRule.addDescOrder("prize");
    	}
    	if (publish) {
    		queryRule.addDescOrder("publish");
    	}
		queryRule.addDescOrder("createTime");
    	return super.find(queryRule, pageNo, pageSize);
    }
    
	public List<Archivement> queryArchListByUserIdAndObj(String userId, String obj) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userId", userId);
		if (null != obj && !"".equals(obj) && 0 < obj.length()) {
			queryRule.addEqual("category", obj);
		}
		queryRule.addDescOrder("createTime");
		return super.findList(queryRule, 0, 3);
	}
    
	public List<Archivement> queryArchPageListByUserIdAndObj(String userId, String obj, int topicFlag, Pager pager) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userId", userId);
		if (null != obj && !"".equals(obj) && 0 < obj.length()) {
			queryRule.addEqual("category", obj);
		}
		
		if (topicFlag==1) {
			queryRule.addNotEqual("topicResearchId", 0);
		}else if(topicFlag==-1){
			queryRule.addEqual("topicResearchId", 0);
		}
		
		queryRule.addDescOrder("createTime");
		return super.findList(queryRule, pager.getOffset(), pager.getPageSize());
	}
    
	@SuppressWarnings("unchecked")
	public int queryArchCount(String obj, int type, String userId) {
		String hql = "";
		List<Archivement> list = null;
		if (0 < type) {
	    	hql = "FROM Archivement WHERE category = ? AND type = ?";
			list = (List<Archivement>) super.findByHql(hql, new Object[] { obj, type });
		} else {
			if (null != userId && !"".equals(userId) && 0 < userId.length()) {
				if (null != obj && !"".equals(obj) && 0 < obj.length()) {
					hql = "FROM Archivement WHERE category = ? AND userId = ?";
					list = (List<Archivement>) super.findByHql(hql, new Object[] { obj, userId });
				} else {
					hql = "FROM Archivement WHERE userId = ?";
					list = (List<Archivement>) super.findByHql(hql, new Object[] { userId });
				}
			} else {
				hql = "FROM Archivement WHERE category = ?";
				list = (List<Archivement>) super.findByHql(hql, new Object[] { obj });
			}
		}
		return 0 < list.size() ? list.size() : 0;
	}
	
	public List<Archivement> queryArchList(String obj, String orderby, int count) {
		QueryRule queryRule = QueryRule.getInstance();
		if (!"".equals(obj)) {
			queryRule.addEqual("category", obj);
		}
		queryRule.addDescOrder(orderby);
		return super.findList(queryRule, 0, count);
	}
	
	public List<Archivement> queryArchHotList(String obj, int count) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("category", obj);
		queryRule.addDescOrder("hotCount");
		return super.findList(queryRule, 0, count);
	}
	
	public List<Archivement> queryArchListByIds(List<Integer> list) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("archId", list);
		return super.find(queryRule);
	}
			
}
