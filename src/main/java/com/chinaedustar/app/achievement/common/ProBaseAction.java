package com.chinaedustar.app.achievement.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinaedustar.app.achievement.common.logger.ProLogger;
import com.chinaedustar.app.achievement.domain.Archivement;
import com.chinaedustar.app.achievement.domain.ArchivementExtra;
import com.chinaedustar.app.achievement.domain.Author;
import com.chinaedustar.app.achievement.domain.Dictionary;
import com.chinaedustar.app.achievement.domain.Keyword;
import com.chinaedustar.app.achievement.domain.Operation;
import com.chinaedustar.app.achievement.domain.Stat;
import com.chinaedustar.common.utils.JsonUtils;
import com.chinaedustar.common.utils.SignUtils;
import com.chinaedustar.common.vo.PageInfo;
import com.chinaedustar.common.web.BaseAction;
import com.chinaedustar.hessian.app.bean.Topic;
import com.chinaedustar.hessian.factory.HessianFactoryUtils;
import com.chinaedustar.honeybee.constants.AppConst;
import com.chinaedustar.honeybee.domain.STopic;
import com.chinaedustar.honeybee.domain.SUserBaseInfo;
import com.chinaedustar.honeybee.vo.appTool.AppArticleResVo;
import com.chinaedustar.honeybee.vo.appTool.AppPhotoResVo;
import com.chinaedustar.honeybee.vo.appTool.AppResourceResVo;
import com.chinaedustar.honeybee.vo.appTool.AppVideoResVo;
import com.chinaedustar.honeybee.vo.user.PortalUserVo;
import com.chinaedustar.simba.util.SimbaHessianUtils;
import com.chinaedustar.simba.vo.GradePhaseVo;

/**
 * 项目Action基类
 */
@SuppressWarnings("serial")
public class ProBaseAction extends BaseAction {

    protected ProLogger logger = new ProLogger();

	protected Map<String, Object> map = new HashMap<String, Object>();

	protected SUserBaseInfo loginUser = null;
	protected SUserBaseInfo user = null;
	protected String userId;
	
	/** 选择的页码 */
	protected String p = "1";
	
	/** 查询关键字 */
	protected String k;
	
	protected String ip;
	
	/** 使用系统地址 */
	protected String clientUrl;
    
    @Autowired
    protected ServiceManager serviceManager;

    /** 404跳转 */
    public static final String NOT_FIND = "404";
    
    /** 500跳转 */
    public static final String SYSTEM_ERROR = "500";
    
    /** 提示信息 */
    protected String tip;
    
    /** obj 为："论文", "著作", "课件", "教学设计", "专利", "其他成果" */
    protected String obj;
    /** 查询课题:1,非课题:-1,不区分：0 ;默认为0*/
    protected int topicFlag = 0;
    protected int topicId;
    /** 课题ID*/
    protected int topicResearchId;
    /** 课题名称*/
    protected String topicResearchName;
    protected int t;
    protected boolean prize;
    protected boolean publish;
    protected boolean upd;

    protected List<Topic> topicList = new ArrayList<Topic>();
	protected List<Dictionary> dictTypeList = new ArrayList<Dictionary>();
	protected List<Dictionary> dictPrizeLevelList = new ArrayList<Dictionary>();
	/** 作者列表 */
	protected List<Author> authorList = new ArrayList<Author>();
	
	protected Archivement arch;
	
	protected int archId;
	
	protected ArchivementExtra archExtra;
	
	protected Stat stat;
	
	protected Operation op;

	/** 学段 */
	protected List<GradePhaseVo> gradeList = SimbaHessianUtils.getPhasesByEduTypeId(1);

	/** 学科 */
	protected List<GradePhaseVo> subjectList = SimbaHessianUtils.getSubjectsByEduTypeIdAndPhaseId(1, 1);

	/** 作者 */
	protected String[] authors;

	/** 关键字 */
	protected String[] keywords;

	/** 我的科研成果列表 */
	protected List<Archivement> archList = new ArrayList<Archivement>();
	
	/** 分页的科研成果列表 */
	protected PageInfo<Archivement> archPageList;
	protected Pager pager;

