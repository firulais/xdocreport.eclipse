package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.commands;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.SkillsDiagram;
import org.eclipse.gef.commands.Command;

public class CreateCommand extends Command {

	private SkillsDiagram parent;
	private ConnectableNode child;
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
	public void setParent(SkillsDiagram sa) {
		parent = sa;
	}

	/**
	 * Sets the Activity to create
	 * 
	 * @param activity
	 *            the Activity to create
	 */
	public void setChild(ConnectableNode activity) {
		child = activity;
		child.setName("Skill " + (parent.getChildren().size() + 1));
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.removeChild(child);
	}

}
