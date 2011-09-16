package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportLoader;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DocxResumeReportLoader extends XDocReportLoader {

	public InputStream getSourceStream() {
		return ResumeReportProcessor.class.getResourceAsStream("Resume.docx");
	}

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		return null;
	}

}
