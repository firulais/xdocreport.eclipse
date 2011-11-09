package fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr;

public class Diploma {
	
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
	private String period;

	public Long getId() {
		return id;
	}

	public String getInstitute() {
		return institute;
	}

	public String getLabel() {
		return label;
	}

	public String getPeriod() {
		return period;
	}

	public void setId(long id) {

		this.id = id;

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

	public void setPeriod(String period) {
		// Object oldValue = this.period;
		this.period = period;
		// firePropertyChange("period", oldValue, period);
	}

}
