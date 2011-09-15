package fr.opensagres.xdocreport.eclipse.internal.extensions.reporting;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineType;

public class ReportEngineType implements IReportEngineType {

	private final String id;
	private final String name;
	private String description;
	private final IReportEngine engine;

	public ReportEngineType(String id, String name, IReportEngine engine) {
		this.id = id;
		this.name = name;
		this.engine = engine;
	}

	public String getId() {
		return id;
	}

	public IReportEngine getEngine() {
		return engine;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
