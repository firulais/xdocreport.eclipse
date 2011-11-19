package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.TreeDiagram;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.TreeNode;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.TreeNodeEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class TreeNodeEditPartFactory implements EditPartFactory {

	public static final EditPartFactory INSTANCE = new TreeNodeEditPartFactory();

	public EditPart createEditPart(EditPart context, Object modelElement) {
		// get EditPart for model element
		EditPart part = getPartForElement(modelElement);
		// store model element in EditPart
		part.setModel(modelElement);
		return part;
	}

	/**
	 * Maps an object to an EditPart.
	 * 
	 * @throws RuntimeException
	 *             if no match was found (programming error)
	 */
	private EditPart getPartForElement(Object modelElement) {
		if (modelElement instanceof TreeDiagram) {
			return new TreeDiagramEditPart();
		}
		if (modelElement instanceof TreeNode) {
			return new TreeNodeEditPart();
		}
		throw new RuntimeException("Can't create part for model element: "
				+ ((modelElement != null) ? modelElement.getClass().getName()
						: "null"));
	}

}
