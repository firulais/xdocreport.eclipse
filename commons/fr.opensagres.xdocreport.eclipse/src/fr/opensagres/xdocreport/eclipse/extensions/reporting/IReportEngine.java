package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;
import java.io.OutputStream;

public interface IReportEngine {

	void process(IReportLoader reportLoader, Object model,
			ReportConfiguration options, OutputStream out) throws IOException,
			ReportException;

	ReportMimeMapping getMimeMapping(IReportLoader reportLoader,
			ReportConfiguration options) throws IOException, ReportException;

	boolean canSupportFormat(IReportLoader reportLoader, IReportFormat format)
			throws IOException, ReportException;

}
