package org.dynaresume.admin.eclipse.ui.graphics.internal;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class ImageResources {

	public final static String ICONS_PATH = "icons/"; //$NON-NLS-1$

	/**
	 * Set of predefined Image Descriptors.
	 */
	private static final String PATH_OBJ_16 = ICONS_PATH + "obj16/"; //$NON-NLS-1$
	private static final String PATH_OBJ_24 = ICONS_PATH + "obj24/"; //$NON-NLS-1$

	public static final String IMG_SKILLS_16 = "skills_16";
	public static final String IMG_CONNECTION_16 = "connection_16";
	public static final String IMG_CONNECTION_24 = "connection_24";

	public static void initialize(ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IMG_SKILLS_16, PATH_OBJ_16 + "skills.png");
		registerImage(imageRegistry, IMG_CONNECTION_16, PATH_OBJ_16
				+ "connection.gif");
		registerImage(imageRegistry, IMG_CONNECTION_24, PATH_OBJ_24
				+ "connection.gif");
	}

	private static void registerImage(ImageRegistry registry, String key,
			String fileName) {
		try {
			IPath path = new Path(fileName);
			Bundle bundle = Activator.getDefault().getBundle();
			URL url = FileLocator.find(bundle, path, null);
			if (url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {
		}
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		return imageRegistry.getDescriptor(key);
	}

	public static Image getImage(String key) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		return imageRegistry.get(key);
	}

}
