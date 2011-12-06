package org.dynaresume.project.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.project.eclipse.ui.internal.Messages";//$NON-NLS-1$

	public static String error;

	public static String ProjectFormEditor_OverviewPage_title;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_title;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_desc;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_Name_label;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_Description_label;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_URL_label;

	public static String ClientFormEditor_OverviewPage_title;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_title;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_desc;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_Name_label;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_Description_label;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
