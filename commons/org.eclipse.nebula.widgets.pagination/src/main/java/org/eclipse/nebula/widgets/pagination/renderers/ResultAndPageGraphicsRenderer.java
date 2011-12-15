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
package org.eclipse.nebula.widgets.pagination.renderers;

import java.util.Locale;

import org.eclipse.nebula.widgets.pagination.AbstractPageControllerComposite;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.PaginationHelper;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.BlueGraphicsPageNavigationConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.GraphicsPageNavigation;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.GraphicsPageNavigationConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.GraphicsPageNavigationItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.HyperlinkEvent;

public class ResultAndPageGraphicsRenderer extends
		AbstractPageControllerComposite<PaginationController> {

	private Label resultsLabel;
	private GraphicsPageNavigation pageItems;
	private final GraphicsPageNavigationConfigurator configurator;

	public ResultAndPageGraphicsRenderer(PaginationController controller,
			Composite parent, int style) {
		this(controller, parent, style, BlueGraphicsPageNavigationConfigurator
				.getInstance());
	}

	public ResultAndPageGraphicsRenderer(PaginationController controller,
			Composite parent, int style, GraphicsPageNavigationConfigurator configurator) {
		super(parent, style, DEFAULT_PAGE_SIZE, controller, false);
		this.configurator = configurator;
		createUI(this);
		refreshEnabled(controller);
	}

	protected void createUI(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		createLeftContainer(parent);
		createRightContainer(parent);
	}

	private void createLeftContainer(Composite parent) {
		Composite left = createComposite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		left.setLayoutData(data);

		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		left.setLayout(layout);

		resultsLabel = new Label(left, SWT.NONE);
		resultsLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	private void createRightContainer(Composite parent) {
		Composite right = createComposite(parent, SWT.NONE);
		right.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		right.setLayout(layout);

		pageItems = new GraphicsPageNavigation(right, SWT.NONE, configurator) {
			@Override
			protected void handleSelection(GraphicsPageNavigationItem pageItem) {
				Integer newCurrentPage = null;
				if (!pageItem.isEnabled()) {
					return;
				}
				if (pageItem.isNext()) {
					newCurrentPage = getController().getCurrentPage() + 1;
				} else if (pageItem.isPrevious()) {
					newCurrentPage = getController().getCurrentPage() - 1;
				} else {
					newCurrentPage = pageItem.getIndex();
				}
				if (newCurrentPage != null) {
					getController().setCurrentPage(newCurrentPage);
				}
			}
		};
		pageItems.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	}

	public void widgetDefaultSelected(SelectionEvent e) {

	}

	public void linkEntered(HyperlinkEvent e) {

	}

	public void linkExited(HyperlinkEvent e) {

	}

	public void pageIndexChanged(int oldPageNumber, int newPageNumber,
			PaginationController controller) {

		StringBuilder s = new StringBuilder();

		int[] indexes = PaginationHelper.getPageIndexes(
				controller.getCurrentPage(), controller.getTotalPages(), 10);

		pageItems.setIndexes(indexes);
		pageItems.setPageIndexSelected(newPageNumber);
		refreshEnabled(controller);
	}

	public void pageSizeChanged(int oldPageSize, int newPageSize,
			PaginationController paginationController) {
		// TODO Auto-generated method stub

	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		refreshEnabled(controller);
	}

	private void refreshEnabled(PaginationController controller) {
		resultsLabel.setText(PaginationHelper.getResultsText(controller,
				getLocale()));
		pageItems.setNextEnabled(controller.hasNextPage());
		pageItems.setPreviousEnabled(controller.hasPreviousPage());
	}

	protected void displayResults(PaginationController controller) {

	}

	protected Composite createComposite(Composite parent, int style) {
		return new Composite(parent, style);
	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {

	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);

		// setLinkText(previousLink, Resources.getText(
		// Resources.PaginationDecorator_previous, getLocale()));
		// setLinkText(nextLink, Resources.getText(
		// Resources.PaginationDecorator_next, getLocale()));
	}

	public GraphicsPageNavigation getGraphicsPage() {
		return pageItems;
	}
}
