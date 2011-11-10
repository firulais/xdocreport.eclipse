package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.handlers;

import org.dynaresume.domain.hr.Resume;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.dialogs.SearchResumeDialog;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors.ResumeEditorInput;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors.ResumeFormEditor;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenDialogHandler;

public class SearchResumeHandler extends OpenDialogHandler {

	@Override
	protected SelectionDialog createDialog(Shell parentShell) {
		return new SearchResumeDialog(parentShell);
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
