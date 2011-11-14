package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.core.runtime.ListenerList;
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

public class PaginationControl extends BaseComposite implements
		IHyperlinkListener {

	private static final int DEFAULT_PAGE_INDEX = 0;
	private static final int DEFAULT_PAGE_SIZE = 10;

	private Hyperlink firstLink;
	private Hyperlink lastLink;
	private Hyperlink previousLink;
	private Hyperlink nextLink;

	private int currentPage = DEFAULT_PAGE_INDEX;
	private int itemsPerPage = DEFAULT_PAGE_SIZE;
	private long totalElements = 0;

	private ListenerList pageSelectionListeners = new ListenerList();

	public interface PageSelectionListener {
		public void pageSelected(int pageNumber);
	}

	public PaginationControl(int currentPage, int itemsPerPage,
			Composite parent, int style, FormToolkit toolkit) {
		super(parent, style, toolkit);
		createUI();
		setCurrentPage(currentPage);
		setItemsPerPage(itemsPerPage);
	}

	public PaginationControl(Composite parent, int style, FormToolkit toolkit) {
		this(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE, parent, style, toolkit);
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
//		Hyperlink aaLink = super.createHyperlink(center, SWT.WRAP);
//		aaLink.setText("1");
//		aaLink.addHyperlinkListener(this);
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (this.currentPage != currentPage) {
			this.currentPage = currentPage;
			nextLink.setEnabled(hasNextPage());
			previousLink.setEnabled(hasPreviousPage());
		}
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long itemsTotal) {
		this.totalElements = itemsTotal;
	}

	public void linkActivated(HyperlinkEvent e) {
		Hyperlink hyperlink = (Hyperlink) e.getSource();
		int newCurrentPage = 0;
		if (hyperlink == firstLink) {
			newCurrentPage = 0;
		} else if (hyperlink == lastLink) {
			newCurrentPage = getTotalPages();
		} else if (hyperlink == previousLink) {
			newCurrentPage = getCurrentPage() - 1;
		} else if (hyperlink == nextLink) {
			newCurrentPage = getCurrentPage() + 1;
		}
		setCurrentPage(newCurrentPage);
		notifyListeners(newCurrentPage);
	}

	public void linkEntered(HyperlinkEvent e) {

	}

	public void linkExited(HyperlinkEvent e) {

	}

	public void addPageSelectionListener(PageSelectionListener listener) {
		if (listener == null) {
			throw new NullPointerException(
					"Cannot add a null page selection listener"); //$NON-NLS-1$
		}
		pageSelectionListeners.add(listener);
	}

	private void notifyListeners(int selectedPage) {
		final Object[] listeners = pageSelectionListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final PageSelectionListener listener = (PageSelectionListener) listeners[i];
			listener.pageSelected(selectedPage);
		}
	}

	/**
	 * Returns if there is a previous page.
	 * 
	 * @return if there is a previous page
	 */
	public boolean hasPreviousPage() {
		return getCurrentPage() > 0;
	}

	/**
	 * Returns whether the current page is the first one.
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return !hasPreviousPage();
	}

	/**
	 * Returns if there is a next page.
	 * 
	 * @return if there is a next page
	 */
	public boolean hasNextPage() {
		return ((getCurrentPage() + 1) * getPageSize()) < totalElements;
	}

	private int getPageSize() {
		return itemsPerPage;
	}

	public int getTotalPages() {

		return getPageSize() == 0 ? 0 : (int) Math.ceil((double) totalElements
				/ (double) getPageSize());
	}

	/**
	 * Returns whether the current page is the last one.
	 * 
	 * @return
	 */
	public boolean isLastPage() {

		return !hasNextPage();
	}
}
