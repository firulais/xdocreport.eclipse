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
package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.commands;

import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.Connection;
import org.eclipse.gef.commands.Command;

/**
 * @author Daniel Lee
 */
public class ConnectionCreateCommand extends Command {

	/** The Transistion between source and target Activities **/
	protected Connection connection;
	/** The source ConnectableNode **/
	protected ConnectableNode source;
	/** The target ConnectableNode **/
	protected ConnectableNode target;

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (source.equals(target))
			return false;

		// Check for existence of connection already
		List transistions = source.getTargetConnections();
		for (int i = 0; i < transistions.size(); i++) {
			if (((Connection) transistions.get(i)).getTarget().equals(target))
				return false;
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		connection = new Connection(source, target);
	}

	/**
	 * Returns the source ConnectableNode
	 * 
	 * @return the source
	 */
	public ConnectableNode getSource() {
		return source;
	}

	/**
	 * Returns the target ConnectableNode
	 * 
	 * @return the target
	 */
	public ConnectableNode getTarget() {
		return target;
	}

	/**
	 * Returns the Transistion between the source and target Activities
	 * 
	 * @return the transistion
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		source.addConnection(connection);
		target.addConnection(connection);
	}

	/**
	 * Sets the source ConnectableNode
	 * 
	 * @param activity
	 *            the source ConnectableNode
	 */
	public void setSource(ConnectableNode activity) {
		source = activity;
	}

	/**
	 * Sets the Transistion between the source and target Activities
	 * 
	 * @param connection
	 *            the transistion
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Sets the target ConnectableNode
	 * 
	 * @param activity
	 *            the target
	 */
	public void setTarget(ConnectableNode activity) {
		target = activity;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		source.removeConnection(connection);
		target.removeConnection(connection);
	}

}
