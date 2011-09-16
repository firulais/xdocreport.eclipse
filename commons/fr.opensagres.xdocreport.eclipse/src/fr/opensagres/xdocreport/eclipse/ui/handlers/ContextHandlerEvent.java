package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.swt.widgets.Event;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;

public class ContextHandlerEvent extends Event {

	private final IReportModuleEntry sourceEntry;
	private final Object model;
	private final ReportConfiguration configuration;
	private final IReportLoader reportLoader;

	public ContextHandlerEvent(IReportModuleEntry sourceEntry, Object model,
			IReportLoader reportLoader, ReportConfiguration configuration) {
		this.sourceEntry = sourceEntry;
		this.model = model;
		this.configuration = configuration;
		this.reportLoader = reportLoader;
	}

	public ContextHandlerEvent(IReportModuleEntry sourceEntry, Object model) {
		this(sourceEntry, model, null, null);
	}

	public IReportModuleEntry getSourceEntry() {
		return sourceEntry;
	}

	public Object getModel() {
		return model;
	}

	public ReportConfiguration getReportConfiguration() {
		return configuration;
	}

	public IReportLoader getReportLoader() {
		return reportLoader;
	}
}
