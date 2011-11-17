package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import org.dynaresume.domain.hr.Skill;

public class GraphicalSkill extends ConnectableNode {

	private final Skill skill;

	public boolean equals(Object obj) {
		return skill.equals(obj);
	}

	public Long getId() {
		return skill.getId();
	}

	public String getLabel() {
		return skill.getLabel();
	}

	public Skill getParent() {
		return skill.getParent();
	}

	public int hashCode() {
		return skill.hashCode();
	}

	public void setId(Long id) {
		skill.setId(id);
	}

	public void setLabel(String label) {
		skill.setLabel(label);
	}

	public void setParent(Skill parent) {
		skill.setParent(parent);
	}

	public String toString() {
		return skill.toString();
	}

	public GraphicalSkill(Skill skill) {
		this.skill = skill;
	}

}
