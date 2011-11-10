package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.ui.internal;

import java.util.Collection;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.xdocreport.eclipse.reporting.ui.editors.GenericReportLoaderDetailsPage;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.ui.internal.viewers.TemplateEngineDiscoveryContentProvider;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.ui.internal.viewers.TemplateEngineDiscoveryLabelProvider;
import fr.opensagres.xdocreport.template.discovery.ITemplateEngineDiscovery;
import fr.opensagres.xdocreport.template.registry.TemplateEngineRegistry;

public class XDocReportLoaderDetailsPage extends GenericReportLoaderDetailsPage {

	@Override
	protected void createSpecificContent(FormToolkit toolkit, Composite parent) {
		// Template Engine
		toolkit.createLabel(parent,
				Messages.XDocReportLoaderDetailsPage_templateEngine_label);

		Collection<ITemplateEngineDiscovery> templateEngineDiscoveries = TemplateEngineRegistry
				.getRegistry().getTemplateEnginesDiscoveryCache().values();

		Combo templateEngineCombo = new Combo(parent, SWT.NONE);
		templateEngineCombo
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final ComboViewer processorViewer = new ComboViewer(templateEngineCombo);
		processorViewer.setContentProvider(TemplateEngineDiscoveryContentProvider
				.getInstance());
		processorViewer.setLabelProvider(TemplateEngineDiscoveryLabelProvider
				.getInstance());
		processorViewer.setInput(templateEngineDiscoveries);
		if (templateEngineDiscoveries.size() > 0) {
			templateEngineCombo.select(0);
		}
	}
	
	
	@Override
	protected void update() {		
		super.update();
		if (getReportLoader() == null) {
			return;
		}
		//getReportLoader().setTemplateEngineKind(templateEngineKind);
	}
	
	@Override
	public XDocReportLoader getReportLoader() {
		return (XDocReportLoader)super.getReportLoader();
	}
}
