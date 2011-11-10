package fr.opensagres.xdocreport.eclipse.ui.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public abstract class AbstractEditorInput implements IEditorInput {

	public Object getAdapter(Class adapter) {
		return null;
	}

	public boolean exists() {
		return true;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public IPersistableElement getPersistable() {
		return null;
	}

}
