package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.services.ServicesProvider;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;
import fr.opensagres.xdocreport.eclipse.ui.editors.ReportingFormEditor;

public class ResumeFormEditor extends ReportingFormEditor<Resume> {

	public static final String ID = "fr.opensagres.xdocreport.eclipse.demo.resume.editor.ResumeFormEditorPart";

	@Override
	protected void doAddPages() {
		try {
			addPage(new OverviewPage(this));
			addPage(new ExperiencesPage(this));
			addPage(new SkillsPage(this));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Resume onLoad(ModelAndEntryEditorInput<Resume> input) {
		return ServicesProvider.getResumeService().findById(
				input.getModel().getId());
	}

	@Override
	protected Resume onSave(Resume resume, IProgressMonitor monitor) {
		return ServicesProvider.getResumeService().save(resume);
	}

}
