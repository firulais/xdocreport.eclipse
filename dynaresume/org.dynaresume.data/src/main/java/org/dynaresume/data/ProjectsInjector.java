package org.dynaresume.data;

import org.dynaresume.domain.project.Project;

public class ProjectsInjector extends AbstractInjector {

	public ProjectsInjector(DataInjector dataInjector) {
		super(dataInjector);
	}

	public void inject() {

		addProject(
				"XDocReport",
				"http://code.google.com/p/xdocreport",
				"XDocReport means XML Document reporting. It's Java API to merge XML document created with MS Office (docx) or OpenOffice (odt), LibreOffice (odt) with a Java model to generate report and convert it if you need to another format (PDF, XHTML...).");
		addProject("SIDoc", "", "");
	}

	private void addProject(String name, String url, String description) {
		Project project = new Project();
		project.setName(name);
		project.setURL(url);
		project.setDescription(description);
		getDataInjector().getProjectService().save(project);
	}

}
