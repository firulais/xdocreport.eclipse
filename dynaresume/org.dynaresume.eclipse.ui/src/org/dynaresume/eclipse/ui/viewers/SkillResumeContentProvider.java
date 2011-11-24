package org.dynaresume.eclipse.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class SkillResumeContentProvider extends ArrayContentProvider {

	private static SkillResumeContentProvider instance;

	public static SkillResumeContentProvider getInstance() {
		synchronized (SkillResumeContentProvider.class) {
			if (instance == null) {
				instance = new SkillResumeContentProvider();
			}
			return instance;
		}
	}

}
