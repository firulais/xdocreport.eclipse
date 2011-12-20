package org.dynaresume.project.eclipse.ui.viewer;

import org.dynaresume.domain.project.ProjectDescription;
import org.dynaresume.project.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class ProjectDescriptionLabelProvider extends LabelProvider {

	private static ProjectDescriptionLabelProvider instance;

	public static ProjectDescriptionLabelProvider getInstance() {
		synchronized (ProjectDescriptionLabelProvider.class) {
			if (instance == null) {
				instance = new ProjectDescriptionLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ProjectDescription) {
			return ((ProjectDescription) element).getDescription();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ProjectDescription) {
			return ImageResources.getImage(ImageResources.IMG_DESCRIPTIONS_16);
		}
		return super.getImage(element);
	}
}
