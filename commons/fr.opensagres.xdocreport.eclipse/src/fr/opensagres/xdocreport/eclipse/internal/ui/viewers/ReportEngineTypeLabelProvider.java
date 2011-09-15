package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineType;

public class ReportEngineTypeLabelProvider extends LabelProvider {

	private static ReportEngineTypeLabelProvider instance;

	public static ReportEngineTypeLabelProvider getInstance() {
		synchronized (ReportEngineTypeLabelProvider.class) {
			if (instance == null) {
				instance = new ReportEngineTypeLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IReportEngineType) {
			return ((IReportEngineType) element).getName();
		}
		return super.getText(element);
	}
}
