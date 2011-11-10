package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.io.IOException;
import java.io.OutputStream;

public interface IReportEngine {

	String getId();

	String getName();

	String getDescription();

	void generateReport(IReportLoader reportLoader, Object model,
			ReportConfiguration configuration, OutputStream out)
			throws IOException, ReportException;

	void writeReportSource(IReportLoader reportLoader, OutputStream out)
			throws IOException, ReportException;

	ReportMimeMapping getMimeMapping(IReportLoader reportLoader,
			ReportConfiguration configuration) throws IOException,
			ReportException;

	boolean canSupportFormat(IReportLoader reportLoader, IReportFormat format)
			throws IOException, ReportException;

	void unloadReport(IReportLoader reportLoader);

}
