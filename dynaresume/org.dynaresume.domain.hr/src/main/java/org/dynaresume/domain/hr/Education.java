package org.dynaresume.domain.hr;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//FIXME : please don't put any technical dependencies on Domain Model.
//import fr.opensagres.xdocreport.commons.utils.DateUtils;

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
		this.institute = institute;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	//kept for compatibility reason
	//I don't think It's a good Idea :
	//this code should be placed in the caller  
	public int getStartDateYear() {
		
		return getDateYear(startDate);
	}
	
	private int getDateYear(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);

	}
	//kept for compatibility reason
	//I don't think It's a good Idea :
	//this code should be placed in the caller 
	public int getEndDateYear() {
		return getDateYear(endDate);
	}
}
