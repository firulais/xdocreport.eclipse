package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import java.beans.PropertyChangeEvent;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.SkillFigure;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.GraphicalSkill;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

public class SkillPart extends ConnectableNodePart {

	@Override
	protected IFigure createFigure() {
		return new SkillFigure();
	}

	@Override
	public GraphicalSkill getModel() {
		return (GraphicalSkill) super.getModel();
	}

	@Override
	public SkillFigure getFigure() {
		return (SkillFigure) super.getFigure();
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
		else
			super.propertyChange(evt);

		// Causes Graph to re-layout
		((GraphicalEditPart) (getViewer().getContents())).getFigure()
				.revalidate();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		GraphicalSkill skill = getModel();
		getFigure().setLabel(skill.getLabel());
	}

}
