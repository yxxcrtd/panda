package com.chinaedustar.app.achievement.dao;

import org.springframework.stereotype.Component;

import com.chinaedustar.app.achievement.domain.Dictionary;
import com.chinaedustar.common.dao.BaseDaoHibernate;

@Component("dictionaryDao")
public class DictionaryDao extends BaseDaoHibernate<Dictionary, Long> {

}
