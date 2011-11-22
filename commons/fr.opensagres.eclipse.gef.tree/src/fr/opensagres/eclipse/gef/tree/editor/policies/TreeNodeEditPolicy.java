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
package fr.opensagres.eclipse.gef.tree.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import fr.opensagres.eclipse.gef.tree.editor.commands.DeleteCommand;
import fr.opensagres.eclipse.gef.tree.editor.model.TreeNode;

/**
 * @author Daniel Lee
 */
public class TreeNodeEditPolicy extends ComponentEditPolicy {

	/**
	 * @see ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parentModel = getHost().getParent().getModel();
		if (!(parentModel instanceof TreeNode)) {
			// avoid having java.lang.ClassCastException: java.lang.Object
			// cannot be cast to
			// org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.TreeNode
			// because of
			// GraphicalSkillsEditor#getGraphicalViewer().setRootEditPart(new
			// ScalableRootEditPart());
			return null;
		}
		TreeNode parent = (TreeNode) parentModel;
		DeleteCommand deleteCmd = new DeleteCommand();
		deleteCmd.setParent(parent);
		deleteCmd.setChild((TreeNode) (getHost().getModel()));
		return deleteCmd;
	}

}
