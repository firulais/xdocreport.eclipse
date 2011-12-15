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

import org.eclipse.nebula.widgets.pagination.AbstractPaginationTable;
import org.eclipse.nebula.widgets.pagination.renderers.PageRendererFactory;
import org.eclipse.swt.widgets.Composite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Implementation of the paginated SWT Table {@link AbstractPaginationTable}
 * with Spring Data pagination structure {@link Page} and {@link Pageable}.
 * 
 * @see http://www.springsource.org/spring-data
 */
public class PageableTable extends AbstractPaginationTable<PageableController> {

	private PageLoader pageLoader;

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			int pageSize) {
		this(parent, compositeStyle, tableStyle, pageSize,
				getDefaultBannerTopFactory(), getDefaultBannerBottomFactory(),
				true);
	}

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			int pageSize, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, compositeStyle, tableStyle, pageSize, bannerTopFactory,
				bannerBottomFactory, true);
	}

	public PageableTable(Composite parent, int compositeStyle, int tableStyle,
			PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, compositeStyle, tableStyle, DEFAULT_PAGE_SIZE,
				bannerTopFactory, bannerBottomFactory, true);
	}

	protected PageableTable(Composite parent, int style, int tableStyle,
			int pageSize, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory, boolean createUI) {
		super(parent, style, tableStyle, pageSize, bannerTopFactory,
				bannerBottomFactory, createUI);
	}

	@Override
	protected PageableController createController(int size) {
		return new PageableController(size);
	}

	@Override
	public void refreshPage() {
		PageLoaderStrategyHelper.loadPageAndReplaceItems(getController(),
				viewer, pageLoader);
	}

	/**
	 * Set the page loader to use to load paginated list.
	 * 
	 * @param pageLoader
	 */
	public void setPageLoader(PageLoader pageLoader) {
		this.pageLoader = pageLoader;
	}

	/**
	 * Returns the page loader to use to load paginated list.
	 * 
	 * @return
	 */
	public PageLoader getPageLoader() {
		return pageLoader;
	}

}
