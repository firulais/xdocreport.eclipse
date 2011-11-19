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
package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.commands;

import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.SkillsDiagram;
import org.eclipse.gef.commands.Command;

/**
 * OrphanChildCommand
 * 
 * @author Daniel Lee
 */
public class OrphanChildCommand extends Command {

	private SkillsDiagram parent;
	private ConnectableNode child;
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
	 * Sets the child to the passed ConnectableNode
	 * 
	 * @param child
	 *            the child
	 */
	public void setChild(ConnectableNode child) {
		this.child = child;
	}

	/**
	 * Sets the parent to the passed SkillsDiagram
	 * 
	 * @param parent
	 *            the parent
	 */
	public void setParent(SkillsDiagram parent) {
		this.parent = parent;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.addChild(child, index);
	}

}
