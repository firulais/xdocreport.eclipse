/*******************************************************************************
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com>, Pascal Leclercq <pascal.leclercq@gmail.com>
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR - initial API and implementation
 *     Pascal Leclercq - initial API and implementation
 *******************************************************************************/
package org.eclipse.nebula.widgets.pagination;

import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

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

	public static Color getColor(RGB rgb) {
		String key = rgb.toString();
		Color color = JFaceResources.getColorRegistry().get(key);
		if (color == null) {
			JFaceResources.getColorRegistry().put(key, rgb);
		}
		return JFaceResources.getColorRegistry().get(key);
	}
}
