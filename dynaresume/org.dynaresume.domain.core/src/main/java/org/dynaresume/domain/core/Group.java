package org.dynaresume.domain.core;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Group implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	/**
    * 
    */
	private static final long serialVersionUID = 7884537382593661072L;
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Agency> subsidiaries;

	public Set<Agency> getSubsidiaries() {
		return subsidiaries;
	}

	public void setSubsidiaries(Set<Agency> subsidiaries) {
		this.subsidiaries = subsidiaries;
	}

	public Long getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {

		this.id = id;

	}

}
