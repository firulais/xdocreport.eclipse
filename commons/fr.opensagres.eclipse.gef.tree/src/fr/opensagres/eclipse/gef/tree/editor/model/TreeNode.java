package fr.opensagres.eclipse.gef.tree.editor.model;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeNode<Model> extends ModelElement {

	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "TreeNode.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "TreeNode.ChildRemoved";
	public static final String LABEL_PROP = "TreeNode.Label";

	private String label;
	private List<TreeNode> children;

	private final Model model;

	public TreeNode() {
		this(null);
	}

	public TreeNode(Model model) {
		children = new ArrayList<TreeNode>();
		this.model = model;
		if (model != null) {
			this.label = this.getModelLabel();
		}
	}

	public Model getModel() {
		return model;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setLabel(String label) {
		String oldLabel = this.label;
		this.label = label;
		this.setModelLabel(label);
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

	public Object getParent() {
		if (model != null) {
			return this.getModelParent();
		}
		return null;
	}

	protected abstract String getModelLabel();

	protected abstract void setModelLabel(String label);

	protected abstract Object getModelParent();

	public abstract String getTypeName();
}
