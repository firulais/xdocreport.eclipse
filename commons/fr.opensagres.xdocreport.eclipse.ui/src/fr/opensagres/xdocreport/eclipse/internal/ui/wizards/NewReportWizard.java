package fr.opensagres.xdocreport.eclipse.internal.ui.wizards;

import org.eclipse.jface.wizard.Wizard;

public class NewReportWizard extends Wizard {

	public NewReportWizard() {
		super.addPage(new SelectModuleAndEngineWizardPage());
		super.addPage(new ModifyReportProcessorWizardPage());
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
