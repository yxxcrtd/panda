package com.chinaedustar.app.achievement.dao;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.ArchivementExtra;


public interface ArchivementExtraDao {

	public void save(ArchivementExtra archExtra) throws ProException;
	
}
