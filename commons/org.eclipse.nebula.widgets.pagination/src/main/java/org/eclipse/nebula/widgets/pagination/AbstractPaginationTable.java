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

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.renderers.PageRendererFactory;
import org.eclipse.nebula.widgets.pagination.renderers.ResultAndPageLinksRendererFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public abstract class AbstractPaginationTable<T extends PaginationController>
		extends AbstractPageControllerComposite<T> {

	protected TableViewer viewer;
	private PageRendererFactory bannerTopFactory;
	private PageRendererFactory bannerBottomFactory;
	private int tableStyle = SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
			| SWT.V_SCROLL;
	private Table table;
	private Composite bannerTop;
	private Composite bannerBottom;

	public AbstractPaginationTable(Composite parent, int style, int tableStyle,
			PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, tableStyle, style, getDefaultPageSize(), bannerTopFactory,
				bannerBottomFactory, true);
	}

	public AbstractPaginationTable(Composite parent, int style, int tableStyle,
			int pageSize, PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory) {
		this(parent, tableStyle, style, pageSize, bannerTopFactory,
				bannerBottomFactory, true);
	}

	protected AbstractPaginationTable(Composite parent, int style,
			int tableStyle, int pageSize,
			PageRendererFactory bannerTopFactory,
			PageRendererFactory bannerBottomFactory, boolean createUI) {
		super(parent, style, pageSize, null, false);
		this.tableStyle = tableStyle;
		this.bannerTopFactory = bannerTopFactory;
		this.bannerBottomFactory = bannerBottomFactory;
		if (createUI) {
			createUI(this);
		}
	}

	@Override
	protected void createUI(Composite parent) {
		this.setLayout(new GridLayout());
		createBannerTop(parent);
		this.table = createTable(parent);
		PaginationHelper.setPaginationTable(table, this);
		this.viewer = new TableViewer(table);
		createBannerBottom(parent);
		super.setLocale(Locale.getDefault());
	}

	private void createBannerTop(Composite parent) {
		PageRendererFactory bannerTopFactory = getBannerTopFactory();
		if (bannerTopFactory != null) {
			bannerTop = bannerTopFactory.createRenderer(getController(),
					parent, SWT.NONE);
			bannerTop.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}

	private void createBannerBottom(Composite parent) {
		PageRendererFactory bannerBottomFactory = getBannerBottomFactory();
		if (bannerBottomFactory != null) {
			bannerBottom = bannerBottomFactory.createRenderer(getController(),
					parent, SWT.NONE);
			bannerBottom.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}

	protected Table createTable(Composite parent) {
		Table table = createTable(parent, getTableStyle());
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return table;
	}

	protected int getTableStyle() {
		return tableStyle;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public PageRendererFactory getBannerBottomFactory() {
		return bannerBottomFactory;
	}

	public PageRendererFactory getBannerTopFactory() {
		return bannerTopFactory;
	}

	public static PageRendererFactory getDefaultBannerTopFactory() {
		return ResultAndPageLinksRendererFactory.getFactory();
	}

	public static PageRendererFactory getDefaultBannerBottomFactory() {
		return null;
	}

	public Table getTable() {
		return table;
	}

	protected Table createTable(Composite parent, int style) {
		return new Table(parent, style);
	}

	public void pageIndexChanged(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		refreshPage();
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {

	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {
		refreshPage();
	}

	public void refreshPage(boolean reset) {
		if (reset) {
			getController().reset();
		} else {
			refreshPage();
		}
	}

	public void pageSizeChanged(int oldPageSize, int newPageSize,
			PaginationController paginationController) {
		refreshPage(false);
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		if (bannerTop != null
				&& bannerTop instanceof AbstractPageControllerComposite<?>) {
			((AbstractPageControllerComposite) bannerTop).setLocale(locale);
		}
		if (bannerBottom != null
				&& bannerBottom instanceof AbstractPageControllerComposite<?>) {
			((AbstractPageControllerComposite) bannerBottom).setLocale(locale);
		}
	}

	public abstract void refreshPage();
}
