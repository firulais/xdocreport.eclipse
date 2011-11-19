package fr.opensagres.xdocreport.eclipse.ui.dialogs;

import org.eclipse.swt.widgets.Shell;

import fr.opensagres.eclipse.forms.dialogs.SelectionFormDialog;

public abstract class SearchDialog extends SelectionFormDialog {

	public SearchDialog(Shell parentShell) {
		super(parentShell);
	}

	public SearchDialog() {
		super(null);
	}

	@Override
	public void setParentShell(Shell newParentShell) {
		super.setParentShell(newParentShell);
	}

}