	/** 首页最热7条科研成果 */
	protected List<Archivement> thesisHotArchList = new ArrayList<Archivement>();
	protected List<Archivement> bookHotArchList = new ArrayList<Archivement>();
	protected List<Archivement> coursewareHotArchList = new ArrayList<Archivement>();
	protected List<Archivement> instructionHotArchList = new ArrayList<Archivement>();
	protected List<Archivement> patentHotArchList = new ArrayList<Archivement>();
	protected List<Archivement> otherHotArchList = new ArrayList<Archivement>();
	/** 最热的科研成果 */
	protected List<Archivement> hotArchList = new ArrayList<Archivement>();

	/** 首页最新10条科研成果 */
	protected List<Archivement> thesisNewArchList = new ArrayList<Archivement>();
	protected List<Archivement> bookNewArchList = new ArrayList<Archivement>();
	protected List<Archivement> coursewareNewArchList = new ArrayList<Archivement>();
	protected List<Archivement> instructionNewArchList = new ArrayList<Archivement>();
	protected List<Archivement> patentNewArchList = new ArrayList<Archivement>();
	protected List<Archivement> otherNewArchList = new ArrayList<Archivement>();
	
	/** 我的科研成果列表 */
	protected List<Archivement> allNewArchList = new ArrayList<Archivement>();
	
	/** 首页上的贡献达人 */
	protected List<Stat> statList = new ArrayList<Stat>();
	
	/** 首页上的热门标签 */
	protected List<Keyword> keywordList = new ArrayList<Keyword>();
	
	/** 数字签名 */
	protected String sign;
	
	/** 应用分类集合JSON串 */
	protected String selfTypeVos;
	
	/** 往SNS发布文章、资源、图片或视频后的返回地址 */
	protected String returnUrl;
	
	/** SNS返回的参数 */
	protected String suc;
	
	/** 科研成果ID */
	protected String appObjId;
	
	/** 应用类型 */
	protected String objType;
	
	/** 应用类型的名称 */
	protected String objTypeName;
	
	/** 应用内容详情ID */
	protected String appContentId;
	
	/** 上传图片的张数 */
	protected String photoNo;
	
	/** 根据科研类型返回的科研类型ID */
	protected String appObjCategoryId;
	
	/** 显示的科研类型名称 */
	protected String appObjCategoryIdName;
	
    /** 上传科研成果后的文章列表 */
    protected List<AppArticleResVo> appArticleList = new ArrayList<AppArticleResVo>();
	
    /** 上传科研成果后的资源列表 */
    protected List<AppResourceResVo> appResourceList = new ArrayList<AppResourceResVo>();
	
    /** 上传科研成果后的图片列表 */
    protected List<AppPhotoResVo> appPhotoList = new ArrayList<AppPhotoResVo>();
	
    /** 上传科研成果后的视频列表 */
    protected List<AppVideoResVo> appVideoList = new ArrayList<AppVideoResVo>();
    
    protected List<STopic> appTopicList = new ArrayList<STopic>();
    protected PageInfo<STopic> appTopicPageList;
    protected String totalCount;
    
    /** 页面输入的话题标题 */
    protected String topic;
    /** 页面输入的话题内容 */
    protected String content;
    
    /** 应用ID（文章、资源、图片或视频） */
    protected String appId;
    
    /** SNS用户列表 */
    protected List<SUserBaseInfo> userList = new ArrayList<SUserBaseInfo>();
    /** 查找的用户列表 */
    protected List<PortalUserVo> searchUserList = new ArrayList<PortalUserVo>();
    
