package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;

public class SearchControlModelProperty extends WidgetValueProperty {
	
	
	public SearchControlModelProperty() {
		super(new int[] {9999}, null);
	}
	public Object getValueType() {
		return Object.class;
	}

	protected Object doGetValue(Object source) {
		return ((SearchControl) source).getModel();
	}

	protected void doSetValue(Object source, Object value) {
		((SearchControl) source).setModel(value);
	}

	public String toString() {
		return "SearchControl.model<Object>"; //$NON-NLS-1$
	}

}
