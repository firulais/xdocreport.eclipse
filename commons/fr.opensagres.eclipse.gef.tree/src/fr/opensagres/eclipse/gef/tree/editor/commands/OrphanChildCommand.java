/*******************************************************************************
 * Copyright (c) 2003, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fr.opensagres.eclipse.gef.tree.editor.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

/**
 * OrphanChildCommand
 * 
 * @author Daniel Lee
 */
public class OrphanChildCommand extends Command {

	private TreeNode parent;
	private TreeNode child;
	private int index;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		List children = parent.getChildren();
		index = children.indexOf(child);
		parent.removeChild(child);
	}

	/**
	 * Sets the child to the passed TreeNode
	 * 
	 * @param child
	 *            the child
	 */
	public void setChild(TreeNode child) {
		this.child = child;
	}

	/**
	 * Sets the parent to the passed TreeNode
	 * 
	 * @param parent
	 *            the parent
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.addChild(child, index);
	}

}
