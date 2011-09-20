package fr.opensagres.xdocreport.eclipse.extensions.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.forms.IDetailsPage;

public interface IUIFragmentRegistry {

	IDetailsPage createDetailsPage(String fragmentId) throws CoreException;

}
