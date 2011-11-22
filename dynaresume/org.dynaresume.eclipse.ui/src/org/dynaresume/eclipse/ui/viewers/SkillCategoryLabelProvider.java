package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class SkillCategoryLabelProvider extends LabelProvider {

	private static SkillCategoryLabelProvider instance;

	public static SkillCategoryLabelProvider getInstance() {
		synchronized (SkillCategoryLabelProvider.class) {
			if (instance == null) {
				instance = new SkillCategoryLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof SkillCategory) {
			return ((SkillCategory) element).getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof SkillCategory) {
			return ImageResources.getImage(ImageResources.IMG_SKILLS_16);
		}
		return super.getImage(element);
	}
}
