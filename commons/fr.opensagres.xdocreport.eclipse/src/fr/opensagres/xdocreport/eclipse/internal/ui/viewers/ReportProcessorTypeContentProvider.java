package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReportProcessorTypeContentProvider extends ArrayContentProvider {

	private static ReportProcessorTypeContentProvider instance;

	public static ReportProcessorTypeContentProvider getInstance() {
		synchronized (ReportProcessorTypeContentProvider.class) {
			if (instance == null) {
				instance = new ReportProcessorTypeContentProvider();
			}
			return instance;
		}
	}

}
