package org.dynaresume.project.eclipse.ui.viewer;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ProjectDescriptionContentProvider extends ArrayContentProvider {

	private static ProjectDescriptionContentProvider instance;

	public static ProjectDescriptionContentProvider getInstance() {
		synchronized (ProjectDescriptionContentProvider.class) {
			if (instance == null) {
				instance = new ProjectDescriptionContentProvider();
			}
			return instance;
		}
	}

}
