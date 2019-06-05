package com.chinaedustar.app.achievement.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 网站常用常量定义
 * @author mxh
 *
 */
public class WebConst {
	
	/** 资源文件后缀类型与显示样式对应 */
	public final static  Map<String,Object> imgs = new HashMap<String,Object>();
	
    /** 本地应用记录的登录用户 */
    public static final String SESSION_USER_KEY = "loginUser";
    
    /** 统一用户客户端为了验证是否在线的SessionID */
    public static final String SESSION_SSO_SESSIONID = "octopusUsId";
    
    /** 校级机构类型 */
    public static final int SCHOOL_LEVEL = 2005;
    
    /** 门户网站缓存名称  */
    public static final String cacheName = "portal";
    
    /** 二级栏目最多显示的条数 */
    public static final int MAX_SHOW_ROWS = 1000;
    
	
	/**
	 * 这里维护一个map集合存放文件扩展名和对应的小图标样式对应
	 */
	static{
		//word
		imgs.put("doc", "wordIcon");
		imgs.put("docx", "wordIcon");
		imgs.put("wps", "wordIcon");
		imgs.put("docm", "wordIcon");
		imgs.put("dotm", "wordIcon");
		imgs.put("dot", "wordIcon");
		imgs.put("rtf", "wordIcon");
		imgs.put("odt", "wordIcon");
		imgs.put("wtf", "wordIcon");
		
		//excel
		imgs.put("xls", "excIcon");
		imgs.put("xlsx", "excIcon");
		imgs.put("xlsm", "excIcon");
		imgs.put("xlsb", "excIcon");
		imgs.put("xml", "excIcon");
		imgs.put("xltx", "excIcon");
		imgs.put("xltm", "excIcon");
		imgs.put("xlt", "excIcon");
		imgs.put("cvs", "excIcon");
		imgs.put("xltm", "excIcon");
		imgs.put("xlam", "excIcon");
		imgs.put("xla", "excIcon");
		imgs.put("xps", "excIcon");
		imgs.put("ods", "excIcon");
		
		//ppt
		imgs.put("ppt", "pptIcon");
		imgs.put("pptx", "pptIcon");
		imgs.put("pptm", "pptIcon");
		imgs.put("potx", "pptIcon");
		imgs.put("potm", "pptIcon");
		imgs.put("pot", "pptIcon");
		imgs.put("ppsx", "pptIcon");
		imgs.put("ppsm", "pptIcon");
		imgs.put("pps", "pptIcon");
		imgs.put("ppam", "pptIcon");
		imgs.put("ppa", "pptIcon");
		imgs.put("odp", "pptIcon");
		
		//flash
		imgs.put("swf", "fwIcon");
		imgs.put("fla", "fwIcon");
		
		//sound
		imgs.put("acm", "mIcon");
		imgs.put("aif", "mIcon");
		imgs.put("aif", "mIcon");
		imgs.put("aifc", "mIcon");
		imgs.put("aiff", "mIcon");
		imgs.put("asf", "mIcon");
		imgs.put("au", "mIcon");
		imgs.put("mp3", "mIcon");
		imgs.put("wma", "mIcon");
		imgs.put("ape", "mIcon");
		imgs.put("flac", "mIcon");
		
		//img
		imgs.put("bmp", "imgIcon");
		imgs.put("pcx", "imgIcon");
		imgs.put("tiff", "imgIcon");
		imgs.put("gif", "imgIcon");
		imgs.put("jpeg", "imgIcon");
		imgs.put("tag", "imgIcon");
		imgs.put("exif", "imgIcon");
		imgs.put("fpx", "imgIcon");
		imgs.put("svg", "imgIcon");
		imgs.put("png", "imgIcon");
		imgs.put("jpg", "imgIcon");
		imgs.put("raw", "imgIcon");
		imgs.put("ico", "imgIcon");
		
		//video
		imgs.put("avi", "vIcon");
		imgs.put("mpg", "vIcon");
		imgs.put("mpeg", "vIcon");
		imgs.put("asf", "vIcon");
		imgs.put("mov", "vIcon");
		imgs.put("wmv", "vIcon");
		imgs.put("rm", "vIcon");
		imgs.put("rmvb", "vIcon");
		imgs.put("mp4", "vIcon");
		imgs.put("divx", "vIcon");
		imgs.put("tp", "vIcon");
		imgs.put("ts", "vIcon");
	    
		//pdf
		imgs.put("pdf", "pdfIcon");
		
		//txt
		imgs.put("txt", "txtIcon");
		
		//压缩文件
		imgs.put("rar", "rarIcon");
		imgs.put("arj", "rarIcon");
		imgs.put("gz", "rarIcon");
		imgs.put("z", "rarIcon");
		
		//zip压缩文件
		imgs.put("zip", "zipIcon");
	}    
}
