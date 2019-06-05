package com.chinaedustar.app.achievement.common.configuration.tip;

import com.chinaedustar.common.configure.ConfigEnumInter;

/**
 * 系统提示配置枚举
 * 
 * @author liangzh
 *
 * 2013年9月16日 下午2:24:36
 *
 */
public enum TipConfigEnum implements ConfigEnumInter {
    TIPS_TIP("个性化提示", "properties/tips/tip"), TIPS_COMMON("通用提示", "properties/tips/common");
    // 成员变量
    private String name;
    private String value;

    // 构造方法
    private TipConfigEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
