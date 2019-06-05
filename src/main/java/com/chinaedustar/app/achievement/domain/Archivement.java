package com.chinaedustar.app.achievement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Archivement")
@SuppressWarnings("serial")
public class Archivement implements Serializable {
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ArchId", unique = true, nullable = false)
	private int archId;
    
    @Column(name = "UserId", nullable = false)
    private String userId;

    // 论文：thesis；著作：book；课件：courseware；教学设计：instruction；专利：patent；其他成果：other；
	@Column(name = "Category", nullable = false)
    private String category;
	
	@Column(name = "Type", nullable = true)
    private int type;

	@Column(name = "Title", nullable = true)
    private String title;

	@Column(name = "Publish", nullable = false)
    private boolean publish;

	@Column(name = "Prize", nullable = false)
    private boolean prize;

	@Column(name = "Author", nullable = false)
    private String author;

	@Column(name = "Keyword", nullable = true)
    private String keyword;

	@Column(name = "ViewCount", nullable = false)
    private int viewCount;

	@Column(name = "CollectCount", nullable = false)
    private int collectCount;

	@Column(name = "PraiseCount", nullable = false)
    private int praiseCount;

	@Column(name = "HotCount", nullable = false)
    private int hotCount;

	@Column(name = "GradeId", nullable = false)
    private int gradeId;

	@Column(name = "SubjectId", nullable = false)
    private int subjectId;

	@Column(name = "CreateTime", nullable = false)
    private long createTime;

	@Column(name = "TopicResearchId", nullable = true)
    private int topicResearchId;

	@Column(name = "TopicResearchStageId", nullable = true)
    private int topicResearchStageId;
	
	@Column(name = "TopicResearchName", nullable = true)
    private String topicResearchName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ArchId")
	public ArchivementExtra archExtra;
	
	public Archivement() {
		//
	}

	public int getArchId() {
		return archId;
	}

	public void setArchId(int archId) {
		this.archId = archId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public boolean getPrize() {
		return prize;
	}

	public void setPrize(boolean prize) {
		this.prize = prize;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public int getHotCount() {
		return hotCount;
	}

	public void setHotCount(int hotCount) {
		this.hotCount = hotCount;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getTopicResearchId() {
		return topicResearchId;
	}

	public void setTopicResearchId(int topicResearchId) {
		this.topicResearchId = topicResearchId;
	}

	public int getTopicResearchStageId() {
		return topicResearchStageId;
	}

	public void setTopicResearchStageId(int topicResearchStageId) {
		this.topicResearchStageId = topicResearchStageId;
	}

	public String getTopicResearchName() {
        return topicResearchName;
    }

    public void setTopicResearchName(String topicResearchName) {
        this.topicResearchName = topicResearchName;
    }

    public ArchivementExtra getArchExtra() {
		return archExtra;
	}

	public void setArchivementExtra(ArchivementExtra archExtra) {
		this.archExtra = archExtra;
	}

}
