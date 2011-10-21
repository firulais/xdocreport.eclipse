package fr.opensagres.xdocreport.eclipse.demo.resume.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages";//$NON-NLS-1$

	// Overview Page
	public static String ResumeFormEditor_OverviewPage_title;
	
	// Overview/General Info section
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_title;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_desc;
	
	public static String ResumeFormEditor_FirstName_label;
	public static String ResumeFormEditor_LastName_label;
	public static String ResumeFormEditor_Birthday_label;
	public static String ResumeFormEditor_Photo_label;
	public static String OverviewPage_lblNewLabel_text;
	public static String OverviewPage_txtNewText_text;
	
	// Overview/Resume content section
	public static String ResumeFormEditor_OverviewPage_ResumeContent_title;
	public static String ResumeFormEditor_OverviewPage_ResumeContent_content;	

	// Overview/Experiences Page
	public static String ResumeFormEditor_ExperiencesPage_title;
	
	// Skills Page
	public static String ResumeFormEditor_SkillsPage_title;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
