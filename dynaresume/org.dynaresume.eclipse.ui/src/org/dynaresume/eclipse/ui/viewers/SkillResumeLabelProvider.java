package org.dynaresume.eclipse.ui.viewers;

import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.ImageResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class SkillResumeLabelProvider extends LabelProvider {

	private static SkillResumeLabelProvider instance;

	public static SkillResumeLabelProvider getInstance() {
		synchronized (SkillResumeLabelProvider.class) {
			if (instance == null) {
				instance = new SkillResumeLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof SkillResume) {
			if (isFree((SkillResume) element)) {
				return ((SkillResume) element).getFreeSkill();
			}
			return ((SkillResume) element).getSkill().getName();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof SkillResume) {
			if (isFree((SkillResume) element)) {
				return ImageResources
						.getImage(ImageResources.IMG_SKILL_RESUME_FREE_16);
			}
			return ImageResources.getImage(ImageResources.IMG_SKILL_RESUME_16);
		}
		return super.getImage(element);
	}

	private boolean isFree(SkillResume skill) {
		return skill.getFreeSkill() != null;
	}
}
