package org.eclipse.nebula.widgets.pagination.decorators;

import java.util.Locale;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.nebula.widgets.pagination.AbstractPageControllerComposite;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.PaginationHelper;
import org.eclipse.nebula.widgets.pagination.Resources;
import org.eclipse.nebula.widgets.pagination.decorators.draw.PageItem;
import org.eclipse.nebula.widgets.pagination.decorators.draw.PageItems;
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

public class ResultAndPageButtonsDecorator extends
		AbstractPageControllerComposite<PaginationController> implements
		SelectionListener {

	private static final String END_HREF = "</a>";
	private static final String OPEN_START_HREF = "<a href=\"";
	private static final String OPEN_END_HREF = "\" >";

	private Link previousLink;
	private Link nextLink;
	private Label resultsLabel;
	private int maxLinks = 10;
	private PageItems pageItems;

	public ResultAndPageButtonsDecorator(PaginationController controller,
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
			ResultAndPageButtonsDecorator paginationBannerWidget2) {
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
		right.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		// layout.wrap = false;
		// layout.fill=true;
		right.setLayout(layout);

		// Previous link
		previousLink = createHyperlink(right, SWT.NONE);
		setLinkText(previousLink, Resources.getText(
				Resources.PaginationDecorator_previous, getLocale()));

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.grabExcessHorizontalSpace = false;
		previousLink.setLayoutData(new GridData());

		addHyperlinkListener(previousLink, this);


		pageItems = new PageItems(right, SWT.NONE) {
			@Override
			protected void handleSelection(PageItem pageItem) {
				if (!pageItem.isDot()) {
					getController().setCurrentPage(pageItem.getIndex());
				}
			}
		};
		pageItems.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// pageLinks = createHyperlink(right, SWT.NONE);
		// pageLinks.setForeground(getColor());

		//gridData = new GridData();
//		gridData.horizontalAlignment = SWT.FILL;
		//gridData.grabExcessHorizontalSpace = true;
		// pageLinks.setLayoutData(gridData);

		// setLinkText(pageLinks, "");
		// pageLinks.setToolTipText("Next");
		GridData r = new GridData(GridData.FILL_HORIZONTAL);
		// pageLinks.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// addHyperlinkListener(pageLinks, this);

		// Next link
		nextLink = createHyperlink(right, SWT.NONE);
		setLinkText(nextLink, Resources.getText(
				Resources.PaginationDecorator_next, getLocale()));

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;

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
		if (hyperlink == previousLink) {
			newCurrentPage = getController().getCurrentPage() - 1;
		} else if (hyperlink == nextLink) {
			newCurrentPage = getController().getCurrentPage() + 1;
		}
		// else if (hyperlink == pageLinks) {
		// newCurrentPage = Integer.parseInt(e.text);
		// }
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
		
		
		pageItems.setIndexes(indexes);
		pageItems.setPageIndexSelected(newPageNumber);
//		
//		for (int i = 0; i < indexes.length; i++) {
//			int j = indexes[i];
//			if (i > 0) {
//				s.append(" ");
//			}
//			if (j == PaginationHelper.DOT) {
//				s.append("...");
//			} else if (j == newPageNumber)
//				s.append("" + (j + 1) + "");
//			else
//				s.append("<a href=\"" + j + "\">" + (j + 1) + "</a>");
//
//		}
		// pageLinks.setText(s.toString());
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
		Color color = JFaceResources.getColorRegistry().get(
				"ResultAndPageLinksBannerWidget_red");
		if (color == null) {
			JFaceResources.getColorRegistry().put(
					"ResultAndPageLinksBannerWidget_red", new RGB(255, 0, 0));
		}
		return JFaceResources.getColorRegistry().get(
				"ResultAndPageLinksBannerWidget_red");
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
