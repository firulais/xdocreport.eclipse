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
package org.eclipse.nebula.widgets.pagination.springdata.table;

import org.eclipse.nebula.widgets.pagination.renderers.CompositeRendererFactory;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoader;
import org.eclipse.nebula.widgets.pagination.springdata.PageLoaderStrategyHelper;
import org.eclipse.nebula.widgets.pagination.springdata.PageableController;
import org.eclipse.nebula.widgets.pagination.table.AbstractPaginationTable;
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

	/** the page loader used to load paginated data list */
	private PageLoader pageLoader;

	/**
	 * Constructs a new instance of this class given its parent and a style
	 * value describing its behavior and appearance. Here defaut page size is
	 * used.
	 * 
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot
	 *            be null)
	 * @param style
	 *            the style of widget to construct
	 * @param tableStyle
	 *            the style of table to construct
	 * @param pageSize
	 *            size of the page (number items displayed per page).
	 * 
	 */
	public PageableTable(Composite parent, int style, int tableStyle,
			int pageSize) {
		this(parent, style, tableStyle, pageSize,
				getDefaultPageRendererTopFactory(),
				getDefaultPageRendererBottomFactory(), true);
	}

	/**
	 * Constructs a new instance of this class given its parent and a style
	 * value describing its behavior and appearance.
	 * 
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot
	 *            be null)
	 * @param style
	 *            the style of widget to construct
	 * @param tableStyle
	 *            the style of table to construct
	 * @param pageSize
	 *            size of the page (number items displayed per page).
	 * 
	 * @param pageRendererTopFactory
	 *            the page renderer factory used to create a SWT Composite on
	 *            the top of the widget. Null if none Composite must be created.
	 * @param pageRendererBottomFactory
	 *            the page renderer factory used to create a SWT Composite on
	 *            the bottom of the widget. Null if none Composite must be
	 *            created.
	 * 
	 */
	public PageableTable(Composite parent, int style, int tableStyle,
			int pageSize, CompositeRendererFactory pageRendererTopFactory,
			CompositeRendererFactory pageRendererBottomFactory) {
		this(parent, style, tableStyle, pageSize, pageRendererTopFactory,
				pageRendererBottomFactory, true);
	}

	/**
	 * Constructs a new instance of this class given its parent and a style
	 * value describing its behavior and appearance.
	 * 
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot
	 *            be null)
	 * @param style
	 *            the style of widget to construct
	 * @param tableStyle
	 *            the style of table to construct
	 * 
	 * @param pageRendererTopFactory
	 *            the page renderer factory used to create a SWT Composite on
	 *            the top of the widget. Null if none Composite must be created.
	 * @param pageRendererBottomFactory
	 *            the page renderer factory used to create a SWT Composite on
	 *            the bottom of the widget. Null if none Composite must be
	 *            created.
	 * 
	 */
	public PageableTable(Composite parent, int style, int tableStyle,
			CompositeRendererFactory pageRendererTopFactory,
			CompositeRendererFactory pageRendererBottomFactory) {
		this(parent, style, tableStyle, DEFAULT_PAGE_SIZE,
				pageRendererTopFactory, pageRendererBottomFactory, true);
	}

	/**
	 * Constructs a new instance of this class given its parent and a style
	 * value describing its behavior and appearance.
	 * 
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot
	 *            be null)
	 * @param style
	 *            the style of widget to construct
	 * @param tableStyle
	 *            the style of table to construct
	 * @param pageSize
	 *            size of the page (number items displayed per page).
	 * 
	 * @param pageRendererTopFactory
	 *            the page renderer factory used to create a SWT Composite on
	 *            the top of the widget. Null if none Composite must be created.
	 * @param pageRendererBottomFactory
	 *            the page renderer factory used to create a SWT Composite on
	 *            the bottom of the widget. Null if none Composite must be
	 *            created.
	 * @param createUI
	 *            true if the UI must be created and false otherwise.
	 * 
	 */
	protected PageableTable(Composite parent, int style, int tableStyle,
			int pageSize, CompositeRendererFactory pageRendererTopFactory,
			CompositeRendererFactory pageRendererBottomFactory, boolean createUI) {
		super(parent, style, tableStyle, pageSize, pageRendererTopFactory,
				pageRendererBottomFactory, createUI);
	}

	@Override
	protected PageableController createController(int size) {
		return new PageableController(size);
	}

	@Override
	public void refreshPage() {
		PageLoaderStrategyHelper.loadPageAndReplaceItems(getController(),
				viewer, pageLoader, null);
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
