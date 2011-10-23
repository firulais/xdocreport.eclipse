package fr.opensagres.eclipse.forms.internal;

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
	private static final String PATH_OBJ = ICONS_PATH + "obj16/"; //$NON-NLS-1$

	public static final String IMG_CALENDAR = "calendar";
	public static final String IMG_DEFAULT_PHOTO = "default_photo";
	public static final String IMG_TH_HORIZONTAL = "th_horizontal";
	public static final String IMG_TH_VERTICAL = "th_vertical";

	public static void initialize(ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IMG_CALENDAR, PATH_OBJ + "calendar.png");
		registerImage(imageRegistry, IMG_DEFAULT_PHOTO, PATH_OBJ + "default_photo.jpg");
		registerImage(imageRegistry, IMG_TH_HORIZONTAL, PATH_OBJ + "th_horizontal.gif");		
		registerImage(imageRegistry, IMG_TH_VERTICAL, PATH_OBJ + "th_vertical.gif");
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
