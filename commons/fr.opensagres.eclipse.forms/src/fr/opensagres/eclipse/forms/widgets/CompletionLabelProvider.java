package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.jface.viewers.LabelProvider;

public abstract class CompletionLabelProvider extends LabelProvider implements
		ICompletionLabelProvider {

	public int getCursorPosition(Object element) {
		return ICompletionLabelProvider.UNDEFINED_CURSOR_POSITION;
	}

	@Override
	public String getText(Object element) {
		return getContent(element);
	}

	public String getDescription(Object element) {
		return null;
	}

}
