package com.chinaedustar.app.achievement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stat")
@SuppressWarnings("serial")
public class Stat implements Serializable {
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int id;

	@Column(name = "UserId", nullable = false)
    private String userId;

	@Column(name = "Thesis", nullable = false)
    private int thesis;

	@Column(name = "Book", nullable = false)
    private int book;

	@Column(name = "Courseware", nullable = false)
    private int courseware;

	@Column(name = "Instruction", nullable = false)
    private int instruction;

	@Column(name = "Patent", nullable = false)
    private int patent;

	@Column(name = "Other", nullable = false)
    private int other;

	@Column(name = "TotalCount", nullable = false)
    private int sum;
	
	public Stat() {
		//
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getThesis() {
		return thesis;
	}

	public void setThesis(int thesis) {
		this.thesis = thesis;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}

	public int getCourseware() {
		return courseware;
	}

	public void setCourseware(int courseware) {
		this.courseware = courseware;
	}

	public int getInstruction() {
		return instruction;
	}

	public void setInstruction(int instruction) {
		this.instruction = instruction;
	}

	public int getPatent() {
		return patent;
	}

	public void setPatent(int patent) {
		this.patent = patent;
	}

	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}

