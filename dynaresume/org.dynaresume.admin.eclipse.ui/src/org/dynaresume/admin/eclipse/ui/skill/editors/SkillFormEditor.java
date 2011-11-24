package org.dynaresume.admin.eclipse.ui.skill.editors;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import fr.opensagres.eclipse.forms.editor.IModelProvider;
import fr.opensagres.eclipse.forms.editor.ModelFormEditor;

public class SkillFormEditor extends ModelFormEditor<IEditorInput, Skill> {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.skill.editors.SkillFormEditor";

	private SkillService skillService;

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	@Override
	protected void doAddPages() {
		try {
			super.addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Skill onLoad(IEditorInput input) {
		// if (input instanceof IModelProvider<?>) {
		long skillId = ((IModelProvider<Skill>) input).getModel().getId();
		return skillService.findById(skillId);
		// }

		// return skillService.findById(input.getModel().getId());
	}

	@Override
	protected Skill onSave(Skill skill, IProgressMonitor monitor) {
		return skillService.save(skill);
	}

	@Override
	protected String getEditorId() {
		return ID;
	}

}
