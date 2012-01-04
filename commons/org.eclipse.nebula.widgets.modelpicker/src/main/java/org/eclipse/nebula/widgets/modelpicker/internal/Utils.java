package org.eclipse.nebula.widgets.modelpicker.internal;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class Utils {

	private static final boolean IS_RAP = "rap".equals(SWT.getPlatform());

	public static boolean match(KeyStroke triggerKeyStroke, Event e) {
		if (triggerKeyStroke != null) {
			// Either there are no modifiers for the trigger and we
			// check the character field...
			if ((triggerKeyStroke.getModifierKeys() == KeyStroke.NO_KEY && triggerKeyStroke
					.getNaturalKey() == e.character) ||
			// ...or there are modifiers, in which case the
			// keycode and state must match
					(triggerKeyStroke.getNaturalKey() == e.keyCode && ((triggerKeyStroke
							.getModifierKeys() & e.stateMask) == triggerKeyStroke
							.getModifierKeys()))) {
				// We never propagate the keystroke for an explicit
				// keystroke invocation of the popup
				return true;
			}
		}
		return false;
	}

	public static boolean isRap() {
		return IS_RAP;
	}

	public static void addFilterIfNeeded(final Listener keyDownListener,
			Control control) {
		if (isRap()) {
			Display.getCurrent().addFilter(SWT.KeyDown, keyDownListener);
			control.addDisposeListener(new DisposeListener() {

				public void widgetDisposed(DisposeEvent e) {
					Display.getCurrent().removeFilter(SWT.KeyDown,
							keyDownListener);
				}
			});
		}
	}
}
