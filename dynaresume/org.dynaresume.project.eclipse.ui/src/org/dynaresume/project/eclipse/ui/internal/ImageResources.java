package org.dynaresume.project.eclipse.ui.internal;

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
	private static final String PATH_OBJ_48 = ICONS_PATH + "obj48/"; //$NON-NLS-1$


	public static final String IMG_OVERVIEW_16 = "overview_16";
	public static final String IMG_CLIENT_16 = "client_16";
	public static final String IMG_CLIENT_24 = "client_24";
	public static final String IMG_SKILLS_16 = "skills_16";
	public static final String IMG_PROJECT_48 = "project_48";

	public static final String IMG_DESCRIPTIONS_16 = "description_16";
	
	public static void initialize(ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IMG_OVERVIEW_16, PATH_OBJ_16 + "overview.gif");
		registerImage(imageRegistry, IMG_CLIENT_16, PATH_OBJ_16 + "client.png");
		registerImage(imageRegistry, IMG_CLIENT_24, PATH_OBJ_24 + "client.png");
		registerImage(imageRegistry, IMG_SKILLS_16, PATH_OBJ_16 + "skills.png");
		registerImage(imageRegistry, IMG_PROJECT_48, PATH_OBJ_48 + "project.png");
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
