package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;

public class TreeDiagram extends TreeNode {

	public TreeDiagram(Collection<Skill> skills) {
		super();
		setLabel("Root");
		Map<Skill, TreeNode> skillsMapping = new HashMap<Skill, TreeNode>();
		for (Skill skill : skills) {
			addSkill(skill, skillsMapping);
		}
	}

	private void addSkill(Skill skill, Map<Skill, TreeNode> skillsMapping) {
		TreeNode skillWrapper = getSkillWrapper(skill, skillsMapping);
		if (skill.getParent() != null) {
			TreeNode parentSkillWrapper = getSkillWrapper(skill.getParent(),
					skillsMapping);
			parentSkillWrapper.addChild(skillWrapper);
		}
	}

	private TreeNode getSkillWrapper(Skill skill,
			Map<Skill, TreeNode> skillsMapping) {
		TreeNode skillWrapper = (TreeNode) skillsMapping.get(skill);
		if (skillWrapper == null) {
			skillWrapper = new TreeNode(skill);
			skillsMapping.put(skill, skillWrapper);
			if (skillWrapper.getParent() == null)
				addChild(skillWrapper);
		}
		return skillWrapper;
	}
}
