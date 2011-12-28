package org.eclipse.nebula.widgets.modelpicker.example;

import org.eclipse.nebula.widgets.modelpicker.example.model.Person;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.CompletionLabelProvider;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.ICompletionLabelProvider;

public class PersonCompletionLabelProvider extends CompletionLabelProvider {

	private static ICompletionLabelProvider INSTANCE = new PersonCompletionLabelProvider();

	public static ICompletionLabelProvider getInstance() {
		return INSTANCE;
	}

	public String getContent(Object element) {
		return ((Person) element).getFirstName();
	}

}
