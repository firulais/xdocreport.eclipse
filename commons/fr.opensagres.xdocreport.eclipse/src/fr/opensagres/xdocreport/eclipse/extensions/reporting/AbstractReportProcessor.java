package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;

public abstract class AbstractReportProcessor implements IReportProcessor {

	public boolean canSupportFormat(IReportEngine reportEngine,
			IReportFormat format) throws IOException, ReportException {		
		return reportEngine.canSupportFormat(this, format);
	}
}
