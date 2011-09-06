package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.InputStream;

public class ODTResumeReportProcessor extends AbstractResumeReportProcessor {

	public String getReportId() {
		return "resume3";
	}

	public InputStream getSourceStream() {
		return ODTResumeReportProcessor.class.getResourceAsStream("Resume.odt");
	}

}
