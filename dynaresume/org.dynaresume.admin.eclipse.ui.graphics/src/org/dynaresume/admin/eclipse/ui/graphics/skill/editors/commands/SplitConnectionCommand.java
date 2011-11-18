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
package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.Connection;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.SkillsDiagram;
import org.eclipse.gef.commands.Command;

/**
 * SplitConnectionCommand
 * 
 * @author Daniel Lee
 */
public class SplitConnectionCommand extends Command {

	private SkillsDiagram parent;
	private ConnectableNode oldSource;
	private ConnectableNode oldTarget;
	private Connection connection;

	private ConnectableNode newConnectableNode;
	private Connection newIncomingConnection;
	private Connection newOutgoingConnection;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldSource.removeConnection(connection);
		oldTarget.removeConnection(connection);
		parent.addChild(newConnectableNode);
		newIncomingConnection = new Connection(oldSource, newConnectableNode);
		newOutgoingConnection = new Connection(newConnectableNode, oldTarget);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		oldSource.addConnection(newIncomingConnection);
		oldTarget.addConnection(newOutgoingConnection);
		newConnectableNode.addConnection(newIncomingConnection);
		newConnectableNode.addConnection(newOutgoingConnection);
		parent.addChild(newConnectableNode);
		oldSource.removeConnection(connection);
		oldTarget.removeConnection(connection);
	}

	/**
	 * Sets the parent ConnectableNode. The new ConnectableNode will be added as a child to
	 * the parent.
	 * 
	 * @param activity
	 *            the parent
	 */
	public void setParent(SkillsDiagram activity) {
		parent = activity;
	}

	/**
	 * Sets the connection to be "split".
	 * 
	 * @param connection
	 *            the connection to be "split".
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
		oldSource = connection.getSource();
		oldTarget = connection.getTarget();
	}

	/**
	 * Sets the ConnectableNode to be added.
	 * 
	 * @param activity
	 *            the new activity
	 */
	public void setNewConnectableNode(ConnectableNode activity) {
		newConnectableNode = activity;
		newConnectableNode.setName("a " + (parent.getChildren().size() + 1));
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		oldSource.removeConnection(newIncomingConnection);
		oldTarget.removeConnection(newOutgoingConnection);
		newConnectableNode.removeConnection(newIncomingConnection);
		newConnectableNode.removeConnection(newOutgoingConnection);
		parent.removeChild(newConnectableNode);
		oldSource.addConnection(connection);
		oldTarget.addConnection(connection);
	}

}
