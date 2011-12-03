package org.dynaresume.eclipse.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class SkillCategoryContentProvider extends ArrayContentProvider {

	private static SkillCategoryContentProvider instance;

	public static SkillCategoryContentProvider getInstance() {
		synchronized (SkillCategoryContentProvider.class) {
			if (instance == null) {
				instance = new SkillCategoryContentProvider();
			}
			return instance;
		}
	}

}
