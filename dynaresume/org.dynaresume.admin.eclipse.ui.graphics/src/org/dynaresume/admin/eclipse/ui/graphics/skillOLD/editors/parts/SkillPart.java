package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors.model.GraphicalSkill;
import org.dynaresume.admin.eclipse.ui.graphics.tree.PageNode;
import org.dynaresume.admin.eclipse.ui.graphics.tree.TreeBranch;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

public class SkillPart extends ConnectableNodePart {

	@Override
	protected IFigure createFigure() {
		// if (getModel().getParent() == null) {
		// return new TreeRoot(new PageNode("Graph Root"));
		// }
		TreeBranch treeBranch = new TreeBranch(new PageNode("Child 1"));
		treeBranch.setStyle(TreeBranch.STYLE_NORMAL);
		return treeBranch;

		// return new SkillFigure();
	}

	@Override
	public GraphicalSkill getModel() {
		return (GraphicalSkill) super.getModel();
	}

	@Override
	public TreeBranch getFigure() {
		return (TreeBranch) super.getFigure();
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new BottomAnchor(getFigure(), getAnchorOffset());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new BottomAnchor(getFigure(), getAnchorOffset());
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new TopAnchor(getFigure(), getAnchorOffset());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new TopAnchor(getFigure(), getAnchorOffset());
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (GraphicalSkill.LABEL_PROP.equals(prop))
			refreshVisuals();
		else if (GraphicalSkill.SOURCE_CONNECTIONS_PROP.equals(prop)) {
			//getModel().
		} else
			super.propertyChange(evt);

		// Causes Graph to re-layout
		((GraphicalEditPart) (getViewer().getContents())).getFigure()
				.revalidate();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		GraphicalSkill skill = getModel();
		((PageNode) getFigure().getNode()).setText(skill.getLabel());
	}

	@Override
	public IFigure getContentPane() {
		// TODO Auto-generated method stub
		return ((TreeBranch) super.getContentPane()).getContentsPane();
	}

	@Override
	protected List getModelChildren() {
		// TODO Auto-generated method stub
		return getModel().getChildren();
	}

}
