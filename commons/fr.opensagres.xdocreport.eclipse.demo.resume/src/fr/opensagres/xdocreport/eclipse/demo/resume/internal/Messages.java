package fr.opensagres.xdocreport.eclipse.demo.resume.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages";//$NON-NLS-1$

	// Buttons
	public static String addButton_label;
	public static String removeButton_label;

	// Overview Page
	public static String ResumeFormEditor_OverviewPage_title;
	
	// Overview/General Info section
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_title;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_desc;
	
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_FirstName_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_LastName_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_Birthday_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_Email_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_Photo_label;
	
	// Overview/Resume content section
	public static String ResumeFormEditor_OverviewPage_ResumeContent_title;
	public static String ResumeFormEditor_OverviewPage_ResumeContent_content;	

	// Diploma Page
	public static String ResumeFormEditor_DiplomasPage_title;
	public static String ResumeFormEditor_DiplomasPage_DiplomasMasterDetailsBlock_title;
	public static String ResumeFormEditor_DiplomasPage_DiplomasMasterDetailsBlock_desc;
	public static String ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_title;
	public static String ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_desc;
	public static String ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_diplomaLabel_label;
	public static String ResumeFormEditor_DiplomasPage_DiplomaDetailsPage_diplomaInstitute_label;
	
	// Experiences Page
	public static String ResumeFormEditor_ExperiencesPage_title;
	public static String ResumeFormEditor_ExperiencesPage_ExperiencesMasterDetailsBlock_title;
	public static String ResumeFormEditor_ExperiencesPage_ExperiencesMasterDetailsBlock_desc;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_title;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_desc;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceTitle_label;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceMission_label;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceDetail_label;
	
	// Skills Page
	public static String ResumeFormEditor_SkillsPage_title;

	// Hobbies Page
	public static String ResumeFormEditor_HobbiesPage_title;
	public static String ResumeFormEditor_HobbiesPage_HobbiesMasterDetailsBlock_title;
	public static String ResumeFormEditor_HobbiesPage_HobbiesMasterDetailsBlock_desc;
	public static String ResumeFormEditor_HobbiesPage_HobbyDetailsPage_title;
	public static String ResumeFormEditor_HobbiesPage_HobbyDetailsPage_desc;
	public static String ResumeFormEditor_HobbiesPage_HobbyDetailsPage_hobbyLabel_label;
	
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
