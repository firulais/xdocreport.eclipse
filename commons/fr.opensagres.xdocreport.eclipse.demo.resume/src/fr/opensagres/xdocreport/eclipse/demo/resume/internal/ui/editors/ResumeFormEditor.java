package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.demo.resume.services.ServicesProvider;
import fr.opensagres.xdocreport.eclipse.ui.editors.AbstractFormEditor;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class ResumeFormEditor extends AbstractFormEditor<User> {

	public static final String ID = "fr.opensagres.xdocreport.eclipse.demo.resume.editor.ResumeFormEditorPart";

	@Override
	protected void doAddPages() {
		try {
			addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			//
			e.printStackTrace();
		}
	}

	@Override
	protected User onLoad(ModelAndEntryEditorInput<User> input) {
		return ServicesProvider.getUserService().findById(
				input.getModel().getId());
	}

	@Override
	protected User onSave(User user, IProgressMonitor monitor) {
		return ServicesProvider.getUserService().save(user);
	}

}
