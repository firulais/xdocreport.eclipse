package org.eclipse.nebula.widgets.pagination.banner;

import org.eclipse.nebula.widgets.pagination.PageControllerChangedListener;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.events.HyperlinkEvent;

public class LinksBannerWidget extends Composite implements
		SelectionListener, PageControllerChangedListener {

	private static final String END_HREF = "</a>";
	private static final String START_HREF = "<a href=\"#\" >";

	private final PaginationController controller;
	private Link firstLink;
	private Link lastLink;
	private Link previousLink;
	private Link nextLink;

	public LinksBannerWidget(PaginationController controller,
			Composite parent, int style) {
		super(parent, style);
		this.controller = controller;
		createUI();
		controller.addPageSelectionListener(this);
		refreshEnabled(controller);
	}

	protected void createUI() {
		GridLayout layout = new GridLayout(3, true);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		createLeftContainer();
		createCenterContainer();
		createRightContainer();
	}

	private void createLeftContainer() {
		Composite left = createComposite(this, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = SWT.BEGINNING;
		left.setLayoutData(data);
		RowLayout layout = new RowLayout();
		layout.marginHeight = 0;
		left.setLayout(layout);

		// First link
		firstLink = createHyperlink(left, SWT.WRAP);
		setLinkText(firstLink, "First");
		firstLink.setLayoutData(new RowData());
		addHyperlinkListener(firstLink, this);

		// Previous link
		previousLink = createHyperlink(left, SWT.WRAP);
		setLinkText(previousLink, "<");
		previousLink.setToolTipText("Previous");
		previousLink.setLayoutData(new RowData());
		addHyperlinkListener(previousLink, this);
	}

	protected void setLinkText(Link link, String text) {
		StringBuilder href = new StringBuilder();
		href.append(START_HREF);
		href.append(text);
		href.append(END_HREF);
		link.setText(href.toString());
	}

	private void addHyperlinkListener(Link link,
			LinksBannerWidget paginationBannerWidget2) {
		link.addSelectionListener(this);
	}

	private void createCenterContainer() {
		Composite center = createComposite(this, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = SWT.CENTER;
		center.setLayoutData(data);
		RowLayout layout = new RowLayout();
		layout.marginHeight = 0;
		center.setLayout(layout);
		// Hyperlink aaLink = super.createHyperlink(center, SWT.WRAP);
		// aaLink.setText("1");
		// aaLink.addHyperlinkListener(this);
	}

	private void createRightContainer() {
		Composite right = createComposite(this, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		right.setLayoutData(data);
		RowLayout layout = new RowLayout();
		layout.marginHeight = 0;
		right.setLayout(layout);

		// Next link
		nextLink = createHyperlink(right, SWT.WRAP);
		setLinkText(nextLink, ">>>");
		nextLink.setToolTipText("Next");
		nextLink.setLayoutData(new RowData());
		addHyperlinkListener(nextLink, this);

		// Last link
		lastLink = createHyperlink(right, SWT.WRAP);
		setLinkText(lastLink, "Last");
		lastLink.setLayoutData(new RowData());
		addHyperlinkListener(lastLink, this);
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	public void widgetSelected(SelectionEvent e) {
		Link hyperlink = (Link) e.getSource();
		int newCurrentPage = 0;
		if (hyperlink == firstLink) {
			newCurrentPage = 0;
		} else if (hyperlink == lastLink) {
			newCurrentPage = controller.getTotalPages() - 1;
		} else if (hyperlink == previousLink) {
			newCurrentPage = controller.getCurrentPage() - 1;
		} else if (hyperlink == nextLink) {
			newCurrentPage = controller.getCurrentPage() + 1;
		}
		controller.setCurrentPage(newCurrentPage);
	}

	public void linkEntered(HyperlinkEvent e) {

	}

	public void linkExited(HyperlinkEvent e) {

	}

	public void pageSelected(int oldPageNumber, int newPageNumber,
			PaginationController controller) {
		refreshEnabled(controller);
	}

	public void totalElementsChanged(long oldTotalElements,
			long newTotalElements, PaginationController controller) {
		refreshEnabled(controller);
	}

	private void refreshEnabled(PaginationController controller) {
		nextLink.setEnabled(controller.hasNextPage());
		previousLink.setEnabled(controller.hasPreviousPage());
	}

	protected Composite createComposite(Composite parent, int style) {
		return new Composite(parent, style);
	}

	protected Link createHyperlink(Composite parent, int style) {
		return new Link(parent, style);
	}

}
