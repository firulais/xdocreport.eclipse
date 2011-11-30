package org.dynaresume.domain.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class NaturalPerson extends Person {

	public static final String FIRST_NAME_PROPERTY = "firstName";
	public static final String LAST_NAME_PROPERTY = "lastName";
	public static final String BIRTH_DATE_PROPERTY = "birthDate";
	/**
     * 
     */
	private static final long serialVersionUID = -1233890503792715089L;

	public NaturalPerson() {

	}

	public NaturalPerson(long id) {
		this.id = id;
	}

	 @Id
	 @GeneratedValue
	private Long id;

	public void setId(long id) {

		this.id = id;

	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private MaritalStatus maritalStatus;

	// @Column(length=100,nullable=false)
	
	@Size(min = 1)
	private String firstName;
	 @Column(length=100,nullable=false)
	@Size(min = 1)
	private String lastName;
	 @Column
	 @Temporal(TemporalType.DATE)
	private Date birthDate;

	 @Column
	private String birthPlace;

	 @Column
	private String mobilePhone;

	 @Column
	private boolean drivingLicense;

	 @ManyToOne
	private Country nationality;

	 @OneToOne(mappedBy="employee")
	 @PrimaryKeyJoinColumn(referencedColumnName = "ID")
	private Collaboration currentEmployer;

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public boolean isDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(boolean drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public Long getId() {
		return id;
	}

	public Collaboration getCurrentEmployer() {
		return currentEmployer;
	}

	public void setCurrentEmployer(Collaboration currentEmployer) {
		this.currentEmployer = currentEmployer;
	}

}
