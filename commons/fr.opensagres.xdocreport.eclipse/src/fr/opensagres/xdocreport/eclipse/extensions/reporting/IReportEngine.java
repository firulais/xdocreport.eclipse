package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;
import java.io.OutputStream;

public interface IReportEngine {

	void process(IReportProcessor processor, Object model,
			ReportConfiguration options, OutputStream out) throws IOException,
			ReportException;

	ReportMimeMapping getMimeMapping(IReportProcessor processor,
			ReportConfiguration options) throws IOException, ReportException;

	boolean canSupportFormat(IReportProcessor processor, IReportFormat format)
			throws IOException, ReportException;

}
