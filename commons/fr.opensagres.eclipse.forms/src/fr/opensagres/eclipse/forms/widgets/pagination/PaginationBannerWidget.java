package fr.opensagres.eclipse.forms.widgets.pagination;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import fr.opensagres.eclipse.forms.widgets.BaseComposite;

public class PaginationBannerWidget extends BaseComposite implements
		IHyperlinkListener, PageControllerChangedListener {

	private final PaginationController controller;
	private Hyperlink firstLink;
	private Hyperlink lastLink;
	private Hyperlink previousLink;
	private Hyperlink nextLink;

	public PaginationBannerWidget(PaginationController controller, Composite parent,
			int style, FormToolkit toolkit) {
		super(parent, style, toolkit);
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
		Composite left = super.createComposite(this, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = SWT.BEGINNING;
		left.setLayoutData(data);
		RowLayout layout = new RowLayout();
		layout.marginHeight = 0;
		left.setLayout(layout);

		// First link
		firstLink = super.createHyperlink(left, SWT.WRAP);
		firstLink.setText("First");
		firstLink.setLayoutData(new RowData());
		firstLink.addHyperlinkListener(this);

		// Previous link
		previousLink = super.createHyperlink(left, SWT.WRAP);
		previousLink.setText("<<<");
		previousLink.setToolTipText("Previous");
		previousLink.setLayoutData(new RowData());
		previousLink.addHyperlinkListener(this);
	}

	private void createCenterContainer() {
		Composite center = super.createComposite(this, SWT.NONE);
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
		Composite right = super.createComposite(this, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		right.setLayoutData(data);
		RowLayout layout = new RowLayout();
		layout.marginHeight = 0;
		right.setLayout(layout);

		// Next link
		nextLink = super.createHyperlink(right, SWT.WRAP);
		nextLink.setText(">>>");
		nextLink.setToolTipText("Next");
		nextLink.setLayoutData(new RowData());
		nextLink.addHyperlinkListener(this);

		// Last link
		lastLink = super.createHyperlink(right, SWT.WRAP);
		lastLink.setText("Last");
		lastLink.setLayoutData(new RowData());
		lastLink.addHyperlinkListener(this);
	}

	public void linkActivated(HyperlinkEvent e) {
		Hyperlink hyperlink = (Hyperlink) e.getSource();
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

}
