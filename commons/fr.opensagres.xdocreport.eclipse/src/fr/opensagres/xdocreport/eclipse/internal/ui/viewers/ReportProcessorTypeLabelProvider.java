package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;

public class ReportProcessorTypeLabelProvider extends LabelProvider {

	private static ReportProcessorTypeLabelProvider instance;

	public static ReportProcessorTypeLabelProvider getInstance() {
		synchronized (ReportProcessorTypeLabelProvider.class) {
			if (instance == null) {
				instance = new ReportProcessorTypeLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IReportProcessorType) {
			return ((IReportProcessorType) element).getName();
		}
		return super.getText(element);
	}
}
