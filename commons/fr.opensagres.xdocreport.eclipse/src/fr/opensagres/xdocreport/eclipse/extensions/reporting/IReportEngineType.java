package fr.opensagres.xdocreport.eclipse.extensions.reporting;

public interface IReportEngineType {

	String getId();
	
	String getName();

	String getDescription();

	IReportEngine getEngine();
}
