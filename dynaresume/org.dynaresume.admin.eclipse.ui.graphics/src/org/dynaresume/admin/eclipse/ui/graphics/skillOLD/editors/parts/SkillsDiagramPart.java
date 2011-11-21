package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.PageNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.TreeBranch;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.TreeRoot;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.ConnectableNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.SkillsDiagram;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.TreeNode;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.policies.SkillContainerEditPolicy;
import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.policies.SkillsLayoutEditPolicy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.EditPolicy;

public class SkillsDiagramPart extends AbstractBaseModelGraphicalEditPart {

	static final Insets PADDING = new Insets(8, 6, 8, 6);
	static final Insets INNER_PADDING = new Insets(0);

	// CommandStackListener stackListener = new CommandStackListener() {
	// public void commandStackChanged(EventObject event) {
	// if (!GraphAnimation.captureLayout(getFigure()))
	// return;
	// while (GraphAnimation.step())
	// getFigure().getUpdateManager().performUpdate();
	// GraphAnimation.end();
	// }
	// };

	@Override
	protected IFigure createFigure() {
		TreeRoot treeBranch = new TreeRoot(new PageNode("Root"));
		return treeBranch;
		//return new SkillsDiagramFigure(this);
	}

	@Override
	protected List<ConnectableNode> getModelChildren() {
		return getModel().getChildren();
	}

	@Override
	public SkillsDiagram getModel() {
		return (SkillsDiagram) super.getModel();
	}

	public void contributeNodesToGraph(CompoundDirectedGraph graph, Subgraph s,
			Map map) {
		// GraphAnimation.recordInitialState(getContentPane());
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

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#activate()
	 */
	public void activate() {
		super.activate();
		// getViewer().getEditDomain().getCommandStack()
		// .addCommandStackListener(stackListener);
	}

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#deactivate()
	 */
	public void deactivate() {
		// getViewer().getEditDomain().getCommandStack()
		// .removeCommandStackListener(stackListener);
		super.deactivate();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
	 */
	public boolean isSelectable() {
		return false;
	}

	/**
	 * @see org.eclipse.gef.examples.flow.parts.ActivityPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
//		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
//				new SkillNodeEditPolicy());
		// installEditPolicy(EditPolicy.COMPONENT_ROLE, new
		// ActivityEditPolicy());
		// installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
		// new ActivityContainerHighlightEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new SkillContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new SkillsLayoutEditPolicy());
		// installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
		// new StructuredActivityDirectEditPolicy());
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		// these properties are fired when Shapes are added into or removed from
		// the ShapeDiagram instance and must cause a call of refreshChildren()
		// to update the diagram's contents.
		if (SkillsDiagram.CHILD_ADDED_PROP.equals(prop)
				|| SkillsDiagram.CHILD_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}
	}
	
	@Override
	public IFigure getContentPane() {
		// TODO Auto-generated method stub
		return ((TreeBranch)super.getContentPane()).getContentsPane();
	}
}
