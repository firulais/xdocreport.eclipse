package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import org.dynaresume.admin.eclipse.ui.graphics.tree.PageNode;
import org.dynaresume.admin.eclipse.ui.graphics.tree.TreeRoot;
import org.eclipse.draw2d.IFigure;

public class TreeDiagramEditPart extends TreeNodeEditPart {

	@Override
	protected IFigure createFigure() {
		TreeRoot treeBranch = new TreeRoot(new PageNode("Root"));
		return treeBranch;
		// return new SkillsDiagramFigure(this);
	}
}
