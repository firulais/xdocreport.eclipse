package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.SkillsContainerFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class SkillsContainerPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		return new SkillsContainerFigure();
	}

	@Override
	protected void createEditPolicies() {

	}

}
