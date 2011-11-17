package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import java.util.List;
import java.util.Map;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.SkillsDiagramFigure;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.GraphicalSkill;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.SkillsDiagram;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class SkillsDiagramPart extends AbstractGraphicalEditPart {

	static final Insets PADDING = new Insets(8, 6, 8, 6);
	static final Insets INNER_PADDING = new Insets(0);
	
	@Override
	protected IFigure createFigure() {
		return new SkillsDiagramFigure(this);
	}

	@Override
	protected void createEditPolicies() {

	}

	@Override
	protected List<GraphicalSkill> getModelChildren() {
		return getModel().getSkills();
	}

	@Override
	public SkillsDiagram getModel() {
		return (SkillsDiagram) super.getModel();
	}
	
	public void contributeNodesToGraph(CompoundDirectedGraph graph, Subgraph s,
			Map map) {
		Subgraph me = new Subgraph(this, s);
		me.outgoingOffset = 5;
		me.incomingOffset = 5;
		me.innerPadding = INNER_PADDING;
		me.setPadding(PADDING);
		map.put(this, me);
		graph.nodes.add(me);
		for (int i = 0; i < getChildren().size(); i++) {
			ConnectableNodePart activity = (ConnectableNodePart) getChildren()
					.get(i);
			activity.contributeNodesToGraph(graph, me, map);
		}
	}

	public void contributeEdgesToGraph(CompoundDirectedGraph graph, Map map) {
		for (int i = 0; i < getChildren().size(); i++) {
			ConnectableNodePart child = (ConnectableNodePart) children.get(i);
			child.contributeEdgesToGraph(graph, map);
		}
	}

	protected void applyGraphResults(CompoundDirectedGraph graph, Map map) {
		applyOwnResults(graph, map);
		applyChildrenResults(graph, map);
	}

	protected void applyOwnResults(CompoundDirectedGraph graph, Map map) {
		Node n = (Node) map.get(this);
		getFigure().setBounds(new Rectangle(n.x, n.y, n.width, n.height));
	}

	protected void applyChildrenResults(CompoundDirectedGraph graph, Map map) {
		for (int i = 0; i < getChildren().size(); i++) {
			ConnectableNodePart part = (ConnectableNodePart) getChildren().get(
					i);
			part.applyGraphResults(graph, map);
		}
	}

}
