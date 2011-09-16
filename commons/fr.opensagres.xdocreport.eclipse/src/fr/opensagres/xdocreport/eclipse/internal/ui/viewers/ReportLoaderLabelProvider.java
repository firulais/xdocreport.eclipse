package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;

public class ReportLoaderLabelProvider extends LabelProvider {

	private static ReportLoaderLabelProvider instance;

	public static ReportLoaderLabelProvider getInstance() {
		synchronized (ReportLoaderLabelProvider.class) {
			if (instance == null) {
				instance = new ReportLoaderLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IReportLoader) {
			return ((IReportLoader) element).getReportId();
		}
		return super.getText(element);
	}
}
