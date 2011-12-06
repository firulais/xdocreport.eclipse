package org.dynaresume.domain.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Project implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 9217187221005720810L;

	public static final String NAME_PROPERTY = "name";
	public static final String DESCRIPTION_PROPERTY = "description";
	public static final String URL_PROPERTY = "URL";

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String url;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}
}