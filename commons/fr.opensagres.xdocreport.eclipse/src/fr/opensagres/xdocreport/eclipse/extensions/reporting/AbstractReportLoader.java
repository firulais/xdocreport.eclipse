package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;

public abstract class AbstractReportLoader implements IReportLoader {

	private String reportId;
	private String name;
	private String description;
	private IReportProcessor processor;

	public boolean canSupportFormat(IReportFormat format) throws IOException,
			ReportException {
		return getProcessor().getEngine().canSupportFormat(this, format);
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public IReportProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(IReportProcessor processor) {
		this.processor = processor;
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
