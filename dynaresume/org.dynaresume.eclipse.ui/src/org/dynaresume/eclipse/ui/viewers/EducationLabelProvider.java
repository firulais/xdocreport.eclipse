package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.Education;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class EducationLabelProvider extends LabelProvider {

	private static EducationLabelProvider instance;

	public static EducationLabelProvider getInstance() {
		synchronized (EducationLabelProvider.class) {
			if (instance == null) {
				instance = new EducationLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Education) {
			Education education = ((Education) element);
			StringBuilder text = new StringBuilder();
			text.append(education.getEndDateYear());
			text.append(" - ");
			text.append(education.getLabel());
			return text.toString();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Education) {
			return ImageResources.getImage(ImageResources.IMG_EDUCATION_16);
		}
		return super.getImage(element);
	}
}
