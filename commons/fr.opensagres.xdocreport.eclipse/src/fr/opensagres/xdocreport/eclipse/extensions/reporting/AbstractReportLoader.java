package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.IOException;

public abstract class AbstractReportLoader implements IReportLoader {

	private String reportId;
	private IReportProcessorType processorType;

	public boolean canSupportFormat(IReportFormat format) throws IOException,
			ReportException {
		return getProcessorType().getEngine().canSupportFormat(this, format);
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public IReportProcessorType getProcessorType() {
		return processorType;
	}

	public void setProcessorType(IReportProcessorType processorType) {
		this.processorType = processorType;
	}

}
