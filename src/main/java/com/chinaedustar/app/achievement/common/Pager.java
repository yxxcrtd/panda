package com.chinaedustar.app.achievement.common;

/**
 * 分页
 */
public class Pager {

	/** 总记录数 */
	private int totalCount;

	/** 每页显示的行数 */
	private int pageSize = 20;

	/** 当前分页号 */
	private int pageNo = 1;

	/** 分页URL */
	private String pageUrl = "?userId={userId}&obj={obj}&t={t}&prize={prize}&publish={publish}&archId={archId}&k={k}&p={p}";

	/**
	 * 在需要使用分页的地方使用：Pager pager = new Pager();即构造初始化了：默认当前页是1；每页显示多少条记录
	 */
	public Pager() {
		pageNo = 1;
		pageSize = 20;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/** 计算当前分页号*/
	public int getPageNo() {
		if (pageNo > getTotalPageCount()) {
			return getTotalPageCount();
		}
		return pageNo;
	}
	
	/** 计算分页总数 */
	public int getTotalPageCount() {
		if (0 >= totalCount) {
			return 0;
		}
		if (0 >= pageSize) {
			return 0;
		}
		int i = totalCount / pageSize;
		// 取模不为0表示是整数
		if ( 0 != (totalCount % pageSize)) {
			i++;
		}
		return i;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	// 计算PostgreSQL或MySQL的偏移量，在DAO中使用
	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

}
