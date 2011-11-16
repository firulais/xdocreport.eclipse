package fr.opensagres.xdocreport.eclipse.internal.extensions.ui.dialogs;

import java.lang.reflect.Method;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs.DialogInitException;

public class DialogDescriptor {

	private static final String CLASS_ATTR = "class";
	private final String id;
	private final String title;
	private final IConfigurationElement ce;

	public DialogDescriptor(String id, String title, IConfigurationElement ce) {
		this.id = id;
		this.title = title;
		this.ce = ce;
	}

	public Window createDialog(Shell shell) throws CoreException {
		Window window = (Window) ce.createExecutableExtension(CLASS_ATTR);
		// Set parent shell
		Method method = null;
		try {
			method = window.getClass().getMethod("setParentShell", Shell.class);
		} catch (Exception e) {
			throw new DialogInitException(
					"Public Method setParentShell donesnt' exists for the class"
							+ window.getClass().getName(), e);
		}
		try {
			method.invoke(window, shell);
		} catch (Exception e) {
			throw new DialogInitException(
					"Invocation of Public Method setParentShell has error for the class"
							+ window.getClass().getName(), e);
		}
		// Set title
		if (title != null) {
			method = null;
			try {
				method = window.getClass().getMethod("setTitle", String.class);
			} catch (Exception e) {
				//
			}
			try {
				method.invoke(window, title);
			} catch (Exception e) {
				//
			}
		}
		return window;
	}

}
