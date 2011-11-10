package fr.opensagres.xdocreport.eclipse.reporting.ui.internal.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReportLoaderContentProvider extends ArrayContentProvider {

	private static ReportLoaderContentProvider instance;

	public static ReportLoaderContentProvider getInstance() {
		synchronized (ReportLoaderContentProvider.class) {
			if (instance == null) {
				instance = new ReportLoaderContentProvider();
			}
			return instance;
		}
	}

}
