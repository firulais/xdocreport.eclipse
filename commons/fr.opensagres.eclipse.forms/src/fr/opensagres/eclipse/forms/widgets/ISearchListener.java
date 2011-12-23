package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.swt.internal.SWTEventListener;

public interface ISearchListener extends SWTEventListener {

	void modelChanged(Object oldModel, Object newModel);
}
