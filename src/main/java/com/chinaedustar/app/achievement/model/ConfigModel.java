package com.chinaedustar.app.achievement.model;

import com.chinaedustar.app.achievement.common.ConfigEnum;
import com.chinaedustar.app.achievement.common.Configuration;

/**
 * 统一配置对象设置。
 * @author 
 */
public class ConfigModel implements TemplateModelObject {

    
    @Override
    public String getVariableName() {
        return "Config";
    }    
  
    /** sns地址 */
    public String getSnsUrl() {
        return Configuration.getValue("honeybee.siteUrl", ConfigEnum.ESI); 
    }
    
    /** 统一用户地址 */
    public  String getSsoUrl() {
        return Configuration.getValue("octopus.siteUrl", ConfigEnum.ESI);
    }
    
    /** 课题研究地址 */
    public  String getResearchUrl() {
        return Configuration.getValue("research.siteUrl", ConfigEnum.ESI);
    }
}
