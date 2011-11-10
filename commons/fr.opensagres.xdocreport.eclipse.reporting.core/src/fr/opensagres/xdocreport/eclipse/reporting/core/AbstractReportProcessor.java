package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReportProcessor implements IReportProcessor {

	private String id;
	private String name;
	private String description;
	private IReportEngine engine;
	private List<IReportLoader> reportLoaders;
	private final List<IReportFormat> supportedFormats;

	public AbstractReportProcessor() {
		this.supportedFormats = new ArrayList<IReportFormat>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<IReportLoader> getReportLoaders() {
		return reportLoaders;
	}

	public void setReportLoaders(List<IReportLoader> reportLoaders) {
		this.reportLoaders = reportLoaders;
	}

	public IReportEngine getEngine() {
		return engine;
	}
	
	public void setEngine(IReportEngine engine) {
		this.engine = engine;
	}
	
	public List<IReportFormat> getSupportedFormats() {
		return supportedFormats;
	}

	public boolean canSupportFormat(IReportFormat format) {
		try {
			for (IReportLoader reportLoader : reportLoaders) {
				if (reportLoader.canSupportFormat(format)) {
					return true;
				}
			}
			return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
}
