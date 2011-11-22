package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;
import org.dynaresume.services.SkillCategoryService;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class ResumeFormEditor extends ReportingFormEditor<Resume> {

	public static final String ID = "fr.opensagres.xdocreport.eclipse.demo.resume.editor.ResumeFormEditorPart";

	private ResumeService resumeService;
	private SkillService skillService;
	private SkillCategoryService skillCategoryService;

	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}
	
	public void setSkillCategoryService(
			SkillCategoryService skillCategoryService) {
		this.skillCategoryService = skillCategoryService;
	}
	

	@Override
	protected void doAddPages() {
		try {
			addPage(new OverviewPage(this));
			addPage(new EducationsPage(this));
			addPage(new ExperiencesPage(this));
			addPage(new SkillsPage(this));
			addPage(new HobbiesPage(this));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Resume onLoad(ModelAndEntryEditorInput<Resume> input) {
		return resumeService.findById(input.getModel().getId());
	}

	@Override
	protected Resume onSave(Resume resume, IProgressMonitor monitor) {
		return resumeService.save(resume);
	}

	public SkillService getSkillService() {
		return skillService;
	}

	public SkillCategoryService getSkillCategoryService() {
		return skillCategoryService;
	}
}
