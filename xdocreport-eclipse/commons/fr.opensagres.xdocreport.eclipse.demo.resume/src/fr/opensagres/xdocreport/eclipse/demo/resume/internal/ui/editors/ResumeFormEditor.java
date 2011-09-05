package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.ui.editors.AbstractFormEditor;

public class ResumeFormEditor extends AbstractFormEditor<User> {

	public static final String ID = "fr.opensagres.xdocreport.eclipse.demo.resume.editor.ResumeFormEditorPart";

	@Override
	protected void addPages() {
		try {
			addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			//
		}
	}

}
