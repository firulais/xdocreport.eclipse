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
package org.eclipse.nebula.widgets.pagination.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.AbstractPaginationWidget;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.renderers.CompositeRendererFactory;
import org.eclipse.nebula.widgets.pagination.renderers.navigation.ResultAndNavigationPageLinksRendererFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * Abstract class SWT {@link Composite} which host a SWT {@link Table} linked to
 * a pagination controller to display data with pagination. The
 * {@link AbstractPaginationTable#refreshPage()} must be implemented to load
 * paginated data and update the total element of the controller.
 * 
 * This composite is able to to add another {@link Composite} on the top and on
 * the bottom of the table. For instance you can display navigation page links
 * on the top of the table.
 * 
 * @param <T>
 *            the pagination controller.
 */
public abstract class AbstractPaginationTable<T extends PaginationController>
		extends AbstractPaginationWidget<Table, T> {

	/** the table viewer **/
	protected TableViewer viewer;

	/** the table style **/
	private int tableStyle = SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
			| SWT.V_SCROLL;

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
	public AbstractPaginationTable(Composite parent, int style, int tableStyle,
			CompositeRendererFactory pageRendererTopFactory,
			CompositeRendererFactory pageRendererBottomFactory) {
		this(parent, tableStyle, style, DEFAULT_PAGE_SIZE,
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
	 * 
	 */
	public AbstractPaginationTable(Composite parent, int style, int tableStyle,
			int pageSize, CompositeRendererFactory pageRendererTopFactory,
			CompositeRendererFactory pageRendererBottomFactory) {
		this(parent, tableStyle, style, pageSize, pageRendererTopFactory,
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
	protected AbstractPaginationTable(Composite parent, int style,
			int tableStyle, int pageSize,
			CompositeRendererFactory pageRendererTopFactory,
			CompositeRendererFactory pageRendererBottomFactory, boolean createUI) {
		super(parent, style, pageSize, pageRendererTopFactory,
				pageRendererBottomFactory, false);
		this.tableStyle = tableStyle;
		if (createUI) {
			createUI(this);
		}
	}

	@Override
	protected Table createWidget(Composite parent) {
		Table table = createTable(parent);
		this.viewer = new TableViewer(table);
		return table;
	}

	/**
	 * Create the table widget and layout it.
	 * 
	 * @param parent
	 * @return
	 */
	protected Table createTable(Composite parent) {
		Table table = createTable(parent, getTableStyle());
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return table;
	}

	/**
	 * Returns the table style.
	 * 
	 * @return
	 */
	protected int getTableStyle() {
		return tableStyle;
	}

	/**
	 * Returns the table viewer.
	 * 
	 * @return
	 */
	public TableViewer getViewer() {
		return viewer;
	}

	/**
	 * Create a table.
	 * 
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot
	 *            be null)
	 * @param style
	 *            the style of table to constr * @return
	 */
	protected Table createTable(Composite parent, int style) {
		return new Table(parent, style);
	}

	/**
	 * Returns the default page renderer factory for the top region.
	 * 
	 * @return
	 */
	public static CompositeRendererFactory getDefaultPageRendererTopFactory() {
		return ResultAndNavigationPageLinksRendererFactory.getFactory();
	}

	/**
	 * Returns the default page renderer factory for the bottom region.
	 * 
	 * @return
	 */
	public static CompositeRendererFactory getDefaultPageRendererBottomFactory() {
		return null;
	}
}
