package org.dynaresume.domain.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SkillCategory {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String code;
	
	@Column
	private String label;
	@Transient
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

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
//	public void setChildren(Collection<SkillCategory> children) {
//		this.children = children;
//	}
//
//	public Collection<SkillCategory> getChildren() {
//		return children;
//	}
}
