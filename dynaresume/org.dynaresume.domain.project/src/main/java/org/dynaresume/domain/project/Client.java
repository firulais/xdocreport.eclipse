package org.dynaresume.domain.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 9217187221005720810L;

	public static final String NAME_PROPERTY = "name";

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;

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

}