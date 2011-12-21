package org.dynaresume.project.eclipse.ui.viewer;

import org.dynaresume.domain.project.ProjectDescription;
import org.dynaresume.project.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ProjectDescriptionTreeLabelProvider extends LabelProvider {

	private static ProjectDescriptionTreeLabelProvider instance;

	public static ProjectDescriptionTreeLabelProvider getInstance() {
		synchronized (ProjectDescriptionTreeLabelProvider.class) {
			if (instance == null) {
				instance = new ProjectDescriptionTreeLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ProjectDescriptionTypeWrapper) {
			return ((ProjectDescriptionTypeWrapper) element).getType()
					.getLabel();
		}
		if (element instanceof ProjectDescription) {
			return ((ProjectDescription) element).getDescription();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ProjectDescriptionTypeWrapper) {
			return ImageResources.getImage(ImageResources.IMG_DESCRIPTION_16);
		}
		if (element instanceof ProjectDescription) {
			return ImageResources.getImage(ImageResources.IMG_BULLET_16);
		}
		return super.getImage(element);
	}
}
