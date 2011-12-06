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
	// private final CollaborateurWizardPage collaborateurWizardPage;

	private ProjectService projectService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public NewProjectWizard() {
	}

	public boolean canFinish() {
		return false;
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
		// collaborateurWizardPage.updateData();
		projectService.save(project);
		super.performFinish();
		return true;
	}
}