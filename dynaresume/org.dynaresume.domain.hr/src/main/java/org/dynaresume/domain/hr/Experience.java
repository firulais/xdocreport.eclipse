package org.dynaresume.domain.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Experience {

	public static final String START_DATE_PROPERTY = "startDate";
	public static final String END_DATE_PROPERTY = "endDate";
	public static final String TITLE_PROPERTY = "title";
	public static final String MISSION_PROPERTY = "mission";
	public static final String DETAIL_PROPERTY = "detail";
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column
	private String detail;
	@Column
	private String mission;

	public Long getId() {
		return id;
	}

	public void setId(long id) {

		this.id = id;

	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		// Object oldValue = this.startDate;
		this.startDate = startDate;
		// firePropertyChange("startDate", oldValue, startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		// Object oldValue = this.endDate;
		this.endDate = endDate;
		// firePropertyChange("endDate", oldValue, endDate);
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String context) {
		// Object oldValue = this.context;
		this.detail = context;
		// firePropertyChange("context", oldValue, context);
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		// Object oldValue = this.mission;
		this.mission = mission;
		// irePropertyChange("mission", oldValue, mission);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
