package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.Collection;

public interface IReportEngineRegistry {

	IReportEngine getEngine(String id);

	Collection<IReportEngine> getEngines();

}
