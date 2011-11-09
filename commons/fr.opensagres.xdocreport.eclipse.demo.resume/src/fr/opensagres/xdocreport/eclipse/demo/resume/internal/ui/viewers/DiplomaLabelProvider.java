package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ImageResources;

public class DiplomaLabelProvider extends LabelProvider {

	private static DiplomaLabelProvider instance;

	public static DiplomaLabelProvider getInstance() {
		synchronized (DiplomaLabelProvider.class) {
			if (instance == null) {
				instance = new DiplomaLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Diploma) {
			return ((Diploma) element).getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Diploma) {
			return ImageResources.getImage(ImageResources.IMG_DIPLOMA_16);
		}
		return super.getImage(element);
	}
}
