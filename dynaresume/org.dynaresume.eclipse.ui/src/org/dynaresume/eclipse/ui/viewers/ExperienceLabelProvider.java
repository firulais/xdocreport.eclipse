package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.Experience;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class ExperienceLabelProvider extends LabelProvider {

	private static ExperienceLabelProvider instance;

	public static ExperienceLabelProvider getInstance() {
		synchronized (ExperienceLabelProvider.class) {
			if (instance == null) {
				instance = new ExperienceLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Experience) {
			return ((Experience) element).getTitle();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Experience) {
			return ImageResources.getImage(ImageResources.IMG_EXPERIENCES_16);
		}
		return super.getImage(element);
	}
}
