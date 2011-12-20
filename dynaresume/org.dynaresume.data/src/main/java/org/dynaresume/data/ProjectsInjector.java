package org.dynaresume.data;

import org.dynaresume.domain.project.Project;
import org.dynaresume.domain.project.ProjectDescriptionType;

public class ProjectsInjector extends AbstractInjector {

	public ProjectsInjector(DataInjector dataInjector) {
		super(dataInjector);
	}

	public void inject() {

		addType("CTXT", "Context");
		addType("PBS", "Problems");
		addType("SOL", "Solutions");
		//addType("SOL", "Solutions");
		
		addProject(
				"XDocReport",
				"http://code.google.com/p/xdocreport",
				"XDocReport means XML Document reporting. It's Java API to merge XML document created with MS Office (docx) or OpenOffice (odt), LibreOffice (odt) with a Java model to generate report and convert it if you need to another format (PDF, XHTML...).");
		addProject("SIDoc", "", "");
	}

	private ProjectDescriptionType addType(String code, String label) {
		ProjectDescriptionType type = new ProjectDescriptionType();
		type.setCode(code);
		type.setLabel(label);
		return getDataInjector().getProjectDescriptionTypeService().save(type);
	}

	private void addProject(String name, String url, String description) {
		Project project = new Project();
		project.setName(name);
		project.setURL(url);
		// project.setDescription(description);
		getDataInjector().getProjectService().save(project);
	}

}
