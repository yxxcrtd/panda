package com.chinaedustar.app.achievement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Operation")
@SuppressWarnings("serial")
public class Operation implements Serializable {
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int id;

	@Column(name = "ArchId", nullable = false)
    private int archId;

	@Column(name = "Praise", nullable = false)
    private boolean praise;

	@Column(name = "Collect", nullable = false)
    private boolean collect;

	@Column(name = "UserId", nullable = false)
    private String userId;
	
	public Operation() {
		//
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArchId() {
		return archId;
	}

	public void setArchId(int archId) {
		this.archId = archId;
	}

	public boolean getPraise() {
		return praise;
	}

	public void setPraise(boolean praise) {
		this.praise = praise;
	}

	public boolean getCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

