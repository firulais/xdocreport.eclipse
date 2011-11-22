package org.dynaresume.admin.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.admin.eclipse.ui.internal.Messages";//$NON-NLS-1$

	// Buttons
	public static String addButton_label;
	public static String removeButton_label;

	// Overview Page
	public static String SkillsFormEditor_OverviewPage_title;
	
	public static String GroupFormEditor_OverviewPage_title;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
