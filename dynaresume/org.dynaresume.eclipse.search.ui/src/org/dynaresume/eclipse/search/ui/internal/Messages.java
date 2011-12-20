package org.dynaresume.eclipse.search.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.dynaresume.eclipse.search.ui.internal.Messages";//$NON-NLS-1$

	// Buttons
	public static String addButton_label;
	public static String quickAddButton_label;
	public static String removeButton_label;
	public static String searchButton_label;

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
