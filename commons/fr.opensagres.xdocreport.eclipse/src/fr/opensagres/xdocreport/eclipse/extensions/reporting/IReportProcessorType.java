package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.List;

public interface IReportProcessorType {

	String getId();
	
	String getName();
	
	IReportProcessor getProcessor();
	
	IReportEngine getEngine();
	
	List<IReportFormat> getSupportedFormats();

	boolean canSupportFormat(IReportFormat format);
	
}
