package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.commands;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.Connection;
import org.eclipse.gef.commands.Command;

/**
 * Handles the deletion of connections between Activities.
 * 
 * @author Daniel Lee
 */
public class DeleteConnectionCommand extends Command {

	private ConnectableNode source;
	private ConnectableNode target;
	private Connection connection;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		connection.disconnect();
	}

	/**
	 * Sets the source activity
	 * 
	 * @param activity
	 *            the source
	 */
	public void setSource(ConnectableNode activity) {
		source = activity;
	}

	/**
	 * Sets the target activity
	 * 
	 * @param activity
	 *            the target
	 */
	public void setTarget(ConnectableNode target) {
		target = target;
	}

	/**
	 * Sets the connection
	 * 
	 * @param connection
	 *            the connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		connection.setSource(source);
		connection.setTarget(target);
		source.addConnection(connection);
		target.addConnection(connection);
	}

}
