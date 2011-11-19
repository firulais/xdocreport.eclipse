package org.dynaresume.admin.eclipse.ui.graphics.skillOLD.editors;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class SkillsEditorInput extends ModelAndEntryEditorInput {

	public SkillsEditorInput(IReportModuleEntry entry, Object model) {
		super(entry, model);
	}

	public String getName() {
		return "Skills";
	}

	public String getToolTipText() {
		return "Graphical Skills";
	}

}
