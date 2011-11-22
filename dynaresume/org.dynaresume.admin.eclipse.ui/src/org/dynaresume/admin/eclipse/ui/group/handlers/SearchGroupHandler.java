package org.dynaresume.admin.eclipse.ui.group.handlers;

import org.dynaresume.admin.eclipse.ui.group.dialogs.SearchGroupDialog;
import org.dynaresume.admin.eclipse.ui.group.editors.GroupEditorInput;
import org.dynaresume.admin.eclipse.ui.group.editors.GroupFormEditor;
import org.dynaresume.domain.core.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenDialogHandler;

public class SearchGroupHandler extends OpenDialogHandler {

	@Override
	protected SelectionDialog createDialog(Shell parentShell) throws Exception {
		return PlatformXDocReport.getDialogFactory().createDialog(parentShell,
				SearchGroupDialog.ID, SearchGroupDialog.class);
	}

	@Override
	protected String getEditorId() {
		return GroupFormEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry,
			Object model) {
		return new GroupEditorInput(entry, (Group) model);
	}
}
