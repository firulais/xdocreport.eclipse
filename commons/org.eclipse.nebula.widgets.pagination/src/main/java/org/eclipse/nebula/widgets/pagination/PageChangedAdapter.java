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

/**
 * This adapter class provides default implementations for the methods described
 * by the {@link PageChangedListener} interface.
 * 
 * @see PageChangedListener
 */
public class PageChangedAdapter implements PageChangedListener {

	public void pageChanged(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		// Do nothing
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		// Do nothing
	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {
		// Do nothing
	}

}
