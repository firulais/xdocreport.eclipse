package fr.opensagres.xdocreport.eclipse.internal.extensions.reporting;

import java.util.ArrayList;
import java.util.List;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormat;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;

public class ReportProcessorType implements IReportProcessorType {

	private final String id;
	private String name;
	private String description;
	private final IReportProcessor processor;
	private final IReportEngine engine;
	private final List<IReportFormat> supportedFormats;

	public ReportProcessorType(String id, IReportProcessor processor,
			IReportEngine engine) {
		this.id = id;
		this.processor = processor;
		this.engine = engine;
		this.supportedFormats = new ArrayList<IReportFormat>();
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

	public List<IReportFormat> getSupportedFormats() {
		return supportedFormats;
	}

	public boolean canSupportFormat(IReportFormat format) {
		try {
			return processor.canSupportFormat(getEngine(), format);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
