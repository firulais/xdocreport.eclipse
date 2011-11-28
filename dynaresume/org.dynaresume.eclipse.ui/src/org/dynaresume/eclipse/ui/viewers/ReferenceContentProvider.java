package org.dynaresume.eclipse.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReferenceContentProvider extends ArrayContentProvider {

	private static ReferenceContentProvider instance;

	public static ReferenceContentProvider getInstance() {
		synchronized (ReferenceContentProvider.class) {
			if (instance == null) {
				instance = new ReferenceContentProvider();
			}
			return instance;
		}
	}

}
