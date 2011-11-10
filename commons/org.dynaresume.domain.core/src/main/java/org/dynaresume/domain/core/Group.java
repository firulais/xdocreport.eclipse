package org.dynaresume.domain.core;

import java.io.Serializable;
import java.util.Set;

public class Group {
	// @Id
	// @GeneratedValue
	private Long id;

	// @Column
	private String name;

	/**
    * 
    */
	private static final long serialVersionUID = 7884537382593661072L;
	// @OneToMany(mappedBy="group",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Agency> subsidiaries;

	public Set<Agency> getSubsidiaries() {
		return subsidiaries;
	}

	public void setSubsidiaries(Set<Agency> subsidiaries) {
		// Object oldValue = this.subsidiaries;
		this.subsidiaries = subsidiaries;
		// firePropertyChange("subsidiaries", oldValue, subsidiaries);
	}

	// @Override
	public Serializable getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// Object oldValue = this.name;
		this.name = name;
		// firePropertyChange("name", oldValue, name);
	}

	public void setId(Long id) {

		this.id = id;

	}

}
