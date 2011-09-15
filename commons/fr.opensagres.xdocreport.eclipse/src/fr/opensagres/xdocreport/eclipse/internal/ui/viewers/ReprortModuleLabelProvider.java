package fr.opensagres.xdocreport.eclipse.internal.ui.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;

public class ReprortModuleLabelProvider extends LabelProvider {

	private static ReprortModuleLabelProvider instance;

	public static ReprortModuleLabelProvider getInstance() {
		synchronized (ReprortModuleLabelProvider.class) {
			if (instance == null) {
				instance = new ReprortModuleLabelProvider();
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
