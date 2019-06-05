package com.chinaedustar.app.achievement.service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.ArchivementExtra;

public interface ArchivementExtraService {
	
    public void save(ArchivementExtra archExtra) throws ProException;
    
}
