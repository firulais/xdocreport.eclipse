package fr.opensagres.xdocreport.eclipse.demo.resume.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages";//$NON-NLS-1$

	public static String ResumeFormEditor_OverviewPage_title;
	
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_desc;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_title;

	public static String ResumeFormEditor_FirstName_label;
	public static String ResumeFormEditor_LastName_label;
	public static String ResumeFormEditor_Birthday_label;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
