package org.dynaresume.admin.eclipse.ui.group.editors;

import org.dynaresume.domain.core.Group;
import org.dynaresume.services.GroupService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class GroupFormEditor extends
		ReportingFormEditor<Group> {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.group.editors.GroupFormEditor";

	private GroupService groupService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	protected void doAddPages() {
		try {
			super.addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Group onLoad(ModelAndEntryEditorInput<Group> input) {
		return groupService.findById(
				input.getModel().getId());
	}

	@Override
	protected Group onSave(Group modelObject, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String getEditorId() {
		return ID;
	}

	
}
