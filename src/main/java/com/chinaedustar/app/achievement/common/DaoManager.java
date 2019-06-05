package com.chinaedustar.app.achievement.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.dao.ArchivementDao;
import com.chinaedustar.app.achievement.dao.ArchivementExtraDao;
import com.chinaedustar.app.achievement.dao.AuthorDao;
import com.chinaedustar.app.achievement.dao.DictionaryDao;
import com.chinaedustar.app.achievement.dao.KeywordDao;
import com.chinaedustar.app.achievement.dao.OperationDao;
import com.chinaedustar.app.achievement.dao.StatDao;

@Component("daoManager")
public class DaoManager {
	
    @Autowired
    private DictionaryDao dictionaryDao;

    public DictionaryDao getDictionaryDao() {
        return this.dictionaryDao;
    }
	
    @Autowired
    private ArchivementDao archivementDao;

    public ArchivementDao getArchivementDao() {
        return this.archivementDao;
    }
	
    @Autowired
    private ArchivementExtraDao archivementExtraDao;

    public ArchivementExtraDao getArchivementExtraDao() {
        return this.archivementExtraDao;
    }
    
    @Autowired
    private AuthorDao authorDao;

	public AuthorDao getAuthorDao() {
		return authorDao;
	}
	
	@Autowired
	private KeywordDao keywordDao;

	public KeywordDao getKeywordDao() {
		return keywordDao;
	}
	
	@Autowired
	private StatDao statDao;

	public StatDao getStatDao() {
		return statDao;
	}
	
	@Autowired
	private OperationDao operationDao;

	public OperationDao getOperationDao() {
		return operationDao;
	}
    
}
