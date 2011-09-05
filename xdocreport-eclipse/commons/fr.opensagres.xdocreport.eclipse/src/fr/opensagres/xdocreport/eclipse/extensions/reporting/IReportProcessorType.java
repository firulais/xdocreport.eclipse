package fr.opensagres.xdocreport.eclipse.extensions.reporting;

public interface IReportProcessorType {

	IReportProcessor getProcessor();
	
	IReportEngine getEngine();
}
