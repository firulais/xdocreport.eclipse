package org.dynaresume.admin.eclipse.ui.graphics.skill.editors.figures;

import org.dynaresume.admin.eclipse.ui.graphics.internal.ImageResources;
import org.eclipse.draw2d.Label;

public class SkillFigure extends Label {

	public SkillFigure() {
		super.setIcon(ImageResources.getImage(ImageResources.IMG_SKILLS_16));
	}

	public void setLabel(String name) {
		super.setText(name);
	}

}
