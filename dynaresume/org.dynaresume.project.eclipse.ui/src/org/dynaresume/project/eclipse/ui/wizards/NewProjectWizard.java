package org.dynaresume.project.eclipse.ui.wizards;

import org.dynaresume.domain.project.Project;
import org.dynaresume.project.eclipse.ui.editors.project.ProjectEditorInput;
import org.dynaresume.project.eclipse.ui.editors.project.ProjectFormEditor;
import org.dynaresume.services.ProjectService;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewProjectWizard extends AbstractWizard {

	public static final String ID = "org.dynaresume.project.eclipse.ui.wizards.NewProjectWizard";

	private Project project;

	private ProjectService projectService;

	private CreateProjectWizardPage createProjectWizardPage;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public NewProjectWizard() {
		createProjectWizardPage = new CreateProjectWizardPage();
		super.addPage(createProjectWizardPage);
	}

	public boolean canFinish() {
		return true;
	}

	@Override
	protected Object getModel() {
		return project;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new ProjectEditorInput(entry, (Project) model);
	}

	@Override
	protected String getEditorId() {
		return ProjectFormEditor.ID;
	}

	/**
	 * Called when user clicks Finish
	 * 
	 * @return boolean
	 */
	public boolean performFinish() {
		// ResumeService resumeService = (ResumeService)
		// PlatformUI.getWorkbench()
		// .getService(ResumeService.class);
		this.project = new Project();
		createProjectWizardPage.updateData(project);
		projectService.save(project);
		super.performFinish();
		return true;
	}
}