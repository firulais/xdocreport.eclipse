package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.util.Collection;

public interface IReportEngineRegistry {

	IReportEngine getEngine(String id);

	IReportEngineType getEngineType(String id);

	Collection<IReportEngineType> getEngineTypes();

}
