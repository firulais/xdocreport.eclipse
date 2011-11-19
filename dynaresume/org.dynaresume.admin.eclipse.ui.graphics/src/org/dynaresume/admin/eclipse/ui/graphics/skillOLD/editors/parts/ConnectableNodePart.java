package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.Connection;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.GraphicalSkill;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.policies.SkillContainerEditPolicy;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.policies.SkillNodeEditPolicy;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.policies.SkillsLayoutEditPolicy;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

public abstract class ConnectableNodePart extends
		AbstractBaseModelGraphicalEditPart implements NodeEditPart {

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
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

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new RootComponentEditPolicy());
		//installEditPolicy(EditPolicy.LAYOUT_ROLE, new SkillsLayoutEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new SkillNodeEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new SkillContainerEditPolicy());
	}
	
	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (GraphicalSkill.SOURCE_CONNECTIONS_PROP.equals(prop))
			refreshSourceConnections();
		else if (GraphicalSkill.TARGET_CONNECTIONS_PROP.equals(prop))
			refreshTargetConnections();
	}
}
