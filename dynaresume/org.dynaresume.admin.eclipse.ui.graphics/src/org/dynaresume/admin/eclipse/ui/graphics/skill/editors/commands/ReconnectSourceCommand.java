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
package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.commands;

import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.Connection;
import org.eclipse.gef.commands.Command;

/**
 * Command that handles the reconnection of source Activities.
 * 
 * @author Daniel Lee
 */
public class ReconnectSourceCommand extends Command {

	/** source ConnectableNode **/
	protected ConnectableNode source;
	/** target ConnectableNode **/
	protected ConnectableNode target;
	/** connection between source and target **/
	protected Connection connection;
	/** previous source prior to command execution **/
	protected ConnectableNode oldSource;

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (connection.getTarget().equals(source))
			return false;

		List connections = source.getTargetConnections();
		for (int i = 0; i < connections.size(); i++) {
			Connection trans = ((Connection) (connections.get(i)));
			if (trans.getTarget().equals(target)
					&& !trans.getSource().equals(oldSource))
				return false;
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (source != null) {
			oldSource.removeConnection(connection);
			connection.setSource(source);
			source.addConnection(connection);
		}
	}

	/**
	 * Returns the source ConnectableNode associated with this command
	 * 
	 * @return the source ConnectableNode
	 */
	public ConnectableNode getSource() {
		return source;
	}

	/**
	 * Returns the target ConnectableNode associated with this command
	 * 
	 * @return the target ConnectableNode
	 */
	public ConnectableNode getTarget() {
		return target;
	}

	/**
	 * Returns the Connection associated with this command
	 * 
	 * @return the Connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets the source ConnectableNode associated with this command
	 * 
	 * @param activity
	 *            the source ConnectableNode
	 */
	public void setSource(ConnectableNode activity) {
		source = activity;
	}

	/**
	 * Sets the target ConnectableNode assoicated with this command
	 * 
	 * @param activity
	 *            the target ConnectableNode
	 */
	public void setTarget(ConnectableNode activity) {
		target = activity;
	}

	/**
	 * Sets the connection associated with this
	 * 
	 * @param trans
	 *            the connection
	 */
	public void setConnection(Connection trans) {
		connection = trans;
		target = trans.getTarget();
		oldSource = trans.getSource();
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		source.removeConnection(connection);
		connection.setSource(oldSource);
		oldSource.addConnection(connection);
	}

}
