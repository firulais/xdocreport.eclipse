package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.InputStream;

public class ResumeReportProcessor extends AbstractResumeReportProcessor {

	public String getReportId() {
		return "resume";
	}

	public InputStream getSourceStream() {
		return ResumeReportProcessor.class.getResourceAsStream("Resume.docx");
	}

}
