package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.InputStream;

public class DocxResumeReportProcessor extends AbstractResumeReportProcessor {

	public String getReportId() {
		return "resume";
	}

	public InputStream getSourceStream() {
		return DocxResumeReportProcessor.class.getResourceAsStream("Resume.docx");
	}

}
