package org.dynaresume.eclipse.reporting;

import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportLoader;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.textstyling.SyntaxKind;

public class SodifranceResumeReportLoader extends XDocReportLoader {

	public InputStream doGetSourceStream() {
		return ResumeReportProcessor.class
				.getResourceAsStream("Sodifrance.docx");
	}

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		FieldsMetadata metadata = new FieldsMetadata();
		metadata.addFieldAsImage("photo");
		metadata.addFieldAsList("experiences.Title");
		//metadata.addFieldAsList("experiences.Detail");
		metadata.addFieldAsTextStyling("exp.Detail", SyntaxKind.Html);
		metadata.addFieldAsList("educations.Label");
		metadata.addFieldAsList("educations.StartDateYear");
		metadata.addFieldAsList("educations.EndDateYear");
		metadata.addFieldAsList("hobbies.Label");
		return metadata;
	}

}
