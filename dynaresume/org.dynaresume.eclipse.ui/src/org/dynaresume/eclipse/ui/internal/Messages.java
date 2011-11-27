package org.dynaresume.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.eclipse.ui.internal.Messages";//$NON-NLS-1$

	// Buttons
	public static String addButton_label;
	public static String quickAddButton_label;
	public static String removeButton_label;
	public static String searchButton_label;

	// Overview Page
	public static String ResumeFormEditor_OverviewPage_title;

	// Overview/General Info section
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_title;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_desc;

	public static String ResumeFormEditor_OverviewPage_GeneralInfo_FirstName_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_LastName_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_Birthday_label;
	public static String ResumeFormEditor_OverviewPage_GeneralInfo_Email_label;

	// Overview/Adress section
	public static String ResumeFormEditor_OverviewPage_Address_title;
	public static String ResumeFormEditor_OverviewPage_Address_desc;

	public static String ResumeFormEditor_OverviewPage_Address_Street_label;
	public static String ResumeFormEditor_OverviewPage_Address_City_label;
	public static String ResumeFormEditor_OverviewPage_Address_ZipCode_label;
	public static String ResumeFormEditor_OverviewPage_Address_Country_label;
	public static String ResumeFormEditor_OverviewPage_Address_Telephone_label;
	public static String ResumeFormEditor_OverviewPage_Address_Fax_label;

	// Overview/Resume content section
	public static String ResumeFormEditor_OverviewPage_ResumeContent_title;
	public static String ResumeFormEditor_OverviewPage_ResumeContent_content;

	// Overview/General Info section
	public static String ResumeFormEditor_OverviewPage_ResumeInfo_title;
	public static String ResumeFormEditor_OverviewPage_ResumeInfo_desc;
	public static String ResumeFormEditor_OverviewPage_ResumeInfo_Title_label;
	public static String ResumeFormEditor_OverviewPage_ResumeInfo_Photo_label;
	
	// Educations Page
	public static String ResumeFormEditor_EducationsPage_title;
	public static String ResumeFormEditor_EducationsPage_EducationsMasterDetailsBlock_title;
	public static String ResumeFormEditor_EducationsPage_EducationsMasterDetailsBlock_desc;
	public static String ResumeFormEditor_EducationsPage_EducationDetailsPage_title;
	public static String ResumeFormEditor_EducationsPage_EducationDetailsPage_desc;	
	public static String ResumeFormEditor_EducationsPage_EducationDetailsPage_educationStartDate_label;
	public static String ResumeFormEditor_EducationsPage_EducationDetailsPage_educationEndDate_label;
	public static String ResumeFormEditor_EducationsPage_EducationDetailsPage_educationLabel_label;
	public static String ResumeFormEditor_EducationsPage_EducationDetailsPage_educationInstitute_label;

	// Experiences Page
	public static String ResumeFormEditor_ExperiencesPage_title;
	public static String ResumeFormEditor_ExperiencesPage_ExperiencesMasterDetailsBlock_title;
	public static String ResumeFormEditor_ExperiencesPage_ExperiencesMasterDetailsBlock_desc;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_title;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_desc;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceStartDate_label;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceEndDate_label;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceTitle_label;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceMission_label;
	public static String ResumeFormEditor_ExperiencesPage_ExperienceDetailsPage_experienceDetail_label;

	// Skills Page
	public static String ResumeFormEditor_SkillsPage_title;
	public static String ResumeFormEditor_SkillsPage_SkillsMasterDetailsBlock_title;
	public static String ResumeFormEditor_SkillsPage_SkillsMasterDetailsBlock_desc;
	public static String ResumeFormEditor_SkillsPage_SkillDetailsPage_title;
	public static String ResumeFormEditor_SkillsPage_SkillDetailsPage_desc;
	public static String ResumeFormEditor_SkillsPage_SkillDetailsPage_skillLabel_label;
	public static String ResumeFormEditor_SkillsPage_SkillDetailsPage_skillURL_label;
	public static String ResumeFormEditor_SkillsPage_SkillDetailsPage_skillDescription_label;
	public static String ResumeFormEditor_SkillsPage_SkillDetailsPage_skillFreeLabel_label;
	
	// Hobbies Page
	public static String ResumeFormEditor_HobbiesPage_title;
	public static String ResumeFormEditor_HobbiesPage_HobbiesMasterDetailsBlock_title;
	public static String ResumeFormEditor_HobbiesPage_HobbiesMasterDetailsBlock_desc;
	public static String ResumeFormEditor_HobbiesPage_HobbyDetailsPage_title;
	public static String ResumeFormEditor_HobbiesPage_HobbyDetailsPage_desc;
	public static String ResumeFormEditor_HobbiesPage_HobbyDetailsPage_hobbyLabel_label;

	// Quick Add Skill wizard
	public static String QuickAddSillFillSkillsWizardPage_title;
	public static String QuickAddSillFillSkillsWizardPage_desc;
	public static String QuickAddSillFillSkillsWizardPage_Skills_label;
	public static String QuickAddSillFillSkillsWizardPage_skillsText_required;
	public static String QuickAddSillsPreviewWizardPage_title;
	public static String QuickAddSillsPreviewWizardPage_desc;
	public static String QuickAddSillsPreviewWizardPage_skillsTable_label;
	public static String QuickAddSillsPreviewWizardPage_freeSkillsTable_label;
	public static String QuickAddSillsPreviewWizardPage_existingSkillsTable_label;

	// SearchResumeDialog
	public static String SearchResumeDialog_searchCriteria_label;
	public static String SearchResumeDialog_searchCriteria_FirstName_label;
	public static String SearchResumeDialog_searchCriteria_LastName_label;
	public static String SearchResumeDialog_searchResults_label;
	public static String SearchResumeDialog_searchResults_desc;
	public static String SearchResumeDialog_resumeTable_FirstName_label;
	public static String SearchResumeDialog_resumeTable_LastName_label;
	public static String SearchResumeDialog_resumeTable_ResumeTitle_label;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
