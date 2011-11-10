package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.util.Collection;

public interface IReportEngineRegistry {

	IReportEngine getEngine(String id);

	Collection<IReportEngine> getEngines();

}
