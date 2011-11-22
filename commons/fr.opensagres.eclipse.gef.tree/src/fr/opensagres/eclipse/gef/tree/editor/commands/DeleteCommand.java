/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fr.opensagres.eclipse.gef.tree.editor.commands;

import org.eclipse.gef.commands.Command;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

/**
 * Handles the deletion of Activities.
 * 
 * @author Daniel Lee
 */
public class DeleteCommand extends Command {

	private TreeNode child;
	private TreeNode parent;
	private int index = -1;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		primExecute();
	}

	/**
	 * Invokes the execution of this command.
	 */
	protected void primExecute() {
		index = parent.getChildren().indexOf(child);
		parent.removeChild(child);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		primExecute();
	}

	/**
	 * Sets the child to the passed TreeNode
	 * 
	 * @param a
	 *            the child
	 */
	public void setChild(TreeNode a) {
		child = a;
	}

	/**
	 * Sets the parent to the passed TreeNode
	 * 
	 * @param sa
	 *            the parent
	 */
	public void setParent(TreeNode sa) {
		parent = sa;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.addChild(child, index);
	}

}
