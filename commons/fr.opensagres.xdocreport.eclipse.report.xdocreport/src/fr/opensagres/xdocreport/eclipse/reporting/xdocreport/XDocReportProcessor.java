package fr.opensagres.xdocreport.eclipse.reporting.xdocreport;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public abstract class XDocReportProcessor implements IReportProcessor {

	public abstract String getTemplateEngineKind();

	public abstract FieldsMetadata getFieldsMetadata();

	public abstract void populateContext(IContext context, Object model);

}
