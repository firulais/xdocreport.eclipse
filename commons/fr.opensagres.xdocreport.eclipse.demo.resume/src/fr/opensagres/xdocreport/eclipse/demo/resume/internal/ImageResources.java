package fr.opensagres.xdocreport.eclipse.demo.resume.internal;

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

	public static final String IMG_NEW_RESUME_16 = "new_resume_16";
	public static final String IMG_SEARCH_RESUME_16 = "search_resume_16";
	public static final String IMG_RESUME_16 = "resume_16";
	public static final String IMG_OVERVIEW_16 = "overview_16";
	public static final String IMG_DIPLOMA_16 = "diploma_16";
	public static final String IMG_DIPLOMA_24 = "diploma_24";
	public static final String IMG_EXPERIENCES_16 = "experiences_16";
	public static final String IMG_SKILLS_16 = "skills_16";
	public static final String IMG_HOBBIES_16 = "hobbies_16";
	public static final String IMG_HOBBIES_24 = "hobbies_24";

	public static void initialize(ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IMG_NEW_RESUME_16, PATH_OBJ_16
				+ "new_resume.gif");
		registerImage(imageRegistry, IMG_SEARCH_RESUME_16, PATH_OBJ_16
				+ "search.gif");
		registerImage(imageRegistry, IMG_RESUME_16, PATH_OBJ_16 + "resume.gif");
		registerImage(imageRegistry, IMG_OVERVIEW_16, PATH_OBJ_16
				+ "overview.gif");
		registerImage(imageRegistry, IMG_DIPLOMA_16, PATH_OBJ_16
				+ "diploma.png");
		registerImage(imageRegistry, IMG_DIPLOMA_24, PATH_OBJ_24
				+ "diploma.png");
		registerImage(imageRegistry, IMG_EXPERIENCES_16, PATH_OBJ_16
				+ "experiences.png");
		registerImage(imageRegistry, IMG_SKILLS_16, PATH_OBJ_16 + "skills.png");
		registerImage(imageRegistry, IMG_HOBBIES_16, PATH_OBJ_16
				+ "hobbies.png");
		registerImage(imageRegistry, IMG_HOBBIES_24, PATH_OBJ_24
				+ "hobbies.png");		
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
