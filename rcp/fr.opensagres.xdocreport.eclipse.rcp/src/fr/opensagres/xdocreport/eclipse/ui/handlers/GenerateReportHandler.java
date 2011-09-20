package fr.opensagres.xdocreport.eclipse.ui.handlers;

import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportMimeMapping;

public class GenerateReportHandler extends AbstractGenerateReportHandler {

	@Override
	protected void generateReport(ExecutionEvent event,
			ContextHandlerEvent contextEvent, IReportLoader reportLoader,
			IReportEngine engine, ReportConfiguration configuration,
			Object model) throws ExecutionException {
		try {
			ReportMimeMapping mimeMapping = engine.getMimeMapping(reportLoader,
					configuration);
			File file = mimeMapping.formatFile(configuration.getTempBaseDir(),
					reportLoader);
			FileOutputStream out = new FileOutputStream(file);
			
			if (model == null) {
				// model is null, open the report source
				engine.writeReportSource(reportLoader, out);
			}
			else {
				// model is filled, generate the report and open the generated report
				engine.generateReport(reportLoader, model, configuration, out);
			}
			try {
				ContextHandlerUtils.openSystemExternalEditor(event, file, false);
			} catch (PartInitException e) {
				throw new RuntimeException(e);
			}

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}
