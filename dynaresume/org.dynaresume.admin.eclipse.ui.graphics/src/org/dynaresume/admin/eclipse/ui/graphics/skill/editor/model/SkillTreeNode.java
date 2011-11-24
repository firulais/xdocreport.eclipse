package org.dynaresume.admin.eclipse.ui.graphics.skill.editor.model;

import org.dynaresume.domain.hr.Skill;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

public class SkillTreeNode extends TreeNode<Skill> {

	public SkillTreeNode() {
		super(new Skill());
	}

	public SkillTreeNode(Skill skill) {
		super(skill);
	}

	@Override
	protected String getModelLabel() {
		return getModel().getName();
	}

	@Override
	protected Object getModelParent() {
		// TODO Auto-generated method stub
		return getModel().getParent();
	}

	@Override
	protected void setModelLabel(String label) {
		getModel().setName(label);
	}
	
	@Override
	public String getTypeName() {
		return "Skill";
	}

}
