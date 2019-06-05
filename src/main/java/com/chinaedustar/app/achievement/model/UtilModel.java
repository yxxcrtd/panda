package com.chinaedustar.app.achievement.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import freemarker.core.Environment;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class UtilModel implements TemplateHashModelEx, TemplateModelObject {

    /** 所有方法的集合 */
    private Map<String, Object> methods = new HashMap<String, Object>();
    public UtilModel() {
        methods.put("getDateByLong", new GetDateByLong());
        methods.put("getCountedWords", new GetCountedWords());
        methods.put("iconCss", new IconCss());
        methods.put("getFirstString", new GetFirstString());
    }
    
    /**
     * 时间的转换函数
     * 需要一个或 两个参数，第一个参数是LongTime,如果传入第2个参数，则第2个参数是字符串格式化参数例如 yyyy-MM-dd HH:mm:ss等 
     * 如果只有一个参数，则返回  Date 类型的
     * 如果两个参数，则返回格式化后则事件字符串
     * @author dell
     *
     */
    public class GetDateByLong  implements TemplateMethodModelEx {
		@Override
        public Object exec(List args) throws TemplateModelException {
            if (args == null || args.size() == 0){
                throw new TemplateModelException(" getDateByLong 至少需要1个参数");
            }
            String strValue = args.get(0).toString();
            long dateTime = Long.valueOf(strValue);
            java.util.Date dt = new Date(dateTime);
            if(args.size() == 1){
                return dt;
            }
            String strFormat = args.get(1).toString();
            SimpleDateFormat sdf= new SimpleDateFormat(strFormat);
            String sDateTime = sdf.format(dt);
            return sDateTime;
        }
    }

	/**
	 * 该函数支持第三个参数
	 * 如果第三个参数是1 则：
	 * 		截取前多少个字符串，由于有汉字和字母，返回截取的长度以汉字宽度为准。
	 * 		即：两个英文字母或数字代表一个长度，一个汉字代表一个长度
	 * 		例如：
	 * 		参数  abcdefghi,截取4个字符,实际返回 'abcdefg...'
	 * 		参数  我爱中国，我爱人民 ,截取6个字符,实际返回   '我爱中国，我...'
	 * 		参数  我爱ABC,截取3个字符,实际返回   '我爱AB...'
	 * 如果没有第三个参数，则和原来截取方式一样
	 */
	public class GetCountedWords implements TemplateMethodModelEx {
		public Object exec(List args) throws TemplateModelException {
			// 判断参数
			if (args == null || args.size() == 0)
				throw new TemplateModelException(" getCountedWords 需要2个参数");	
			if(args.get(0) == null) return "";
			String strContent = args.get(0).toString();
			//过滤html标签，避免导致标签不封闭导致页面错乱。
			strContent = filterHtml(strContent);
			strContent = strContent.replaceAll("\r", "");
			strContent = strContent.replaceAll("\n", "");
            strContent = strContent.trim();
			String countShow = args.get(1).toString();
			boolean unicodeLength = false;
			boolean includePoints = false;
			if(args.size() == 3){
				if("1".equals(args.get(2).toString())){
					unicodeLength = true;
				}else if("2".equals(args.get(2).toString())){
					unicodeLength = true;
					includePoints = true;
				}
			}else{
			    unicodeLength = true;
			    includePoints = true;
			}
			
			if (isInteger(countShow) == false)
				throw new TemplateModelException(" getCountedWords 第二个参数是数字");
			String ret = "";
			int count = Integer.valueOf(countShow);
			if(count < 1) throw new TemplateModelException(" getCountedWords 第二个参数必须大于1");
			if (strContent.length() > count) {
				if(unicodeLength){
					int iIndex = 0 ;
					String sValue = "";
					for(int i = 0; i < strContent.length(); i++){
						String s = strContent.substring(i,i+1);
						if(s.getBytes().length == s.length()){
							iIndex++;
						}else{
							//汉字
							iIndex++;
							iIndex++;
						}
						sValue = sValue + s;
						if(iIndex>=2*count){
							break;
						}
					}
					ret = sValue;
					if(sValue.length() < strContent.length()){
						if(includePoints){
							ret = sValue + "...";
						}
					}
				}else{
					ret = strContent.substring(0, count-1) + "...";
				}
			} else {
				return strContent;
			}
			return ret;
		}
	}
	
    public class IconCss implements TemplateMethodModelEx {
        /*
         * (non-Javadoc)
         * @see freemarker.template.TemplateMethodModelEx#exec(java.util.List)
         */
        public Object exec(List args) throws TemplateModelException {
            // 判断参数.
            if (args == null || args.size() == 0)
                throw new TemplateModelException("iconCss() 方法需要 1 个字符串参数");
            String fileName = args.get(0).toString();
            String ext = getFileExtension(fileName);            
            Object icon = WebConst.imgs.get(ext.trim().toLowerCase());
            return (null!=icon&&!"".equals(icon))?icon:"defIcon"; // 暂时这么写 直接返回后缀名        
        }       
    }
    
    public class GetFirstString implements TemplateMethodModelEx {
    	public Object exec(List args) throws TemplateModelException {
            if (null == args || 0 == args.size()) {
                throw new TemplateModelException("getFirstString() 方法需要 2 个字符串参数");
            }
            String s = args.get(0).toString();
            String separator = args.get(1).toString();
            if (s.contains(separator)) {
            	int i = s.indexOf(separator);
            	s = s.substring(0, i);
            }
            return s;
    	}
    }
    
    @Override
    public boolean isEmpty() throws TemplateModelException {
        return false;
    }

    @Override
    public String getVariableName() {
        return "Util";
    }

    @Override
    public TemplateCollectionModel keys() throws TemplateModelException {
        return (TemplateCollectionModel)Environment.getCurrentEnvironment().getObjectWrapper().wrap(this.methods.keySet());
    }

    @Override
    public int size() throws TemplateModelException {
        return this.methods.size();
    }


    @Override
    public TemplateCollectionModel values() throws TemplateModelException {
        throw new TemplateModelException("Unsupport values() method.");
    }
    
	public void setMethods(Map m) {
        methods.putAll(m);
    }

    @Override
    public TemplateModel get(String name) throws TemplateModelException {
        Object val = this.methods.get(name);
        if (val == null) return TemplateModel.NOTHING;
        if (val instanceof TemplateModel) return (TemplateModel)val;
        return Environment.getCurrentEnvironment().getObjectWrapper().wrap(val);
    }
    
    /** 
     *  
     * 基本功能：过滤所有以"<"开头以">"结尾的标签 
     * <p> 
     *  
     * @param str 
     * @return String 
     */  
    public static String filterHtml(String str) {  
        String regxpForHtml = "<([^>]*)>";
        Pattern pattern = Pattern.compile(regxpForHtml);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        boolean result1 = matcher.find();  
        while (result1) {  
            matcher.appendReplacement(sb, "");  
            result1 = matcher.find();  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }
	
	/**
	 * 判定所给的字符串是否是一个整数
	 * 
	 * @param val
	 * @return
	 */
	public static final boolean isInteger(String val) {
		if (val == null)
			return false;
		
		if (val.length() == 0)
			return false;
		
		val = val.trim();
		if (val.length() == 0)
			return false;
		
		for (int i = 0; i < val.length(); ++i) {
			char c = val.charAt(i);
			if (c == '+' || c == '-')
				continue;
			if (c > '9' || c < '0')
				return false;
		}
		try {
			Integer.parseInt(val);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
    
	/**
	 * 得到指定文件的后缀.
	 * 
	 * @param fileName
	 * @return 如果没有后缀则返回 "", 否则返回后缀(不含 . 符号)
	 */
	public static final String getFileExtension(String fileName) {
		if (fileName == null || fileName.length() == 0)
			return "";
		int pos = fileName.lastIndexOf('.');
		if (pos < 0)
			return "";
		String ext = fileName.substring(pos + 1);
		if (ext.indexOf('/') >= 0 || ext.indexOf('\\') >= 0)
			return "";
		return ext;
	}
    
}
