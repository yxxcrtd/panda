package com.chinaedustar.app.achievement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Keyword")
@SuppressWarnings("serial")
public class Keyword implements Serializable {
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int id;

	@Column(name = "ArchId", nullable = false)
    private int archId;

	@Column(name = "Name", nullable = false)
    private String name;

	@Column(name = "Count", nullable = false)
    private int count;
	
	public Keyword() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

