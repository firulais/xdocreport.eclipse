package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class ReportEngineContentProvider extends ArrayContentProvider {

	private static ReportEngineContentProvider instance;

	public static ReportEngineContentProvider getInstance() {
		synchronized (ReportEngineContentProvider.class) {
			if (instance == null) {
				instance = new ReportEngineContentProvider();
			}
			return instance;
		}
	}

}
