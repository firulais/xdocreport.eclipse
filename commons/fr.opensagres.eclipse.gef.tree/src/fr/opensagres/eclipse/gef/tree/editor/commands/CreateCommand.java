package fr.opensagres.eclipse.gef.tree.editor.commands;

import org.eclipse.gef.commands.Command;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

public class CreateCommand extends Command {

	private TreeNode parent;
	private TreeNode child;
	private int index = -1;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (index > 0)
			parent.addChild(child, index);
		else
			parent.addChild(child);
	}

	/**
	 * Sets the index to the passed value
	 * 
	 * @param i
	 *            the index
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * Sets the parent ActivityDiagram
	 * 
	 * @param sa
	 *            the parent
	 */
	public void setParent(TreeNode sa) {
		parent = sa;
	}

	/**
	 * Sets the Activity to create
	 * 
	 * @param treeNode
	 *            the Activity to create
	 */
	public void setChild(TreeNode treeNode) {
		child = treeNode;
		child.setLabel(treeNode.getTypeName() + (parent.getChildren().size() + 1));
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.removeChild(child);
	}

}
