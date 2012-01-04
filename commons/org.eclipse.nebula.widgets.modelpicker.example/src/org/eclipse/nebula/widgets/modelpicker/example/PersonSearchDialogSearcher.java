package org.eclipse.nebula.widgets.modelpicker.example;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.nebula.widgets.modelpicker.AbstractSearcher;

public class PersonSearchDialogSearcher extends AbstractSearcher {

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void handle() {
		TitleAreaDialog dialog = new TitleAreaDialog(getText().getShell());
		dialog.open();

	}

}
