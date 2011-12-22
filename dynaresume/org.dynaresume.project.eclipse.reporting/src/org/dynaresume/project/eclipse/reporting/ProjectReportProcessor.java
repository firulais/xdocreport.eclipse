package org.dynaresume.project.eclipse.reporting;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dynaresume.domain.project.DefaultProjectDescriptionCode;
import org.dynaresume.domain.project.Project;
import org.dynaresume.domain.project.ProjectDescription;

import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public class ProjectReportProcessor extends XDocReportProcessor {

	@Override
	public void populateContext(IContext context, Object model) {
		Project project = (Project) model;
		context.put("project", project);

		List<ProjectDescription> contextDescriptions = new ArrayList<ProjectDescription>();
		List<ProjectDescription> problemsDescriptions = new ArrayList<ProjectDescription>();
		List<ProjectDescription> solutionsDescriptions = new ArrayList<ProjectDescription>();
		List<ProjectDescription> companyBenefitsDescriptions = new ArrayList<ProjectDescription>();
		List<ProjectDescription> clientBenefitsDescriptions = new ArrayList<ProjectDescription>();

		Set<ProjectDescription> descriptions = project.getDescriptions();
		if (descriptions != null) {
			for (ProjectDescription description : descriptions) {
				if (DefaultProjectDescriptionCode.Context.getCode().equals(
						description.getType().getCode())) {
					contextDescriptions.add(description);
				} else if (DefaultProjectDescriptionCode.Problems.getCode()
						.equals(description.getType().getCode())) {
					problemsDescriptions.add(description);
				} else if (DefaultProjectDescriptionCode.Solutions.getCode()
						.equals(description.getType().getCode())) {
					solutionsDescriptions.add(description);
				} else if (DefaultProjectDescriptionCode.CompanyBenefits
						.getCode().equals(description.getType().getCode())) {
					companyBenefitsDescriptions.add(description);
				} else if (DefaultProjectDescriptionCode.ClientBenefits
						.getCode().equals(description.getType().getCode())) {
					clientBenefitsDescriptions.add(description);
				}
			}
		}

		String clientName = project.getClient() != null ? project.getClient()
				.getName() : "";
		context.put("clientName", clientName);

		context.put("contextDescriptions", contextDescriptions);
		context.put("problemsDescriptions", problemsDescriptions);
		context.put("solutionsDescriptions", solutionsDescriptions);
		context.put("companyBenefitsDescriptions", companyBenefitsDescriptions);
		context.put("clientBenefitsDescriptions", clientBenefitsDescriptions);
	}
}
