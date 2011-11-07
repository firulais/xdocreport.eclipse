package fr.opensagres.xdocreport.eclipse.demo.resume.domain.core;

import java.util.Date;

public class Collaboration {
	/**
    * 
    */
	private static final long serialVersionUID = 1L;
	// @Id
	private long id;
	// @Id
	// private long projectId;

	// @Column
	private Date startDate;
	// @Column
	private Date endDate;

	// @OneToOne(fetch=FetchType.EAGER)
	// @PrimaryKeyJoinColumn
	private NaturalPerson employee;
	// @ManyToOne(fetch=FetchType.EAGER)
	// @PrimaryKeyJoinColumn(referencedColumnName = "ID")
	private Agency agency;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		// Object oldValue = this.id;
		this.id = id;
		// firePropertyChange("id", oldValue, id);
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

	public NaturalPerson getEmployee() {
		return employee;
	}

	public void setEmployee(NaturalPerson employee) {
		// Object oldValue = this.employee;
		this.employee = employee;
		// firePropertyChange("employee", oldValue, employee);
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		// Object oldValue = this.agency;
		this.agency = agency;
		// firePropertyChange("agency", oldValue, agency);
	}

}
