package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core;

import fr.opensagres.xdocreport.eclipse.reporting.core.AbstractReportLoader;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public abstract class XDocReportLoader extends AbstractReportLoader {

	public abstract String getTemplateEngineKind();
	
	public void setTemplateEngineKind(String templateEngineKind) {
		// TODO
	}
	
	public abstract FieldsMetadata getFieldsMetadata();

}
