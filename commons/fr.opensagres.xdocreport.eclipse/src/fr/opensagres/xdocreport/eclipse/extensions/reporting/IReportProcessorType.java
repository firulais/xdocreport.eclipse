package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.List;

public interface IReportProcessorType {

	IReportProcessor getProcessor();
	
	IReportEngine getEngine();
	
	List<IReportFormat> getSupportedFormats();

	boolean canSupportFormat(IReportFormat format);
	
}
