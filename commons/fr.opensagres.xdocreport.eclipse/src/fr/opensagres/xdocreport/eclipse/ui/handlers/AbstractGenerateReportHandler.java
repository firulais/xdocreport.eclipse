package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;

public abstract class AbstractGenerateReportHandler extends
		AbstractContextHandler {

	@Override
	protected Object execute(ExecutionEvent event,
			ContextHandlerEvent contextEvent) throws ExecutionException {
		Object model = contextEvent.getModel();
		ReportConfiguration options = contextEvent.getReportOptions();

		IReportProcessorType processorType = contextEvent
				.getReportProcessorType();
		IReportProcessor processor = processorType.getProcessor();
		IReportEngine engine = processorType.getEngine();
		generateReport(event, contextEvent, processor, engine, options, model);
		return null;
	}

	protected abstract void generateReport(ExecutionEvent event,
			ContextHandlerEvent contextEvent, IReportProcessor processor,
			IReportEngine engine, ReportConfiguration options, Object model)
			throws ExecutionException;

}
