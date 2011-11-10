package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.dynaresume.domain.hr.Resume;

import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class ResumeEditorInput extends ModelAndEntryEditorInput<Resume> {

	public ResumeEditorInput(IReportModuleEntry entry, Resume user) {
		super(entry, user);
	}

	public String getName() {
		return (getModel() != null ? (getModel().getOwner().getFirstName()
				+ " " + getModel().getOwner().getLastName()) : "New");
	}

	public String getToolTipText() {
		return getName();
	}

}
