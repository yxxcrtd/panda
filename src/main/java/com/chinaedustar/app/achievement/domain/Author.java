package com.chinaedustar.app.achievement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Author")
@SuppressWarnings("serial")
public class Author implements Serializable {
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int id;

	@Column(name = "ArchId", nullable = false)
    private int archId;

	@Column(name = "Name", nullable = false)
    private String name;

	@Column(name = "NameId", nullable = true)
    private String nameId;
	
	@Column(name = "UnitId")
    private Integer unitId;

    @Column(name = "UnitPath")
    private String unitPath;
		
	
	public Author() {
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

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitPath() {
        return unitPath;
    }

    public void setUnitPath(String unitPath) {
        this.unitPath = unitPath;
    }

}

