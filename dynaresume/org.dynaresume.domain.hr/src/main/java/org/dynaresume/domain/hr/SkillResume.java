package org.dynaresume.domain.hr;

public class SkillResume {

	public static final String FREE_SKILL_PROPERTY = "freeSkill";
	public static final String SKILL_PROPERTY = "skill";
	
	private SkillCategory category;
	private Skill skill;
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
	
	
}
