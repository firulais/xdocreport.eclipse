package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.ui.editors.ModelAndEntryEditorInput;

public class UserEditorInput extends ModelAndEntryEditorInput<User> {

	public UserEditorInput(IReportModuleEntry entry, User user) {
		super(entry, user);
	}

	public String getName() {
		return (getModel() != null ? (getModel().getFirstName() + " " + getModel()
				.getLastName()) : "New");
	}

	public String getToolTipText() {
		return getName();
	}

}
