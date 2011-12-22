package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.jface.viewers.ILabelProvider;

public interface ICompletionLabelProvider extends ILabelProvider {

	int UNDEFINED_CURSOR_POSITION =-1;

	String getContent(Object element);
	
	String getDescription(Object element);

	int getCursorPosition(Object element);
}
