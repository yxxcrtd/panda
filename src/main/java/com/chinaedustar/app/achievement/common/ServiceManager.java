package com.chinaedustar.app.achievement.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.service.ArchivementExtraService;
import com.chinaedustar.app.achievement.service.ArchivementService;
import com.chinaedustar.app.achievement.service.AuthorService;
import com.chinaedustar.app.achievement.service.DictionaryService;
import com.chinaedustar.app.achievement.service.KeywordService;
import com.chinaedustar.app.achievement.service.OperationService;
import com.chinaedustar.app.achievement.service.StatService;

/**
 * Service管理类
 */
@Component("serviceManager")
public class ServiceManager {

	@Autowired
	private DictionaryService dictionaryService;

	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	@Autowired
	private ArchivementService archivementService;

	public ArchivementService getArchivementService() {
		return archivementService;
	}

	@Autowired
	private ArchivementExtraService archivementExtraService;

	public ArchivementExtraService getArchivementExtraService() {
		return archivementExtraService;
	}

	@Autowired
	private StatService statService;

	public StatService getStatService() {
		return statService;
	}

	@Autowired
	private KeywordService keywordService;

	public KeywordService getKeywordService() {
		return keywordService;
	}

	@Autowired
	private OperationService operationService;

	public OperationService getOperationService() {
		return operationService;
	}
	
	@Autowired
	private AuthorService authorService;

	public AuthorService getAuthorService() {
		return authorService;
	}

}
