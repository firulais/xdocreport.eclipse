package org.dynaresume.admin.eclipse.ui.graphics.group.handlers;

import org.dynaresume.admin.eclipse.ui.graphics.group.editor.GraphicalAllGroupsEditor;
import org.dynaresume.admin.eclipse.ui.group.editors.GroupEditorInput;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.handlers.OpenEditorHandler;

public class OpenGraphicalAllGroupsEditorHandler extends OpenEditorHandler {

	@Override
	protected String getEditorId() {
		return GraphicalAllGroupsEditor.ID;
	}

	@Override
	protected IEditorInput createEditorInput(IReportModuleEntry entry) {
		return new GroupEditorInput(entry, null);
	}
}
