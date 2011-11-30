package org.dynaresume.domain.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Address implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 9217187221005720810L;

	public static final String STREET_PROPERTY = "street";
	public static final String ZIPCODE_PROPERTY = "zipCode";
	public static final String CITY_PROPERTY = "city";
	public static final String COUNTRY_PROPERTY = "country";
	public static final String TELEPHONE_PROPERTY = "telephone";
	public static final String FAX_PROPERTY = "fax";

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String street;
	@Column
	private String zipCode;
	@Column
	private String city;
	@Column
	private String fax;
	@Column
	private String telephone;

	 @ManyToOne
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}