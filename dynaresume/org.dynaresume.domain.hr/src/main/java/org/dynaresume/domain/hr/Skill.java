package org.dynaresume.domain.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Skill {

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

	@ManyToOne(optional = true)
	private Skill parent;
	@ManyToOne
	private SkillCategory category;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getURL() {
		return url;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		// Object oldValue = this.name;
		this.name = name;
		// firePropertyChange("name", oldValue, name);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public Skill getParent() {
		return parent;
	}

	public void setParent(Skill parent) {
		this.parent = parent;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	public SkillCategory getCategory() {
		return category;
	}
}
