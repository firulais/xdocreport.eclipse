package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.internal.ui.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;

public class TemplateEngineDiscoveryContentProvider extends ArrayContentProvider {

	private static TemplateEngineDiscoveryContentProvider instance;

	public static TemplateEngineDiscoveryContentProvider getInstance() {
		synchronized (TemplateEngineDiscoveryContentProvider.class) {
			if (instance == null) {
				instance = new TemplateEngineDiscoveryContentProvider();
			}
			return instance;
		}
	}

}
