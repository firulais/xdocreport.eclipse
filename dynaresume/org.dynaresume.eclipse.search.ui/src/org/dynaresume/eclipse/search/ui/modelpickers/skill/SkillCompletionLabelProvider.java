package org.dynaresume.eclipse.search.ui.modelpickers.skill;

import org.dynaresume.domain.hr.Skill;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.CompletionLabelProvider;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.ICompletionLabelProvider;
import org.eclipse.swt.graphics.Image;

public class SkillCompletionLabelProvider extends CompletionLabelProvider {

	private static final ICompletionLabelProvider INSTANCE = new SkillCompletionLabelProvider();

	public static ICompletionLabelProvider getInstance() {
		return INSTANCE;
	}

	public String getContent(Object element) {
		return ((Skill) element).getName();
	}

	@Override
	public Image getImage(Object element) {
		return null;
		//return ImageResources.getImage(ImageResources.IMG_CLIENT_16_16);
	}

}
