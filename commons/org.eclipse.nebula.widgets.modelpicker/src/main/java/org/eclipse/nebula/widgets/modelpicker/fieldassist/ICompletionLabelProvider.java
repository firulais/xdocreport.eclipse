package org.eclipse.nebula.widgets.modelpicker.fieldassist;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.nebula.widgets.modelpicker.IModelLabelProvider;

public interface ICompletionLabelProvider extends ILabelProvider,
		IModelLabelProvider {

	int UNDEFINED_CURSOR_POSITION = -1;

	String getDescription(Object element);

	int getCursorPosition(Object element);
}
