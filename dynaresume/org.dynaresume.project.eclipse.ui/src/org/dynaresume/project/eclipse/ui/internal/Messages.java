package org.dynaresume.project.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.project.eclipse.ui.internal.Messages";//$NON-NLS-1$

	public static String error;
	public static String addButton_label;
	public static String removeButton_label;
	
	public static String ProjectFormEditor_OverviewPage_title;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_title;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_desc;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_Name_label;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_Client_label;
	public static String ProjectFormEditor_OverviewPage_GeneralInfo_URL_label;
	
	public static String ProjectFormEditor_OverviewPage_Description_title;
	public static String ProjectFormEditor_OverviewPage_Description_desc;	

	public static String ProjectFormEditor_OverviewPage_ProjectContent_title;
	public static String ProjectFormEditor_OverviewPage_ProjectContent_content;
	
	public static String ProjectFormEditor_DescriptionsPage_title;
	public static String ResumeFormEditor_DescriptionsPage_DescriptionsMasterDetailsBlock_title;
	public static String ResumeFormEditor_DescriptionsPage_DescriptionsMasterDetailsBlock_desc;
	public static String ProjectFormEditor_DescriptionsPage_DescriptionDetailsPage_title;
	public static String ProjectFormEditor_DescriptionsPage_DescriptionDetailsPage_desc;
	public static String ProjectFormEditor_DescriptionsPage_DescriptionDetailsPage_description_label;
	public static String ProjectFormEditor_DescriptionsPage_DescriptionTypeDetailsPage_title;
	public static String ProjectFormEditor_DescriptionsPage_DescriptionTypeDetailsPage_desc;
	public static String ProjectFormEditor_DescriptionsPage_DescriptionTypeDetailsPage_label_label;
	
	public static String ProjectFormEditor_SkillsPage_title;	
	
	public static String ClientFormEditor_OverviewPage_title;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_title;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_desc;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_Name_label;
	public static String ClientFormEditor_OverviewPage_GeneralInfo_Description_label;

	// CreateProjectWizardPage
	public static String CreateProjectWizardPage_title;
	public static String CreateProjectWizardPage_desc;
	public static String CreateProjectWizardPage_projectName_label;
	public static String CreateProjectWizardPage_projectDescription_label;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
