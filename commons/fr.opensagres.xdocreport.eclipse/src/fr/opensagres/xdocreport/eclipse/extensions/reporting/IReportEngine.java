package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface IReportEngine {

	void process(IReportProcessor processor, Object model,
			ReportConfiguration options, OutputStream out) throws IOException,
			ReportException;

	ReportMimeMapping getMimeMapping(IReportProcessor processor, ReportConfiguration options)
			throws IOException, ReportException;

	List<ReportFormat> getSupportedFormat();

}
