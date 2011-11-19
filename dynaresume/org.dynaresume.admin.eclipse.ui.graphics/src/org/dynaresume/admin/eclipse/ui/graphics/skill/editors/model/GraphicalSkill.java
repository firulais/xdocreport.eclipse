package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import org.dynaresume.domain.hr.Skill;

public class GraphicalSkill extends ConnectableNode {

	public static final String LABEL_PROP = "label";
	private final Skill skill;

	public GraphicalSkill(Skill skill) {
		this.skill = skill;
	}

	public GraphicalSkill() {
		this.skill = new Skill();
	}

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
		String oldLabel = skill.getLabel();
		skill.setLabel(label);
		firePropertyChange(LABEL_PROP, oldLabel, label);
	}

	public void setParent(Skill parent) {
		skill.setParent(parent);
	}

	public String toString() {
		return skill.toString();
	}

}
