package org.dynaresume.eclipse.search.ui.searchers;

import org.dynaresume.domain.project.Client;
import org.dynaresume.eclipse.search.ui.internal.ImageResources;
import org.eclipse.swt.graphics.Image;

import fr.opensagres.eclipse.forms.widgets.CompletionLabelProvider;
import fr.opensagres.eclipse.forms.widgets.ICompletionLabelProvider;

public class ClientsCompletionLabelProvider extends CompletionLabelProvider {

	private static final ICompletionLabelProvider INSTANCE = new ClientsCompletionLabelProvider();

	public static ICompletionLabelProvider getInstance() {
		return INSTANCE;
	}

	public String getContent(Object element) {
		return ((Client) element).getName();
	}

	@Override
	public Image getImage(Object element) {
		return ImageResources.getImage(ImageResources.IMG_CLIENT_16);
	}

}
