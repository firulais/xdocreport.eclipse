package org.dynaresume.eclipse.search.ui.modelpickers;

import org.dynaresume.eclipse.search.ui.dialogs.SearchClientDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.nebula.widgets.modelpicker.ISearcher;
import org.eclipse.nebula.widgets.modelpicker.ModelHolder;
import org.eclipse.nebula.widgets.modelpicker.Utils;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Text;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;

public class ClientSearchDialogSearcher implements ISearcher {

	private Text text;
	private ModelHolder modelHolder;

	public void init(ModelHolder modelHolder, String keyStroke) {
		this.modelHolder = modelHolder;
		text = modelHolder.getText();
		final KeyStroke triggerKeyStroke = getKeyStroke(keyStroke);

		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Utils.match(triggerKeyStroke, e)) {
					handle();
				}
			}
		});
	}

	private KeyStroke getKeyStroke(String keyStroke) {
		try {
			return KeyStroke.getInstance(keyStroke);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void handle() {
		try {
			SearchClientDialog dialog = PlatformXDocReport.getDialogFactory()
					.createDialog(text.getShell(), SearchClientDialog.ID,
							SearchClientDialog.class);
			dialog.open();
			Object[] results = dialog.getResult();
			if (results != null && results.length == 1) {
				modelHolder.setModel(results[0]);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

}
