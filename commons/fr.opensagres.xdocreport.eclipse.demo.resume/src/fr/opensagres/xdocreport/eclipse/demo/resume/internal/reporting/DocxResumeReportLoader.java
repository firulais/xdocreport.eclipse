package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.IOException;
import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportException;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportLoader;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DocxResumeReportLoader extends XDocReportLoader {

	public InputStream doGetSourceStream() throws IOException, ReportException {
		return ResumeReportProcessor.class.getResourceAsStream("Resume.docx");
	}

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		FieldsMetadata metadata = new FieldsMetadata();
		metadata.addFieldAsImage("photo", "resume.Photo");
		metadata.addFieldAsList("experiences.Title");
		metadata.addFieldAsList("experiences.Detail");
		metadata.addFieldAsList("diplomas.Label");
		metadata.addFieldAsList("hobbies.Label");
		return metadata;
	}

}
