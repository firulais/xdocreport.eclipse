package org.dynaresume.domain.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class LegalEntity implements Serializable{
	/**
     * 
     */
	private static final long serialVersionUID = -8975049737136593445L;
	 @Id
	 @GeneratedValue
	private Long id;

	 @Column
	private String name;

	 @Column
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
