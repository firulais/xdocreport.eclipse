package org.dynaresume.eclipse.search.ui.modelpickers;

import org.dynaresume.eclipse.search.ui.dialogs.SearchClientDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.nebula.widgets.modelpicker.AbstractSearcher;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;

public class ClientSearchDialogSearcher extends AbstractSearcher {

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void handle() {
		try {
			SearchClientDialog dialog = PlatformXDocReport.getDialogFactory()
					.createDialog(getText().getShell(), SearchClientDialog.ID,
							SearchClientDialog.class);
			dialog.setMultipleSelection(false);
			dialog.setClientNameCriteria(getText().getText());
			dialog.open();
			Object[] results = dialog.getResult();
			if (results != null && results.length == 1) {
				super.setModel(results[0]);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

}
