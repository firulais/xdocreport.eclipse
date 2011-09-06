package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.InputStream;

public class DocxReportProcessor2 extends AbstractResumeReportProcessor {

	public String getReportId() {
		return "resume2";
	}

	public InputStream getSourceStream() {
		return DocxReportProcessor2.class.getResourceAsStream("Resume2.docx");
	}

}
