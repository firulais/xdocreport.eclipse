package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.Language;
import org.dynaresume.domain.hr.SkillLanguage;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class SkillLanguageLabelProvider extends LabelProvider {

	private static SkillLanguageLabelProvider instance;

	public static SkillLanguageLabelProvider getInstance() {
		synchronized (SkillLanguageLabelProvider.class) {
			if (instance == null) {
				instance = new SkillLanguageLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof SkillLanguage) {
			Language language = ((SkillLanguage) element).getLanguage();
			if (language != null) {
				return language.getLabel();
			}
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		return ImageResources.getImage(ImageResources.IMG_LANGUAGES_16);
	}
}
