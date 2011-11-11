package org.dynaresume.eclipse.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ExperienceContentProvider extends ArrayContentProvider {

	private static ExperienceContentProvider instance;

	public static ExperienceContentProvider getInstance() {
		synchronized (ExperienceContentProvider.class) {
			if (instance == null) {
				instance = new ExperienceContentProvider();
			}
			return instance;
		}
	}

}
