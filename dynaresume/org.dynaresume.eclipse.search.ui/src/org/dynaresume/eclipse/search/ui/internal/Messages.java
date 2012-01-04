package org.dynaresume.eclipse.search.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.eclipse.search.ui.internal.Messages";//$NON-NLS-1$

	// Buttons
	public static String addButton_label;
	public static String quickAddButton_label;
	public static String removeButton_label;
	public static String searchButton_label;

	// SearchClientDialog
	public static String SearchClientDialog_searchCriteriaGroupText;
	public static String SearchClientDialog_searchResultSectionText;
	public static String SearchClientDialog_searchResultSectionDescription;

	// SearchGroupDialog
	public static String SearchGroupDialog_searchCriteriaGroupText;
	public static String SearchGroupDialog_searchResultSectionText;
	public static String SearchGroupDialog_searchResultSectionDescription;

	// SearchProjectDialog
	public static String SearchProjectDialog_searchCriteriaGroupText;
	public static String SearchProjectDialog_searchResultSectionText;
	public static String SearchProjectDialog_searchResultSectionDescription;

	// SearchResumeDialog
	public static String SearchResumeDialog_searchCriteria_label;
	public static String SearchResumeDialog_searchCriteria_FirstName_label;
	public static String SearchResumeDialog_searchCriteria_LastName_label;
	public static String SearchResumeDialog_searchResults_label;
	public static String SearchResumeDialog_searchResults_desc;
	public static String SearchResumeDialog_resumeTable_FirstName_label;
	public static String SearchResumeDialog_resumeTable_LastName_label;
	public static String SearchResumeDialog_resumeTable_ResumeTitle_label;

	// SearchSkillDialog
	public static String SearchSkillDialog_searchCriteriaGroupText;
	public static String SearchSkillDialog_searchResultSectionText;
	public static String SearchSkillDialog_searchResultSectionDescription;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
