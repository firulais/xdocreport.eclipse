package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;

public class SkillsDiagram {

	private final List<GraphicalSkill> skills;

	public SkillsDiagram(Collection<Skill> skills) {
		this.skills = new ArrayList<GraphicalSkill>();
		Map<Skill, GraphicalSkill> skillsMapping = new HashMap<Skill, GraphicalSkill>();
		for (Skill skill : skills) {
			addSkill(skill, skillsMapping);
		}
	}

	private void addSkill(Skill skill, Map<Skill, GraphicalSkill> skillsMapping) {
		GraphicalSkill skillWrapper = getSkillWrapper(skill, skillsMapping);
		if (skill.getParent() != null) {
			GraphicalSkill parentSkillWrapper = getSkillWrapper(
					skill.getParent(), skillsMapping);
			Connection connection = new Connection(skillWrapper,
					parentSkillWrapper);
			skillWrapper.addConnection(connection);
			//parentSkillWrapper.addConnection(connection);
		}
	}

	private GraphicalSkill getSkillWrapper(Skill skill,
			Map<Skill, GraphicalSkill> skillsMapping) {
		GraphicalSkill skillWrapper = skillsMapping.get(skill);
		if (skillWrapper == null) {
			skillWrapper = new GraphicalSkill(skill);
			skillsMapping.put(skill, skillWrapper);
			this.skills.add(skillWrapper);
		}
		return skillWrapper;
	}

	public List<GraphicalSkill> getSkills() {
		return skills;
	}
}
