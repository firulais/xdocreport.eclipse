package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.Hobby;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


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
