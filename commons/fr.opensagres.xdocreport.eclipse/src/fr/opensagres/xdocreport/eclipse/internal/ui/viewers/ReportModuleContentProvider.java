package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReportModuleContentProvider extends ArrayContentProvider {

	private static ReportModuleContentProvider instance;

	public static ReportModuleContentProvider getInstance() {
		synchronized (ReportModuleContentProvider.class) {
			if (instance == null) {
				instance = new ReportModuleContentProvider();
			}
			return instance;
		}
	}

}
