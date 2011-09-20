package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;
import java.io.InputStream;

public interface IReportLoader {

	String getReportId();
	
	String getName();
	
	String getDescription();

	InputStream getSourceStream() throws IOException, ReportException;

	void setSourceStream(InputStream sourceStream) throws IOException, ReportException ;
	
	IReportProcessor getProcessor();

	boolean canSupportFormat(IReportFormat format) throws IOException,
			ReportException;

	

}
