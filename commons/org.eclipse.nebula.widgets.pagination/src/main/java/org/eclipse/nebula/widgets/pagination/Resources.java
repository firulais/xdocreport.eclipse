package org.eclipse.nebula.widgets.pagination;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {

	/** Bundle name constant */
	public static final String BUNDLE_NAME = "org.eclipse.nebula.widgets.pagination.resources"; //$NON-NLS-1$

	public static final String PaginationDecorator_results = "PaginationDecorator.results";
	public static final String PaginationDecorator_previous = "PaginationDecorator.previous";
	public static final String PaginationDecorator_next = "PaginationDecorator.next";

	public static String getText(String messageKey, Locale locale) {
		if (locale == null) {
			return ResourceBundle.getBundle(BUNDLE_NAME).getString(messageKey);
		}
		return ResourceBundle.getBundle(BUNDLE_NAME, locale).getString(
				messageKey);
	}
}
