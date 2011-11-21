package fr.opensagres.xdocreport.eclipse.internal.extensions.ui.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import fr.opensagres.eclipse.forms.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs.DialogInitException;
import fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs.IDialogFactory;
import fr.opensagres.xdocreport.eclipse.internal.Activator;

public class DialogFactory extends AbstractRegistry implements IDialogFactory {

	private static final String DIALOG_FACTORIES_EXTENSION_POINT = "dialogFactories";
	private static final IDialogFactory INSTANCE = new DialogFactory();
	private static final Object FACTORY_ELT = "factory";
	
	private static final String TITLE_ATTR = "title";

	private final Map<String, DialogDescriptor> descriptors = new HashMap<String, DialogDescriptor>();

	public static IDialogFactory getRegistry() {
		return INSTANCE;
	}

	public <T extends Window> T createDialog(Shell shell, String dialogId,
			Class<T> clazz) throws DialogInitException, CoreException {
		if (dialogId == null) {
			throw new IllegalArgumentException();
		}
		loadRegistryIfNedded();
		DialogDescriptor descriptor = descriptors.get(dialogId);
		if (descriptor == null) {
			throw new DialogInitException("Cannot find dialog with id="
					+ dialogId);
		}
		return (T) descriptor.createDialog(shell);
	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseDialogs(cf);
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
			parseDialogs(cf);
		}
	}

	private void parseDialogs(IConfigurationElement[] cf) {
		for (IConfigurationElement ce : cf) {
			String id = null;
			String title = null;
			if (FACTORY_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);
				title = ce.getAttribute(TITLE_ATTR);
				DialogDescriptor descriptor = new DialogDescriptor(id, title,
						ce);
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
		return DIALOG_FACTORIES_EXTENSION_POINT;
	}
}
