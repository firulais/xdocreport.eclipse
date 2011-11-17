package org.dynaresume.admin.eclipse.ui.skill.editors;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class SkillFormEditor extends
		ReportingFormEditor<Skill> {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Skill onLoad(ModelAndEntryEditorInput<Skill> input) {
		return skillService.findById(
				input.getModel().getId());
	}

	@Override
	protected Skill onSave(Skill modelObject, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
