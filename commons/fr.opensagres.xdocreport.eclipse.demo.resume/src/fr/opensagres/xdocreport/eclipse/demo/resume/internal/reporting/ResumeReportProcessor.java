package fr.opensagres.xdocreport.eclipse.demo.resume.internal.reporting;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public class ResumeReportProcessor extends XDocReportProcessor {

	@Override
	public void populateContext(IContext context, Object model) {
		User user = (User)model;
		context.put("user", user);
	}
}
