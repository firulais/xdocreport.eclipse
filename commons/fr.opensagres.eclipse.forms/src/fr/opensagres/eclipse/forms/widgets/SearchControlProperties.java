package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.jface.databinding.swt.IWidgetValueProperty;

public class SearchControlProperties {

	public static IWidgetValueProperty model() {
		return new SearchControlModelProperty();
	}

}
