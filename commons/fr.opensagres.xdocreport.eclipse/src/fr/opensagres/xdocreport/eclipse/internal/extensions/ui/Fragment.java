package fr.opensagres.xdocreport.eclipse.internal.extensions.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.forms.IDetailsPage;

public class Fragment {

	private final String id;
	private static final String CLASS_ATTR = "class";
	private IConfigurationElement detailsPageElement;

	public Fragment(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setDetailsPageElement(IConfigurationElement detailsPageElement) {
		this.detailsPageElement = detailsPageElement;
	}

	public IDetailsPage createDetailsPage() throws CoreException {
		return (IDetailsPage) detailsPageElement
				.createExecutableExtension(CLASS_ATTR);
	}
}
