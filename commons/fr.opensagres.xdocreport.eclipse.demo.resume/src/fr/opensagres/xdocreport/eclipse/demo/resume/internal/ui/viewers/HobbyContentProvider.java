package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class HobbyContentProvider extends ArrayContentProvider {

	private static HobbyContentProvider instance;

	public static HobbyContentProvider getInstance() {
		synchronized (HobbyContentProvider.class) {
			if (instance == null) {
				instance = new HobbyContentProvider();
			}
			return instance;
		}
	}

}
