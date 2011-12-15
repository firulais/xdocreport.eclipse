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
import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.events.HyperlinkEvent;

public class ResultAndPageLinksRenderer extends
		AbstractPageControllerComposite<PaginationController> implements
		SelectionListener {

	private static final RGB RED_COLOR = new RGB(255, 0, 0);
	private static final String END_HREF = "</a>";
	private static final String OPEN_START_HREF = "<a href=\"";
	private static final String OPEN_END_HREF = "\" >";

	private Link firstLink;
	private Link lastLink;
	private Link previousLink;
	private Link nextLink;
	private Label resultsLabel;
	private Link pageLinks;

	public ResultAndPageLinksRenderer(PaginationController controller,
			Composite parent, int style) {
		super(parent, style, controller);
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

	private void addHyperlinkListener(Link link,
			ResultAndPageLinksRenderer paginationBannerWidget2) {
		link.addSelectionListener(this);
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
		// right.setOrientation(SWT.RIGHT_TO_LEFT);
		right.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// GridData gridData = new GridData();
		// // Should be
		// // gridData.horizontalAlignment = SWT.RIGHT;
		// // But with this config layout, pageLinks is not filled (problem
		// whern
		// // page index grows the Links is not resized).
		// gridData.horizontalAlignment = SWT.RIGHT;
		// gridData.grabExcessHorizontalSpace = true;
		// right.setLayoutData(gridData);

		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		// layout.wrap = false;
		// layout.fill=true;
		right.setLayout(layout);

		// First link
		// firstLink = createHyperlink(right, SWT.NONE);
		// setLinkText(firstLink, "[First");
		// firstLink.setLayoutData(new GridData());
		// addHyperlinkListener(firstLink, this);
		//
		// Label l = new Label(right, SWT.WRAP);
		// l.setText("/");
		// l.setLayoutData(new GridData());

		// Previous link
		previousLink = createHyperlink(right, SWT.NONE);
		setLinkText(previousLink, Resources.getText(
				Resources.PaginationDecorator_previous, getLocale()));

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;
		// gridData.grabExcessHorizontalSpace = true;
		previousLink.setLayoutData(gridData);

		addHyperlinkListener(previousLink, this);

		pageLinks = createHyperlink(right, SWT.NONE);
		pageLinks.setForeground(getColor());

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		pageLinks.setLayoutData(gridData);

		// setLinkText(pageLinks, "");
		// pageLinks.setToolTipText("Next");
		GridData r = new GridData(GridData.FILL_HORIZONTAL);
		// pageLinks.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addHyperlinkListener(pageLinks, this);

		// Next link
		nextLink = createHyperlink(right, SWT.NONE);
		setLinkText(nextLink, Resources.getText(
				Resources.PaginationDecorator_next, getLocale()));

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.LEFT;

		nextLink.setLayoutData(gridData);
		addHyperlinkListener(nextLink, this);
		//
		// l = new Label(right, SWT.WRAP);
		// l.setText("/");
		// l.setLayoutData(new GridData());
		//
		// // Last link
		// lastLink = createHyperlink(right, SWT.NONE);
		// setLinkText(lastLink, "Last]");
		// lastLink.setLayoutData(new GridData());
		// addHyperlinkListener(lastLink, this);

	}

	public void widgetDefaultSelected(SelectionEvent e) {

	}

	public void widgetSelected(SelectionEvent e) {
		Link hyperlink = (Link) e.getSource();
		int newCurrentPage = 0;
		if (hyperlink == firstLink) {
			newCurrentPage = 0;
		} else if (hyperlink == lastLink) {
			newCurrentPage = getController().getTotalPages() - 1;
		} else if (hyperlink == previousLink) {
			newCurrentPage = getController().getCurrentPage() - 1;
		} else if (hyperlink == nextLink) {
			newCurrentPage = getController().getCurrentPage() + 1;
		} else if (hyperlink == pageLinks) {
			newCurrentPage = Integer.parseInt(e.text);
		}
		getController().setCurrentPage(newCurrentPage);
	}

	public void linkEntered(HyperlinkEvent e) {

	}

	public void linkExited(HyperlinkEvent e) {

	}

	public void pageIndexChanged(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		refreshEnabled(controller);

		StringBuilder s = new StringBuilder();

		int[] indexes = PaginationHelper.getPageIndexes(
				controller.getCurrentPage(), controller.getTotalPages(), 10);
		for (int i = 0; i < indexes.length; i++) {
			int j = indexes[i];
			if (i > 0) {
				s.append(" ");
			}
			if (j == PaginationHelper.DOT) {
				s.append("...");
			} else if (j == newPageNumber)
				s.append("" + (j + 1) + "");
			else
				s.append("<a href=\"" + j + "\">" + (j + 1) + "</a>");

		}
		pageLinks.setText(s.toString());
		// pageLinks.redraw();
		// pageLinks.getParent().getParent().layout();
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
		nextLink.setEnabled(controller.hasNextPage());
		previousLink.setEnabled(controller.hasPreviousPage());
	}

	protected void displayResults(PaginationController controller) {

	}

	protected Composite createComposite(Composite parent, int style) {
		return new Composite(parent, style);
	}

	protected Link createHyperlink(Composite parent, int style) {
		return new Link(parent, style);
	}

	protected void setLinkText(Link link, String text) {
		StringBuilder href = new StringBuilder();
		href.append(OPEN_START_HREF);
		href.append(text);
		href.append(OPEN_END_HREF);
		href.append(text);
		href.append(END_HREF);
		link.setText(href.toString());
	}

	protected Color getColor() {
		return Resources.getColor(RED_COLOR);
	}

	public void sortChanged(String oldPopertyName, String propertyName,
			int oldSortDirection, int sortDirection,
			PaginationController paginationController) {

	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		setLinkText(previousLink, Resources.getText(
				Resources.PaginationDecorator_previous, getLocale()));
		setLinkText(nextLink, Resources.getText(
				Resources.PaginationDecorator_next, getLocale()));
	}
}
