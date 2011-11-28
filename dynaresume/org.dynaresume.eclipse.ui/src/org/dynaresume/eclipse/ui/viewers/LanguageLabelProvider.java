package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.Language;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class LanguageLabelProvider extends LabelProvider {

	private static LanguageLabelProvider instance;

	public static LanguageLabelProvider getInstance() {
		synchronized (LanguageLabelProvider.class) {
			if (instance == null) {
				instance = new LanguageLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Language) {
			Language language = ((Language) element);
			return language.getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		return ImageResources.getImage(ImageResources.IMG_LANGUAGES_16);
	}
}
