package fr.opensagres.xdocreport.eclipse.ui.dialogs;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

public class SearchDialog extends SelectionDialog {

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
