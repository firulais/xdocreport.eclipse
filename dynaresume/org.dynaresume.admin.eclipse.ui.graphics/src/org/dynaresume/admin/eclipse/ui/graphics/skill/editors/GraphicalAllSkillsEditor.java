package org.dynaresume.admin.eclipse.ui.graphics.skill.editors;

import java.util.Collection;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;

public class GraphicalAllSkillsEditor extends GraphicalSkillEditor {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalAllSkillsEditor";

	private SkillService skillService;

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		MessageDialog.openInformation(getSite().getShell(), "TODO",
				"TODO Implement Editor#doSave");
	}

	@Override
	protected Iterable<Skill> getSkills(IEditorInput input) {
		return skillService.findAll();
	}

}
