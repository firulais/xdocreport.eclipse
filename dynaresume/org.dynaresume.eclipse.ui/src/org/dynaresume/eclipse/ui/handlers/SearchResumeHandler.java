package org.dynaresume.eclipse.ui.handlers;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.ui.dialogs.SearchResumeDialog;
import org.dynaresume.eclipse.ui.editors.ResumeEditorInput;
import org.dynaresume.eclipse.ui.editors.ResumeFormEditor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenDialogHandler;

public class SearchResumeHandler extends OpenDialogHandler {

	@Override
	protected SelectionDialog createDialog(Shell parentShell) {
		try {
			return PlatformXDocReport.getDialogFactory().createDialog(
					parentShell, SearchResumeDialog.ID,
					SearchResumeDialog.class);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		// return new SearchResumeDialog(parentShell);
	}

	@Override
	protected String getEditorId() {
		return ResumeFormEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new ResumeEditorInput(entry, (Resume) model);
	}
}
