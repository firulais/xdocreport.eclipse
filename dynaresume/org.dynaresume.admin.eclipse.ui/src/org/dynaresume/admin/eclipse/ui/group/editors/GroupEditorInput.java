package org.dynaresume.admin.eclipse.ui.group.editors;

import org.dynaresume.domain.core.Group;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class GroupEditorInput extends ModelAndEntryEditorInput<Group> {

	public GroupEditorInput(IReportModuleEntry entry, Group group) {
		super(entry, group);
	}

	public String getName() {
		return "Groups";
	}

	public String getToolTipText() {
		return getName();
	}

}
