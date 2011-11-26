package org.dynaresume.domain.hr;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.opensagres.xdocreport.commons.utils.DateUtils;

@Entity
public class Education {

	public static final String START_DATE_PROPERTY = "startDate";
	public static final String END_DATE_PROPERTY = "endDate";
	public static final String LABEL_PROPERTY = "label";
	public static final String INSTITUTE_PROPERTY = "institute";

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String institute;

	@Column
	private String label;

	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getInstitute() {
		return institute;
	}

	public String getLabel() {
		return label;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setInstitute(String institute) {
		// Object oldValue = this.institute;
		this.institute = institute;
		// firePropertyChange("institute", oldValue, institute);
	}

	public void setLabel(String label) {
		// Object oldValue = this.label;
		this.label = label;
		// firePropertyChange("label", oldValue, label);
	}

	public int getStartDateYear() {
		return DateUtils.getDateYear(startDate);
	}
	
	public int getEndDateYear() {
		return DateUtils.getDateYear(endDate);
	}
}
