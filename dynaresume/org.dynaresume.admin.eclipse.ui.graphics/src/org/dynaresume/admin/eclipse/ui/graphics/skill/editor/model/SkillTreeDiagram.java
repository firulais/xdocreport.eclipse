package org.dynaresume.admin.eclipse.ui.graphics.skill.editor.model;

import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.Skill;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeDiagram;
import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

public class SkillTreeDiagram extends TreeDiagram {

	public SkillTreeDiagram(Iterable<Skill> skills) {
		super();
		setLabel("Root");
		Map<Skill, TreeNode> skillsMapping = new HashMap<Skill, TreeNode>();
		for (Skill skill : skills) {
			addSkill(skill, skillsMapping);
		}
	}

	private void addSkill(Skill skill, Map<Skill, TreeNode> skillsMapping) {
		TreeNode skillWrapper = getSkillWrapper(skill, skillsMapping);
		boolean parentAlreadyParsed = false;
		while (skill.getParent() != null && !parentAlreadyParsed) {
			parentAlreadyParsed = skillsMapping.containsKey(skill.getParent());
			TreeNode parentSkillWrapper = getSkillWrapper(skill.getParent(),
					skillsMapping);
			parentSkillWrapper.addChild(skillWrapper);
			skill = skill.getParent();
		}
	}

	private TreeNode getSkillWrapper(Skill skill,
			Map<Skill, TreeNode> skillsMapping) {
		TreeNode skillWrapper = (TreeNode) skillsMapping.get(skill);
		if (skillWrapper == null) {
			skillWrapper = new SkillTreeNode(skill);
			skillsMapping.put(skill, skillWrapper);
			if (skillWrapper.getParent() == null)
				addChild(skillWrapper);
		}
		return skillWrapper;
	}
}
