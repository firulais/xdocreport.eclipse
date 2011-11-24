package org.dynaresume.domain.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SkillResume {

	public static final String FREE_SKILL_PROPERTY = "freeSkill";
	public static final String SKILL_PROPERTY = "skill";
	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne
	private SkillCategory category;
	
	@ManyToOne
	private Skill skill;
	@Column
	private String freeSkill;

	public SkillCategory getCategory() {
		return category;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getFreeSkill() {
		return freeSkill;
	}

	public void setFreeSkill(String freeSkill) {
		this.freeSkill = freeSkill;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
