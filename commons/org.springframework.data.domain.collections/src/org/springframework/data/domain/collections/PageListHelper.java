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
package org.springframework.data.domain.collections;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Helper to create implementation of Spring Data {@link Page} from a Java
 * {@link List}.
 * 
 */
public class PageListHelper {

	public static Page createPage(List<?> list, Pageable pageable) {
		return createPage(list, pageable, DefaultSortProcessor.getInstance());
	}

	/**
	 * Create Spring Data {@link Page} from the given list and pageable.
	 * 
	 * @param list
	 * @param pageable
	 * @return
	 */
	public static Page createPage(List<?> list, Pageable pageable,
			SortProcessor processor) {
		Sort sort = pageable.getSort();
		if (sort != null) {
			// Sort the list
			processor.sort(list, sort);
		}

		int totalSize = list.size();
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();

		int fromIndex = pageIndex;
		int toIndex = pageIndex + pageSize;
		if (toIndex > totalSize) {
			toIndex = totalSize;
		}
		List content = list.subList(fromIndex, toIndex);
		return new PageImpl(content, pageable, totalSize);

	}
}
