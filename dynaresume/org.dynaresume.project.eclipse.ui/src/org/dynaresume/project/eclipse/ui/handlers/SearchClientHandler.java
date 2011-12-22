package org.dynaresume.project.eclipse.ui.handlers;

import org.dynaresume.domain.project.Client;
import org.dynaresume.eclipse.search.ui.dialogs.SearchClientDialog;
import org.dynaresume.project.eclipse.ui.editors.client.ClientEditorInput;
import org.dynaresume.project.eclipse.ui.editors.client.ClientFormEditor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenDialogHandler;

public class SearchClientHandler extends OpenDialogHandler {

	@Override
	protected SelectionDialog createDialog(Shell parentShell) {
		try {
			return PlatformXDocReport.getDialogFactory().createDialog(
					parentShell, SearchClientDialog.ID,
					SearchClientDialog.class);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		// return new SearchResumeDialog(parentShell);
	}

	@Override
	protected String getEditorId() {
		return ClientFormEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new ClientEditorInput(entry, (Client) model);
	}
}
