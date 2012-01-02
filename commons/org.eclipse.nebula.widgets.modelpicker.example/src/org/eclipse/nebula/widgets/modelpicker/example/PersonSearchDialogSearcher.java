package org.eclipse.nebula.widgets.modelpicker.example;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.nebula.widgets.modelpicker.ISearcher;
import org.eclipse.nebula.widgets.modelpicker.ModelHolder;
import org.eclipse.nebula.widgets.modelpicker.Utils;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Text;

public class PersonSearchDialogSearcher implements ISearcher {

	private Text text;

	public void init(ModelHolder modelPicker, String keyStroke) {
		text = modelPicker.getText();
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
		TitleAreaDialog dialog = new TitleAreaDialog(text.getShell());
		dialog.open();

	}

}
