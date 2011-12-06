package fr.opensagres.xdocreport.eclipse.internal.extensions.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;

public class WizardDescriptor {

	private static final String CLASS_ATTR = "class";
	private final String id;
	private final String title;
	private final ImageDescriptor imageDescriptor;
	private final IConfigurationElement ce;

	public WizardDescriptor(String id, String title,
			ImageDescriptor imageDescriptor, IConfigurationElement ce) {
		this.id = id;
		this.title = title;
		this.imageDescriptor = imageDescriptor;
		this.ce = ce;
	}

	public Wizard createWizard() throws CoreException {
		Wizard wizard = (Wizard) ce.createExecutableExtension(CLASS_ATTR);
		if (wizard == null) {
			return null;
		}
		if (title != null) {
			wizard.setWindowTitle(title);
		}
		if (imageDescriptor != null) {
			wizard.setDefaultPageImageDescriptor(imageDescriptor);
		}
		return wizard;
	}

}
