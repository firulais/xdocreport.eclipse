package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import java.io.InputStream;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public abstract class AbstractResumeReportProcessor extends XDocReportProcessor {

	@Override
	public String getTemplateEngineKind() {
		return TemplateEngineKind.Velocity.name();
	}

	@Override
	public FieldsMetadata getFieldsMetadata() {
		return null;
	}

	@Override
	public void populateContext(IContext context, Object model) {
		User user = (User)model;
		context.put("user", user);
	}
}
