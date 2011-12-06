package org.dynaresume.project.eclipse.reporting;

import org.dynaresume.domain.project.Project;

import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public class ProjectReportProcessor extends XDocReportProcessor {

	@Override
	public void populateContext(IContext context, Object model) {
		Project project = (Project) model;
		context.put("project", project);
	}
}
