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

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;

/**
 * 
 * Pagination controller.
 * 
 */
public class PaginationController {

	private static final int DEFAULT_PAGE_INDEX = -1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	private int currentPage;
	private int pageSize;
	private long totalElements;
	private String sortPropertyName;
	private int sortDirection;

	private ListenerList pageChangedListeners = new ListenerList();

	public PaginationController() {
		this(DEFAULT_PAGE_SIZE);
	}

	public PaginationController(int pageSize) {
		this.currentPage = DEFAULT_PAGE_INDEX;
		this.pageSize = pageSize;
		this.totalElements = 0;
		this.sortPropertyName = null;
		this.sortDirection = SWT.NONE;
	}

	public void addPageChangedListener(PageChangedListener listener) {
		if (listener == null) {
			throw new NullPointerException(
					"Cannot add a null page changed listener"); //$NON-NLS-1$
		}
		pageChangedListeners.add(listener);
	}

	public void removePageChangedListener(PageChangedListener listener) {
		if (listener != null) {
			pageChangedListeners.remove(listener);
		}
	}

	/**
	 * Returns if there is a previous page.
	 * 
	 * @return if there is a previous page
	 */
	public boolean hasPreviousPage() {
		return getCurrentPage() > 0;
	}

	/**
	 * Returns whether the current page is the first one.
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return !hasPreviousPage();
	}

	/**
	 * Returns if there is a next page.
	 * 
	 * @return if there is a next page
	 */
	public boolean hasNextPage() {
		return ((getCurrentPage() + 1) * getPageSize()) < totalElements;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (this.currentPage != currentPage) {
			int oldPageNumber = this.currentPage;
			this.currentPage = currentPage;
			notifyListenersForPageIndexChanged(oldPageNumber, currentPage);
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (this.pageSize != pageSize) {
			int oldPageSize = this.pageSize;
			this.pageSize = pageSize;
			notifyListenersForPageSizeChanged(oldPageSize, pageSize);
		}
	}

	public int getTotalPages() {
		return getPageSize() == 0 ? 0 : (int) Math.ceil((double) totalElements
				/ (double) getPageSize());
	}

	/**
	 * Returns whether the current page is the last one.
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return !hasNextPage();
	}

	public void setTotalElements(long totalElements) {
		if (this.totalElements != totalElements) {
			long oldTotalElements = this.totalElements;
			this.totalElements = totalElements;
			notifyListenersForTotalElementsChanged(oldTotalElements,
					totalElements);
		}
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getPageOffset() {
		return getCurrentPage() * getPageSize();
	}

	/**
	 * Update the sort.
	 * 
	 * @param propertyName
	 *            the sort property name.
	 * @param controller
	 *            the controller to update when sort is applied.
	 */
	public void setSort(String propertyName, int sortDirection) {
		if (this.sortPropertyName != propertyName
				|| this.sortDirection != sortDirection) {
			String oldPopertyName = this.sortPropertyName;
			this.sortPropertyName = propertyName;
			int oldSortDirection = this.sortDirection;
			this.sortDirection = sortDirection;
			notifyListenersForSortChanged(oldPopertyName, propertyName,
					oldSortDirection, sortDirection);
		}
	}

	public String getSortPropertyName() {
		return sortPropertyName;
	}

	public int getSortDirection() {
		return sortDirection;
	}

	public void reset() {
		int oldCurrentPage = currentPage;
		this.currentPage = 0;
		notifyListenersForPageIndexChanged(oldCurrentPage, currentPage);
		// this.propertyName = null;
		// this.sortDirection = SWT.NONE;
	}

	private void notifyListenersForPageIndexChanged(int oldPageNumber,
			int newPageNumber) {
		final Object[] listeners = pageChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final PageChangedListener listener = (PageChangedListener) listeners[i];
			listener.pageIndexChanged(oldPageNumber, newPageNumber, this);
		}
	}

	private void notifyListenersForTotalElementsChanged(long oldTotalElements,
			long newTotalElements) {
		final Object[] listeners = pageChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final PageChangedListener listener = (PageChangedListener) listeners[i];
			listener.totalElementsChanged(oldTotalElements, newTotalElements,
					this);
		}
	}

	private void notifyListenersForSortChanged(String oldPopertyName,
			String propertyName, int oldSortDirection, int sortDirection) {
		final Object[] listeners = pageChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final PageChangedListener listener = (PageChangedListener) listeners[i];
			listener.sortChanged(oldPopertyName, propertyName,
					oldSortDirection, sortDirection, this);
		}
	}

	private void notifyListenersForPageSizeChanged(int oldPageSize,
			int newPageSize) {
		final Object[] listeners = pageChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final PageChangedListener listener = (PageChangedListener) listeners[i];
			listener.pageSizeChanged(oldPageSize, newPageSize, this);
		}
	}

}
