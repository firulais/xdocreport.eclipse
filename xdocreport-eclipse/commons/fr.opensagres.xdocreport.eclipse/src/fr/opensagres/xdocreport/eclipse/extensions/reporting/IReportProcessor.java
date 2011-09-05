package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.InputStream;

public interface IReportProcessor {

	String getReportId();

	InputStream getSourceStream();

}
