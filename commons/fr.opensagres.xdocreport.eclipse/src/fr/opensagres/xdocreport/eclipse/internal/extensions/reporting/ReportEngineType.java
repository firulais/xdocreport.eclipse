package fr.opensagres.xdocreport.eclipse.internal.extensions.reporting;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineType;

public class ReportEngineType implements IReportEngineType {

	private final String id;
	private String name;
	private String description;
	private final IReportEngine engine;

	public ReportEngineType(String id, IReportEngine engine) {
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
