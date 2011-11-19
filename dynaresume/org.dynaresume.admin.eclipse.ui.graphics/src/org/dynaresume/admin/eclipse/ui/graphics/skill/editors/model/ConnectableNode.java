package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ConnectableNode extends ModelElement {

	private final List<Connection> sourceConnections;
	private final List<Connection> targetConnections;

	/** Property ID to use when the list of outgoing connections is modified. */
	public static final String SOURCE_CONNECTIONS_PROP = "Shape.SourceConn";
	/** Property ID to use when the list of incoming connections is modified. */
	public static final String TARGET_CONNECTIONS_PROP = "Shape.TargetConn";
	
	public ConnectableNode() {
		this.sourceConnections = new ArrayList<Connection>();
		this.targetConnections = new ArrayList<Connection>();
	}

	public List<Connection> getSourceConnections() {
		return sourceConnections;
	}

	public List<Connection> getTargetConnections() {
		return targetConnections;
	}

	public void addConnection(Connection conn) {
		if (conn == null || conn.getSource() == conn.getTarget()) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.add(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.add(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
		}
	}

	public void removeConnection(Connection conn) {
		if (conn == null) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.remove(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.remove(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
		}
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

}
