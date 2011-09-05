package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.swt.widgets.Event;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;

public class ContextHandlerEvent extends Event {

	private final IReportModuleEntry sourceEntry;
	private final Object model;
	private final ReportConfiguration options;
	private final IReportProcessorType processorType;

	public ContextHandlerEvent(IReportModuleEntry sourceEntry, Object model,
			IReportProcessorType processorType, ReportConfiguration options) {
		this.sourceEntry = sourceEntry;
		this.model = model;
		this.options = options;
		this.processorType = processorType;
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

	public ReportConfiguration getReportOptions() {
		return options;
	}

	public IReportProcessorType getReportProcessorType() {
		return processorType;
	}
}
