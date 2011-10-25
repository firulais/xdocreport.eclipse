package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Hobby;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ImageResources;

public class HobbyLabelProvider extends LabelProvider {

	private static HobbyLabelProvider instance;

	public static HobbyLabelProvider getInstance() {
		synchronized (HobbyLabelProvider.class) {
			if (instance == null) {
				instance = new HobbyLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Hobby) {
			return ((Hobby) element).getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Hobby) {
			return ImageResources.getImage(ImageResources.IMG_HOBBIES_16);
		}
		return super.getImage(element);
	}
}
