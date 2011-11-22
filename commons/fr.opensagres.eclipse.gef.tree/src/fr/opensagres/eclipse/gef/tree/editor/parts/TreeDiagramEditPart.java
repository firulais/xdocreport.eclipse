package fr.opensagres.eclipse.gef.tree.editor.parts;

import org.eclipse.draw2d.IFigure;

import fr.opensagres.eclipse.gef.tree.editor.figures.PageNode;
import fr.opensagres.eclipse.gef.tree.editor.figures.TreeRoot;

public class TreeDiagramEditPart extends TreeNodeEditPart {

	@Override
	protected IFigure createFigure() {
		TreeRoot treeBranch = new TreeRoot(new PageNode("Root"));
		return treeBranch;
	}
}
