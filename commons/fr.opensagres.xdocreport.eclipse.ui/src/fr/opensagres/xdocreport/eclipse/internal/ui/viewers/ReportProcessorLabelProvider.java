package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.reporting.core.IReportProcessor;

public class ReportProcessorLabelProvider extends LabelProvider {

	private static ReportProcessorLabelProvider instance;

	public static ReportProcessorLabelProvider getInstance() {
		synchronized (ReportProcessorLabelProvider.class) {
			if (instance == null) {
				instance = new ReportProcessorLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IReportProcessor) {
			return ((IReportProcessor) element).getName();
		}
		return super.getText(element);
	}
}
