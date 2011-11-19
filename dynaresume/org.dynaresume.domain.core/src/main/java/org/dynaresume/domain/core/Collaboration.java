package org.dynaresume.domain.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

public class Collaboration implements Serializable {
	/**
    * 
    */
	private static final long serialVersionUID = 1L;
	 @Id
	private long id;
	// @Id
	// private long projectId;

	 @Column
	private Date startDate;
	 @Column
	private Date endDate;

	 @OneToOne(fetch=FetchType.EAGER)
	 @PrimaryKeyJoinColumn
	private NaturalPerson employee;
	 @ManyToOne(fetch=FetchType.EAGER)
	 @PrimaryKeyJoinColumn(referencedColumnName = "ID")
	private Agency agency;

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
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public NaturalPerson getEmployee() {
		return employee;
	}

	public void setEmployee(NaturalPerson employee) {
		this.employee = employee;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

}
