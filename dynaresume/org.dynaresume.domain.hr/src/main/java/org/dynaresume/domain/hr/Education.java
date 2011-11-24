package org.dynaresume.domain.hr;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Education {

	public static final String DATE_PROPERTY = "date";
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
	private Date date;

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
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

	public void setDate(Date date) {
		this.date = date;
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

	public int getDateYear() {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
}
