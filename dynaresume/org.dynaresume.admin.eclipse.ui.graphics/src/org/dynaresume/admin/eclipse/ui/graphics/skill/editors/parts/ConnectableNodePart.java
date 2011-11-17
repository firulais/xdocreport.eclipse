package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import java.util.List;
import java.util.Map;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.Connection;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.NodeEditPart;

public abstract class ConnectableNodePart extends AbstractBaseModelGraphicalEditPart
		implements NodeEditPart {

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

//		int x = new Double(Math.random() * 400).intValue();
//		int y = new Double(Math.random() * 400).intValue();
//
//		Rectangle bounds = new Rectangle(x, y, 50, 50);
//		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
//				getFigure(), bounds);
	}

	@Override
	protected List<Connection> getModelSourceConnections() {
		return getConnectableNode().getSourceConnections();
	}

	@Override
	protected List<Connection> getModelTargetConnections() {
		return getConnectableNode().getTargetConnections();
	}

	public ConnectableNode getConnectableNode() {
		return (ConnectableNode) super.getModel();
	}

	public void contributeNodesToGraph(CompoundDirectedGraph graph, Subgraph s,
			Map map) {
		Node n = new Node(this, s);
		n.outgoingOffset = getAnchorOffset();
		n.incomingOffset = getAnchorOffset();
		n.width = getFigure().getPreferredSize().width;
		n.height = getFigure().getPreferredSize().height;
		n.setPadding(new Insets(10, 8, 10, 12));
		map.put(this, n);
		graph.nodes.add(n);
	}

	int getAnchorOffset() {
		return 9;
	}

	public void contributeEdgesToGraph(CompoundDirectedGraph graph, Map map) {
		List outgoing = getSourceConnections();
		for (int i = 0; i < outgoing.size(); i++) {
			ConnectionPart part = (ConnectionPart) getSourceConnections()
					.get(i);
			part.contributeToGraph(graph, map);
		}
	}

	public void applyGraphResults(CompoundDirectedGraph graph, Map map) {
		Node n = (Node) map.get(this);
		getFigure().setBounds(new Rectangle(n.x, n.y, n.width, n.height));

		for (int i = 0; i < getSourceConnections().size(); i++) {
			ConnectionPart trans = (ConnectionPart) getSourceConnections().get(
					i);
			trans.applyGraphResults(graph, map);
		}
	}

}
