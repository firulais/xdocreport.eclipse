package org.dynaresume.admin.eclipse.ui.graphics.group.editor.model;

import org.dynaresume.domain.core.Agency;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

public class AgencyTreeNode extends TreeNode<Agency> {

	public AgencyTreeNode() {
		super(new Agency());
	}

	public AgencyTreeNode(Agency agency) {
		super(agency);
	}

	@Override
	protected String getModelLabel() {
		return getModel().getName();
	}

	@Override
	protected Object getModelParent() {
		return getModel().getGroup();
	}

	@Override
	protected void setModelLabel(String label) {
		getModel().setName(label);
	}

	@Override
	public String getTypeName() {
		return "Agency";
	}

}
