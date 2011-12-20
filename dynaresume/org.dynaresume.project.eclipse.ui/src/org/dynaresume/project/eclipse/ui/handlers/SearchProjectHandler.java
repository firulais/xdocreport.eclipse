package org.dynaresume.project.eclipse.ui.handlers;

import org.dynaresume.domain.project.Project;
import org.dynaresume.eclipse.search.ui.dialogs.SearchProjectDialog;
import org.dynaresume.project.eclipse.ui.editors.project.ProjectEditorInput;
import org.dynaresume.project.eclipse.ui.editors.project.ProjectFormEditor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenDialogHandler;

public class SearchProjectHandler extends OpenDialogHandler {

	@Override
	protected SelectionDialog createDialog(Shell parentShell) {
		try {
			return PlatformXDocReport.getDialogFactory().createDialog(
					parentShell, SearchProjectDialog.ID,
					SearchProjectDialog.class);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		// return new SearchResumeDialog(parentShell);
	}

	@Override
	protected String getEditorId() {
		return ProjectFormEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new ProjectEditorInput(entry, (Project) model);
	}
}
