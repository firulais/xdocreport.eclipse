package fr.opensagres.xdocreport.eclipse.ui.editors;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

public class SystemFileEditorInput implements IPathEditorInput {

	private final File file;
	private final IPath location;
	
	public SystemFileEditorInput(File file) {
		this.file = file;
		this.location= Path.fromOSString(file.getAbsolutePath()); 
	}

	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return file.getName();
	}

	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPath getPath() {
		return location;
	}

}
