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
package org.eclipse.nebula.widgets.pagination.springdata;

import java.util.List;

import org.eclipse.nebula.widgets.pagination.IPageContentProvider;
import org.springframework.data.domain.Page;

/**
 * Implementation of {@link IPageContentProvider} to retrieves pagination
 * information (total elements and paginated list) from the pagination structure
 * Spring Data {@link Page}.
 * 
 */
public class SpringDataPageContentProvider implements IPageContentProvider {

	private static final IPageContentProvider INSTANCE = new SpringDataPageContentProvider();

	public static IPageContentProvider getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.nebula.widgets.pagination.IPageContentProvider#createController
	 * (int)
	 */
	public SpringDataPageableController createController(int pageSize) {
		return new SpringDataPageableController(pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.nebula.widgets.pagination.IPageContentProvider#getTotalElements
	 * (java.lang.Object)
	 */
	public long getTotalElements(Object page) {
		return ((Page<?>) page).getTotalElements();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.nebula.widgets.pagination.IPageContentProvider#getPaginatedList
	 * (java.lang.Object)
	 */
	public List<?> getPaginatedList(Object page) {
		return ((Page<?>) page).getContent();
	}

}
