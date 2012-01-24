package org.dynaresume.project.eclipse.reporting;

import java.io.IOException;
import java.io.InputStream;

import fr.opensagres.xdocreport.core.document.SyntaxKind;
import fr.opensagres.xdocreport.eclipse.reporting.core.ReportException;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportLoader;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DocxSodifranceProjectReportLoader extends XDocReportLoader {

	public InputStream doGetSourceStream() throws IOException, ReportException {
		return ProjectReportProcessor.class.getResourceAsStream("SodifranceProject.docx");
	}

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		FieldsMetadata metadata = new FieldsMetadata();
		metadata.addFieldAsTextStyling("project.Description", SyntaxKind.Html);
		return metadata;
	}

}
