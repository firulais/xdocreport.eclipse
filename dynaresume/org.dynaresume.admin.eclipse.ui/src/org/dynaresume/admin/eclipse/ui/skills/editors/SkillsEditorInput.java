package org.dynaresume.admin.eclipse.ui.skills.editors;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class SkillsEditorInput extends ModelAndEntryEditorInput {

	public SkillsEditorInput(IReportModuleEntry entry) {
		super(entry, null);
	}

	public String getName() {
		return "Skills";
	}

	public String getToolTipText() {
		return getName();
	}

}
