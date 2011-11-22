package org.dynaresume.admin.eclipse.ui.graphics.group.editor.model;

import org.dynaresume.domain.core.Group;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

public class GroupTreeNode extends TreeNode<Group> {

	public GroupTreeNode() {
		super(new Group());
	}

	public GroupTreeNode(Group group) {
		super(group);
	}

	@Override
	protected String getModelLabel() {
		return getModel().getName();
	}

	@Override
	protected Object getModelParent() {
		return null;
	}

	@Override
	protected void setModelLabel(String label) {
		getModel().setName(label);
	}
	
	@Override
	public String getTypeName() {
		return "Group";
	}

}
