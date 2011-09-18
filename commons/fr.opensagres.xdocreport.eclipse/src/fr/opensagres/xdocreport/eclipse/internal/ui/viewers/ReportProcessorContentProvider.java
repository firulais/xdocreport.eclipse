package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReportProcessorContentProvider extends ArrayContentProvider {

	private static ReportProcessorContentProvider instance;

	public static ReportProcessorContentProvider getInstance() {
		synchronized (ReportProcessorContentProvider.class) {
			if (instance == null) {
				instance = new ReportProcessorContentProvider();
			}
			return instance;
		}
	}

}
