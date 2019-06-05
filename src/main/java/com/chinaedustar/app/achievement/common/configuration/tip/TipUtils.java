package com.chinaedustar.app.achievement.common.configuration.tip;

import com.chinaedustar.app.achievement.common.configuration.Configuration;

/**
 * 提示信息工具类
 * 
 * @author liangzh
 *
 * 2013年10月22日 上午9:22:55
 *
 */
public class TipUtils {

    /**
     * 获取提示信息
     * 
     * @param key
     * @param params
     * @return
     */
    public static String getTipInfo(String key, String... params) {
        try {
            return Configuration.getValue(key, params, TipConfigEnum.TIPS_TIP);
        } catch (Exception e) {
            return "";
        }
    }
}
