package com.chinaedustar.app.achievement.service;

import java.util.List;

import com.chinaedustar.app.achievement.common.exception.ProException;
import com.chinaedustar.app.achievement.domain.Dictionary;

public interface DictionaryService {
	
    public void saveDictionary(Dictionary dictionary) throws ProException;
    
    public List<Dictionary> getDictionaryList(String obj) throws ProException;
    
}
