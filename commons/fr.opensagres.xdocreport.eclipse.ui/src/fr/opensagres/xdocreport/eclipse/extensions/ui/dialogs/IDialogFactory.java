package fr.opensagres.xdocreport.eclipse.extensions.ui.dialogs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public interface IDialogFactory {

	<T extends Window> T createDialog(Shell shell, String dialogId,
			Class<T> clazz) throws DialogInitException, CoreException;
}
