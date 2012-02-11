package org.eclipse.nebula.widgets.modelpicker;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.nebula.widgets.modelpicker.internal.Utils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractSearcher<T> implements ISearcher<T> {

	private ModelHolder<T> modelHolder;
	private Text text;

	public void init(ModelHolder<T> modelHolder, String keyStroke) {
		this.text = modelHolder.getText();
		this.modelHolder = modelHolder;
		final KeyStroke triggerKeyStroke = getKeyStroke(keyStroke);

		Listener keyDownListener = new Listener() {

			public void handleEvent(Event event) {
				if (Utils.match(triggerKeyStroke, event)) {
					handle();
				}
			}
		};
		text.addListener(SWT.KeyDown, keyDownListener);
		//Utils.addFilterIfNeeded(keyDownListener, text);
	}

	private KeyStroke getKeyStroke(String keyStroke) {
		try {
			return KeyStroke.getInstance(keyStroke);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Text getText() {
		return text;
	}

	public ModelHolder<T> getModelHolder() {
		return modelHolder;
	}

	public void setModel(T model) {
		getModelHolder().setModel(model);
	}
}
