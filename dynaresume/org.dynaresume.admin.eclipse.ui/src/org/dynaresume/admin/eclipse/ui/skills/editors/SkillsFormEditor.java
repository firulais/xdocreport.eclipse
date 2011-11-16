package org.dynaresume.admin.eclipse.ui.skills.editors;

import java.util.Collection;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class SkillsFormEditor extends
		ReportingFormEditor<Collection<Skill>> {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.skills.editors.SkillsFormEditor";

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
	protected Collection<Skill> onLoad(
			ModelAndEntryEditorInput<Collection<Skill>> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Skill> onSave(Collection<Skill> modelObject,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
