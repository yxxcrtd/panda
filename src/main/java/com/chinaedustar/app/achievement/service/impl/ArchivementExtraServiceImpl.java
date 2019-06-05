package com.chinaedustar.app.achievement.service.impl;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.ArchivementExtra;
import com.chinaedustar.app.achievement.service.ArchivementExtraService;
import com.chinaedustar.app.achievement.service.BaseService;

@Service("ArchivementExtraService")
public class ArchivementExtraServiceImpl extends BaseService implements ArchivementExtraService {
    
	@Override
	public void save(ArchivementExtra archExtra) throws ProException {
    	try {
			this.daoManager.getArchivementExtraDao().save(archExtra);
		} catch (Exception e) {
        	throw new ProException("【ArchivementExtraServiceImpl - save】保存对象发生异常！", e);
		}
	}
	
}
