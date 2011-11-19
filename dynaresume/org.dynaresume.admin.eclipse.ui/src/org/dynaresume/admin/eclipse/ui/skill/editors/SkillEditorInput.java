package org.dynaresume.admin.eclipse.ui.skill.editors;

import org.dynaresume.domain.hr.Skill;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class SkillEditorInput extends ModelAndEntryEditorInput<Skill> {

	public SkillEditorInput(IReportModuleEntry entry, Skill skill) {
		super(entry, skill);
	}

	public String getName() {
		return "Skills";
	}

	public String getToolTipText() {
		return getName();
	}

}
