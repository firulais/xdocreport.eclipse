package org.eclipse.nebula.widgets.modelpicker.fieldassist;

import org.eclipse.jface.viewers.ILabelProvider;

public interface ICompletionLabelProvider extends ILabelProvider {

	int UNDEFINED_CURSOR_POSITION =-1;

	String getContent(Object element);
	
	String getDescription(Object element);

	int getCursorPosition(Object element);
}
