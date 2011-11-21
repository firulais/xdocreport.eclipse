package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.PageNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures.TreeRoot;
import org.eclipse.draw2d.IFigure;

public class TreeDiagramEditPart extends TreeNodeEditPart {

	@Override
	protected IFigure createFigure() {
		TreeRoot treeBranch = new TreeRoot(new PageNode("Root"));
		return treeBranch;
	}
}
