package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReportEngineTypeContentProvider extends ArrayContentProvider {

	private static ReportEngineTypeContentProvider instance;

	public static ReportEngineTypeContentProvider getInstance() {
		synchronized (ReportEngineTypeContentProvider.class) {
			if (instance == null) {
				instance = new ReportEngineTypeContentProvider();
			}
			return instance;
		}
	}

}
