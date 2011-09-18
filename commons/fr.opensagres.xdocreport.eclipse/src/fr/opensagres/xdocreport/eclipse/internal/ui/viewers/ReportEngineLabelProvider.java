package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;

public class ReportEngineLabelProvider extends LabelProvider {

	private static ReportEngineLabelProvider instance;

	public static ReportEngineLabelProvider getInstance() {
		synchronized (ReportEngineLabelProvider.class) {
			if (instance == null) {
				instance = new ReportEngineLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IReportEngine) {
			return ((IReportEngine) element).getName();
		}
		return super.getText(element);
	}
}
