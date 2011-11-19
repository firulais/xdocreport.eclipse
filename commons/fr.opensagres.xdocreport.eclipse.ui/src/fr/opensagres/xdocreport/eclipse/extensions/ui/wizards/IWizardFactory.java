package fr.opensagres.xdocreport.eclipse.extensions.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.Wizard;

public interface IWizardFactory {

	<T extends Wizard> T createWizard(String wizardId, Class<T> clazz)
			throws WizardInitException, CoreException;
}
