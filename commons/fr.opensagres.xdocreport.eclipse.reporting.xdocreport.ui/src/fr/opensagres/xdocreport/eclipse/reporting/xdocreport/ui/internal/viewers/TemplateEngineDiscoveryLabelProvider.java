package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.ui.internal.viewers;

import org.eclipse.jface.viewers.LabelProvider;

import fr.opensagres.xdocreport.template.discovery.ITemplateEngineDiscovery;

public class TemplateEngineDiscoveryLabelProvider extends LabelProvider {

	private static TemplateEngineDiscoveryLabelProvider instance;

	public static TemplateEngineDiscoveryLabelProvider getInstance() {
		synchronized (TemplateEngineDiscoveryLabelProvider.class) {
			if (instance == null) {
				instance = new TemplateEngineDiscoveryLabelProvider();
			}
			return instance;
		}
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ITemplateEngineDiscovery) {
			return ((ITemplateEngineDiscovery) element).getId();
		}
		return super.getText(element);
	}
}
