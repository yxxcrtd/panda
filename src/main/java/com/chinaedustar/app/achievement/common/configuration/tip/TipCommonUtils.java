package com.chinaedustar.app.achievement.common.configuration.tip;

import org.apache.commons.lang3.StringUtils;

import com.chinaedustar.app.achievement.common.configuration.Configuration;

/**
 * 公共提示工具类
 * 
 * @author liangzh
 *
 * 2013年10月21日 下午2:35:42
 *
 */
public class TipCommonUtils {

    /**
     * 成功提示信息
     * 
     * @param exeName
     * @return
     */
    public static String getSuccess(String exeName) {
        try {
            if (StringUtils.isNotEmpty(exeName)) {
                return Configuration.getValue("common.successful", new String[] { exeName }, TipConfigEnum.TIPS_COMMON);
            } else {
                return Configuration.getValue("common.successful", new String[] { "操作" }, TipConfigEnum.TIPS_COMMON);
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 系统异常提示信息
     * 
     * @return
     */
    public static String getSystemError() {
        try {
            return Configuration.getValue("common.system.error", TipConfigEnum.TIPS_COMMON);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 参数错误提示
     * 
     * @return
     */
    public static String getArgumentError() {
        try {
            return Configuration.getValue("common.argument.error", TipConfigEnum.TIPS_COMMON);
        } catch (Exception e) {
            return "";
        }
    }
}
