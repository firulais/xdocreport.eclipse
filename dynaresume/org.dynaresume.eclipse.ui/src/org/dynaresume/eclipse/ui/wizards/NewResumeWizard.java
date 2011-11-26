package org.dynaresume.eclipse.ui.wizards;

import org.dynaresume.domain.core.NaturalPerson;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.editors.ResumeEditorInput;
import org.dynaresume.eclipse.ui.editors.ResumeFormEditor;
import org.dynaresume.services.ResumeService;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.wizards.AbstractWizard;

public class NewResumeWizard extends AbstractWizard {

	public static final String ID = "org.dynaresume.eclipse.ui.wizards.NewResumeWizard";

	private final Resume resume;
	private final CollaborateurWizardPage collaborateurWizardPage;

	private ResumeService resumeService;

	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	public NewResumeWizard() {
		resume = new Resume();
		resume.setOwner(new NaturalPerson());
		// Add the pages
		// addPage( new ComplaintsPage() );
		// addPage( new MoreInformationPage() );
		this.collaborateurWizardPage = new CollaborateurWizardPage(resume);
		addPage(collaborateurWizardPage);
	}

	public boolean canFinish() {
		return getContainer() != null
				&& getContainer().getCurrentPage() instanceof CollaborateurWizardPage;
	}

	@Override
	protected Object getModel() {
		return resume;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new ResumeEditorInput(entry, (Resume) model);
	}

	@Override
	protected String getEditorId() {
		return ResumeFormEditor.ID;
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
		collaborateurWizardPage.updateData();
		resumeService.save(resume);
		super.performFinish();
		return true;
	}
}