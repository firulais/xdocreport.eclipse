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
 * Command to rename Activities.
 * 
 * @author Daniel Lee
 */
public class RenameTreeNodeCommand extends Command {

	private TreeNode source;
	private String name, oldName;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		source.setLabel(name);
	}

	/**
	 * Sets the new TreeNode name
	 * 
	 * @param string
	 *            the new name
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * Sets the old TreeNode name
	 * 
	 * @param string
	 *            the old name
	 */
	public void setOldName(String string) {
		oldName = string;
	}

	/**
	 * Sets the source TreeNode
	 * 
	 * @param activity
	 *            the source TreeNode
	 */
	public void setSource(TreeNode activity) {
		source = activity;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		source.setLabel(oldName);
	}

}
