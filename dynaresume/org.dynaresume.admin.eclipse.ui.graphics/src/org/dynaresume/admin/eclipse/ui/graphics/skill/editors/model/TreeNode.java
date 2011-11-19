package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.domain.hr.Skill;

public class TreeNode extends ModelElement {

	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "TreeNode.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "TreeNode.ChildRemoved";
	public static final String LABEL_PROP = "TreeNode.Label";

	private String label;
	private List<TreeNode> children;
	private Skill parent;

	private Skill skill;

	public TreeNode() {
		this(null);
	}

	public TreeNode(Skill skill) {
		children = new ArrayList<TreeNode>();
		this.skill = skill;
		if (skill != null) {
			this.label = skill.getLabel();
		}
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setLabel(String label) {
		String oldLabel = this.label;
		this.label = label;
		firePropertyChange(LABEL_PROP, oldLabel, label);
	}

	public String getLabel() {
		return label;
	}

	public boolean addChild(TreeNode child, int index) {
		if (index >= 0) {
			if (child != null) {
				children.add(index, child);
				firePropertyChange(CHILD_ADDED_PROP, null, child);
				return true;
			}
		} else {
			if (child != null && children.add(child)) {
				firePropertyChange(CHILD_ADDED_PROP, null, child);
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a shape to this diagram.
	 * 
	 * @param s
	 *            a non-null shape instance
	 * @return true, if the shape was added, false otherwise
	 */
	public boolean addChild(TreeNode child) {
		return addChild(child, -1);
	}

	/**
	 * Remove a shape from this diagram.
	 * 
	 * @param s
	 *            a non-null shape instance;
	 * @return true, if the shape was removed, false otherwise
	 */
	public boolean removeChild(TreeNode s) {
		if (s != null && children.remove(s)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, s);
			return true;
		}
		return false;
	}

	public Skill getParent() {
		if (skill != null) {
			return skill.getParent();
		}
		return null;
	}
}
