package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class EducationContentProvider extends ArrayContentProvider {

	private static EducationContentProvider instance;

	public static EducationContentProvider getInstance() {
		synchronized (EducationContentProvider.class) {
			if (instance == null) {
				instance = new EducationContentProvider();
			}
			return instance;
		}
	}

}
