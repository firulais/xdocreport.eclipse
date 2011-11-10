package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.io.IOException;
import java.io.OutputStream;

import fr.opensagres.xdocreport.commons.utils.IOUtils;

public abstract class AbstractReportEngine implements IReportEngine {

	private static final String REPORT_MIME_MAPPING_KEY = "___ReportMimeMapping";

	private String id;
	private String name;
	private String description;

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

	protected String getMimeMappingKey(ReportConfiguration configuration) {
		if (configuration == null) {
			return REPORT_MIME_MAPPING_KEY;
		}
		IReportFormat format = configuration.getFormat();
		if (format == null) {
			return REPORT_MIME_MAPPING_KEY;
		}
		return REPORT_MIME_MAPPING_KEY + format.getId();
	}
	
	public void writeReportSource(IReportLoader reportLoader, OutputStream out)
			throws IOException, ReportException {
		IOUtils.copy(reportLoader.getSourceStream(), out);		
	}

}
