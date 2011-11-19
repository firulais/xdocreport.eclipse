package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model;

import java.util.List;

public class ConnectionUtils {

	public static void connect(ConnectableNode source, ConnectableNode target) { 
	    Connection connection = new Connection(source, target); 
	    source.getSourceConnections().add(connection); 
	    target.getTargetConnections().add(connection); 
	  } 
	 
	  public static void clearTargetConnections(ConnectableNode node) { 
	    List<Connection> targetConnections = node.getTargetConnections(); 
	    for (Connection targetConnection : targetConnections) { 
	      ConnectableNode source = targetConnection.getSource(); 
	      source.getSourceConnections().remove(targetConnection); 
	    } 
	    targetConnections.clear(); 
	  } 
	 
	  public static void clearSourceConnections(ConnectableNode node) { 
	    List<Connection> sourceConnections = node.getSourceConnections(); 
	    for (Connection sourceConnection : sourceConnections) { 
	      ConnectableNode target = sourceConnection.getTarget(); 
	      target.getTargetConnections().remove(sourceConnection); 
	    } 
	    sourceConnections.clear(); 
	  } 
}
