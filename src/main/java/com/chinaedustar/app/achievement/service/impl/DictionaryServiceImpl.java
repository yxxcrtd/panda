package com.chinaedustar.app.achievement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Dictionary;
import com.chinaedustar.app.achievement.service.BaseService;
import com.chinaedustar.app.achievement.service.DictionaryService;
import com.chinaedustar.common.vo.QueryRule;

@Service("DictionaryService")
public class DictionaryServiceImpl extends BaseService implements DictionaryService {

    @Override
    public void saveDictionary(Dictionary dictionary) {
        this.daoManager.getDictionaryDao().save(dictionary);
    }

	@Override
	public List<Dictionary> getDictionaryList(String obj) throws ProException {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("type", obj);
		return daoManager.getDictionaryDao().find(queryRule);
	}
	
}
