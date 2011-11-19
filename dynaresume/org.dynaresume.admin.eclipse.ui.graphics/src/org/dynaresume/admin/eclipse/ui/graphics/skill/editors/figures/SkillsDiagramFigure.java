package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.GraphLayoutManager;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.SkillsDiagramPart;
import org.eclipse.draw2d.Figure;

public class SkillsDiagramFigure extends Figure {

	public SkillsDiagramFigure(SkillsDiagramPart diagram) {
		super.setLayoutManager(new GraphLayoutManager(diagram));
	}
}
