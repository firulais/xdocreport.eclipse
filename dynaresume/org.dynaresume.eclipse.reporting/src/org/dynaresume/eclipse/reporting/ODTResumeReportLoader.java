package org.dynaresume.eclipse.reporting;

import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportLoader;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class ODTResumeReportLoader extends XDocReportLoader {

	public InputStream doGetSourceStream() {
		return ResumeReportProcessor.class.getResourceAsStream("Resume.odt");
	}

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		FieldsMetadata metadata = new FieldsMetadata();
		metadata.addFieldAsImage("resume.Photo");
		return metadata;
	}

}
