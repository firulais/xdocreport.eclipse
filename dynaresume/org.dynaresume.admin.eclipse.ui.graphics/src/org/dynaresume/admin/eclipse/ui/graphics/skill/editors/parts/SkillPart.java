package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.SkillFigure;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.GraphicalSkill;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

public class SkillPart extends ConnectableNodePart {

	@Override
	protected IFigure createFigure() {
		return new SkillFigure();
	}

	@Override
	protected void createEditPolicies() {

	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		int x = new Double(Math.random() * 400).intValue();
		int y = new Double(Math.random() * 400).intValue();

		Rectangle bounds = new Rectangle(x, y, 50, 50);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), bounds);

		GraphicalSkill skill = getModel();
		getFigure().setLabel(skill.getLabel());
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
		return new EllipseAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new EllipseAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new EllipseAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new EllipseAnchor(getFigure());
	}
}
