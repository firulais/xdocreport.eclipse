package org.eclipse.nebula.widgets.modelpicker;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.events.KeyEvent;

public class Utils {

	public static boolean match(KeyStroke triggerKeyStroke, KeyEvent e) {
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
}
