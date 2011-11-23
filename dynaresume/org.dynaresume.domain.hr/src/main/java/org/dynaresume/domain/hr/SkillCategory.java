package org.dynaresume.domain.hr;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SkillCategory {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String label;

	private SkillCategory parent;

	//private Collection<SkillCategory> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public SkillCategory getParent() {
		return parent;
	}

	public void setParent(SkillCategory parent) {
		this.parent = parent;
	}

//	public void setChildren(Collection<SkillCategory> children) {
//		this.children = children;
//	}
//
//	public Collection<SkillCategory> getChildren() {
//		return children;
//	}
}
