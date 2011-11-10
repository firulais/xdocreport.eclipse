package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import fr.opensagres.xdocreport.eclipse.reporting.core.IReportEngine;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.core.ReportConfiguration;

public abstract class AbstractGenerateReportHandler extends
		AbstractContextHandler {

	@Override
	protected Object execute(ExecutionEvent event,
			ContextHandlerEvent contextEvent) throws ExecutionException {
		Object model = contextEvent.getModel();
		ReportConfiguration options = contextEvent.getReportConfiguration();

		IReportLoader reportLoader = contextEvent
				.getReportLoader();
		IReportEngine engine = reportLoader.getProcessor().getEngine();
		generateReport(event, contextEvent, reportLoader, engine, options, model);
		return null;
	}

	protected abstract void generateReport(ExecutionEvent event,
			ContextHandlerEvent contextEvent, IReportLoader reportLoader,
			IReportEngine engine, ReportConfiguration options, Object model)
			throws ExecutionException;

}
