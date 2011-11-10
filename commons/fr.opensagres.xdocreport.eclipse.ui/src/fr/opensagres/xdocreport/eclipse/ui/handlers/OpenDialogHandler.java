package fr.opensagres.xdocreport.eclipse.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;

public abstract class OpenDialogHandler extends AbstractContextHandler {

	@Override
	protected Object execute(ExecutionEvent event,
			ContextHandlerEvent contextEvent) throws ExecutionException {
		Shell parentShell = HandlerUtil.getActiveShell(event);
		SelectionDialog dialog = createDialog(parentShell);
		dialog.open();
		Object[] results = dialog.getResult();
		if (results != null) {
			IWorkbenchWindow window = HandlerUtil
					.getActiveWorkbenchWindow(event);
			IWorkbenchPage page = window.getActivePage();
			IReportModuleEntry entry = contextEvent.getSourceEntry();
			Object model = null;
			for (int i = 0; i < results.length; i++) {
				model = results[i];
				try {
					page.openEditor(createEditorInput(entry, model),
							getEditorId(), true);
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

	protected abstract SelectionDialog createDialog(Shell parentShell);

	protected abstract String getEditorId();

	protected abstract IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model);
}
