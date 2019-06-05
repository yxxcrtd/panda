package com.chinaedustar.app.achievement.dao.impl;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.dao.ArchivementExtraDao;
import com.chinaedustar.app.achievement.domain.ArchivementExtra;
import com.chinaedustar.common.dao.BaseDaoHibernate;

@Component("archivementExtraDao")
public class ArchivementExtraDaoImpl extends BaseDaoHibernate<ArchivementExtra, Integer> implements ArchivementExtraDao {
	
	public void save(ArchivementExtra archExtra) {
		super.save(archExtra);
	}
	
}
