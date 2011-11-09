package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class DiplomaContentProvider extends ArrayContentProvider {

	private static DiplomaContentProvider instance;

	public static DiplomaContentProvider getInstance() {
		synchronized (DiplomaContentProvider.class) {
			if (instance == null) {
				instance = new DiplomaContentProvider();
			}
			return instance;
		}
	}

}
