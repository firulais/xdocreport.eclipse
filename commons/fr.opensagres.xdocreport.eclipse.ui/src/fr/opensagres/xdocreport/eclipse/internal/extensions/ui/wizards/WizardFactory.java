package fr.opensagres.xdocreport.eclipse.internal.extensions.ui.wizards;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.Wizard;

import fr.opensagres.xdocreport.eclipse.core.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.ui.wizards.IWizardFactory;
import fr.opensagres.xdocreport.eclipse.extensions.ui.wizards.WizardInitException;
import fr.opensagres.xdocreport.eclipse.internal.Activator;

public class WizardFactory extends AbstractRegistry implements IWizardFactory {

	private static final String WIZARD_FACTORIES_EXTENSION_POINT = "wizardFactories";
	private static final IWizardFactory INSTANCE = new WizardFactory();
	private static final Object FACTORY_ELT = "factory";

	private final Map<String, WizardDescriptor> descriptors = new HashMap<String, WizardDescriptor>();

	public static IWizardFactory getRegistry() {
		return INSTANCE;
	}

	public <T extends Wizard> T createWizard(String wizardId,
			Class<T> clazz) throws WizardInitException, CoreException {
		if (wizardId == null) {
			throw new IllegalArgumentException();
		}
		loadRegistryIfNedded();
		WizardDescriptor descriptor = descriptors.get(wizardId);
		if (descriptor == null) {
			throw new WizardInitException("Cannot find wizard with id="
					+ wizardId);
		}
		return (T) descriptor.createWizard();
	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseWizards(cf);
		} else {
			// TODO : remove references
		}
	}

	protected synchronized void loadRegistry() {
		if (isRegistryIntialized()) {
			return;
		}
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			IConfigurationElement[] cf = registry.getConfigurationElementsFor(
					getPluginId(), getExtensionPoint());
			parseWizards(cf);
		}
	}

	private void parseWizards(IConfigurationElement[] cf) {
		for (IConfigurationElement ce : cf) {
			String id = null;

			if (FACTORY_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);

				WizardDescriptor descriptor = new WizardDescriptor(id, ce);
				descriptors.put(id, descriptor);
			}
		}

	}

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected String getExtensionPoint() {
		return WIZARD_FACTORIES_EXTENSION_POINT;
	}
}
