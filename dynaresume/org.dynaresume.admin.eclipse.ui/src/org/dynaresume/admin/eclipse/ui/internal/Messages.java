package org.dynaresume.admin.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.admin.eclipse.ui.internal.Messages";//$NON-NLS-1$

	// Buttons
	public static String addButton_label;
	public static String removeButton_label;

	// Overview Page	
	public static String SkillFormEditor_OverviewPage_title;
	public static String SkillFormEditor_OverviewPage_GeneralInfo_title;
	public static String SkillFormEditor_OverviewPage_GeneralInfo_desc;
	public static String SkillFormEditor_OverviewPage_GeneralInfo_Name_label;
	public static String SkillFormEditor_OverviewPage_GeneralInfo_Code_label;
	public static String SkillFormEditor_OverviewPage_GeneralInfo_Description_label;
	public static String SkillFormEditor_OverviewPage_GeneralInfo_URL_label;
	
	public static String GroupFormEditor_OverviewPage_title;

	public static String error;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
