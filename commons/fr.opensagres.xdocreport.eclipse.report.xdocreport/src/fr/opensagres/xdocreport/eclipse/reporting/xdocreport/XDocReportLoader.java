package fr.opensagres.xdocreport.eclipse.reporting.xdocreport;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.AbstractReportLoader;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public abstract class XDocReportLoader extends AbstractReportLoader {

	public abstract String getTemplateEngineKind();

	public abstract FieldsMetadata getFieldsMetadata();

}
