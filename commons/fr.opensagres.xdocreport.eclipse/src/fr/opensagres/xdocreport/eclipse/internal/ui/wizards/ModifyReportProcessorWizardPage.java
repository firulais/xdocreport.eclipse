package fr.opensagres.xdocreport.eclipse.internal.ui.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ModifyReportProcessorWizardPage extends WizardPage {

	protected ModifyReportProcessorWizardPage() {
		super("x");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
	}

}
