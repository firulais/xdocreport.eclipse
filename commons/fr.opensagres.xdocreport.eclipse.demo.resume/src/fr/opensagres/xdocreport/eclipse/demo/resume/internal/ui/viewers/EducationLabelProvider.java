package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Education;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ImageResources;

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
			return ((Education) element).getLabel();
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
