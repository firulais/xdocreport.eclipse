package org.dynaresume.project.eclipse.ui.wizards;

import org.dynaresume.domain.project.Client;
import org.dynaresume.project.eclipse.ui.editors.client.ClientEditorInput;
import org.dynaresume.project.eclipse.ui.editors.client.ClientFormEditor;
import org.dynaresume.services.ClientService;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewClientWizard extends AbstractWizard {

	public static final String ID = "org.dynaresume.project.eclipse.ui.wizards.NewClientWizard";

	private Client project;
	// private final CollaborateurWizardPage collaborateurWizardPage;

	private ClientService clientService;

	public NewClientWizard() {
		
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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
		return new ClientEditorInput(entry, (Client) model);
	}

	@Override
	protected String getEditorId() {
		return ClientFormEditor.ID;
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
		clientService.save(project);
		super.performFinish();
		return true;
	}
}