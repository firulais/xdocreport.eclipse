package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.utils.ByteArrayOutputStream;
import fr.opensagres.xdocreport.eclipse.utils.IOUtils;

public abstract class AbstractReportLoader implements IReportLoader {

	private String reportId;
	private String name;
	private String description;
	private IReportProcessor processor;
	private byte[] sourceByteArray;

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

	public final InputStream getSourceStream() throws IOException,
			ReportException {
		if (sourceByteArray != null) {
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			IOUtils.write(sourceByteArray, o);
			return new ByteArrayInputStream(o.toByteArray());
		}
		return doGetSourceStream();
	}

	public void setSourceStream(InputStream sourceStream) throws IOException,
			ReportException {
		try {
			sourceByteArray = IOUtils.toByteArray(sourceStream);
		} finally {
			IOUtils.closeQuietly(sourceStream);
		}
		getProcessor().getEngine().unloadReport(this);
	}

	protected abstract InputStream doGetSourceStream() throws IOException,
			ReportException;
}
