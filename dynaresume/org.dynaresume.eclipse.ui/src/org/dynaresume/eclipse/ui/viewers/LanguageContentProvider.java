package org.dynaresume.eclipse.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class LanguageContentProvider extends ArrayContentProvider {

	private static LanguageContentProvider instance;

	public static LanguageContentProvider getInstance() {
		synchronized (LanguageContentProvider.class) {
			if (instance == null) {
				instance = new LanguageContentProvider();
			}
			return instance;
		}
	}

}
