package fr.opensagres.xdocreport.eclipse.internal.extensions.reporting;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;

public class ReportProcessorType implements IReportProcessorType {

	private final String id;
	private String name;
	private String description;
	private final IReportProcessor processor;
	private final IReportEngine engine;

	public ReportProcessorType(String id, IReportProcessor processor,
			IReportEngine engine) {
		this.id = id;
		this.processor = processor;
		this.engine = engine;
	}

	public String getId() {
		return id;
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

	public IReportProcessor getProcessor() {
		return processor;
	}

	public IReportEngine getEngine() {
		return engine;
	}

}
