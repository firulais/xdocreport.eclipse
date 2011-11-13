package fr.opensagres.xdocreport.eclipse.internal.extensions.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.wizard.Wizard;

public class WizardDescriptor {

	private static final String CLASS_ATTR = "class";
	private final String id;
	private final IConfigurationElement ce;

	public WizardDescriptor(String id, IConfigurationElement ce) {
		this.id = id;
		this.ce = ce;
	}

	public Wizard createWizard() throws CoreException {
		return (Wizard) ce.createExecutableExtension(CLASS_ATTR);
	}

}
