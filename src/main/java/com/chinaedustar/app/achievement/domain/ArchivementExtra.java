package com.chinaedustar.app.achievement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ArchivementExtra")
@SuppressWarnings("serial")
public class ArchivementExtra implements Serializable {
    @Id
	@Column(name = "ArchId", nullable = false)
	private int archId;

	@Column(name = "Summary", nullable = true)
    private String summary;

	@Column(name = "PublishName", nullable = true)
    private String publishName;

	@Column(name = "PublishTime", nullable = true)
    private Date publishTime;

	@Column(name = "PrizeItem", nullable = true)
    private String prizeItem;

	@Column(name = "PrizeLevel", nullable = true)
    private int prizeLevel;

	@Column(name = "PrizeTime", nullable = true)
    private Date prizeTime;

	@Column(name = "ServicePatent", nullable = true)
    private boolean servicePatent;

	@Column(name = "Authorize", nullable = true)
    private boolean authorize;

	@Column(name = "ApplyNumber", nullable = true)
    private String applyNumber;
	
	@Column(name = "ApplyTime", nullable = true)
    private Date applyTime;

	@Column(name = "Number", nullable = true)
    private String number;
	
	@Column(name = "ObtainTime", nullable = true)
    private Date obtainTime;

	@Column(name = "Agency", nullable = true)
    private String agency;

	@Column(name = "Agent", nullable = true)
    private String agent;
	
	@OneToOne(mappedBy = "archExtra")
	private Archivement archivement;
	
	public ArchivementExtra() {
		//
	}

	public int getArchId() {
		return archId;
	}

	public void setArchId(int archId) {
		this.archId = archId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPrizeItem() {
		return prizeItem;
	}

	public void setPrizeItem(String prizeItem) {
		this.prizeItem = prizeItem;
	}

	public int getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(int prizeLevel) {
		this.prizeLevel = prizeLevel;
	}

	public Date getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(Date prizeTime) {
		this.prizeTime = prizeTime;
	}

	public boolean isServicePatent() {
		return servicePatent;
	}

	public void setServicePatent(boolean servicePatent) {
		this.servicePatent = servicePatent;
	}

	public boolean isAuthorize() {
		return authorize;
	}

	public void setAuthorize(boolean authorize) {
		this.authorize = authorize;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getObtainTime() {
		return obtainTime;
	}

	public void setObtainTime(Date obtainTime) {
		this.obtainTime = obtainTime;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

}

