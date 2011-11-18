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
package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.policies;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands.ConnectionCreateCommand;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands.ReconnectSourceCommand;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands.ReconnectTargetCommand;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.Connection;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.ConnectableNodePart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * 
 * Created on Jul 17, 2003
 */
public class SkillNodeEditPolicy extends GraphicalNodeEditPolicy {

	/**
	 * @see GraphicalNodeEditPolicy#getConnectionCompleteCommand(CreateConnectionRequest)
	 */
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		ConnectionCreateCommand cmd = (ConnectionCreateCommand) request
				.getStartCommand();
		cmd.setTarget(getActivity());
		return cmd;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getConnectionCreateCommand(CreateConnectionRequest)
	 */
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		ConnectionCreateCommand cmd = new ConnectionCreateCommand();
		cmd.setSource(getActivity());
		request.setStartCommand(cmd);
		return cmd;
	}

	/**
	 * Returns the ActivityPart on which this EditPolicy is installed
	 * 
	 * @return the
	 */
	protected ConnectableNodePart getActivityPart() {
		return (ConnectableNodePart) getHost();
	}

	/**
	 * Returns the model associated with the EditPart on which this EditPolicy
	 * is installed
	 * 
	 * @return the model
	 */
	protected ConnectableNode getActivity() {
		return (ConnectableNode) getHost().getModel();
	}

	/**
	 * @see GraphicalNodeEditPolicy#getReconnectSourceCommand(ReconnectRequest)
	 */
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((Connection) request.getConnectionEditPart()
				.getModel());
		cmd.setSource(getActivity());
		return cmd;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getReconnectTargetCommand(ReconnectRequest)
	 */
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		ReconnectTargetCommand cmd = new ReconnectTargetCommand();
		cmd.setConnection((Connection) request.getConnectionEditPart()
				.getModel());
		cmd.setTarget(getActivity());
		return cmd;
	}

}