	/* (non-Javadoc)
	 * 
	 * @see com.chinaedustar.common.web.BaseAction#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		ip = request.getRemoteAddr();
		HttpSession s = request.getSession(false);
		this.loginUser = null == s ? null : (SUserBaseInfo) s.getAttribute(MyConst.SESSION_USER_KEY);
		clientUrl = Configuration.getValue("honeybee.serverUrl", ConfigEnum.ESI); 
	}
	
	protected boolean validateObj() {
    	if (!"thesis".equals(obj) && !"book".equals(obj) && !"courseware".equals(obj) && !"instruction".equals(obj) && !"patent".equals(obj) && !"other".equals(obj)) {
    		addActionError("科研成果类型不存在！");
    		return true;
    	} else {
    		return false;
    	}
	}
	
	protected void showAppsList() {
		map.put("userId", loginUser.getUserId());
		map.put("appCode", AppConst.APP_CODE_ARCHIVE);
		map.put("appObjId", arch.getArchId());
		map.put("count", 100);
		appArticleList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getArticleService().queryAppArticles(JsonUtils.toJson(map)), false, AppArticleResVo.class);
		appResourceList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getResourceService().queryAppResources(JsonUtils.toJson(map)), false, AppResourceResVo.class);
		appPhotoList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getPhotoService().queryAppPhotoList(JsonUtils.toJson(map)), false, AppPhotoResVo.class);
		appVideoList = getGenericList(HessianFactoryUtils.honeyBeeServiceFactory.getVideoService().queryAppVideos(JsonUtils.toJson(map)), false, AppVideoResVo.class);
	}
	
	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public SUserBaseInfo getUser() {
		return user;
	}

	public void setUser(SUserBaseInfo user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}
	
    public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getTip() {
        return this.tip;
    }
    
    public void setTip(String tip) {
        this.tip = tip;
    }
    
    public String getObj() {
		return obj;
	}
    
	public void setObj(String obj) {
		this.obj = obj;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public boolean getPrize() {
		return prize;
	}

	public void setPrize(boolean prize) {
		this.prize = prize;
	}

	public boolean getPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public boolean getUpd() {
		return upd;
	}

	public void setUpd(boolean upd) {
		this.upd = upd;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public List<Dictionary> getDictTypeList() {
		return dictTypeList;
	}
	
    public List<Dictionary> getDictPrizeLevelList() {
		return dictPrizeLevelList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public Archivement getArch() {
		return arch;
	}
	
	public void setArch(Archivement arch) {
		this.arch = arch;
	}

	public int getArchId() {
		return archId;
	}

	public void setArchId(int archId) {
		this.archId = archId;
	}

	public ArchivementExtra getArchExtra() {
		return archExtra;
	}
	
	public void setArchExtra(ArchivementExtra archExtra) {
		this.archExtra = archExtra;
	}
	
	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public Operation getOp() {
		return op;
	}

	public void setOp(Operation op) {
		this.op = op;
	}
	
	public List<GradePhaseVo> getGradeList() {
		return gradeList;
	}
	
	public List<GradePhaseVo> getSubjectList() {
		return subjectList;
	}
	
	public String[] getAuthors() {
		return authors;
	}
	
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	
	public String[] getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	
	public List<Archivement> getArchList() {
		return archList;
	}
	
	public PageInfo<Archivement> getArchPageList() {
		return archPageList;
	}
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<Archivement> getThesisHotArchList() {
		return thesisHotArchList;
	}

	public List<Archivement> getBookHotArchList() {
		return bookHotArchList;
	}

	public List<Archivement> getCoursewareHotArchList() {
		return coursewareHotArchList;
	}

	public List<Archivement> getInstructionHotArchList() {
		return instructionHotArchList;
	}

	public List<Archivement> getPatentHotArchList() {
		return patentHotArchList;
	}

	public List<Archivement> getOtherHotArchList() {
		return otherHotArchList;
	}

	public List<Archivement> getHotArchList() {
		return hotArchList;
	}

	public List<Archivement> getThesisNewArchList() {
		return thesisNewArchList;
	}

	public List<Archivement> getBookNewArchList() {
		return bookNewArchList;
	}

	public List<Archivement> getCoursewareNewArchList() {
		return coursewareNewArchList;
	}

	public List<Archivement> getInstructionNewArchList() {
		return instructionNewArchList;
	}

	public List<Archivement> getPatentNewArchList() {
		return patentNewArchList;
	}

	public List<Archivement> getOtherNewArchList() {
		return otherNewArchList;
	}

	public List<Archivement> getAllNewArchList() {
		return allNewArchList;
	}

	public List<Stat> getStatList() {
		return statList;
	}

	public List<Keyword> getKeywordList() {
		return keywordList;
	}

	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSelfTypeVos() {
		return selfTypeVos;
	}
	
	public void setSelfTypeVos(String selfTypeVos) {
		this.selfTypeVos = selfTypeVos;
	}

	public String getReturnUrl() {
		return returnUrl;
	}
	
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getSuc() {
		return suc;
	}
	
	public void setSuc(String suc) {
		this.suc = suc;
	}

	public String getAppObjId() {
		return appObjId;
	}
	
	public void setAppObjId(String appObjId) {
		this.appObjId = appObjId;
	}

	public String getObjType() {
		return objType;
	}
	
	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getObjTypeName() {
		return objTypeName;
	}
	
	public void setObjTypeName(String objTypeName) {
		this.objTypeName = objTypeName;
	}

	public String getAppContentId() {
		return appContentId;
	}
	
	public void setAppContentId(String appContentId) {
		this.appContentId = appContentId;
	}

	public String getPhotoNo() {
		return photoNo;
	}
	
	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}

	public String getAppObjCategoryId() {
		return appObjCategoryId;
	}
	
	public void setAppObjCategoryId(String appObjCategoryId) {
		this.appObjCategoryId = appObjCategoryId;
	}

	public String getAppObjCategoryIdName() {
		return appObjCategoryIdName;
	}
	
	public void setAppObjCategoryIdName(String appObjCategoryIdName) {
		this.appObjCategoryIdName = appObjCategoryIdName;
	}
	
	/**
	 * 根据对象获取科研成果名称
	 * 
	 * @param obj - 对象（论文：thesis；著作：book；课件：courseware；教学设计：instruction；专利：patent；其他成果：other；）
	 * @return
	 */
	protected String getArchNameByObj(String obj) {
		switch (obj) {
		case "thesis": 
			appObjCategoryIdName = "论文";
			break;
		case "book": 
			appObjCategoryIdName = "著作";
			break;
		case "courseware": 
			appObjCategoryIdName = "课件";
			break;
		case "instruction": 
			appObjCategoryIdName = "教学设计";
			break;
		case "patent": 
			appObjCategoryIdName = "专利";
			break;
		case "other": 
			appObjCategoryIdName = "其他成果";
			break;
		default:
			appObjCategoryIdName = "其他成果";
			break;
		}
		return appObjCategoryIdName;
	}
	
