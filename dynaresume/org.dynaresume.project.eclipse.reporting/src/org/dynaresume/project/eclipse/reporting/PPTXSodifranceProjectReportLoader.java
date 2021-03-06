package org.dynaresume.project.eclipse.reporting;

import java.io.IOException;
import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.reporting.core.ReportException;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportLoader;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class PPTXSodifranceProjectReportLoader extends XDocReportLoader {

	public InputStream doGetSourceStream() throws IOException, ReportException {
		return ProjectReportProcessor.class
				.getResourceAsStream("SodifranceProject.pptx");
	}

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		FieldsMetadata metadata = new FieldsMetadata();
		metadata.addFieldAsList("contextDescriptions.Description");
		metadata.addFieldAsList("problemsDescriptions.Description");
		metadata.addFieldAsList("solutionsDescriptions.Description");
		metadata.addFieldAsList("companyBenefitsDescriptions.Description");
		metadata.addFieldAsList("clientBenefitsDescriptions.Description");
		return metadata;
	}

}
