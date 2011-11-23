package org.dynaresume.domain.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Skill {

	public static final String LABEL_PROPERTY = "label";

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String label;

	// @ManyToOne(optional=true)
	@Transient
	private Skill parent;

	private SkillCategory category;

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		// Object oldValue = this.label;
		this.label = label;
		// firePropertyChange("label", oldValue, label);
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