	/**
	 * 获取返回的URL
	 * 
	 * @return
	 */
	protected String getReturnURL() {
		return Configuration.getValue("octopus.clientServerUrl", ConfigEnum.ESI) + "/uploadResult.do";
	}
	
	/**
	 * 获取返回的URL
	 * 
	 * @return
	 */
	protected String getReturnListURL() {
		return Configuration.getValue("octopus.clientServerUrl", ConfigEnum.ESI) + "/arch_detail.do";
	}
	
	/**
	 * 获取数据签名
	 * 
	 * @param id - 科研成果ID
	 * @param url - 返回的URL
	 * @return
	 */
	protected String getSIGN(int id, String url) {
		String[] s = new String[] { "ARCHIVE", String.valueOf(0), String.valueOf(id), url };
		return SignUtils.sign(s);
	}
    
	public List<AppArticleResVo> getAppArticleList() {
		return appArticleList;
	}

	public List<AppResourceResVo> getAppResourceList() {
		return appResourceList;
	}

	public List<AppPhotoResVo> getAppPhotoList() {
		return appPhotoList;
	}

	public List<AppVideoResVo> getAppVideoList() {
		return appVideoList;
	}

	public List<STopic> getAppTopicList() {
		return appTopicList;
	}

	public PageInfo<STopic> getAppTopicPageList() {
		return appTopicPageList;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public List<SUserBaseInfo> getUserList() {
		return userList;
	}

    public List<PortalUserVo> getSearchUserList() {
		return searchUserList;
	}

	public int getTopicFlag() {
		return topicFlag;
	}

	public void setTopicFlag(int topicFlag) {
		this.topicFlag = topicFlag;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getTopicResearchId() {
		return topicResearchId;
	}

	public void setTopicResearchId(int topicResearchId) {
		this.topicResearchId = topicResearchId;
	}

	public String getTopicResearchName() {
		return topicResearchName;
	}

	public void setTopicResearchName(String topicResearchName) {
		this.topicResearchName = topicResearchName;
	}

	/**
     * 获取泛型的列表
     * 
     * @param data 从SNS获取的返回字符串
     * @param more 多重data解析
     * @param t SNS定义的VO对象
     * @return
     */
    protected <T> List<T> getGenericList(String data, boolean more, Class<T> t) {
        List<T> list = (List<T>) new ArrayList<T>();
        try {
            JSONObject obj = (JSONObject) JSON.parse(data);
            if ("1".equals(obj.get("status"))) {
                // 多重data解析，此时已经没有status了，需要再解析一次data
                if (more) {
                    obj = (JSONObject) JSON.parse(obj.get("data").toString());
                }                
                list = (List<T>) JsonUtils.parseList(obj.get("data").toString(), t);
            } else {
                list = null;
            }
        } catch (Exception e) {
            this.logger.error("获取泛型的列表出现错误！", e);
        }
        if (null == list || 0 == list.size()) {
            list = null;
        }
        return list;
    }

}
