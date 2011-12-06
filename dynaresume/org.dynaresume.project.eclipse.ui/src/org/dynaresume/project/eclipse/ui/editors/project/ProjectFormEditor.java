package org.dynaresume.project.eclipse.ui.editors.project;

import org.dynaresume.domain.project.Project;
import org.dynaresume.services.ProjectService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class ProjectFormEditor extends
		ReportingFormEditor<Project> {

	public static final String ID = "org.dynaresume.project.eclipse.ui.editors.project.ProjectFormEditor";

	private ProjectService projectService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Override
	protected void doAddPages() {
		try {
			super.addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Project onLoad(ModelAndEntryEditorInput<Project> input) {
		return projectService.findById(
				input.getModel().getId());
	}

	@Override
	protected Project onSave(Project modelObject, IProgressMonitor monitor) {
		return projectService.save(modelObject);
	}
	
	@Override
	protected String getEditorId() {
		return ID;
	}

	
}
