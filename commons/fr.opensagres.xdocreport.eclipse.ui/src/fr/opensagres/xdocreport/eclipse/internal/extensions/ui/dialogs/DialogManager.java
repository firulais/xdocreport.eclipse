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

import fr.opensagres.xdocreport.eclipse.core.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs.DialogInitException;
import fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs.IDialogManager;
import fr.opensagres.xdocreport.eclipse.internal.Activator;

public class DialogManager extends AbstractRegistry implements IDialogManager {

	private static final String DIALOGS_EXTENSION_POINT = "dialogs";
	private static final IDialogManager INSTANCE = new DialogManager();
	private static final Object DIALOG_ELT = "dialog";

	private final Map<String, DialogDescriptor> descriptors = new HashMap<String, DialogDescriptor>();

	public static IDialogManager getRegistry() {
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

			if (DIALOG_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);

				DialogDescriptor descriptor = new DialogDescriptor(id, ce);
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
		return DIALOGS_EXTENSION_POINT;
	}
}
