package org.dynaresume.domain.core;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
public class Person implements Serializable {

	public static final String EMAIL_PROPERTY = "email";
	public static final String ADDRESS_PROPERTY = "address";
	@Id
	 @GeneratedValue
	private Long id;
	/**
     * 
     */
	private static final long serialVersionUID = 298482050929739147L;
	@Column(length = 100, nullable = false)
	@Size(max = 100)
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}