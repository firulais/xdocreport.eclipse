package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.domain.hr.Skill;

public class TreeNode extends ModelElement {

	private String label;
	private List<TreeNode> children;

	public TreeNode() {
		children = new ArrayList<TreeNode>();
	}

	public TreeNode(Skill skill) {
		this();
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
