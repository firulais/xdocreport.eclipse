package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.Reference;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class ReferenceLabelProvider extends LabelProvider {

	private static ReferenceLabelProvider instance;

	public static ReferenceLabelProvider getInstance() {
		synchronized (ReferenceLabelProvider.class) {
			if (instance == null) {
				instance = new ReferenceLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Reference) {
			return ((Reference) element).getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Reference) {
			return ImageResources.getImage(ImageResources.IMG_REFERENCES_16);
		}
		return super.getImage(element);
	}
}
