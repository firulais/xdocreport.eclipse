package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;

public class ReportModuleLabelProvider extends LabelProvider {

	private static ReportModuleLabelProvider instance;

	public static ReportModuleLabelProvider getInstance() {
		synchronized (ReportModuleLabelProvider.class) {
			if (instance == null) {
				instance = new ReportModuleLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IReportModule) {
			return ((IReportModule)element).getName();
		}
		return super.getText(element);
	}
}
