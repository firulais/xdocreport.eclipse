package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;
import java.io.InputStream;

public interface IReportProcessor {

	String getReportId();

	InputStream getSourceStream();

	boolean canSupportFormat(IReportEngine reportEngine, IReportFormat format)
			throws IOException, ReportException;

}
