package org.dynaresume.admin.eclipse.ui.graphics.group.editor;

import org.dynaresume.domain.core.Group;
import org.dynaresume.services.GroupService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;

public class GraphicalAllGroupsEditor extends GraphicalGroupEditor {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.group.editor.GraphicalAllGroupsEditor";

	private GroupService groupService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		MessageDialog.openInformation(getSite().getShell(), "TODO",
				"TODO Implement Editor#doSave");
	}

	@Override
	protected Iterable<Group> getGroups(IEditorInput input) {
		return groupService.findAll();
	}

}
