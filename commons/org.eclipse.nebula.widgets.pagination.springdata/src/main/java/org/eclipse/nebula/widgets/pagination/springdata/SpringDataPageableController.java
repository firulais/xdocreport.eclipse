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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.nebula.widgets.pagination.PageableController;
import org.eclipse.swt.SWT;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Extension of pagination controller {@link PageableController} to implement
 * Spring Data {@link Pageable}.
 * 
 * @see http://www.springsource.org/spring-data
 * 
 */
public class SpringDataPageableController extends PageableController implements
		Pageable {

	// Spring Data Sort dooesn't support property null
	private static final String EMPTY_PROPERTY_SORT = "empty";

	// Cache to retrieve Spring Data sort instance by property name.
	private final Map<String, Sort> sortCache;

	// The current sort
	private Sort sort;

	/**
	 * Constructor with page size.
	 * 
	 * @param pageSize
	 *            size of the page (number items displayed per page).
	 */
	public SpringDataPageableController(int pageSize) {
		super(pageSize);
		this.sortCache = new HashMap<String, Sort>();
		this.sort = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Pageable#getOffset()
	 */
	public int getOffset() {
		return getPageOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Pageable#getPageNumber()
	 */
	public int getPageNumber() {
		return getCurrentPage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Pageable#getSort()
	 */
	public Sort getSort() {
		return sort;
	}

	@Override
	public void setSort(String propertyName, int sortDirection) {
		// Sort is applied, update the current Spring Data instance sort.
		this.sort = getSort(propertyName, sortDirection);
		super.setSort(propertyName, sortDirection);
	}

	/**
	 * Returns the Spring Data instance {@link Sort} according the given
	 * property name to sort and direction.
	 * 
	 * @param propertyName
	 *            the sort property name.
	 * @param sortDirection
	 *            the sort direction {@link SWT.UP}, {@link SWT.DOWN}.
	 * @return
	 */
	protected Sort getSort(String propertyName, int sortDirection) {
		String key = getKey(propertyName, sortDirection);
		Sort sort = sortCache.get(key);
		if (sort == null) {
			Direction direction = getDirection(sortDirection);
			// Spring Data Sort dooesn't support property null,
			// EMPTY_PROPERTY_SORT is set if the property is null
			sort = new Sort(direction, propertyName != null ? propertyName
					: EMPTY_PROPERTY_SORT);
			sortCache.put(key, sort);
		}
		return sort;
	}

	/**
	 * Returns the Spring Data {@link Direction} according the given sort
	 * direction {@link SWT.UP} orf {@link SWT.DOWN}.
	 * 
	 * @param sortDirection
	 *            the sort direction {@link SWT.UP}, {@link SWT.DOWN}.
	 * @return
	 */
	protected Direction getDirection(int sortDirection) {
		switch (sortDirection) {
		case SWT.UP:
			return Direction.ASC;
		case SWT.DOWN:
			return Direction.DESC;
		}
		return null;
	}

	/**
	 * Returns the key.
	 * 
	 * @param propertyName
	 *            the sort property name.
	 * @param sortDirection
	 *            the sort direction {@link SWT.UP}, {@link SWT.DOWN}.
	 * @return
	 */
	private String getKey(String propertyName, int sortDirection) {
		StringBuilder key = new StringBuilder();
		key.append(propertyName);
		key.append('_');
		key.append(sortDirection);
		return key.toString();
	}

}
