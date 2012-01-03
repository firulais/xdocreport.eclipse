package org.dynaresume.eclipse.search.ui.modelpickers;

import org.dynaresume.domain.project.Client;
import org.dynaresume.eclipse.search.ui.internal.ImageResources;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.CompletionLabelProvider;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.ICompletionLabelProvider;
import org.eclipse.swt.graphics.Image;

public class ClientCompletionLabelProvider extends CompletionLabelProvider {

	private static final ICompletionLabelProvider INSTANCE = new ClientCompletionLabelProvider();

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
