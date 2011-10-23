package fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr;

import java.util.Date;

//@Entity
//@Table(name = "T_EXPERIENCE", schema = "hr")
public class Experience {

	/**
         * 
         */
	private static final long serialVersionUID = 8846754655040768127L;
	// @Id
	// @GeneratedValue
	private Long id;
	private String projectName;
	// @Column
	private Date startDate;
	// @Column
	private Date endDate;
	// @Column
	private String context;
	// @Column
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		// Object oldValue = this.context;
		this.context = context;
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
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectName() {
		return projectName;
	}
}
