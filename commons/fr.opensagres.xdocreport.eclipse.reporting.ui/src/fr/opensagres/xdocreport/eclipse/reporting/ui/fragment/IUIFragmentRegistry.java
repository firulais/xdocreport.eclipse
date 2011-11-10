package fr.opensagres.xdocreport.eclipse.reporting.ui.fragment;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.forms.IDetailsPage;

public interface IUIFragmentRegistry {

	IDetailsPage createDetailsPage(String fragmentId) throws CoreException;

}
