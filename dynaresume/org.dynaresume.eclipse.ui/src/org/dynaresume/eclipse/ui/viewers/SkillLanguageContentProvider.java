package org.dynaresume.eclipse.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class SkillLanguageContentProvider extends ArrayContentProvider {

	private static SkillLanguageContentProvider instance;

	public static SkillLanguageContentProvider getInstance() {
		synchronized (SkillLanguageContentProvider.class) {
			if (instance == null) {
				instance = new SkillLanguageContentProvider();
			}
			return instance;
		}
	}

}
