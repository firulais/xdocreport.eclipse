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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.collections.PageListHelper;

/**
 * Basic implementation of {@link PageLoader} to load paginated list from a
 * {@link List} stored in the memory.
 * 
 * @see http://www.springsource.org/spring-data
 */
public class SpringDataPageLoaderList<T> implements ISpringDataPageLoader<T> {

	private List<T> items;

	public SpringDataPageLoaderList(List<T> items) {
		this.items = items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public Page<T> loadPage(SpringDataPageableController pageable) {
		return PageListHelper.createPage(items, pageable);
	}

}
