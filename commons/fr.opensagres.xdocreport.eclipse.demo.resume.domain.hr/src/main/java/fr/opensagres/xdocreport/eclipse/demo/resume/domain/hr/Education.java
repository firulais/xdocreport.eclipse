package fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr;

import java.util.Calendar;
import java.util.Date;

public class Education {

	public static final String DATE_PROPERTY = "date";
	public static final String LABEL_PROPERTY = "label";
	public static final String INSTITUTE_PROPERTY = "institute";

	/**
     * 
     */
	private static final long serialVersionUID = 2452995207655152758L;

	// @Id
	// @GeneratedValue
	private Long id;

	// @Column
	private String institute;

	// @Column
	private String label;

	// @Column
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
