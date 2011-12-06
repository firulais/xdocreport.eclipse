package org.dynaresume.eclipse.ui.internal;

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
	public static final String IMG_EDUCATION_16 = "education_16";
	public static final String IMG_EDUCATION_24 = "education_24";
	public static final String IMG_EXPERIENCES_16 = "experiences_16";
	public static final String IMG_SKILLS_16 = "skills_16";
	public static final String IMG_SKILL_RESUME_16 = "skill_resume_16";
	public static final String IMG_SKILL_RESUME_FREE_16 = "skill_resume_free_16";
	public static final String IMG_LANGUAGES_16 = "language_16";
	public static final String IMG_LANGUAGES_24 = "language_24";
	public static final String IMG_REFERENCES_16 = "references_16";
	public static final String IMG_REFERENCES_24 = "references_24";
	public static final String IMG_HOBBIES_16 = "hobbies_16";
	public static final String IMG_HOBBIES_24 = "hobbies_24";

	public static final String IMG_EMPTY_PHOTO = "empty_photo";
	
	public static void initialize(ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IMG_NEW_RESUME_16, PATH_OBJ_16
				+ "new_resume.gif");
		registerImage(imageRegistry, IMG_SEARCH_RESUME_16, PATH_OBJ_16
				+ "search.gif");
		registerImage(imageRegistry, IMG_RESUME_16, PATH_OBJ_16 + "resume.gif");
		registerImage(imageRegistry, IMG_OVERVIEW_16, PATH_OBJ_16
				+ "overview.gif");
		registerImage(imageRegistry, IMG_EDUCATION_16, PATH_OBJ_16
				+ "education.png");
		registerImage(imageRegistry, IMG_EDUCATION_24, PATH_OBJ_24
				+ "education.png");
		registerImage(imageRegistry, IMG_EXPERIENCES_16, PATH_OBJ_16
				+ "experiences.png");
		registerImage(imageRegistry, IMG_SKILLS_16, PATH_OBJ_16 + "skills.png");
		registerImage(imageRegistry, IMG_SKILL_RESUME_16, PATH_OBJ_16
				+ "skill_resume.png");
		registerImage(imageRegistry, IMG_SKILL_RESUME_FREE_16, PATH_OBJ_16
				+ "skill_resume_free.png");
		registerImage(imageRegistry, IMG_LANGUAGES_16, PATH_OBJ_16
				+ "languages.png");
		registerImage(imageRegistry, IMG_LANGUAGES_24, PATH_OBJ_24
				+ "languages.png");
		registerImage(imageRegistry, IMG_REFERENCES_16, PATH_OBJ_16
				+ "references.png");
		registerImage(imageRegistry, IMG_REFERENCES_24, PATH_OBJ_24
				+ "references.png");
		registerImage(imageRegistry, IMG_HOBBIES_16, PATH_OBJ_16
				+ "hobbies.png");
		registerImage(imageRegistry, IMG_HOBBIES_24, PATH_OBJ_24
				+ "hobbies.png");
		registerImage(imageRegistry, IMG_EMPTY_PHOTO, ICONS_PATH
				+ "EmptyPhoto.jpg");
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
